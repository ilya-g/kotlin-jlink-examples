package org.test.modularApp

import org.test.modularLib.LibServices
import kotlin.test.Test
import kotlin.test.assertEquals

class LibServicesTest {
    @Test
    fun testRegex() {
        val match = LibServices.regex.matchEntire("modular")
        assertEquals("u", match!!.groupValues[1])
    }
}