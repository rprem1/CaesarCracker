package Caesar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Very simple program to decrypt a caesar cipher without knowing the shift using a dictionary and brute force.
 * Outputs the decrypted message and the shift.
 * Will only work if the original message is in English.
 * Raffael Prem
 */


public class CaesarCrack {

    private static HashMap<String, Integer> possibleResults;
    private static File dictionary;
    private static long startTime;
    private static long endTime;

    public static void decrypt(String message){


        possibleResults = new HashMap<>();


        // Going through each shift
        for(int shift = 0; shift < 27; shift++){

            String result = "";
            // going through each letter
            for(int j = 0; j < message.length(); j++){

                if(Character.isUpperCase(message.charAt(j))){

                    char ch = (char)(((int)message.charAt(j) + shift - 65) % 26 + 65);
                    result = result + ch;

                }

                else if(Character.isLowerCase(message.charAt(j))){

                    char ch = (char)(((int)message.charAt(j) + shift - 97) % 26 + 97);
                    result = result + ch;

                }

                else{
                    char ch = message.charAt(j);
                    result = result + ch;
                }


                // adding each possible correct decryption


            }

            possibleResults.put(result, shift);

        }


    }


    public static void caesarCracker(HashMap<String, Integer> possibleResults){


        try {
            dictionary = new File("words.txt");
            int wordCount = possibleResults.size();


            for(Map.Entry<String, Integer> entry : possibleResults.entrySet()){

                int englishCount = 0;

                String[] words = entry.getKey().split(" ");
                Scanner dict = new Scanner(dictionary);




                while(dict.hasNext()){

                    String temp = dict.next();

                    for(String s : words){

                        if(s.equalsIgnoreCase(temp)){

                            englishCount++;

                        }


                    }

                }

                if(englishCount >= wordCount / 2){

                    endTime = System.nanoTime();
                    long timeMilli = TimeUnit.NANOSECONDS.toMillis(endTime-startTime);
                    System.out.println("Message decrypted using the shift: " + entry.getValue());
                    System.out.println("Time: " + timeMilli + " milliseconds.");
                    System.out.println("Decrypted Message: " + entry.getKey());

                }

            }

        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }

    }


    public static void main(String[] args) {

        startTime = System.nanoTime();
        String msg = "Bpm Kimaiz Kqxpmz qa mfbzmumtg emis jg bwliga abivlizla. Qba uwabtg caml jg akpwwt kpqtlzmv bw pql amkzmb umaaioma qv ktiaa.";
        decrypt(msg);
        caesarCracker(possibleResults);

    }


}
