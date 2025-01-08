package org.example.geometry3d

import org.example.exceptions.InvalidCylinderParametersException

class Cylinder(private val radius: Double, private val height: Double) {
    init {
        if (radius <= 0 || height <= 0)
            throw InvalidCylinderParametersException("Радиус и высота должны быть больше нуля.")
    }

    fun volume(): Double = Math.PI * radius * radius * height

    override fun toString(): String {
        return "Cylinder(radius=$radius, height=$height)"
    }
}