package net.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeMapPutTest {
    TreeMap<Integer, String> m;

    @Before
    public void setUp() throws Exception {
        m = new TreeMap<>();
    }

    private void check(String[] expected, Iterable<Entry<Integer, String>> actual) {
        int i = 0;
        for (Entry<Integer, String> e : actual) {
            assertEquals(expected[i], e.getValue());
            i++;
        }
    }

    @Test
    public void putTest1() {
        assertEquals(null, m.put(300, "A"));
        assertEquals(null, m.put(200, "B"));
        assertEquals(null, m.put(100, "C"));
        assertEquals(null, m.put(400, "D"));
        assertEquals("B", m.put(200, "E"));
        assertEquals(null, m.put(50, "Z"));
        String[] expected = {"Z","C","E","A","D"};
        check(expected, m.entrySet());
    }
    @Test
    public void putIfAbsentTest1() {
        assertEquals(null, m.putIfAbsent(300, "A"));
        assertEquals(null, m.putIfAbsent(200, "B"));
        assertEquals(null, m.putIfAbsent(100, "C"));
        assertEquals(null, m.putIfAbsent(400, "D"));
        assertEquals("B", m.putIfAbsent(200, "E"));
        assertEquals(null, m.putIfAbsent(50, "Z"));
        String[] expected = {"Z","C","B","A","D"};
        check(expected, m.entrySet());
    }
}
