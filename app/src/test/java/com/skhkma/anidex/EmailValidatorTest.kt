package com.skhkma.anidex

import com.skhkma.anidex.features.auth.ui.screen.login.isValidEmail
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {
    @Test
    fun `Email validator returns true if email is correct`() {
        assertTrue(isValidEmail("minboea.com@gmail.com"))
        assert(isValidEmail("test@example.com"))
        assert(isValidEmail("user_name@sub.example.com"))
        assert(isValidEmail("user.name@sub.example.co.in"))
        assert(isValidEmail("username@example.museum"))
    }

    @Test
    fun `Pass if email contains plus sign`() {
        assert(isValidEmail("user.name+tag+sorting@example.com"))
    }

    @Test
    fun `Email validator returns false if email is incorrect`() {
        // Missing '@' symbol
        assertFalse(!isValidEmail("plainaddress"))
// Missing domain
        assert(!isValidEmail("username@"))
// Multiple '@' symbols
        assert(!isValidEmail("username@@example.com"))
// Missing TLD
        assert(!isValidEmail("username@example"))
// Invalid TLD length (longer than 4 characters)
        assert(!isValidEmail("username@example.corporate"))
// Special characters not allowed in the domain
        assert(!isValidEmail("username@exa!mple.com"))

    }

}
