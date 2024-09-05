package com.skhkma.anidex

import com.skhkma.anidex.features.auth.ui.screen.ValidateResult
import com.skhkma.anidex.features.auth.ui.screen.validatePassword
import kotlin.test.Test

class PasswordValidatorTest {
    @Test
    fun `ValidatePassword returns success when password is valid`() {
        assert(validatePassword("12345678@Anidex") == ValidateResult.Success)
    }

    @Test
    fun `ValidatePassword returns min length error when password length is too short`() {
        assert(validatePassword("1234567") == ValidateResult.MinLengthError)
    }

    @Test
    fun `ValidatePassword returns max length error when password length is too long`() {
        assert(
            validatePassword(
                "1234567fjljflsdjflsjfljflfjlfjljfjfjfljdfodjflksjdfljsdflsj"
            ) == ValidateResult.MaxLengthError
        )
    }

    @Test
    fun `ValidatePassword returns special character error when password is lacking a special character`() {
        assert(validatePassword("12345678") == ValidateResult.SpecialCharError)
    }

    @Test
    fun `ValidatePassword returns digit error when password length is lacking a digit`() {
        assert(validatePassword("abcdefg@") == ValidateResult.DigitError)
    }

    @Test
    fun `ValidatePassword returns char error when password length is lacking a character`() {
        assert(validatePassword("1234567@") == ValidateResult.CharError)
    }

    @Test
    fun `ValidatePassword returns capital char error when password length is lacking a capital character`() {
        assert(validatePassword("1234567@a") == ValidateResult.CapitalCharError)
    }

}
