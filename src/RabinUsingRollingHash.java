import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class RabinUsingRollingHash {

    public final static int d = 256;

    static ArrayList<Integer> al1 = new ArrayList<>();
    static ArrayList<String> al2 = new ArrayList<>();

    static int ind1 = 0;
    static int ind2 = 0;
    static int qwe = 0;

    static int max = 0;

    static String remove = "";

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
                qwe = i;
                al1.add(ind1++, qwe);
                al2.add(ind2++, pattern);
                remove = pattern;
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

    public static void main(String[] args) throws IOException {

        int q = 109;

//        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\1.txt";
//        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\2.txt";

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay07\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay07\\2.txt";

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

        StringBuilder sb = new StringBuilder(s2);

        long start3 = System.nanoTime();
        for(int i = 0 ; i < words1.length; i++){
            if(remove.length()>0)
                sb.delete(qwe, qwe + remove.length()-1);
            search(sb.toString(), words1[i].toLowerCase());
            remove = "";
        }
        long end3 = System.nanoTime();
        double elapsedTimeInSecond3 = (double) (end3 - start3) / 1_000_000_000;
        double totalExe = elapsedTimeInSecond2 + elapsedTimeInSecond2 + elapsedTimeInSecond3;
        System.out.println("Rabin Karp: "+elapsedTimeInSecond3);
        System.out.println("Total Time: "+totalExe);
        int div = Math.min(words1.length, words2.length);
        int max = 0;
        String str = al2.get(0) + " ";
        for(int i = 1 ; i < al1.size(); i++){
            if(al1.get(i - 1) < al1.get(i)){
                str = str + al2.get(i);
                str = str + " ";
                if(backup.contains(str.trim())){
                    if(i == al1.size()-1 && !str.equals(" ")) {
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