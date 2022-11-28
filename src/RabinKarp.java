import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RabinKarp {

    public final static int d = 256;

    static ArrayList<Integer> al1 = new ArrayList<>();
    static ArrayList<String> al2 = new ArrayList<>();

    static int ind1 = 0;
    static int ind2 = 0;

    public static String fileToString(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        return content;
    }

    /* pat -> pattern
        txt -> text
        q -> A prime number
    */
    static void search(String pat, String txt, int q)
    {


        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++) {

            // Check the hash values of current window of
            // text and pattern. If the hash values match
            // then only check for characters one by one
            if (p == t) {
                /* Check for characters one by one */
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                // if p == t and pat[0...M-1] = txt[i, i+1,
                // ...i+M-1]
                if (j == M){
                    al1.add(ind1++, i);
                    al2.add(ind2++, pat);
                }

            }

            // Calculate hash value for next window of text:
            // Remove leading digit, add trailing digit
            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h)
                        + txt.charAt(i + M))
                        % q;

                // We might get negative value of t,
                // converting it to positive
                if (t < 0)
                    t = (t + q);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        int q = 101;

        String file1 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay03\\1.txt";
        String file2 = "C:\\Users\\gyash\\OneDrive\\Desktop\\COMP6651\\sample_data_and_submission\\data\\okay03\\2.txt";

        search(file1, file2, q);

        String s1 = fileToString(file1);
        System.out.println("File-1 Length: "+s1.length());
        System.out.println();

        String s2 = fileToString(file2);
        System.out.println("File-1 Length: "+s2.length());
        System.out.print("Testing: "+s2.charAt(234)+s2.charAt(235)+s2.charAt(236));

        String words1[] = s1.trim().split("\\s+");

        for(int i = 0 ; i < words1.length; i++){
            search(words1[i].toLowerCase(), s2.toLowerCase(), q);
        }

        System.out.println();

        for(int i : al1)
            System.out.print(i+" ");

        System.out.println();

        for(String s : al2)
            System.out.print(s+" ");

        System.out.println();

        System.out.println("al1 size: "+al1.size());

        System.out.println("al2 size: "+al2.size());
    }
}
