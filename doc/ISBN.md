# ISBN

An ISBN (International Standard Book Number) identifies a book. The last digit
of every ISBN is a **check digit**: it is chosen so that a weighted sum of all the
digits comes out to a multiple of some number. This lets a program spot a
mistyped code before doing a full search for the book.

In this task, codes are given as plain strings **without hyphens**, e.g.
`0306406152` rather than `0-306-40615-2`.

You will implement three methods in `src/main/java/com/s2s/ISBN.java`:

| Method | Returns |
|---|---|
| `boolean isbn10Validate(String code)` | `true` if `code` is a valid ISBN10 |
| `boolean isbn13Validate(String code)` | `true` if `code` is a valid ISBN13 |
| `String isbn10ToIsbn13(String code)` | the ISBN13 equivalent of an ISBN10 `code` |

## ISBN10

> An ISBN10 __must__ have __10__ characters $a_1, a_2, \dots, a_{10}$ where
> $$ a_1 \times 10 + a_2 \times 9 + \dots + a_9 \times 2 + a_{10} \times 1 \equiv 0 \pmod{11} $$
>
> Note: the check digit $a_{10}$ can be `X`, to represent the value 10.

Each digit is multiplied by its weight, counting down from 10 to 1. The code is
valid when the total is divisible by **11**.

### Example: `080442957X`

| Digit | 0 | 8 | 0 | 4 | 4 | 2 | 9 | 5 | 7 | X (=10) |
|---|---|---|---|---|---|---|---|---|---|---|
| Weight | 10 | 9 | 8 | 7 | 6 | 5 | 4 | 3 | 2 | 1 |
| Product | 0 | 72 | 0 | 28 | 24 | 10 | 36 | 15 | 14 | 10 |

$$ 0 + 72 + 0 + 28 + 24 + 10 + 36 + 15 + 14 + 10 = 209 $$
$$ 209 = 11 \times 19 $$

Thus `080442957X` is a valid ISBN10.

## ISBN13

> An ISBN13 __must__ have __13__ digits $a_1, a_2, \dots, a_{13}$ where
> $$ a_1 \times 1 + a_2 \times 3 + a_3 \times 1 + a_4 \times 3 + \dots + a_{13} \times 1 \equiv 0 \pmod{10} $$

The weights alternate 1, 3, 1, 3, ..., starting with 1. The code is valid when
the total is divisible by **10**.

### Example: `9780306406157`

| Digit | 9 | 7 | 8 | 0 | 3 | 0 | 6 | 4 | 0 | 6 | 1 | 5 | 7 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Weight | 1 | 3 | 1 | 3 | 1 | 3 | 1 | 3 | 1 | 3 | 1 | 3 | 1 |
| Product | 9 | 21 | 8 | 0 | 3 | 0 | 6 | 12 | 0 | 18 | 1 | 15 | 7 |

$$ 9 + 21 + 8 + 0 + 3 + 0 + 6 + 12 + 0 + 18 + 1 + 15 + 7 = 100 $$
$$ 100 = 10 \times 10 $$

Thus `9780306406157` is a valid ISBN13.

## Converting ISBN10 to ISBN13

To convert an ISBN10 to an ISBN13:

1. Take the first 9 digits of the ISBN10 (drop its old check digit).
2. Put the prefix `979` in front. This gives 12 digits.
   (Real books use `978` or `979`; this task always uses **`979`**.)
3. Work out a new check digit so the 13-digit result is a valid ISBN13, and
   append it.

To find the check digit, compute the ISBN13 weighted sum of the first 12 digits.
The check digit is whatever value (0 to 9) brings that sum up to the next
multiple of 10.

### Example: `0306406152`

1. First 9 digits: `030640615`
2. With prefix: `979030640615`
3. Weighted sum of those 12 digits:

| Digit | 9 | 7 | 9 | 0 | 3 | 0 | 6 | 4 | 0 | 6 | 1 | 5 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Weight | 1 | 3 | 1 | 3 | 1 | 3 | 1 | 3 | 1 | 3 | 1 | 3 |
| Product | 9 | 21 | 9 | 0 | 3 | 0 | 6 | 12 | 0 | 18 | 1 | 15 |

$$ 9 + 21 + 9 + 0 + 3 + 0 + 6 + 12 + 0 + 18 + 1 + 15 = 94 $$

The next multiple of 10 is 100, so the check digit is $100 - 94 = 6$.

Result: `9790306406156`
