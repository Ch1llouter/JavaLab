package org.dut.lab41;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Набір тестів для класу StringChecking.
 */
public class StringCheckingTests {
    static StringChecking stringChecking;

    /**
     * Перед виконанням всіх тестів налаштовуємо середовище.
     */
    @BeforeAll
    static void setup() {
        stringChecking = new StringChecking();
    }

    /**
     * Тест: isPalindrome повертає false для рядка, який не є паліндромом.
     */
    @Test
    void isPalindrome_bool_ReturnsFalse() {
        assertFalse(stringChecking.isPalindrome("palindrome"));
    }

    /**
     * Тест: isPalindrome повертає true для паліндромного рядка.
     */
    @Test
    void isPalindrome_bool_ReturnsTrue() {
        assertTrue(stringChecking.isPalindrome("asdfdsa"));
    }
}