package org.example.geometry2d

import org.example.exceptions.InvalidRadiusException

class Circle(private val radius: Double) : Figure {
    init {
        if (radius <= 0)
            throw InvalidRadiusException("Радиус должен быть больше нуля.")
    }

    override fun area(): Double = Math.PI * radius * radius
    override fun perimeter(): Double = 2 * Math.PI * radius

    override fun toString(): String {
        return "Circle(radius=$radius)"
    }
}