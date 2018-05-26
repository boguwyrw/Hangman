package pl.sda.home;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char[] Hangman = {' ', 'H', 'A', 'N', 'G', 'M', 'A', 'N'};
        int looser = 0;

        ArrayList<String> wordsList = new ArrayList<String>();

        String choiceWord;
        try {
            FileReader fileReader = new FileReader(new File("C:\\SDA\\MyTask\\Trial\\HangmanTrial\\words.txt"));
            BufferedReader bufferReader = new BufferedReader(fileReader);
            while ((choiceWord = bufferReader.readLine()) != null) {
                wordsList.add(choiceWord);
            }

            fileReader.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Random rd = new Random();
        int luckyNo = rd.nextInt(wordsList.size());

        String word = wordsList.get(luckyNo);

        Scanner sc = new Scanner(System.in);

        char[] guessWord = word.toCharArray();

        char[] coverWord = new char[guessWord.length];
        for (int i = 0; i < coverWord.length; i++) {
            coverWord[i] = '_';
        }

        String strCoverWord = "";
        String answer = " ";

        System.out.println("Zgadnij moj wyraz");
        System.out.println("Ma on liter " + guessWord.length);
        System.out.println("NIE UZYWAJ polskich znakow");

        while (!answer.equals(strCoverWord)) {

            System.out.println("Podaj litere:");
            String letter = sc.nextLine().toLowerCase();
            char youLetter = letter.charAt(0);

            for (int i = 0; i < guessWord.length; i++) {
                if (youLetter == guessWord[i]) {
                    coverWord[i] = guessWord[i];
                }
            }
            System.out.println(coverWord);

            int counter = 0;
            for (int i = 0; i < guessWord.length; i++) {
                if (youLetter != guessWord[i]) {
                    counter++;
                }
            }

            if (counter == guessWord.length) {
                looser++;
            }

            for (int i = 1; i <= looser; i++) {
                System.out.print(Hangman[i]);
            }
            System.out.println();

            if (looser == Hangman.length - 1) {
                System.out.println("Wisisz");
                System.out.println("Szukane slowo to: " + word);
                return;
            }

            answer = new String(guessWord);
            strCoverWord = new String(coverWord);
        }
        System.out.println("Gratulacje zwyciesco");
    }
}
