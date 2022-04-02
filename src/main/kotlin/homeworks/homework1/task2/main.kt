package homeworks.homework1.task2

import java.lang.Integer.min

fun getPrimesUpTo(num: Int): MutableList<Int> {
    require(num >= 0) { "Number must be non-negative" }
    val primes = mutableListOf<Int>()
    val isPrime = BooleanArray(num + 1) { true }
    isPrime.fill(false, 0, min(2, num))

    for (i in 2..num) if (isPrime[i]) {
        primes.add(i)
        for (j in (2 * i)..num step (i)) {
            isPrime[j] = false
        }
    }
    return primes
}

fun main() {
    var num = -1
    while (num < 0) {
        print("Enter a natural number up to which to output all prime numbers: ")
        num = readLine()?.toIntOrNull() ?: -1
        if (num < 0) {
            println("You can only write a whole non-negative number")
        }
    }
    val primesArray = getPrimesUpTo(num)
    println("Prime numbers up to $num:")
    print(primesArray.joinToString("\n"))
}
