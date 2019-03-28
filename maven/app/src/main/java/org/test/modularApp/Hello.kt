package org.test.modularApp

import kotlin.math.*
import kotlin.random.*
import org.test.modularLib.*

fun main() {
    println(Random.nextDouble(abs(sin(PI) + cos(PI))))

    val srcText = "modular, modelar, modalar"
    val matches = LibServices.regex.findAll(srcText).forEach { m ->
        println("${m.value} - ${m.groups["letter"]?.value}")
    }
}