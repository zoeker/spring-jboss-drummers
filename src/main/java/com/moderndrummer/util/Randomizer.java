package com.moderndrummer.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class Randomizer {

    /**
     * Method createRandomString.
     * 
     * @return String
     */
    public static String createRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    /**
     * Method createRandomString.
     * 
     * @param size
     *            int
     * @return String
     */
    public static String createRandomString(final int size) {
        return RandomStringUtils.randomAlphabetic(size);
    }

    /**
     * Method shuffle.
     * 
     * @param input
     *            String
     * @return String
     */
    public static String shuffle(final String input) {
        final List<Character> characters = new ArrayList<Character>();
        for (final char c : input.toCharArray()) {
            characters.add(c);
        }
        final StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            final int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
