package org.by1337.lib.match;


import lombok.ToString;
import org.by1337.lib.lang.Lang;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BMatch {

    public static String match(String input){
        try {
            String pattern = "match\\[(.*?)]";
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(input);

            while (matcher.find()) {
                String s = matcher.group(1);
                s = s.replace(" ", "");
                List<Lexeme> lexemes = lexAnalyze(s);
                LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
                input = input.replace(matcher.group(0), String.valueOf(expr(lexemeBuffer)));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return input;
    }

    public enum LexemeType {
        LEFT_BRACKET,
        RIGHT_BRACKET,
        OP_PLUS,
        OP_MINUS,
        OP_MUL,
        OP_DIV,
        NUMBER,
        BITWISE_AND,
        LOGICAL_AND,
        LOGICAL_NOT,
        LESS_THAN,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL_TO,
        LESS_THAN_OR_EQUAL_TO,
        EQUAL_TO,
        NOT_EQUAL_TO,
        MODULUS,
        BITWISE_OR,
        LOGICAL_OR,
        XOR,
        EOF;
    }

    @ToString
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
    }

    public static List<Lexeme> lexAnalyze(String expText) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < expText.length()) {
            char c = expText.charAt(pos);
            switch (c) {
                case '=' -> {
                    if (pos + 1 < expText.length()){
                        if (expText.charAt(pos + 1) == '='){
                            lexemes.add(new Lexeme(LexemeType.EQUAL_TO, "=="));
                            pos+=2;
                        }
                    }
                }
                case '>' -> {
                    if (pos + 1 < expText.length()){
                        if (expText.charAt(pos + 1) == '='){
                            lexemes.add(new Lexeme(LexemeType.GREATER_THAN_OR_EQUAL_TO, ">="));
                            pos+=2;
                        }else {
                            lexemes.add(new Lexeme(LexemeType.GREATER_THAN, '>'));
                            pos++;
                        }
                    }
                }
                case '<' -> {
                    if (pos + 1 < expText.length()){
                        if (expText.charAt(pos + 1) == '='){
                            lexemes.add(new Lexeme(LexemeType.LESS_THAN_OR_EQUAL_TO, "<="));
                            pos+=2;
                        }else {
                            lexemes.add(new Lexeme(LexemeType.LESS_THAN, '<'));
                            pos++;
                        }
                    }
                }
                case '!' -> {
                    if (pos + 1 < expText.length()){
                        if (expText.charAt(pos + 1) == '='){
                            lexemes.add(new Lexeme(LexemeType.NOT_EQUAL_TO, "!="));
                            pos+=2;
                        }else {
                            lexemes.add(new Lexeme(LexemeType.LOGICAL_NOT, '!'));
                            pos++;
                        }
                    }
                }
                case '^' -> {
                    lexemes.add(new Lexeme(LexemeType.XOR, c));
                    pos++;
                }
                case '%' -> {
                    lexemes.add(new Lexeme(LexemeType.MODULUS, c));
                    pos++;
                }
                case '(' -> {
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                }
                case ')' -> {
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                }
                case '+' -> {
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos++;
                }
                case '-' -> {
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos++;
                }
                case '*' -> {
                    lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                    pos++;
                }
                case '/' -> {
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                }
                case '&' -> {
                    if (pos + 1 < expText.length()){
                        if (expText.charAt(pos + 1) == '&'){
                            lexemes.add(new Lexeme(LexemeType.LOGICAL_AND, "&&"));
                            pos+=2;
                        }else {
                            lexemes.add(new Lexeme(LexemeType.BITWISE_AND, '&'));
                            pos++;
                        }
                    }
                }
                case '|' -> {
                    if (pos + 1 < expText.length()){
                        if (expText.charAt(pos + 1) == '|'){
                            lexemes.add(new Lexeme(LexemeType.LOGICAL_OR, "||"));
                            pos+=2;
                        }else {
                            lexemes.add(new Lexeme(LexemeType.BITWISE_OR, '|'));
                            pos++;
                        }
                    }
                }
                default -> {
                    if (c <= '9' && c >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expText.length()) {
                                break;
                            }
                            c = expText.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            throw new IllegalArgumentException(String.format(Lang.getMessage("unexpected-character"), c));
                        }
                        pos++;
                    }
                }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
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
    }

    public static int factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case LOGICAL_NOT:{
                return factor(lexemes) == 0 ? 1 : 0;
            }
            case OP_MINUS:{
                return -factor(lexemes);
            }
            case LEFT_BRACKET:
                int value = plusminus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {

                    throw new RuntimeException(String.format(Lang.getMessage("unexpected-token-at-pos"), lexeme.type, lexemes.getPos()));
                }
                return value;
            default:
                throw new RuntimeException(String.format(Lang.getMessage("unexpected-token-at-pos"), lexeme.type, lexemes.getPos()));
        }
    }

    public static int expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    public static int plusminus(LexemeBuffer lexemes) {
        int value = multdiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS -> value += multdiv(lexemes);
                case XOR -> value ^= multdiv(lexemes);
                case OP_MINUS -> value -= multdiv(lexemes);
                case BITWISE_AND -> value &= multdiv(lexemes);
                case BITWISE_OR -> value |= multdiv(lexemes);
                case LESS_THAN -> value = (value < multdiv(lexemes)) ? 1 : 0;
                case LESS_THAN_OR_EQUAL_TO -> value = (value <= multdiv(lexemes)) ? 1 : 0;
                case GREATER_THAN -> value = (value > multdiv(lexemes)) ? 1 : 0;
                case EQUAL_TO -> value = (value == multdiv(lexemes)) ? 1 : 0;
                case NOT_EQUAL_TO -> value = (value != multdiv(lexemes)) ? 1 : 0;
                case GREATER_THAN_OR_EQUAL_TO -> value = (value >= multdiv(lexemes)) ? 1 : 0;
                case LOGICAL_AND -> {
                    int v1 = value == 1 ? 1 : 0;
                    int v2 = multdiv(lexemes) == 1 ? 1 : 0;
                    value = (v1 == 1 && v2 == 1) ? 1 : 0;
                }
                case LOGICAL_OR -> {
                    int v1 = value == 1 ? 1 : 0;
                    int v2 = multdiv(lexemes) == 1 ? 1 : 0;
                    value = (v1 == 1 || v2 == 1) ? 1 : 0;
                }
                case EOF, RIGHT_BRACKET -> {
                    lexemes.back();
                    return value;
                }
                default -> throw new RuntimeException(String.format(Lang.getMessage("unexpected-token-at-pos"), lexeme.type, lexemes.getPos()));
            }
        }
    }

    public static int multdiv(LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;

                case MODULUS:
                    value %= factor(lexemes);
                    break;
                case LOGICAL_OR:
                case BITWISE_OR:
                case XOR:
                case EQUAL_TO:
                case NOT_EQUAL_TO:
                case GREATER_THAN_OR_EQUAL_TO:
                case LESS_THAN_OR_EQUAL_TO:
                case GREATER_THAN:
                case LESS_THAN:
                case LOGICAL_NOT:
                case LOGICAL_AND:
                case BITWISE_AND:
                case EOF:
                case RIGHT_BRACKET:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException(String.format(Lang.getMessage("unexpected-token-at-pos"), lexeme.type, lexemes.getPos()));
            }
        }
    }

}
