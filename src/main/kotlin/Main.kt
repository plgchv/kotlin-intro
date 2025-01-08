package org.example

import java.util.*
import kotlin.Comparator
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

class PrimesGenerator(private val n: Int) : Iterator<Int> {
    private var count = 0
    private var current = 1

    override fun hasNext(): Boolean {
        return count < n
    }

    override fun next(): Int {
        while (true) {
            current++
            if (isPrime(current)) {
                count++
                return current
            }
        }
    }

    private fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        for (i in 2..Math.sqrt(num.toDouble()).toInt())
            if (num % i == 0) return false
        return true
    }
}

data class Human(val firstName: String, val lastName: String, val age: Int) : Comparable<Human> {
    override fun compareTo(other: Human): Int {
        return this.firstName.compareTo(other.firstName)
    }
}

class HumanComparatorByLastName : Comparator<Human> {
    override fun compare(h1: Human, h2: Human): Int {
        return h1.lastName.compareTo(h2.lastName)
    }
}

fun <K, V> swapMap(map: Map<K, V>): Map<V, K> {
    return map.entries.associate { it.value to it.key }
}

fun main() {
    // 1
    // Создайте массив из N случайных чисел от 0 до 100.
    val n1 = 10
    val randomNumbers = IntArray(n1) { (0..100).random() }
    println("Случайные числа: ${randomNumbers.joinToString(", ")}")

    // На основе массива создайте список List.
    val numberList = randomNumbers.toList()
    println("Список: $numberList")

    // Отсортируйте список по возрастанию.
    val sortedList = numberList.sorted()
    println("Отсортированный список: $sortedList")

    // Отсортируйте список в обратном порядке.
    val reversedList = sortedList.reversed()
    println("Список в обратном порядке: $reversedList")

    // Перемешайте список.
    val shuffledList = numberList.shuffled()
    println("Перемешанный список: $shuffledList")

    // Выполните циклический сдвиг на 1 элемент.
    val cyclicShiftedList = shuffledList.drop(1) + shuffledList.first()
    println("Циклический сдвиг на 1 элемент: $cyclicShiftedList")

    // Оставьте в списке только уникальные элементы.
    val uniqueList = shuffledList.distinct()
    println("Уникальные элементы: $uniqueList")

    // Оставьте в списке только дублирующиеся элементы.
    val duplicatesList = shuffledList.groupBy { it }.filter { it.value.size > 1 }.flatMap { it.value }
    println("Дублирующиеся элементы: $duplicatesList")

    // Из списка получите массив.
    val arrayFromList = shuffledList.toTypedArray()
    println("Массив из списка: ${arrayFromList.joinToString(", ")}")

    // Посчитайте количество вхождений каждого числа в массив и выведите на экран.
    val frequencyMap = arrayFromList.groupingBy { it }.eachCount()
    println("Частота вхождений: $frequencyMap")



    // 2
    val n2 = 10
    val primesGenerator = PrimesGenerator(n2)

    val primesList = mutableListOf<Int>()
    while (primesGenerator.hasNext()) {
        primesList.add(primesGenerator.next())
    }

    println("Первые $n2 простых чисел: $primesList")
    println("Первые $n2 простых чисел в обратном порядке: ${primesList.reversed()}")



    // 3
    val humans = listOf(
        Human("Name1", "Surname1", 30),
        Human("Name2", "Surname2", 25),
        Human("Name3", "Surname3", 35)
    )

    // HashSet
    val hashSet = HashSet(humans)
    println("HashSet: $hashSet")

    // LinkedHashSet
    val linkedHashSet = LinkedHashSet(humans)
    println("LinkedHashSet: $linkedHashSet")

    // TreeSet
    val treeSet = TreeSet(humans)
    println("TreeSet: $treeSet")

    // TreeSet с компаратором
    val treeSetWithComparator = TreeSet(HumanComparatorByLastName())
    treeSetWithComparator.addAll(humans)
    println("TreeSet с компаратором по фамилии: $treeSetWithComparator")

    // TreeSet с анонимным компаратором по возрасту
    val treeSetWithAnonymousComparator = TreeSet<Human>(compareBy { it.age })
    treeSetWithAnonymousComparator.addAll(humans)
    println("TreeSet с анонимным компаратором по возрасту: $treeSetWithAnonymousComparator")


    // 4
    val text = "Test test TEST. Hello world"
    val words = text.split("\\W+".toRegex()).map { it.lowercase() }
    val frequencyMap2 = mutableMapOf<String, Int>()

    for (word in words)
        frequencyMap2[word] = frequencyMap2.getOrDefault(word, 0) + 1

    println("Частота слов: $frequencyMap2")


    // 5
    val originalMap = mapOf(1 to "one", 2 to "two", 3 to "three")
    val swappedMap = swapMap(originalMap)
    println("Ориг: $originalMap")
    println("Свап: $swappedMap")
}