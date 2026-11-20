package com.s2s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrimeTest {
    private Prime prime;

    @BeforeEach
    public void setUp() {
        prime = new Prime();
    }

    // ---------- isCarmichaelNumber ----------

    @Test
    public void carmichaelKnownNumbers() {
        int[] carmichaels = { 561, 1105, 1729, 2465, 2821, 6601, 8911 };
        for (int n : carmichaels) {
            assertTrue(prime.isCarmichaelNumber(n), n + " should be a Carmichael number");
        }
    }

    @Test
    public void carmichaelPrimesAreNotCarmichael() {
        // Primes satisfy Fermat's little theorem but are not composite
        int[] primes = { 2, 3, 5, 7, 13, 17, 97, 563, 1103, 1723, 7919 };
        for (int n : primes) {
            assertFalse(prime.isCarmichaelNumber(n), n + " is prime, not Carmichael");
        }
    }

    @Test
    public void carmichaelOrdinaryCompositesAreNotCarmichael() {
        int[] composites = { 4, 6, 8, 9, 15, 25, 100, 341, 560, 562, 1000, 1728, 1730 };
        for (int n : composites) {
            assertFalse(prime.isCarmichaelNumber(n), n + " is not a Carmichael number");
        }
    }

    @Test
    public void carmichaelSmallAndNonPositiveInputs() {
        assertFalse(prime.isCarmichaelNumber(1));
        assertFalse(prime.isCarmichaelNumber(0));
        assertFalse(prime.isCarmichaelNumber(-1));
        assertFalse(prime.isCarmichaelNumber(-561));
    }

    // ---------- nextCarmichaelNumber ----------

    @Test
    public void nextCarmichaelFromBelowFirst() {
        assertEquals(561, prime.nextCarmichaelNumber(0));
        assertEquals(561, prime.nextCarmichaelNumber(1));
        assertEquals(561, prime.nextCarmichaelNumber(560));
    }

    @Test
    public void nextCarmichaelIsStrictlyGreater() {
        // Starting on a Carmichael number returns the following one
        assertEquals(1105, prime.nextCarmichaelNumber(561));
        assertEquals(1729, prime.nextCarmichaelNumber(1105));
        assertEquals(2465, prime.nextCarmichaelNumber(1729));
    }

    @Test
    public void nextCarmichaelFromBetween() {
        assertEquals(1105, prime.nextCarmichaelNumber(562));
        assertEquals(1105, prime.nextCarmichaelNumber(1104));
        assertEquals(2821, prime.nextCarmichaelNumber(2500));
        assertEquals(8911, prime.nextCarmichaelNumber(6601));
    }

    @Test
    public void nextCarmichaelFromNegative() {
        assertEquals(561, prime.nextCarmichaelNumber(-100));
    }

    // ---------- totient ----------

    @Test
    public void totientOfPrimeIsPMinusOne() {
        assertEquals(1, prime.totient(2));
        assertEquals(2, prime.totient(3));
        assertEquals(96, prime.totient(97));
    }

    @Test
    public void totientOfComposites() {
        assertEquals(6, prime.totient(9));
        assertEquals(4, prime.totient(10));
        assertEquals(4, prime.totient(12));
        assertEquals(12, prime.totient(36));
        assertEquals(40, prime.totient(100));
        assertEquals(320, prime.totient(561));
    }

    @Test
    public void totientOfOne() {
        // Per the spec, counts numbers in {1, ..., n-1}; for n = 1 that set is empty
        assertEquals(0, prime.totient(1));
    }

    // ---------- gcd (provided) ----------

    @Test
    public void gcdProvided() {
        assertEquals(6, prime.gcd(12, 18));
        assertEquals(6, prime.gcd(18, 12));
        assertEquals(1, prime.gcd(17, 5));
        assertEquals(7, prime.gcd(7, 7));
        assertEquals(1, prime.gcd(1, 100));
    }
}
