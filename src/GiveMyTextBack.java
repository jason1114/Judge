import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by baidu on 15/9/7.
 */
public class GiveMyTextBack {

    public static void main(String[] args) throws IOException {
        String para = slurp(System.in, 1000);
        char[] chars = para.toCharArray();
        LinkedList<Character> result = new LinkedList<>();
        for (int i=0; i < chars.length-1; i++){
            if (chars[i] == ' ') {
                if (i+1 <= chars.length && chars[i+1] >= 65 && chars[i+1] != '\n') {
                    result.add(chars[i]);
                }
            } else if (chars[i] == '\n') {
                result.add(chars[i]);
            } else if (chars[i] == ',') {
                result.add(chars[i]);
            } else if (chars[i] == '.') {
                result.add(chars[i]);
            } else {
                // 英文字母
                if (result.size() > 0) {
                    if (result.getLast().equals('\n')) {
                        result.add(Character.toUpperCase(chars[i]));
                    } else if (result.getLast().equals(' ')
                            && result.size() > 2
                            && result.get(result.size()-2).equals('.')){
                        result.add(Character.toUpperCase(chars[i]));
                    } else {
                        result.add(Character.toLowerCase(chars[i]));
                    }
                } else {
                    result.add(Character.toUpperCase(chars[i]));
                }
            }
        }
        StringBuilder sb = new StringBuilder(result.size());
        for (Character c : result) {
            sb.append(c);
        }
        System.out.println(sb.toString());
    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    public static String slurp(final InputStream is, final int bufferSize) {
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try (Reader in = new InputStreamReader(is, "UTF-8")) {
            for (;;) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
        }
        catch (UnsupportedEncodingException ex) {
        /* ... */
        }
        catch (IOException ex) {
        /* ... */
        }
        return out.toString();
    }
}
