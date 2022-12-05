import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PlagiarismDetector {
    static ArrayList<Integer> arrayToStoreIndices_1 = new ArrayList<>();
    static ArrayList<String> arrayToStorePattern_1 = new ArrayList<>();
    static ArrayList<Integer> arrayToStoreIndices_2 = new ArrayList<>();
    static ArrayList<String> arrayToStorePattern_2 = new ArrayList<>();
    static ArrayList<Integer> arrayToStoreIndices_3 = new ArrayList<>();
    static ArrayList<String> arrayToStorePattern_3 = new ArrayList<>();

    static int qwe1 = 0;
    static int qwe2 = 0;
    static int qwe3 = 0;
    static String removePatternFound_1 = "";
    static String removePatternFound_2 = "";
    static String removePatternFound_3 = "";

    public static String fileToString(String fileName) {
        String content = "";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String lineSeparator = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
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
                stringBuilder.append(lineSeparator);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            content = stringBuilder.toString();

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ioException) {
                System.out.println(ioException.getMessage());
            }
            return content.toLowerCase();
        }
    }

    static void searchForThread_1(String string, String patternToSearch)
    {
        int hashValueForString = 0;
        int hashValueForPattern = 0;
        for (int i = 0; i < patternToSearch.length(); ++i) {
            hashValueForString += patternToSearch.charAt(i) - 'a';
            hashValueForPattern += string.charAt(i) - 'a';
        }
        int j = 0;
        for (int i = 0; i <= string.length() - patternToSearch.length(); ++i) {
            if (hashValueForPattern == hashValueForString) {
                for (j = 0; j < patternToSearch.length(); ++j) {
                    if (patternToSearch.charAt(j)
                            != string.charAt(i + j)) {
                        break;
                    }
                }
            }
            if (j == patternToSearch.length()) {
                qwe1 = i;
                arrayToStoreIndices_1.add(qwe1);
                arrayToStorePattern_1.add(patternToSearch);
                removePatternFound_1 = patternToSearch;
                break;
            }
            if (i == string.length() - patternToSearch.length())
                break;
            hashValueForPattern = (int)((hashValueForPattern) - (string.charAt(i) - 'a'))
                    + string.charAt(i + patternToSearch.length()) - 'a';
        }
    }

    static void searchForThread_2(String S2, String pattern2)
    {
        int hashValueForString = 0;
        int hashValueForPattern = 0;

        for (int i = 0; i < pattern2.length(); ++i) {
            hashValueForString += pattern2.charAt(i) - 'a';
            hashValueForPattern += S2.charAt(i) - 'a';
        }
        int j = 0;
        for (int i = 0; i <= S2.length() - pattern2.length(); ++i) {
            if (hashValueForPattern == hashValueForString) {
                for (j = 0; j < pattern2.length(); ++j) {
                    if (pattern2.charAt(j)
                            != S2.charAt(i + j)) {
                        break;
                    }
                }
            }
            if (j == pattern2.length()) {
                qwe2 = i;
                arrayToStoreIndices_2.add(qwe2);
                arrayToStorePattern_2.add(pattern2);
                removePatternFound_2 = pattern2;
                break;
            }
            if (i == S2.length() - pattern2.length())
                break;
            hashValueForPattern = (int)((hashValueForPattern) - (S2.charAt(i) - 'a'))
                    + S2.charAt(i + pattern2.length()) - 'a';
        }
    }


    static void searchForThread_3(String string, String pattern)
    {
        int hashValueForString = 0;
        int hashValueForPattern = 0;

        for (int i = 0; i < pattern.length(); ++i) {
            hashValueForString += pattern.charAt(i) - 'a';
            hashValueForPattern += string.charAt(i) - 'a';
        }
        int j = 0;
        for (int i = 0; i <= string.length() - pattern.length(); ++i) {
            if (hashValueForPattern == hashValueForString) {
                for (j = 0; j < pattern.length(); ++j) {
                    if (pattern.charAt(j)
                            != string.charAt(i + j)) {
                        break;
                    }
                }
            }
            if (j == pattern.length()) {
                qwe3 = i;
                arrayToStoreIndices_3.add(qwe3);
                arrayToStorePattern_3.add(pattern);
                removePatternFound_3 = pattern;
                break;
            }
            if (i == string.length() - pattern.length())
                break;
            hashValueForPattern = (int)((hashValueForPattern) - (string.charAt(i) - 'a'))
                    + string.charAt(i + pattern.length()) - 'a';
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        int q = 109;

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay09\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay09\\2.txt";

//        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism06\\1.txt";
//        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism06\\2.txt";

        long start1 = System.nanoTime();

        String fileName1 = args[0];

        String fileName2 = args[1];

        String s1 = fileToString(fileName1);
        long end1 = System.nanoTime();
        double elapsedTimeInSecond1 = (double) (end1 - start1) / 1_000_000_000;

        long start2 = System.nanoTime();
        String s2 = fileToString(fileName2);
        long end2 = System.nanoTime();
        double elapsedTimeInSecond2 = (double) (end2 - start2) / 1_000_000_000;
        //System.out.println(elapsedTimeInSecond2);

        String backup = s1;

        String words1[] = s1.trim().split("\\s+");

        String words2[] = s2.trim().split("\\s+");

        StringBuilder sb1 = new StringBuilder(s2);

        StringBuilder sb2 = new StringBuilder(s2);

        StringBuilder sb3 = new StringBuilder(s2);

        Thread t1 = new Thread(() -> {
            for(int i = 0 ; i < words1.length / 3; i++){
                if(removePatternFound_1.length()>0)
                    sb1.delete(qwe1, qwe1 + removePatternFound_1.length()-1);
                searchForThread_1(sb1.toString(), words1[i].toLowerCase());
                removePatternFound_1 = "";
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = words1.length / 3 ; i < words1.length / 1.5 ; i++){
                if(removePatternFound_2.length()>0)
                    sb2.delete(qwe2, qwe2 + removePatternFound_2.length()-1);
                searchForThread_2(sb2.toString(), words1[i].toLowerCase());
                removePatternFound_2 = "";
            }
        });

        Thread t3 = new Thread(() -> {
            for(int i = (int)(words1.length / 1.5) ; i < words1.length ; i++){
                if(removePatternFound_3.length()>0)
                    sb3.delete(qwe3, qwe3 + removePatternFound_3.length()-1);
                searchForThread_3(sb3.toString(), words1[i].toLowerCase());
                removePatternFound_3 = "";
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
            arrayToStoreIndices_1.addAll(arrayToStoreIndices_2);
            arrayToStoreIndices_1.addAll(arrayToStoreIndices_3);
        });

        Thread t6 = new Thread(() -> {
            arrayToStorePattern_1.addAll(arrayToStorePattern_2);
            arrayToStorePattern_1.addAll(arrayToStorePattern_3);
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
        String str = arrayToStorePattern_1.get(0) + " ";
        double sqrt = Math.sqrt(words1.length * words2.length);
        double score = 0;
        for(int i = 1; i < arrayToStoreIndices_1.size(); i++){
            if(arrayToStoreIndices_1.get(i - 1) < arrayToStoreIndices_1.get(i)){
                str = str + arrayToStorePattern_1.get(i);
                str = str + " ";
                if(backup.contains(str.trim())){
                    if(i == arrayToStoreIndices_1.size()-1 && !str.equals(" ")) {
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