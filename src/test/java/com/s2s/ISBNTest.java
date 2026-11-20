package com.s2s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISBNTest {
    private ISBN isbn;

    @BeforeEach
    public void setUp() {
        isbn = new ISBN();
    }

    // ---------- isbn10Validate ----------

    @Test
    public void isbn10ValidCodes() {
        String[] valid = { "0306406152", "0198526636", "1843560283", "3598215088", "0545010225", "0201633612" };
        for (String code : valid) {
            assertTrue(isbn.isbn10Validate(code), code + " should be valid");
        }
    }

    @Test
    public void isbn10ValidWithXCheckDigit() {
        assertTrue(isbn.isbn10Validate("080442957X"));
        assertTrue(isbn.isbn10Validate("097522980X"));
    }

    @Test
    public void isbn10AllZerosIsValid() {
        assertTrue(isbn.isbn10Validate("0000000000"));
    }

    @Test
    public void isbn10WrongCheckDigit() {
        assertFalse(isbn.isbn10Validate("0306406153"));
        assertFalse(isbn.isbn10Validate("0306406150"));
        assertFalse(isbn.isbn10Validate("0804429579"));
        assertFalse(isbn.isbn10Validate("030640615X"));
    }

    @Test
    public void isbn10XOnlyAllowedAsLastCharacter() {
        assertFalse(isbn.isbn10Validate("X306406152"));
        assertFalse(isbn.isbn10Validate("03064X6152"));
    }

    @Test
    public void isbn10InvalidCharacters() {
        assertFalse(isbn.isbn10Validate("03064A6152"));
        assertFalse(isbn.isbn10Validate("030640615Y"));
        assertFalse(isbn.isbn10Validate("030640 152"));
    }

    @Test
    public void isbn10WrongLength() {
        assertFalse(isbn.isbn10Validate("030640615"));   // 9 digits
        assertFalse(isbn.isbn10Validate("03064061520")); // 11 digits
        assertFalse(isbn.isbn10Validate("9780306406157")); // a valid ISBN13
    }

    @Test
    public void isbn10EmptyAndNull() {
        assertFalse(isbn.isbn10Validate(""));
        assertFalse(isbn.isbn10Validate(null));
    }

    // ---------- isbn13Validate ----------

    @Test
    public void isbn13ValidCodes() {
        String[] valid = { "9780306406157", "9781861972712", "9783161484100", "9780545010221", "9790306406156" };
        for (String code : valid) {
            assertTrue(isbn.isbn13Validate(code), code + " should be valid");
        }
    }

    @Test
    public void isbn13AllZerosIsValid() {
        assertTrue(isbn.isbn13Validate("0000000000000"));
    }

    @Test
    public void isbn13WrongCheckDigit() {
        assertFalse(isbn.isbn13Validate("9780306406158"));
        assertFalse(isbn.isbn13Validate("9780306406150"));
        assertFalse(isbn.isbn13Validate("9781861972713"));
    }

    @Test
    public void isbn13DoesNotAcceptX() {
        assertFalse(isbn.isbn13Validate("978030640615X"));
    }

    @Test
    public void isbn13InvalidCharacters() {
        assertFalse(isbn.isbn13Validate("97803064A6157"));
        assertFalse(isbn.isbn13Validate("978030640615 "));
    }

    @Test
    public void isbn13WrongLength() {
        assertFalse(isbn.isbn13Validate("978030640615"));   // 12 digits
        assertFalse(isbn.isbn13Validate("97803064061570")); // 14 digits
        assertFalse(isbn.isbn13Validate("0306406152"));     // a valid ISBN10
    }

    @Test
    public void isbn13EmptyAndNull() {
        assertFalse(isbn.isbn13Validate(""));
        assertFalse(isbn.isbn13Validate(null));
    }

    // ---------- isbn10ToIsbn13 ----------

    @Test
    public void convertTypicalCodes() {
        assertEquals("9790306406156", isbn.isbn10ToIsbn13("0306406152"));
        assertEquals("9790198526635", isbn.isbn10ToIsbn13("0198526636"));
        assertEquals("9791843560288", isbn.isbn10ToIsbn13("1843560283"));
        assertEquals("9790545010220", isbn.isbn10ToIsbn13("0545010225"));
    }

    @Test
    public void convertCodeEndingInX() {
        // The ISBN10 check digit X is dropped and replaced by a numeric ISBN13 check digit
        assertEquals("9790804429572", isbn.isbn10ToIsbn13("080442957X"));
        assertEquals("9790975229803", isbn.isbn10ToIsbn13("097522980X"));
    }

    @Test
    public void convertWhereCheckDigitIsZero() {
        assertEquals("9790545010220", isbn.isbn10ToIsbn13("0545010225"));
    }

    @Test
    public void convertAllZeros() {
        assertEquals("9790000000001", isbn.isbn10ToIsbn13("0000000000"));
    }

    @Test
    public void convertedResultIsValidIsbn13() {
        String[] inputs = { "0306406152", "080442957X", "3598215088", "0201633612" };
        for (String code : inputs) {
            String result = isbn.isbn10ToIsbn13(code);
            assertEquals(13, result.length());
            assertTrue(result.startsWith("979"));
            assertEquals(code.substring(0, 9), result.substring(3, 12));
            assertTrue(isbn.isbn13Validate(result), result + " should be a valid ISBN13");
        }
    }
}
