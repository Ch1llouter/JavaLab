# Лабораторна робота №4 (Супрун Євгеній, ПД-33)
## Частина 1

---

### 1. Завдання:
Реалізуйте функцію, яка перевіряє, чи дане слово є паліндромом (читається так само, як назад, так і вперед), використовуючи рядки Java.

### 2. Завдання:
Покрити тестами функціональність програми.

---

### Звіт
**StringCheching клас (.java):**  знаходиться в папці main/java/org.dut/lab41
```java
package org.dut.lab41;

public class StringChecking {

    /**
     * Перевіряє, чи є заданий рядок паліндромом.
     *
     * @param string Рядок для перевірки.
     * @return {@code true}, якщо рядок є паліндромом, {@code false} - в іншому випадку.
     */
    public boolean isPalindrome(String string) {
        // Видаляє пробіли й інші непотрібні символи та переводить рядок у нижній регістр
        String clearString = string.replaceAll("\\s", "").toLowerCase();

        // Порівнює символи рядка з початку та кінця (розділяючи строчку посередині, порівнюючи початок з кінцем), здійснюючи перевірку на паліндром
        for (int i = 0; i < clearString.length() / 2; i++) {
            if (clearString.charAt(i) != clearString.charAt(clearString.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
```
Тести створені в класі StringCheckingTests (.java) і знаходиться в папці test/java/org.dut/lab41
```java
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
```