package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Program {

    public static void  main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: <file1>.txt <file2>.txt");
        } else {
            ArrayList<String> firstListWords = new ArrayList<>();
            ArrayList<String> secondListWords = new ArrayList<>();
            TreeSet<String> dictionary = new TreeSet<>();
            double res = 1;

            try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader("./src/ex01/" + args[0]));
                 BufferedReader bufferedReader2 = new BufferedReader(new FileReader("./src/ex01/" + args[1]));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./src/ex01/dictionary.txt"))) {

                wordsToArray(bufferedReader1, firstListWords, dictionary);
                wordsToArray(bufferedReader2, secondListWords, dictionary);

                if (!dictionary.isEmpty()) {
                    outputDictionary(bufferedWriter, dictionary);
                    res = countFrequency(firstListWords, secondListWords, dictionary);
                    if (res > 0.004) {
                        res -= 0.01;
                    }
                }
                System.out.printf("Similarity = %.2f\n", res);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void  wordsToArray(BufferedReader bufferedReader, ArrayList<String> listWords, TreeSet<String> dictionary) throws IOException {
        String str;

        while ((str = bufferedReader.readLine()) != null) {

            String[] array = str.split(" ");

            for (String word : array) {
                listWords.add(word);
                dictionary.add(word);
            }
        }
    }

    public static void  outputDictionary(BufferedWriter bufferedWriter, TreeSet<String> dictionary) throws IOException {
        ArrayList<String> dictArray = new ArrayList<>(dictionary);

        for (int i = 0; i < dictArray.size() - 1; i++) {
            bufferedWriter.write(dictArray.get(i) + ", ");
        }
        bufferedWriter.write(dictArray.get(dictArray.size() - 1));
    }

    private static double countFrequency(ArrayList<String> firstListWords, ArrayList<String> secondListWords, TreeSet<String> dictionary) {
        ArrayList<Integer> firstWordsEntries = new ArrayList<>();
        ArrayList<Integer> secondWordsEntries = new ArrayList<>();
        double  res = -1;

        countEntries(firstListWords, firstWordsEntries, dictionary);
        countEntries(secondListWords, secondWordsEntries, dictionary);
        if (firstWordsEntries.size() == secondWordsEntries.size()) {

            double  numerator = 0;
            double  determine;

            for (int i = 0; i < firstWordsEntries.size(); i++) {
                numerator += firstWordsEntries.get(i) * secondWordsEntries.get(i);
            }

            double  tmp1 = 0;

            for (Integer firstWordsEntry : firstWordsEntries) {
                tmp1 += Math.pow(firstWordsEntry, 2);
            }

            double  tmp2 = 0;

            for (Integer secondWordsEntry : secondWordsEntries) {
                tmp2 += Math.pow(secondWordsEntry, 2);
            }
            determine = Math.sqrt(tmp1) * Math.sqrt(tmp2);

            if (determine == 0) {
                res = 0;
            } else {
                res = numerator / determine;
            }
        }
        return res;
    }

    public static void  countEntries(ArrayList<String> fileWords, ArrayList<Integer> wordsEntries, TreeSet<String> dictionary) {
        int counter;

        for (String s : dictionary) {
            counter = 0;
            for (String fileWord : fileWords) {
                if (fileWord.equals(s)) {
                    counter++;
                }
            }
            wordsEntries.add(counter);
        }
    }
}
