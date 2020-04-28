package org.test.modularLib

object LibServices {
    val regex = Regex("mod(?<letter>.)lar")

    fun midLetterIn(value: String): String? =
            regex.matchEntire(value)?.run { groups["letter"]?.value }
}