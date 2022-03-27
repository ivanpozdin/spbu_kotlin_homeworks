package homework1.task2

fun getPrimesUpTo(num: Int): MutableList<Int> {
    val primes: MutableList<Int> = mutableListOf()
    if (num < 0) return primes

    val isPrime = BooleanArray(num + 1)
    for (i in 2..num) isPrime[i] = true

    for (i in 0..num) if (isPrime[i]) {
        primes.add(i)
        for (j in (2 * i)..num step (i)) isPrime[j] = false
    }
    return primes
}

fun main() {
    print("Enter a natural number up to which to output all prime numbers: ")
    val num: Int = readLine()?.toInt() ?: 0
    if (num < 0) println("Wrong argument.")
    else {
        val primesArray: MutableList<Int> = getPrimesUpTo(num)
        println("Prime numbers up to $num:")
        for (primeNumber in primesArray) println(primeNumber)
    }
}
