import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class RabinUsingTwoThreads {

    public final static int d = 256;

    static ArrayList<Integer> al1 = new ArrayList<>();
    static ArrayList<String> al2 = new ArrayList<>();

    static ArrayList<Integer> al3 = new ArrayList<>();

    static ArrayList<String> al4 = new ArrayList<>();


    static int qwe1 = 0;

    static int qwe2 = 0;

    static int max = 0;

    static String remove1 = "";

    static String remove2 = "";

    static int test = 10;

    public static String fileToString(String fileName) {
        System.out.println("Inside File to String");
        String content = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                //System.out.println("Before: "+line);
//                line = line.replaceAll("[(){}\\\\]","");
//                line = line.replaceAll("\\b(?i)an?\\b","");
//                line = line.replaceAll("\\b(?i)the?\\b","");
//                line = line.replaceAll("\\b(?i)and?\\b","");
//                line = line.replaceAll("\\b(?i)or?\\b","");
//                line = line.replaceAll("\\b(?i)to?\\b","");
//                line = line.replaceAll("\\b(?i)or?\\b","");
//                line = line.replaceAll("\\b(?i)is?\\b","");
//                line = line.replaceAll("\\b(?i)are?\\b","");
//                line = line.replaceAll("\\b(?i)i?\\b","");
//                line = line.replaceAll("\\b(?i)this?\\b","");
//                line = line.replaceAll("\\b(?i)there?\\b","");
//                line = line.replaceAll(" +", " ");
                //System.out.println("After: "+line);
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            content = stringBuilder.toString();

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            return content.toLowerCase();
        }
    }

    static void search1(String S1, String pattern1)
    {
        // Declaring and initializing the hash values
        int hash11 = 0;
        int hash21 = 0;

        // Iterating over the pattern string to be matched
        // over
        for (int i = 0; i < pattern1.length(); ++i) {

            // Storing the hash value of the pattern
            hash11 += pattern1.charAt(i) - 'a';

            // Storing First hash value of the string
            hash21 += S1.charAt(i) - 'a';
        }

        // Initially declaring with zero
        int j = 0;

        // Iterating over the pattern string to checkout
        // hash values
        for (int i = 0; i <= S1.length() - pattern1.length();
             ++i) {

            // Checking the hash value
            if (hash21 == hash11) {

                // Checking the value
                for (j = 0; j < pattern1.length(); ++j) {

                    // Checking for detection of pattern in a
                    // pattern
                    if (pattern1.charAt(j)
                            != S1.charAt(i + j)) {

                        // Break statement to hault the
                        // execution of program as no
                        // pattern found
                        break;
                    }
                }
            }

            // If execution is not stopped means
            // pattern(sub-string) is present

            // So now simply detecting for one or more
            // occurrences inbetween pattern string using the
            // length() method
            if (j == pattern1.length()) {

                // Pattern is detected so printing the index
                qwe2 = i;
                al3.add(qwe2);
                al4.add(pattern1);
                remove2 = pattern1;
                break;
            }
            // for last case of loop, have to check,
            // otherwise,
            // S.charAt(i + pattern.length()) below will
            // throw error
            if (i == S1.length() - pattern1.length())
                break;

            // Roll the hash value over the string detected
            hash21 = (int)((hash21) - (S1.charAt(i) - 'a'))
                    + S1.charAt(i + pattern1.length()) - 'a';
        }
    }

    static void search(String S, String pattern)
    {
        // Declaring and initializing the hash values
        int hash1 = 0;
        int hash2 = 0;

        // Iterating over the pattern string to be matched
        // over
        for (int i = 0; i < pattern.length(); ++i) {

            // Storing the hash value of the pattern
            hash1 += pattern.charAt(i) - 'a';

            // Storing First hash value of the string
            hash2 += S.charAt(i) - 'a';
        }

        // Initially declaring with zero
        int j = 0;

        // Iterating over the pattern string to checkout
        // hash values
        for (int i = 0; i <= S.length() - pattern.length();
             ++i) {

            // Checking the hash value
            if (hash2 == hash1) {

                // Checking the value
                for (j = 0; j < pattern.length(); ++j) {

                    // Checking for detection of pattern in a
                    // pattern
                    if (pattern.charAt(j)
                            != S.charAt(i + j)) {

                        // Break statement to hault the
                        // execution of program as no
                        // pattern found
                        break;
                    }
                }
            }

            // If execution is not stopped means
            // pattern(sub-string) is present

            // So now simply detecting for one or more
            // occurrences inbetween pattern string using the
            // length() method
            if (j == pattern.length()) {

                // Pattern is detected so printing the index
                qwe1 = i;
                al1.add(qwe1);
                al2.add(pattern);
                remove1 = pattern;
                break;
            }
            // for last case of loop, have to check,
            // otherwise,
            // S.charAt(i + pattern.length()) below will
            // throw error
            if (i == S.length() - pattern.length())
                break;

            // Roll the hash value over the string detected
            hash2 = (int)((hash2) - (S.charAt(i) - 'a'))
                    + S.charAt(i + pattern.length()) - 'a';
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        int q = 109;

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay08\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay08\\2.txt";

//        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism08\\1.txt";
//        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism08\\2.txt";

        long start1 = System.nanoTime();
        String s1 = fileToString(file1);
        long end1 = System.nanoTime();
        double elapsedTimeInSecond1 = (double) (end1 - start1) / 1_000_000_000;
        System.out.println(elapsedTimeInSecond1);

        long start2 = System.nanoTime();
        String s2 = fileToString(file2).toLowerCase().trim();
        long end2 = System.nanoTime();
        double elapsedTimeInSecond2 = (double) (end2 - start2) / 1_000_000_000;
        System.out.println(elapsedTimeInSecond2);

        String backup = s1;

        String words1[] = s1.trim().split("\\s+");

        String words2[] = s2.trim().split("\\s+");

        StringBuilder sb1 = new StringBuilder(s2);

        StringBuilder sb2 = new StringBuilder(s2);

        int a = words1.length / 2;

        Thread t1 = new Thread(() -> {
            for(int i = 0 ; i < words1.length / 2; i++){
                if(remove1.length()>0)
                    sb1.delete(qwe1, qwe1 + remove1.length()-1);
                search(sb1.toString(), words1[i].toLowerCase());
                remove1 = "";
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = words1.length / 2 ; i < words1.length ; i++){
                if(remove2.length()>0)
                    sb2.delete(qwe2, qwe2 + remove2.length()-1);
                search1(sb2.toString(), words1[i].toLowerCase());
                remove2 = "";
            }
        });
        long start3 = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end3 = System.nanoTime();
        double elapsedTimeInSecond3 = (double) (end3 - start3) / 1_000_000_000;

        System.out.println("Rabin Karp: "+elapsedTimeInSecond3);

        long start4 = System.nanoTime();
        al3.addAll(al1);
        al4.addAll(al2);
        long end4 = System.nanoTime();
        double elapsedTimeInSecond4 = (double) (end4 - start4) / 1_000_000_000;
        System.out.println("Merging time: "+elapsedTimeInSecond4);
        double totalExe = elapsedTimeInSecond2 + elapsedTimeInSecond2 + elapsedTimeInSecond3 + elapsedTimeInSecond4;
        System.out.println("Total Time: "+totalExe);
        int div = Math.min(words1.length, words2.length);
        int max = 0;
        String str = al4.get(0) + " ";
        for(int i = 1 ; i < al3.size(); i++){
            if(al3.get(i - 1) < al3.get(i)){
                str = str + al4.get(i);
                str = str + " ";
                if(backup.contains(str.trim())){
                    if(i == al3.size()-1 && !str.equals(" ")) {
                        max += str.trim().split(" ").length;
                    }
                    continue;
                } else if(str.trim().split(" ").length >= 2){
                    max += str.trim().split(" ").length;
                    str = " ";
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");

        double score = Double.parseDouble(df.format(( 100 * (double)max/ (double) Math.sqrt(words1.length * words2.length))));

        System.out.println("Similarity: "+score);

    }
}