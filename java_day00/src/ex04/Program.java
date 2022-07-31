package ex04;

import java.util.Scanner;

public class Program {
    static final int MAX_CHAR_CODES = 65535;
    static final int MAX_TOP_CHARS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int[] charCount = getCharToInt(str);
        char[] topTenChars = getTopTenChars(charCount);

        printGraph(topTenChars, charCount);
    }

    static int[] getCharToInt(String str) {
        int[] charCount = new int[MAX_CHAR_CODES];
        char[] arr = str.toCharArray();

        for (int i = 0; i < str.length(); i++) {
            charCount[arr[i]]++;
        }
        return charCount;
    }

    static char[]   getTopTenChars(int[] charCount) {
        char[] topTenChars = new char[MAX_CHAR_CODES];

        for (int i = 0; i < MAX_CHAR_CODES; i++) {
            int iCharCount = charCount[i];
            if (iCharCount > 0) {
                for (int j = 0; j < MAX_TOP_CHARS; j++) {
                    if (charCount[topTenChars[j]] < iCharCount) {
                        topTenChars = insertCharAt(topTenChars, (char) i, j);
                        break;
                    }
                }
            }
        }
        return topTenChars;
    }

    static char[]   insertCharAt(char[] base, char ch, int index) {
        char[] ret = new char[MAX_TOP_CHARS];

        for (int i = 0; i < index; i++) {
            ret[i] = base[i];
        }
        ret[index] = ch;
        for (int i = index + 1; i < MAX_TOP_CHARS; i++) {
            ret[i] = base[i - 1];
        }
        return ret;
    }

    static void printGraph(char[] topTenChars, int[] charCount) {
        int max = charCount[topTenChars[0]];
        int maxHeight = max <= 10 ? max : 10;
        int totalLines = 2 + maxHeight;
        int[] graphs = new int[MAX_TOP_CHARS];

        for (int i = 0; i < MAX_TOP_CHARS; i++) {
            if (max <= 10) {
                graphs[i] = charCount[topTenChars[i]];
            } else {
                graphs[i] = charCount[topTenChars[i]] * 10 / max;
            }
        }
        System.out.print("\n");
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < MAX_TOP_CHARS; j++) {
                if (topTenChars[j] != 0) {
                    if (i + graphs[j] + 2 == totalLines) {
                        System.out.printf("%3d", charCount[topTenChars[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.printf("%3c", topTenChars[j]);
                    } else if (i + graphs[j] >= maxHeight) {
                        System.out.printf("%3c", '#');
                    }
                    if (j != MAX_TOP_CHARS - 1 && topTenChars[j + 1] != 0 && i + graphs[j + 1] >= maxHeight) {
                        System.out.printf("%c", ' ');
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
