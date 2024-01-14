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