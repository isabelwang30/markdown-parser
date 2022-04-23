import static org.junit.Assert.*;

import java.beans.Transient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

import org.junit.*;
public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void getLinksTest() throws IOException {
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://something.com", "some-thing.html"),
            links);
    }
}