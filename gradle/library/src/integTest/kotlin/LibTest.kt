package org.test.modularLib.test

import org.test.modularLib.LibServices
import kotlin.test.*

class LibTest {
    @Test
    fun regex_matchesInput() {
        assertTrue(LibServices.regex.matches("modular"))
    }

    @Test
    fun midLetterIn_findsLetter() {
        assertEquals(null, LibServices.midLetterIn("unsupported"))
        assertEquals("u", LibServices.midLetterIn("modular"))
        println(this.javaClass.module.name)
    }
}