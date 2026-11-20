package com.s2s;

public class Prime {
    /*
     * A *composite* positive integer n is called a Carmichael number 
     * if for every integer b such that 1 ≤ b < n  and gcd(b, n) = 1 
     * => b^(n-1) ≡ 1 (mod n) 
     */
    public boolean isCarmichaelNumber(int number) {
        return false;
    }

    /*
     * return the next Carmichael Number
     * Hint: you may want to complete isCarmichaelNumber() function first
     */
    public int nextCarmichaelNumber(int number) {
        return 0;
    }

    /*
     * Euler's Totient function for an input n is the count of numbers in
     * {1, 2, 3, ..., n-1} that are relatively prime to n, i.e., the numbers whose
     * GCD (Greatest Common Divisor) with n is 1.
     *
     * n > 0 i.e. positive
     */
    public int totient(int number) {
        return 0;
    }

    /*
     * [Implemented for you to use]
     * returns the greatest common divisor of a and b
     */
    public int gcd(int a, int b) {
        if (a < b)
            return gcd(b, a);
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }
}
