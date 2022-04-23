//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            
            // if no more pairs of brackets or parentheses are found, end
            if (openBracket == -1 || closeBracket == -1 || openParen == -1 
                || closeParen == -1) 
            {
                break;
            }

            // don't add the string inside the parentheses if it contains spaces
            if (markdown.substring(openParen + 1, closeParen).contains(" ")) {
                currentIndex = closeParen + 1;
                continue;
            }
            // add a link if it's on the first line
            else if (openBracket == 0) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            // add the link if it's not an image
            else if (markdown.charAt(openBracket - 1) != '!' 
                && openParen == closeBracket + 1) 
            {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }

            currentIndex = closeParen + 1;
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}