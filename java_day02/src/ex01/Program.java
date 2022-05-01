import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

public class Program {

    public static void  main(String[] args) {
        if (args.length != 2) {
            System.err.println("Input two files .txt!");
            return;
        }

        ArrayList<String>   firstFileWords = new ArrayList<>();
        ArrayList<String>   secondFileWords = new ArrayList<>();
        TreeSet<String>     dictionary = new TreeSet<>();

        try (BufferedReader  br1 = new BufferedReader(new FileReader(args[0]));
        BufferedReader  br2 = new BufferedReader(new FileReader(args[1]));
        BufferedWriter  bw = new BufferedWriter(new FileWriter("dictionary.txt"))) {
            wordsToArray(br1, firstFileWords, dictionary);
            wordsToArray(br2, secondFileWords, dictionary);
            outputDictionary(bw, dictionary);
            double  res = countFrequency(firstFileWords, secondFileWords, dictionary);
            if (res > 0.004) {
                res -= 0.01;
            }
            System.out.printf("Similarity = %.2f\n", res);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void  wordsToArray(BufferedReader bufferedReader, ArrayList<String> worldList, TreeSet<String> dictionary) throws IOException {
        String  tmp;

        while ((tmp = bufferedReader.readLine()) != null) {
            String[] lineWords = tmp.split(" ");
            for (String lineWord : lineWords) {
                worldList.add(lineWord);
                dictionary.add(lineWord);
            }
        }
    }

    public static void  outputDictionary(BufferedWriter bw, TreeSet<String> dictionary) throws IOException {
        ArrayList<String> dictArray = new ArrayList<>(dictionary);

        for (int i = 0; i < dictArray.size() - 1; i++) {
            bw.write(dictArray.get(i) + ", ");
        }
        bw.write(dictArray.get(dictArray.size() - 1));
    }

    private static double countFrequency(ArrayList<String> firstFileWords, ArrayList<String> secondFileWords, TreeSet<String> dictionary) {
        ArrayList<Integer>  firstWordsEntries = new ArrayList<>();
        ArrayList<Integer>  secondWordsEntries = new ArrayList<>();
        double  res = -1;

        countEntries(firstFileWords, firstWordsEntries, dictionary);
        countEntries(secondFileWords, secondWordsEntries, dictionary);
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

            for (int i = 0; i < secondFileWords.size(); i++) {
                tmp2 += Math.pow(secondWordsEntries.get(i), 2);
            }
            determine = Math.sqrt(tmp1) * Math.sqrt(tmp2);
            res = numerator / determine;
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
