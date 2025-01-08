package org.example

import kotlin.math.sqrt

fun seq(n: Int): MutableList<String>? {
    if (n<=0)
        return null

    val res: MutableList<String> = mutableListOf()

    for (i in 1..n)
        if (i%5==0)
            if (i%7==0)
                res.add("fizzbuzz")
            else
                res.add("fizz")
        else if (i%7==0)
            res.add("buzz")
        else
            res.add(i.toString())
    return res
}

fun solve(a: Double, b: Double, c: Double): Pair<Double, Double>? {
    val d = b*b-4*a*c
    if (d<0) return null

    val r1 = (-b+sqrt(d))/(2*a)
    val r2 = (-b-sqrt(d))/(2*a)

    return Pair(r1, r2)
}

fun sumSeries(func: (Int) -> Double, start: Int, epsilon: Double): Double {
    var sum = 0.0
    var n = start

    while (true) {
        val cur = func(n)
        if (cur < epsilon) break
        sum += cur
        n++
    }

    return sum
}

fun isPalindrome(str: String): Boolean {
    val res = str.replace(" ", "").lowercase()
    return res == res.reversed()
}

fun main() {
    // 1
    println(seq(500))

    // 2
    val string = "make install"
    println(string.reversed())

    // 3
    print("Введите a: ")
    val a: Double = readln().toDouble()
    print("Введите b: ")
    val b: Double = readln().toDouble()
    print("Введите c: ")
    val c: Double = readln().toDouble()

    val roots = solve(a, b, c)

    if (roots != null) {
        val (r1, r2) = roots
        println("Корни: r1 = $r1, r2 = $r2")
    } else
        println("Нет вещественных корней.")

    // 4

    val e = 1e-6
    val f = { n: Int -> 1.0 / (n * n + n - 2) }
    val res = sumSeries(f, 2, e)

    println("Сумма ряда: $res")

    // 5
    print("Введите строку: ")
    val str = readln()
    println(isPalindrome(str))
}