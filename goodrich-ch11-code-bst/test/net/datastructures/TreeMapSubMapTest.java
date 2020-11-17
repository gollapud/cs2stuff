package net.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeMapSubMapTest {

    TreeMap<Integer, String> m;

    @Before
    public void setUp() throws Exception {
        m = new TreeMap<>();
        m.put(70, "A");
        m.put(30, "A");
        m.put(90, "A");
        m.put(20, "A");
        m.put(49, "A");
        m.put(106, "A");
        m.put(7, "A");
        m.put(25, "A");
        m.put(62, "A");
        m.put(12, "A");
    }

    private void check(Integer[] expected, Iterable<Entry<Integer, String>> actual) {
        int i = 0;
        for (Entry<Integer, String> e : actual) {
            assertEquals(expected[i], e.getKey());
            i++;
        }
    }

    @Test
    public void subMapTest1() {
        Integer[] expected = {25, 30, 49, 62, 70};
        check(expected, m.subMap(21, 90));
    }

    @Test
    public void subMapTest2() {
        Integer[] expected = {25, 30, 49, 62};
        check(expected, m.subMap(21, 70));
    }

    @Test
    public void subMapTest3() {
        Integer[] expected = {25, 30, 49};
        check(expected, m.subMap(21, 62));
    }

    @Test
    public void subMapTest4() {
        Integer[] expected = {25, 30};
        check(expected, m.subMap(21, 30));
    }

    @Test
    public void subMapTest5() {
        Integer[] expected = {25, 30, 49, 62, 70, 90};
        check(expected, m.subMap(21, 106));
    }

    @Test
    public void subMapTest6() {
        Integer[] expected = {25, 30, 49, 62, 70, 90};
        check(expected, m.subMap(21, 95));
    }



    @Test
    public void subMapInclTest1() {
        Integer[] expected = {25, 30, 49, 62, 70};
        check(expected, m.subMapInclusive(21, 90));
    }

    @Test
    public void subMapInclTest2() {
        Integer[] expected = {25, 30, 49, 62};
        check(expected, m.subMapInclusive(21, 70));
    }

    @Test
    public void subMapInclTest3() {
        Integer[] expected = {25, 30, 49};
        check(expected, m.subMapInclusive(21, 62));
    }

    @Test
    public void subMapInclTest4() {
        Integer[] expected = {25, 30};
        check(expected, m.subMapInclusive(21, 30));
    }

    @Test
    public void subMapInclTest5() {
        Integer[] expected = {25, 30, 49, 62, 70, 90};
        check(expected, m.subMapInclusive(21, 106));
    }

    @Test
    public void subMapInclTest6() {
        Integer[] expected = {25, 30, 49, 62, 70, 90};
        check(expected, m.subMap(21, 95));
    }

}