package org.test.modularApp

import kotlin.math.*
import org.test.modularLib.*

fun main() {
    println(sin(PI) + cos(PI))

    val srcText = "modular, modelar, modalar"
    val matches = LibServices.regex.findAll(srcText).forEach { m ->
        println("${m.value} - ${m.groups[1]?.value}")
//        println("${m.value} - ${m.groups["letter"]?.value}")
    }
}