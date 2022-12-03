import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class RabinKarp {

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
                line = line.replaceAll("[(){}\\\\]","");
                // removes 'a' 'an' in the sentences
                line = line.replaceAll("\\ban?\\b","");
                line = line.replaceAll("\\bthe?\\b","");
                line = line.replaceAll("\\band?\\b","");
                line = line.replaceAll("\\bor?\\b","");
                line = line.replaceAll(" +", " ");
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

    static void search(String pat, String txt, int q)
    {
        if(test == 10)
            System.out.println("Inside Rabin Karp method");
        test = 2;
        qwe = 0;
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < M - 1; i++)
            h = (h * d) % q;


        for (i = 0; i < M; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        for (i = 0; i <= N - M; i++) {

            if (p == t) {

                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                if (j == M){
                    qwe = i;
                    al1.add(ind1++, qwe);
                    al2.add(ind2++, pat);
                    remove = pat;
                    break;
                }
            }

            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h)
                        + txt.charAt(i + M))
                        % q;

                if (t < 0)
                    t = (t + q);
            }
        }
    }

//    public static String removeCommonWords(String s){
//
//        s = s.replaceAll("\"", "");
//
//        String[] commonWords = new String[]{"{","}","is","a","an","the"};
//
//
//        String words1[] = s.trim().split("\\s+");
//        String ans = "";
//        for(String str : words1){
//            if(!Arrays.stream(commonWords).anyMatch(str::equals)){
//                ans = ans + " ";
//                ans = ans + str;
//            }
//        }
//        return ans.toLowerCase();
//    }

    public static void main(String[] args) throws IOException {

        int q = 101;

//        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\1.txt";
//        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\2.txt";

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay07\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay07\\2.txt";

//        String s1 = "cat is an animal. Dog is also an animal.";
//        String s2 = "animal is also a living being. Animals also eat food.";
//
//        ArrayList<Integer> res = new ArrayList<Integer>();
//
//        //String s1 = fileToString(file1);
//
//        System.out.println();
//        System.out.println("Before: "+s1);
//        String res1 = removeCommonWords(s1);
//        System.out.println("After: "+res1);
//        System.out.println();
//
//        System.out.println("Before: "+s2);
//        String res2 = removeCommonWords(s2);
//        System.out.println("After: "+res2);
//        System.out.println();
        long start1 = System.nanoTime();
        String s1 = fileToString(file1);
        long end1 = System.nanoTime();
        long seconds1 = ((end1 - start1) / 1000) % 60;
        System.out.println(seconds1);

        long start2 = System.nanoTime();
        String s2 = fileToString(file2);
        long end2 = System.nanoTime();
        long seconds2 = ((end2 - start2) / 1000) % 60;
        System.out.println(seconds2);

        String backup = s1;

        String words1[] = s1.trim().split("\\s+");

        String words2[] = s2.trim().split("\\s+");

        StringBuilder sb = new StringBuilder(s2);

        long start3 = System.nanoTime();
        for(int i = 0 ; i < words1.length; i++){
            if(remove.length()>0)
                sb.delete(qwe, qwe + remove.length()-1);
            search(words1[i].toLowerCase(), sb.toString(), q);
            remove = "";
        }
        long end3 = System.nanoTime();
        long seconds = ((end3 - start3) / 1000) % 60;
        System.out.println(seconds);
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