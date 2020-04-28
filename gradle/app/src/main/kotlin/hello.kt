package org.test.modularApp

import kotlin.math.*
import kotlin.random.*
import org.test.modularLib.*
import kotlin.time.*

fun main() {
    @OptIn(ExperimentalTime::class)
    measureTime {
        println(Random.nextDouble(abs(sin(PI) + cos(PI))))
        val srcText = "modular, modelar, modalar"
        val matches = LibServices.regex.findAll(srcText).onEach { m ->
            println("${m.value} - ${m.groups["letter"]?.value}")
        }
        println("Total ${matches.count()} matches")
    }.let { duration ->
        println("Time taken $duration")
    }
}