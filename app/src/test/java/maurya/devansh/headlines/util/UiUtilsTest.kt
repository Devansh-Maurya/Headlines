package maurya.devansh.headlines.util

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Devansh on 8/6/20
 */

class UiUtilsTest {

    @Test
    fun combineAuthorAndSource_noAuthorAndNoSource_returnsEmptyString() {
        val author = ""
        val source = ""
        val result = combineAuthorAndSource(author, source)

        assertEquals("", result)
    }

    @Test
    fun combineAuthorAndSource_authorAndNoSource_returnsEmptyString() {
        val author = "John Doe"
        val source = ""
        val result = combineAuthorAndSource(author, source)

        assertEquals(author, result)
    }

    @Test
    fun combineAuthorAndSource_noAuthorAndSource_returnsEmptyString() {
        val author = ""
        val source = "TOI"
        val result = combineAuthorAndSource(author, source)

        assertEquals(source, result)
    }

    @Test
    fun combineAuthorAndSource_authorAndSource_returnsEmptyString() {
        val author = "John Doe"
        val source = "TOI"
        val result = combineAuthorAndSource(author, source)

        val expected = buildString {
            append(author)
            append(", ")
            append(source)
        }

        assertEquals(expected, result)
    }


}