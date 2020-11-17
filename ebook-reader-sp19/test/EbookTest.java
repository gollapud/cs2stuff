import org.junit.Test;

import static org.junit.Assert.*;

public class EbookTest {

    @Test
    public void testEbookCreate() {
        Ebook e = new Ebook("Project Gutenberg's Frankenstein, by Mary Wollstonecraft (Godwin) Shelley\n" +
                "\n" +
                "This eBook is for the use of anyone anywhere at no cost and with\n" +
                "almost no restrictions whatsoever.  You may copy it, give it away or\n" +
                "re-use it under the terms of the Project Gutenberg License included\n" +
                "with this eBook or online at www.gutenberg.net\n");

        assertEquals("Mary Wollstonecraft (Godwin) Shelley", e.getAuthor());
        assertEquals("Frankenstein", e.getTitle());
    }

    @Test
    public void testEbookNumPages() {
        Ebook e = new Ebook("Project Gutenberg's Frankenstein, by Mary Wollstonecraft (Godwin) Shelley\n" +
                "\n" +
                "This eBook is for the use of anyone anywhere at no cost and with\n" +
                "almost no restrictions whatsoever.  You may copy it, give it away or\n" +
                "re-use it under the terms of the Project Gutenberg License included\n" +
                "with this eBook or online at www.gutenberg.net\n");

        assertEquals(324, e.numPages(1));
        assertEquals(162, e.numPages(2));
        assertEquals(108, e.numPages(3));
        assertEquals(81, e.numPages(4));
        assertEquals(65, e.numPages(5));
        assertEquals(54, e.numPages(6));
        assertEquals(27, e.numPages(12));
        assertEquals(3, e.numPages(120));
        assertEquals(1, e.numPages(1200));
    }

    @Test
    public void testEbookReadPage() {
        Ebook e = new Ebook("Project Gutenberg's Frankenstein, by Mary Wollstonecraft (Godwin) Shelley\n" +
                "\n" +
                "This eBook is for the use of anyone anywhere at no cost and with\n" +
                "almost no restrictions whatsoever.  You may copy it, give it away or\n" +
                "re-use it under the terms of the Project Gutenberg License included\n" +
                "with this eBook or online at www.gutenberg.net\n");

        assertEquals("j", e.readPage(3, 1));

        assertEquals("oj", e.readPage(1, 2));

        assertEquals("in,", e.readPage(10, 3));

        assertEquals("w.gutenberg.net\n", e.readPage(4, 77));
    }


}