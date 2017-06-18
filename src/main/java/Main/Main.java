package Main;

import OtherFunctions.FileConverter;
import Trie.TrieST;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Joachim
 */
public class Main {

    public static void main(String args[]) throws IOException {
        String[] text = FileConverter.toStringArray("src\\main\\java\\supplementary\\shakespeare-complete-works.txt", "[^A-Za-z']");
        
        
        Arrays.sort(text);
        TrieST<Integer> trie = new TrieST();
        for (int i = 0; i < text.length; i++) {
            StringBuilder sb = new StringBuilder(text[i]);
            for(int y = 0; y < text[i].length(); y++) {
                char charAt = text[i].charAt(y);
                if (!Character.isLetter(charAt)) {
                    sb.deleteCharAt(y);
                    text[i] = sb.toString();
                }
            }
        }
        for (int i = 0; i < text.length; i++) {
            if (!trie.contains(text[i])) {
                trie.put(text[i], 1);
            } else {
                int tmpInt = trie.get(text[i]);
                trie.delete(text[i]);
                trie.put(text[i], tmpInt + 1);
            }
        }

        for (String key :  trie.keys()) {
            System.out.println(key + " : " + trie.get(key));
        }
    }

}
