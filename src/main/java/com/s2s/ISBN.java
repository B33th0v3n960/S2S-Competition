package com.s2s;

public class ISBN {
    /*
     * ISBN10 must have 10 digits {a1, a2, ..., a10}
     * a1 * 10 + a2 * 9 + ... + a10 * 1 ≡ 0 (mod 11)
     *
     * Note: a10 can be X, to represent the value 10
     * 
     * Check if the `code` is a valid ISBN10 code
     */
    public boolean isbn10Validate(String code) {
        return false;
    }

    /*
     * ISBN13 must have 13 digits {a1, a2, ..., a13}
     * a1 * 1 + a2 * 3 + a3 * 1 + .. + a13 * 1 ≡ 0 (mod 10)
     *
     * Check if the `code` is a valid ISBN13 code
     */
    public boolean isbn13Validate(String code) {
        return false;
    }

    /*
     * To convert from ISBN10 to ISBN13, you prepend code with (978 or 979)
     * We use (979) just for simpler testing.
     *
     * You may need to replace the last digit, check digit, to a valid value.
     * 
     * Convert `code` from ISBN10 to ISBN13
     */
    public String isbn10ToIsbn13(String code) {
        return null;
    }
}
