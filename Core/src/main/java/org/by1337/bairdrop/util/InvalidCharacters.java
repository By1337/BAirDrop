package org.by1337.bairdrop.util;

public class InvalidCharacters implements InvalidCharactersChecker{
    @Override
    public String getInvalidCharacters(String input) {
        String pattern = "^[a-zA-Z0-9-_]+$";
        StringBuilder invalidCharacters = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (!String.valueOf(c).matches(pattern)) {
                invalidCharacters.append(c);
            }
        }
        return invalidCharacters.toString();
    }
}
