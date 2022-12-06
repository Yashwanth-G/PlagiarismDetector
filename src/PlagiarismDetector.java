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

    /**
     * The below method is used to take filePath and covert it into String in lowercase
     * @param fileName
     * @return
     */
    public static String fileToString(String fileName) {
        String content = "";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String lineSeparator = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
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

    /**
     * Search given word from one file in other file by thread-1
     * @param string
     * @param pattern
     */
    static void searchForThread_1(String string, String pattern)
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
                qwe1 = i;
                arrayToStoreIndices_1.add(qwe1);
                arrayToStorePattern_1.add(pattern);
                removePatternFound_1 = pattern;
                break;
            }
            if (i == string.length() - pattern.length())
                break;
            hashValueForPattern = (int)((hashValueForPattern) - (string.charAt(i) - 'a'))
                    + string.charAt(i + pattern.length()) - 'a';
        }
    }

    /**
     * Search given word from one file in other file by thread-2
     * @param string
     * @param pattern
     */
    static void searchForThread_2(String string, String pattern)
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
                qwe2 = i;
                arrayToStoreIndices_2.add(qwe2);
                arrayToStorePattern_2.add(pattern);
                removePatternFound_2 = pattern;
                break;
            }
            if (i == string.length() - pattern.length())
                break;
            hashValueForPattern = (int)((hashValueForPattern) - (string.charAt(i) - 'a'))
                    + string.charAt(i + pattern.length()) - 'a';
        }
    }

    /**
     * Search given word from one file in other file by thread-3
     * @param string
     * @param pattern
     */
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

    /**
     * The application execution starts from here
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

//        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\1.txt";
//        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay06\\2.txt";

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism07\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism07\\2.txt";

//        String fileName1 = args[0];
//
//        String fileName2 = args[1];

        String s1 = fileToString(file1);

        String s2 = fileToString(file2);

        String backup = s1;

        String words1[] = s1.trim().split("\\s+");

        String words2[] = s2.trim().split("\\s+");

        StringBuilder sb1 = new StringBuilder(s2);

        StringBuilder sb2 = new StringBuilder(s2);

        StringBuilder sb3 = new StringBuilder(s2);

        /**
         * Given files are splitted into three parts and each part is handled by one thread
         * first part of the file is executed by thread-1
         */
        Thread t1 = new Thread(() -> {
            for(int i = 0 ; i < words1.length / 3; i++){
                if(removePatternFound_1.length()>0)
                    sb1.delete(qwe1, qwe1 + removePatternFound_1.length()-1);
                searchForThread_1(sb1.toString(), words1[i].toLowerCase());
                removePatternFound_1 = "";
            }
        });

        /**
         * Second part of the files is executed by thread-2
         */
        Thread t2 = new Thread(() -> {
            for(int i = words1.length / 3 ; i < words1.length / 1.5 ; i++){
                if(removePatternFound_2.length()>0)
                    sb2.delete(qwe2, qwe2 + removePatternFound_2.length()-1);
                searchForThread_2(sb2.toString(), words1[i].toLowerCase());
                removePatternFound_2 = "";
            }
        });

        /**
         * Third part of the files is executed by thread-3
         */
        Thread t3 = new Thread(() -> {
            for(int i = (int)(words1.length / 1.5) ; i < words1.length ; i++){
                if(removePatternFound_3.length()>0)
                    sb3.delete(qwe3, qwe3 + removePatternFound_3.length()-1);
                searchForThread_3(sb3.toString(), words1[i].toLowerCase());
                removePatternFound_3 = "";
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        Thread t5 = new Thread(() -> {
            arrayToStoreIndices_1.addAll(arrayToStoreIndices_2);
            arrayToStoreIndices_1.addAll(arrayToStoreIndices_3);
        });

        Thread t6 = new Thread(() -> {
            arrayToStorePattern_1.addAll(arrayToStorePattern_2);
            arrayToStorePattern_1.addAll(arrayToStorePattern_3);
        });
        t5.start();
        t6.start();
        t5.join();
        t6.join();


        /**
         * Using the above arraylists the Similarity score is calculated in the below lines of code
         * The indicies of all the matched words are stored in one list and other array list contains the pattern of words that are
         * matched. By iterating on the indices array list sentenced is formed by traversing all the increasing indices and the sentences
         *  should be more than or equal to 2 words if they are more than or equal to words than the count of the owrds is added to global
         *  variable called max which is used to calculate the similarity score.
         */
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
                        if(score > 42.0){
                            break;
                        }
                    }
                    continue;
                } else if(str.trim().split(" ").length >= 2){
                    max += str.trim().split(" ").length;
                    str = " ";
                    score = ((double)max/ (double) sqrt * 100);
                    if(score > 42.0){
                        break;
                    }
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.###");
        score = Double.parseDouble(df.format(score));

        if(score > 42.0)
            System.out.println("1");
        else
            System.out.println("0");
    }
}