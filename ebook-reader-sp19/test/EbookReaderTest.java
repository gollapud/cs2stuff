import org.junit.Test;

import static org.junit.Assert.*;

public class EbookReaderTest {
    private static String text1 =
            "Project Gutenberg's Frankenstein, by Mary Wollstonecraft (Godwin) Shelley\n" +
            "This eBook is for the use of anyone anywhere at no cost and without restr\n";

    private static String text2 =
            "The Project Gutenberg EBook of Beowulf\n" +
            "This eBook is for the use of anyone in\n" +
            "almost no restrictions whatsoever. You\n";

    private static String text3 = "The Project Gutenberg EBook of TestBook";

    @Test
    public void testAdd() {
        EbookReader ebr = new EbookReader(2, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);
        assertEquals(2, ebr.numBooks());
    }

    @Test(expected = EbookException.class)
    public void testAddFail() {
        EbookReader ebr = new EbookReader(2, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);
        ebr.addBook(text1); // should cause EbookException
    }

    @Test
    public void testDelete() {
        EbookReader ebr = new EbookReader(2, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);
        ebr.deleteBook(1);
        assertEquals(1, ebr.numBooks());
    }

    @Test(expected = EbookException.class)
    public void testOpenFail() {
        EbookReader ebr = new EbookReader(4, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);
        ebr.openBook(3);  // should cause EbookException
    }

    @Test(expected = EbookException.class)
    public void testReadFail() {
        EbookReader ebr = new EbookReader(4, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);

        ebr.turnToPage(3);
        ebr.readPage(); // should cause EbookException
    }

    @Test
    public void testOpenRead() {
        EbookReader ebr = new EbookReader(4, 21);
        ebr.addBook(text1);
        ebr.addBook(text2);

        ebr.openBook(1);
        ebr.turnToPage(0);
        assertEquals("The Project Gutenberg", ebr.readPage());

        ebr.openBook(0);
        ebr.turnToPage(0);
        assertEquals("Project Gutenberg's F", ebr.readPage());
        ebr.turnToPage(3);
        assertEquals("n) Shelley\n" +
                "This eBook", ebr.readPage());

        ebr.deleteBook(0);

        // read the other book
        ebr.openBook(0);
        ebr.turnToPage(0);
        assertEquals("The Project Gutenberg", ebr.readPage());
        ebr.turnToPage(5);
        System.out.println(ebr.readPage());
        assertEquals("soever. You\n", ebr.readPage());
    }

    @Test(expected = EbookException.class)
    public void testDeleteOpenedBook() {
        EbookReader ebr = new EbookReader(4, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);
        // if deleted book is the open book then can no longer read it
        ebr.openBook(0);
        ebr.deleteBook(0);
        ebr.turnToPage(0);
        ebr.readPage();
    }

    @Test
    public void testOpenReadHard() {
        EbookReader ebr = new EbookReader(14, 12);
        for (int i=0; i<10; i++) {
           ebr.addBook(i+text3);
        }
        ebr.openBook(7);
        assertEquals("7The Project", ebr.readPage());
        ebr.deleteBook(3);
        ebr.openBook(7);
        assertEquals("8The Project", ebr.readPage());
        ebr.deleteBook(3);
        ebr.deleteBook(3);
        ebr.addBook("A"+text3);
        ebr.openBook(2);
        assertEquals("2The Project", ebr.readPage());
        ebr.openBook(6);
        assertEquals("9The Project", ebr.readPage());
        ebr.openBook(7);
        assertEquals("AThe Project", ebr.readPage());
        assertEquals(8, ebr.numBooks());
    }

    @Test(expected = EbookException.class)
    public void testOpenFailDeleted() {
        EbookReader ebr = new EbookReader(4, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);
        ebr.deleteBook(0);

        ebr.openBook(1); // should fail
    }

    @Test(expected = EbookException.class)
    public void testDeleteFail() {
        EbookReader ebr = new EbookReader(2, 1);
        ebr.addBook(text1);
        ebr.addBook(text2);
        ebr.deleteBook(2); // should cause EbookException
    }

}