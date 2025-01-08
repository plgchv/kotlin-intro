package org.example

import org.example.geometry2d.Circle
import org.example.geometry2d.Rectangle
import org.example.geometry3d.Cylinder

class Button {
    private var clickCount = 0

    fun click() {
        clickCount++
        println("Количество нажатий: $clickCount")
    }
}

class Balance {
    private var leftWeight = 0
    private var rightWeight = 0

    fun addLeft(weight: Int) {
        leftWeight += weight
    }

    fun addRight(weight: Int) {
        rightWeight += weight
    }

    fun result() {
        when {
            leftWeight > rightWeight -> println("L")
            rightWeight > leftWeight -> println("R")
            else -> println("=")
        }
    }
}

class Bell {
    private var isDing = true

    fun sound() {
        if (isDing)
            println("ding")
        else
            println("dong")
        isDing = !isDing
    }
}

class OddEvenSeparator {
    private val evenNumbers = mutableListOf<Int>()
    private val oddNumbers = mutableListOf<Int>()

    fun addNumber(number: Int) {
        if (number % 2 == 0)
            evenNumbers.add(number)
        else
            oddNumbers.add(number)
    }

    fun even() {
        println("Чётные: $evenNumbers")
    }

    fun odd() {
        println("Нечётные: $oddNumbers")
    }
}

class Table(private val rows: Int, private val cols: Int) {
    private val table: Array<IntArray> = Array(rows) { IntArray(cols) }

    fun getValue(row: Int, col: Int): Int {
        return table[row][col]
    }

    fun setValue(row: Int, col: Int, value: Int) {
        table[row][col] = value
    }

    fun rows(): Int {
        return rows
    }

    fun cols(): Int {
        return cols
    }

    override fun toString(): String {
        return table.joinToString("\n") { it.joinToString(" ") }
    }

    fun average(): Double {
        val total = table.sumOf { it.sum() }
        return total.toDouble() / (rows * cols)
    }
}

fun main() {
    // 1
    val button = Button()
    for (i in 1..2)
        button.click()

    // 2
    val balance = Balance()
    balance.addLeft(5)
    balance.addRight(3)
    balance.result()
    balance.addRight(3)
    balance.result()
    balance.addLeft(1)
    balance.result()

    // 3
    val bell = Bell()
    for (i in 1..3)
        bell.sound()

    // 4
    val separator = OddEvenSeparator()
    for (i in 1..3)
        separator.addNumber(i)
    separator.even()
    separator.odd()

    // 5
    val table = Table(2, 3)
    table.setValue(0, 0, 1)
    table.setValue(0, 1, 2)
    table.setValue(1, 0, 3)
    table.setValue(1, 1, 4)
    println(table)
    println("Среднее: ${table.average()}")

    // 6
    try {
        val circle = Circle(5.0)
        println(circle)
        println("Площадь: ${circle.area()}")
        println("Периметр: ${circle.perimeter()}")
        val invalidCircle = Circle(0.0)
    } catch (e: Exception) {
        println("Exception $e")
    }

    try {
        val rectangle = Rectangle(4.0, 2.0)
        println(rectangle)
        println("Площадь: ${rectangle.area()}")
        println("Периметр: ${rectangle.perimeter()}")
        val invalidRectangle = Rectangle(-1.0, 2.0)
    }  catch (e: Exception) {
        println("Exception $e")
    }

    try {
        val cylinder = Cylinder(3.0, 5.0)
        println(cylinder)
        println("Объем: ${cylinder.volume()}")
        val invalidCylinder = Cylinder(0.0, 5.0)
    } catch (e: Exception) {
        println("Exception $e")
    }
}