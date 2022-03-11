package homework1_task1
//Решето Эратосфена

fun printAllPrimeNumsUpTo(num: Int) {
    if (num < 0){
        println("Wrong argument.")
        return
    }
    val isPrime = BooleanArray(num + 1)
    for (i in 0..num)
        isPrime[i] = true

    isPrime[0] = false
    isPrime[1] = false

    for (i in 0..num)
        if (isPrime[i])
            for (j in (2 * i)..num step (i))
                isPrime[j] = false
    println("Prime numbers up to $num:")
    for (i in 0..num)
        if (isPrime[i]) println(i)
}

fun main() {
    print("Enter a natural number up to which to output all prime numbers: ")
    val num: Int = readLine()?.toInt() ?: 0
    printAllPrimeNumsUpTo(num)
}