import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlagiarismDetector {

    public final static int d = 256;
    static ArrayList<Integer> al1 = new ArrayList<>();
    static ArrayList<String> al2 = new ArrayList<>();
    static ArrayList<Integer> al3 = new ArrayList<>();
    static ArrayList<String> al4 = new ArrayList<>();
    static ArrayList<Integer> al5 = new ArrayList<>();
    static ArrayList<String> al6 = new ArrayList<>();

    static int qwe1 = 0;
    static int qwe2 = 0;
    static int qwe3 = 0;
    static String remove1 = "";
    static String remove2 = "";
    static String remove3 = "";

    public static String fileToString(String fileName) {
//        System.out.println("Inside File to String");
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
        int hash1 = 0;
        int hash2 = 0;
        for (int i = 0; i < pattern1.length(); ++i) {
            hash1 += pattern1.charAt(i) - 'a';
            hash2 += S1.charAt(i) - 'a';
        }
        int j = 0;
        for (int i = 0; i <= S1.length() - pattern1.length(); ++i) {
            if (hash2 == hash1) {
                for (j = 0; j < pattern1.length(); ++j) {
                    if (pattern1.charAt(j)
                            != S1.charAt(i + j)) {
                        break;
                    }
                }
            }
            if (j == pattern1.length()) {
                qwe1 = i;
                al1.add(qwe1);
                al2.add(pattern1);
                remove1 = pattern1;
                break;
            }
            if (i == S1.length() - pattern1.length())
                break;
            hash2 = (int)((hash2) - (S1.charAt(i) - 'a'))
                    + S1.charAt(i + pattern1.length()) - 'a';
        }
    }

    static void search2(String S2, String pattern2)
    {
        int hash11 = 0;
        int hash21 = 0;

        for (int i = 0; i < pattern2.length(); ++i) {
            hash11 += pattern2.charAt(i) - 'a';
            hash21 += S2.charAt(i) - 'a';
        }
        int j = 0;
        for (int i = 0; i <= S2.length() - pattern2.length(); ++i) {
            if (hash21 == hash11) {
                for (j = 0; j < pattern2.length(); ++j) {
                    if (pattern2.charAt(j)
                            != S2.charAt(i + j)) {
                        break;
                    }
                }
            }
            if (j == pattern2.length()) {
                qwe2 = i;
                al3.add(qwe2);
                al4.add(pattern2);
                remove2 = pattern2;
                break;
            }
            if (i == S2.length() - pattern2.length())
                break;
            hash21 = (int)((hash21) - (S2.charAt(i) - 'a'))
                    + S2.charAt(i + pattern2.length()) - 'a';
        }
    }


    static void search3(String S3, String pattern3)
    {
        int hash12 = 0;
        int hash22 = 0;

        for (int i = 0; i < pattern3.length(); ++i) {
            hash12 += pattern3.charAt(i) - 'a';
            hash22 += S3.charAt(i) - 'a';
        }
        int j = 0;
        for (int i = 0; i <= S3.length() - pattern3.length(); ++i) {
            if (hash22 == hash12) {
                for (j = 0; j < pattern3.length(); ++j) {
                    if (pattern3.charAt(j)
                            != S3.charAt(i + j)) {
                        break;
                    }
                }
            }
            if (j == pattern3.length()) {
                qwe3 = i;
                al5.add(qwe3);
                al6.add(pattern3);
                remove3 = pattern3;
                break;
            }
            if (i == S3.length() - pattern3.length())
                break;
            hash22 = (int)((hash22) - (S3.charAt(i) - 'a'))
                    + S3.charAt(i + pattern3.length()) - 'a';
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        int q = 109;

//        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\1.txt";
//        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\2.txt";

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism06\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism06\\2.txt";

        long start1 = System.nanoTime();
        String s1 = fileToString(file1);
        long end1 = System.nanoTime();
        double elapsedTimeInSecond1 = (double) (end1 - start1) / 1_000_000_000;
        //System.out.println(elapsedTimeInSecond1);

        long start2 = System.nanoTime();
        String s2 = fileToString(file2).toLowerCase().trim();
        long end2 = System.nanoTime();
        double elapsedTimeInSecond2 = (double) (end2 - start2) / 1_000_000_000;
        //System.out.println(elapsedTimeInSecond2);

        String backup = s1;

        String words1[] = s1.trim().split("\\s+");

        String words2[] = s2.trim().split("\\s+");

        StringBuilder sb1 = new StringBuilder(s2);

        StringBuilder sb2 = new StringBuilder(s2);

        StringBuilder sb3 = new StringBuilder(s2);

        int a = words1.length / 3;

        Thread t1 = new Thread(() -> {
            for(int i = 0 ; i < words1.length / 3; i++){
                if(remove1.length()>0)
                    sb1.delete(qwe1, qwe1 + remove1.length()-1);
                search1(sb1.toString(), words1[i].toLowerCase());
                remove1 = "";
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = words1.length / 3 ; i < words1.length / 1.5 ; i++){
                if(remove2.length()>0)
                    sb2.delete(qwe2, qwe2 + remove2.length()-1);
                search2(sb2.toString(), words1[i].toLowerCase());
                remove2 = "";
            }
        });

        Thread t3 = new Thread(() -> {
            for(int i = (int)(words1.length / 1.5) ; i < words1.length ; i++){
                if(remove3.length()>0)
                    sb3.delete(qwe3, qwe3 + remove3.length()-1);
                search3(sb3.toString(), words1[i].toLowerCase());
                remove3 = "";
            }
        });

        long start3 = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long end3 = System.nanoTime();
        double elapsedTimeInSecond3 = (double) (end3 - start3) / 1_000_000_000;

//        System.out.println("Searching: "+elapsedTimeInSecond3);

        Thread t5 = new Thread(() -> {
            al1.addAll(al3);
            al1.addAll(al5);
        });

        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                al2.addAll(al4);
                al2.addAll(al6);
            }
        });
        long start4 = System.nanoTime();
        t5.start();
        t6.start();
        t5.join();
        t6.join();

        long end4 = System.nanoTime();
        double elapsedTimeInSecond4 = (double) (end4 - start4) / 1_000_000_000;
//        System.out.println("Merging time: "+elapsedTimeInSecond4);

        long start5 = System.nanoTime();
        int max = 0;
        String str = al2.get(0) + " ";
        double sqrt = Math.sqrt(words1.length * words2.length);
        double score = 0;
        for(int i = 1; i < al1.size(); i++){
            if(al1.get(i - 1) < al1.get(i)){
                str = str + al2.get(i);
                str = str + " ";
                if(backup.contains(str.trim())){
                    if(i == al1.size()-1 && !str.equals(" ")) {
                        max += str.trim().split(" ").length;
                        score = ((double)max/ (double) sqrt * 100);
                        if(score > 46.0){
                            break;
                        }
                    }
                    continue;
                } else if(str.trim().split(" ").length >= 2){
                    max += str.trim().split(" ").length;
                    str = " ";
                    score = ((double)max/ (double) sqrt * 100);
                    if(score > 46.0){
                        break;
                    }
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.###");
        score = Double.parseDouble(df.format(score));
        long end5 = System.nanoTime();
        double elapsedTimeInSecond5 = (double) (end5 - start5) / 1_000_000_000;
//        System.out.println("Score Calculation Time: "+elapsedTimeInSecond5);

        double totalExe = elapsedTimeInSecond2 + elapsedTimeInSecond2 + elapsedTimeInSecond3 + elapsedTimeInSecond4 + elapsedTimeInSecond5;
//        System.out.println("Total Time: "+totalExe);

//        System.out.println("Similarity: "+score);
        if(score > 45)
            System.out.println("1");
        else
            System.out.println("0");

    }
}