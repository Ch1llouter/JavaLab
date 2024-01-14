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
