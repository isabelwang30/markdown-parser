import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;

    @Override
    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        wordCount += text.getLiteral().split("\\W+").length;

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(text);
    }
}

class LinkVisitor extends AbstractVisitor {
    ArrayList<String> links = new ArrayList<>();

    @Override
    public void visit(Link link) {
        // This is called for all Link nodes.

        // Count links
        if (link.getDestination() != null) {
            links.add(link.getDestination());
        }

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(link);
    }
}

class TryCommonMark {
    public static void main(String[] args) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("This is *Sparta*");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        // "<p>This is <em>Sparta</em></p>\n"
        String output = renderer.render(document);
        System.out.println(output);

        // this part actually does the computation
        String text = "[some link](google.com) [another link](apple.com)" 
            + "[not a link] (something.org)";
        Node node = parser.parse(text);
        LinkVisitor visitor = new LinkVisitor();
        node.accept(visitor);
        ArrayList<String> links = visitor.links;
        System.out.println(links);
    }
}
