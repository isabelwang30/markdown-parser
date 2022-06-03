import static org.junit.Assert.*;
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
    public void getLinksTest1() throws IOException {
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://something.com", "some-thing.html"),
            links);
    }

    @Test
    public void getLinksTest2() throws IOException {
        Path fileName = Path.of("test-file2.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://alink.com", "anotherlink.html"),
            links);
    }

    @Test
    public void getLinksTest3() throws IOException {
        Path fileName = Path.of("test-file3.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("firstlink.com"), links);
    }

    @Test
    public void getLinksTest4() throws IOException {
        Path fileName = Path.of("test-file4.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of(), links);
    }

/*
    @Test
    public void getLinksTestBackticks() throws IOException {
        Path fileName = Path.of("snippet1.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("`google.com", "google.com", "ucsd.edu"), links);
    }

    @Test
    public void getLinksTestNestedAndEscaped() throws IOException {
        Path fileName = Path.of("snippet2.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("a.com", "a.com(())", "example.com"), links);
    }

    @Test
    public void getLinksTestLineBreaksAndText() throws IOException {
        Path fileName = Path.of("snippet3.md");
        String content = Files.readString(fileName);
        List<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of(
            "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule"), links);
    } */
}