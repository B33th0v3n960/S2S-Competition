# Number Theory

Number theory is the branch of mathematics devoted primarily to the study of the
integers and arithmetic functions.

You will implement three methods in `src/main/java/com/s2s/Prime.java`:

| Method | Returns |
|---|---|
| `int totient(int number)` | Euler's totient $\phi(n)$ of `number` |
| `boolean isCarmichaelNumber(int number)` | `true` if `number` is a Carmichael number |
| `int nextCarmichaelNumber(int number)` | the smallest Carmichael number **greater than** `number` |

A working `gcd(int a, int b)` is already provided in `Prime.java` for you to use.

## Greatest Common Divisor (GCD)

The GCD of two integers is the largest integer that divides both of them.
Two numbers are **co-prime** (relatively prime) when their GCD is 1.

$$ \gcd(12, 18) = 6 \qquad \gcd(8, 15) = 1 \text{ (co-prime)} $$

## Euler Totient Function

> Euler's totient function $\phi(n)$, for a positive integer $n$, is the count
> of numbers in $\{1, 2, 3, \dots, n-1\}$ that are relatively prime to $n$,
>
> i.e., the numbers whose GCD (Greatest Common Divisor) with $n$ is 1.

### Example: `n = 36`

Find all numbers less than 36 that are co-prime to $36$:
$$ 1, 5, 7, 11, 13, 17, 19, 23, 25, 29, 31, 35 $$
There are 12, thus $\phi(36) = 12$.

### Example: `n = 7`

7 is prime, so every number from 1 to 6 is co-prime to it:
$$ 1, 2, 3, 4, 5, 6 $$
Thus $\phi(7) = 6$.

## Carmichael Number

> A _composite_ positive integer $n$ is called a Carmichael number if, for every
> integer $b$ such that $1 \le b < n$ and $\gcd(b, n) = 1$, the following holds:
> $$ b^{n-1} \equiv 1 \pmod{n} $$

A **composite** number is a positive integer greater than 1 that is **not prime**,
i.e., it has a divisor other than 1 and itself.

The smallest Carmichael number is 561.

### Example: `n = 561`

**Step 1: check that $n$ is composite.**
$$ 561 = 3 \times 11 \times 17 $$
so 561 is not prime.

**Step 2: find every $b$ with $1 \le b < n$ and $\gcd(b, n) = 1$.**
These are the numbers co-prime to 561 (3 is skipped because $\gcd(3, 561) = 3$):

```
1, 2, 4, 5, 7, 8, ..., 560
```

**Step 3: check the equation for each of them.**
$$ 1^{560} \equiv 1 \pmod{561} $$
$$ 2^{560} \equiv 1 \pmod{561} $$
$$ 4^{560} \equiv 1 \pmod{561} $$
$$ \dots $$
$$ 560^{560} \equiv 1 \pmod{561} $$

All of these hold, so 561 is a Carmichael number.

### Computing $b^{n-1} \bmod n$

Numbers like $2^{560}$ are far too large for an `int` or a `long`, so you cannot
compute the power first and take the remainder afterwards. Instead, take the
remainder **as you go**, using:
$$ (x \times y) \bmod n = \big((x \bmod n) \times (y \bmod n)\big) \bmod n $$

For example, to compute $b^{e} \bmod n$, start with $r = 1$ and repeat $e$ times:
$$ r = (r \times b) \bmod n $$

Watch out for overflow: even after taking remainders, $r \times b$ can exceed the
range of an `int`. Use `long` for the intermediate products.

(Repeating $e$ times works, but it is slow for large $e$. Look up
_exponentiation by squaring_ for a faster method.)

## Next Carmichael Number

`nextCarmichaelNumber(number)` returns the smallest Carmichael number that is
strictly greater than `number`.

### Example

The first few Carmichael numbers are $561, 1105, 1729, \dots$

| `number` | Result |
|---|---|
| 100 | 561 |
| 561 | 1105 |
| 600 | 1105 |
