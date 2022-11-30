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

    public static String fileToString(String fileName) {
        String content = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
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

    public static String removeCommonWords(String s){

        s = s.replaceAll("\"", "");

        String[] commonWords = new String[]{"{","}"};


        String words1[] = s.trim().split("\\s+");
        String ans = "";
        for(String str : words1){
            if(!Arrays.stream(commonWords).anyMatch(str::equals)){
                ans = ans + " ";
                ans = ans + str;
            }
        }
        return ans.toLowerCase();
    }

    public static void main(String[] args) throws IOException {

        int q = 101;

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism09\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\plagiarism09\\2.txt";

        ArrayList<Integer> res = new ArrayList<Integer>();

        String s1 = fileToString(file1);

        System.out.println();

        String s2 = fileToString(file2);

        String backup = removeCommonWords(s1);

        String words1[] = removeCommonWords(s1).trim().split("\\s+");

        String words2[] = removeCommonWords(s2).trim().split("\\s+");

        String res2 = removeCommonWords(s2);

        StringBuilder sb = new StringBuilder(res2);

        for(int i = 0 ; i < words1.length; i++){
            if(qwe != 0)
                sb.setCharAt(qwe, ' ');
            search(words1[i].toLowerCase(), sb.toString(), q);
        }


//        for(int i = 0 ; i < al1.size(); i++)
//            System.out.println(al1.get(i)+" : "+al2.get(i));

//        DecimalFormat df = new DecimalFormat("#.####");


        int div = Math.min(words1.length, words2.length);

        int commonality = 1;
        String str = al2.get(0) + " ";
        for(int i = 1 ; i < al1.size(); i++){
            if(al1.get(i - 1) < al1.get(i)){
                str = str + al2.get(i);
                str = str + " ";
                System.out.println(str);
                if(backup.contains(str) && str.length() >= 4 ){
                    commonality = commonality + 1;
                } else {
                    max += commonality;
                    str = " ";
                    commonality = 0;
                }
            }
        }
        System.out.println("Commonality: "+max);
        System.out.println("Score: "+(double)max/(double) div);
    }
}
