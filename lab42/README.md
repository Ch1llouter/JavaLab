# Лабораторна робота №4 (Супрун Євгеній, ПД-33)
## Частина 2

---

### Вимоги:
 - клас Java Decoder зі статичними методами для кожного типу кодування.
 - Використовуйте регулярні вирази, щоб визначити, який метод кодування було застосовано до кожного слова.
 - Використовуйте StringBuilder для ефективних маніпуляцій з рядками.
 - Створіть функцію main для демонстрації вашого декодера з прикладами зашифрованих повідомлень.

### Методи кодування:
 - Кодування заміни голосних: усі голосні (a, e, i, o, u) замінюються на числа: a=1, e=2, i=3, o=4, u=5. Наприклад, "t2st3ng" повинно бути "testing".
 - Кодування заміни приголосних: всі приголосні замінюються на наступний приголосний в послідовності. Наприклад, "vetviph" повинно бути "testing".

---

### Звіт
**[Decoder.java](Decoder.java)**  знаходиться в папці main/java/org.dut/lab42
```java
package org.dut.lab42;
import java.util.regex.Pattern;
public class Decoder {
    private static String decodeVowelReplacement(String encodedWord) {
        char[] vowelsPattern = {'a', 'e', 'i', 'o', 'u'};
        StringBuilder decodedWord = new StringBuilder(encodedWord.length());
        for (char character : encodedWord.toCharArray()) {
            if (isVowel(character)){
                decodedWord.append(vowelsPattern[Character.getNumericValue(character) - 1]);
            }
            else{
                decodedWord.append(character);
            }
        }
        return decodedWord.toString();
    }
    private static boolean isVowel(char charInEncodedWord) {
        Pattern vowelReplacementPattern = Pattern.compile("[12345]");
        return vowelReplacementPattern.matcher(String.valueOf(charInEncodedWord)).matches();
    }
    private static String decodeConsonantReplacement(String encodedWord) {
        StringBuilder decodedWord = new StringBuilder();
        for (char charInEncodedWord : encodedWord.toCharArray()) {
            if (isConsonant(charInEncodedWord)) {
                char decodedChar = (char) (charInEncodedWord + 1);
                decodedWord.append(decodedChar);
            } else {
                decodedWord.append(charInEncodedWord);
            }
        }
        return decodedWord.toString();
    }
    private static boolean isConsonant(char charInEncodedWord) {
        Pattern consonantReplacementPattern = Pattern.compile("[bcdfghjklmnpqrstvwxyz]");
        return consonantReplacementPattern.matcher(String.valueOf(charInEncodedWord)).matches();
    }
    private static String detectAndDecode(String encodedWord) {
        for(char character : encodedWord.toCharArray()) {
            if (isVowel(character)) {
                return decodeVowelReplacement(encodedWord);
            }
        }
        return decodeConsonantReplacement(encodedWord);
    }
    public static String decodeEncodedString(String encodedString){
        StringBuilder decodedString = new StringBuilder();
        for(String substring : encodedString.split(" ")){
            decodedString.append(detectAndDecode(substring)).append(" ");
        }
        decodedString.deleteCharAt(decodedString.length()-1);
        return decodedString.toString();
    }
}

```
Тести створені в класі **[DecoderTests.java](..%2F..%2F..%2F..%2F..%2Ftest%2Fjava%2Forg%2Fdut%2Flab42%2FDecoderTests.java)** (.java) і знаходиться в папці test/java/org.dut/lab41
```java
package org.dut.lab42;

class DecoderTests {
    public static void main(String[] args) {
        String message = "dfsdf3 resres esse efsef33";
        System.out.print(Decoder.decodeEncodedString(message));
    }
}

```