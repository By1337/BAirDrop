package org.by1337.bairdrop.observer.requirement.condition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConditionParse {

    public static Condition parse(String src) {
        LexemeBuffer buffer = new LexemeBuffer(parseExp(src));
        return parse(buffer);
    }

    private static Condition parse(LexemeBuffer buffer) {
        Condition owner = new Condition();
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.type) {
                case IF -> {
                    owner.when(parseCondition(buffer));
                }
                case EOF -> {
                    return owner;
                }
            }
        }
    }

    private static Condition parseCondition(LexemeBuffer buffer) {
        Condition condition = new Condition();
        boolean openBody = false;
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.type) {
                case OPEN_PAREN -> {
                    lexeme = buffer.next();
                    if (lexeme.type == LexemeType.STRING) {
                        condition.setCondition(lexeme.value);
                        lexeme = buffer.next();
                    }
                    if (lexeme.type != LexemeType.CLOSE_PAREN) {
                        throw new IllegalArgumentException("тут должна быть ')'");
                    }
                }
                case OPEN_BRACKET -> {
                    if (!openBody) throw new IllegalArgumentException("body is not been open! " + buffer.report());
                    condition.commands(parseList(buffer));
                }
                case IF -> {
                    if (!openBody) throw new IllegalArgumentException("body is not been open! " + buffer.report());
                    condition.when(parseCondition(buffer));
                }
                case OPEN_BRACE -> {
                    openBody = true;
                }
                case CLOSE_BRACE -> {
                    lexeme = buffer.next();
                    if (lexeme.type == LexemeType.ELSE) {
                        lexeme = buffer.next();
                        if (lexeme.type != LexemeType.IF) {
                            buffer.back();
                        }
                        condition.else_(parseCondition(buffer));
                        return condition;
                    }
                    buffer.back();
                    return condition;
                }

                default -> throw new IllegalArgumentException(buffer.report());
            }
        }
    }

    private static List<String> parseList(LexemeBuffer buffer) {
        List<String> list = new LinkedList<>();
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.type) { // ignore OPEN_BRACKET, COMMA
                case STRING -> {
                    list.add(lexeme.value);
                }
                case CLOSE_BRACKET -> {
                    return list;
                }
                case COMMA -> {
                    // ignore
                }
                default -> throw new IllegalArgumentException(buffer.report());
            }
        }
    }

    public enum LexemeType {
        OPEN_PAREN, // (
        CLOSE_PAREN, // )
        OPEN_BRACKET, // [
        CLOSE_BRACKET, // ]
        OPEN_BRACE, // {
        CLOSE_BRACE, // }
        //       QUOTE, // "
        STRING, // "(any)"
        IF, // if
        ELSE, // else
        //     COMMENT_START, // /*
        //    COMMENT_END, // */
        COMMA, // ,
        EOF; // end exp
    }

    public static List<Lexeme> parseExp(String expText) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        boolean textExpected = false;
        boolean commentAwaited = false;
        boolean commentAwaitedOneLine = false;

        main:
        while (pos < expText.length()) {
            char c = expText.charAt(pos);
            if (textExpected || commentAwaited || commentAwaitedOneLine) {
                StringBuilder sb = new StringBuilder();
                do {
                    switch (c) {
                        case '\\' -> {
                            if (commentAwaited) {
                                pos++;
                                break;
                            }
                            if (pos + 1 < expText.length()) {
                                char next = expText.charAt(pos + 1);
                                if (next == '\\') {
                                    sb.append('\\');
                                    pos += 2;

                                } else if (next == '"') {
                                    sb.append('"');
                                    pos += 2;
                                } else {
                                    throw new ParseException("Ожидался '%s' или '%s', а не '%s', at", "\\", "\"", expText.charAt(pos + 1), pos + 1);
                                }
                            } else {
                                throw new ParseException("Строка неожиданно заканчивается на символе '%s' на позиции '%s'.", c, pos);
                            }
                        }
                        case '"' -> {
                            if (textExpected) {
                                textExpected = false;
                                lexemes.add(new Lexeme(LexemeType.STRING, sb.toString()));
                                pos++;
                                continue main;
                            } else {
                                pos++;
                            }

                        }
                        case '\n' -> {
                            if (commentAwaitedOneLine) {
                                commentAwaitedOneLine = false;
                                pos++;
                                continue main;
                            }else {
                                sb.append(c);
                                pos++;
                            }
                        }
                        case '*' -> {
                            if (commentAwaited) {
                                if (pos + 1 < expText.length()) {
                                    if (expText.charAt(pos + 1) == '/') {
                                        commentAwaited = false;
                                        pos += 2;
                                        continue main;
                                    }
                                }
                                pos++;
                            } else {
                                sb.append(c);
                                pos++;
                            }

                        }
                        default -> {
                            sb.append(c);
                            pos++;
                        }
                    }
                    if (pos < expText.length()) {
                        c = expText.charAt(pos);
                    } else if (commentAwaitedOneLine){
                        break main; // end file
                    }
                    else {
                        throw new ParseException("Строка неожиданно заканчивается на символе '%s' на позиции '%s'.", c, pos);
                    }
                } while (true);

            } else {
                switch (c) {
                    case ',' -> {
                        lexemes.add(new Lexeme(LexemeType.COMMA, c));
                        pos++;
                    }
                    case '(' -> {
                        lexemes.add(new Lexeme(LexemeType.OPEN_PAREN, c));
                        pos++;
                    }
                    case ')' -> {
                        lexemes.add(new Lexeme(LexemeType.CLOSE_PAREN, c));
                        pos++;
                    }
                    case '{' -> {
                        lexemes.add(new Lexeme(LexemeType.OPEN_BRACE, c));
                        pos++;
                    }
                    case '}' -> {
                        lexemes.add(new Lexeme(LexemeType.CLOSE_BRACE, c));
                        pos++;
                    }
                    case '[' -> {
                        lexemes.add(new Lexeme(LexemeType.OPEN_BRACKET, c));
                        pos++;
                    }
                    case ']' -> {
                        lexemes.add(new Lexeme(LexemeType.CLOSE_BRACKET, c));
                        pos++;
                    }
                    case '"' -> {
                        // lexemes.add(new Lexeme(LexemeType.QUOTE, c));
                        textExpected = true;
                        pos++;
                    }
                    case '/' -> {
                        if (pos + 1 < expText.length()) {
                            if (expText.charAt(pos + 1) == '*') {
                                commentAwaited = true;
                                pos += 2;
                            } else if (expText.charAt(pos + 1) == '/') {
                                commentAwaitedOneLine = true;
                                pos += 2;
                            } else {
                                throw new ParseException("Ожидался '%s', а не '%s', at", "*, /", expText.charAt(pos + 1), pos + 1);
                            }
                        } else {
                            throw new ParseException("Строка неожиданно заканчивается на символе '%s' на позиции '%s'.", c, pos);
                        }
                    }
                    case 'i' -> {
                        if (pos + 1 < expText.length()) {
                            if (expText.charAt(pos + 1) == 'f') {
                                lexemes.add(new Lexeme(LexemeType.IF, "if"));
                                pos += 2;
                            } else {
                                throw new ParseException("Ожидался '%s', а не '%s', at", "f", expText.charAt(pos + 1), pos);
                            }
                        } else {
                            throw new ParseException("Строка неожиданно заканчивается на символе '%s' на позиции '%s'.", c, pos);
                        }

                    }
                    case 'e' -> {
                        if (pos + 4 < expText.length()) {
                            if (expText.startsWith("else", pos)) {
                                lexemes.add(new Lexeme(LexemeType.ELSE, "else"));
                                pos += 4;
                            } else {
                                throw new ParseException("Ожидался '%s', а не '%s', at", "else", expText.substring(pos, pos + 4), pos);
                            }

                        } else {
                            throw new ParseException("Строка неожиданно заканчивается на символе '%s' на позиции '%s'.", c, pos);
                        }

                    }
                    case ' ', '\n', '\t' -> {
                        pos++;
                    }
                    default -> {
                        throw new ParseException("Неожиданный символ '%s' at %s", c, pos);
                    }
                }
            }

        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static class Lexeme {
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = String.valueOf(value);
        }

        @Override
        public String toString() {
            return "Lexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class LexemeBuffer {
        private int pos;

        public List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public Lexeme current() {
            return lexemes.get(pos);
        }

        public LexemeBuffer back() {
            pos--;
            return this;
        }

        public LexemeBuffer add() {
            pos++;
            return this;
        }

        public int getPos() {
            return pos;
        }

        public String report() {
            return "LexemeBuffer{" +
                    "pos=" + pos +
                    ", lexeme=" +
                    (pos < lexemes.size() ? lexemes.get(pos) : "null") +
                    '}';
        }

        @Override
        public String toString() {
            AtomicInteger x = new AtomicInteger();
            return "LexemeBuffer{" +
                    "pos=" + pos +
                    ", lexemes=" +
                    lexemes.stream().map(lexeme -> lexeme.toString() + x.getAndIncrement() + "\n").toList() +
                    '}';
        }
    }

    public static class ParseException extends IllegalArgumentException {
        public ParseException() {
        }

        public ParseException(String s) {
            super(s);
        }

        public ParseException(String s, Object... objects) {
            super(String.format(s, objects));
        }
    }
}
