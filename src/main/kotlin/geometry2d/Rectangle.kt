package org.example.geometry2d

import org.example.exceptions.InvalidRectangleParametersException

class Rectangle(private val width: Double, private val height: Double) : Figure {
    init {
        if (width <= 0 || height <= 0)
            throw InvalidRectangleParametersException("Ширина и высота должны быть больше нуля.")
    }

    override fun area(): Double = width * height
    override fun perimeter(): Double = 2 * (width + height)

    override fun toString(): String {
        return "Rectangle(width=$width, height=$height)"
    }
}