package homeworks.homework1.task2

import java.lang.Integer.min

fun getPrimesUpTo(num: Int): MutableList<Int> {
    require(num >= 0) { "Number must be non-negative" }
    val primes = mutableListOf<Int>()
    val isPrime = BooleanArray(num + 1) { true }
    isPrime.fill(false, 0, min(2, num))

    for (i in 2..num) {
        if (isPrime[i]) {
            primes.add(i)
            ((2 * i)..num step i).forEach { isPrime[it] = false }
        }
    }
    return primes
}

fun main() {
    print("Enter a natural number up to which to output all prime numbers: ")
    val num = readLine()?.toIntOrNull() ?: run {
        println("Oops, you entered not a whole number")
        return
    }
    val primesArray = getPrimesUpTo(num)
    if (primesArray.isEmpty()) {
        print("There are not any prime numbers up to $num")
    } else {
        println("Prime numbers up to $num:")
        print(primesArray.joinToString("\n"))
    }
}
