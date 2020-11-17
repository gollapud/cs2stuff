public class thing {
package net.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

    public class LinkedQuadTreeTest {

        @Test
        public void addNWTest() {
            LinkedQuadTree<String> qt = new LinkedQuadTree<>();
            Position<String> r = qt.addRoot("A");
            qt.addNW(r, "B");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.nw(qt.root()).getElement());

            qt.addNW(qt.nw(qt.root()), "C");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.nw(qt.root()).getElement());
            assertEquals("C", qt.nw(qt.nw(qt.root())).getElement());
        }

        @Test
        public void addNETest() {
            // write a test

            LinkedQuadTree<String> qt = new LinkedQuadTree<>();
            Position<String> r = qt.addRoot("A");
            qt.addNE(r, "B");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.ne(qt.root()).getElement());

            qt.addNE(qt.ne(qt.root()), "C");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.ne(qt.root()).getElement());
            assertEquals("C", qt.ne(qt.ne(qt.root())).getElement());
        }

        @Test
        public void addSWTest() {
            // write a test
            LinkedQuadTree<String> qt = new LinkedQuadTree<>();
            Position<String> r = qt.addRoot("A");
            qt.addSW(r, "B");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.sw(qt.root()).getElement());

            qt.addSW(qt.sw(qt.root()), "C");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.sw(qt.root()).getElement());
            assertEquals("C", qt.sw(qt.sw(qt.root())).getElement());
        }

        @Test
        public void addSETest() {
            // write a test
            LinkedQuadTree<String> qt = new LinkedQuadTree<>();
            Position<String> r = qt.addRoot("A");
            qt.addSE(r, "B");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.se(qt.root()).getElement());

            qt.addSE(qt.se(qt.root()), "C");
            assertEquals("A", qt.root().getElement());
            assertEquals("B", qt.se(qt.root()).getElement());
            assertEquals("C", qt.se(qt.se(qt.root())).getElement());
        }

        @Test
        public void setTest() {
            // write a test for the LinkedQuadTree.set method
            LinkedQuadTree<String> qt = new LinkedQuadTree<>();
            Position<String> r = qt.addRoot("A");

            LinkedQuadTree.Node<String> nw = qt.createNode("B", qt.root, null, null, null, null);
            qt.root.setNW(nw);
            LinkedQuadTree.Node<String> nwChild = qt.createNode("B1", nw, null, null, null, null);
            nw.setNW(nwChild);

            LinkedQuadTree.Node<String> ne = qt.createNode("C", qt.root, null, null, null, null);
            qt.root.setNE(ne);
            LinkedQuadTree.Node<String> neChild = qt.createNode("C1", ne, null, null, null, null);
            ne.setNE(neChild);

            LinkedQuadTree.Node<String> sw = qt.createNode("D", qt.root, null, null, null, null);
            qt.root.setSW(sw);
            LinkedQuadTree.Node<String> swChild = qt.createNode("D1", sw, null, null, null, null);
            sw.setSW(swChild);

            LinkedQuadTree.Node<String> se = qt.createNode("E", qt.root, null, null, null, null);
            qt.root.setSE(se);
            LinkedQuadTree.Node<String> seChild = qt.createNode("E1", se, null, null, null, null);
            se.setSE(seChild);

            assertEquals("A", qt.root.getElement());

            assertEquals("B", qt.nw(qt.root).getElement());
            assertEquals("B1", qt.nw(qt.nw(qt.root)).getElement());

            assertEquals("C", qt.ne(qt.root).getElement());
            assertEquals("C1", qt.ne(qt.ne(qt.root)).getElement());

            assertEquals("D", qt.sw(qt.root).getElement());
            assertEquals("D1", qt.sw(qt.sw(qt.root)).getElement());

            assertEquals("E", qt.se(qt.root).getElement());
            assertEquals("E1", qt.se(qt.se(qt.root)).getElement());
        }

        @Test
        public void everythingTest() {
            // write a test
            LinkedQuadTree<String> qt = new LinkedQuadTree<>();
            Position<String> r = qt.addRoot("A");

            qt.addNW(r,"B");
            qt.addNE(r,"C");
            qt.addSW(r,"D");
            qt.addSE(r,"E");

            qt.addNW(qt.nw(r),"B1");
            qt.addNE(qt.nw(r),"B2");
            qt.addSW(qt.nw(r),"B3");
            qt.addSE(qt.nw(r),"B4");

            qt.addNW(qt.ne(r),"C1");
            qt.addNE(qt.ne(r),"C2");
            qt.addSW(qt.ne(r),"C3");
            qt.addSE(qt.ne(r),"C4");

            qt.addNW(qt.sw(r),"D1");
            qt.addNE(qt.sw(r),"D2");
            qt.addSW(qt.sw(r),"D3");
            qt.addSE(qt.sw(r),"D4");

            qt.addNW(qt.se(r),"E1");
            qt.addNE(qt.se(r),"E2");
            qt.addSW(qt.se(r),"E3");
            qt.addSE(qt.se(r),"E4");

            assertEquals("A", qt.root.getElement());

            assertEquals("B", qt.nw(qt.root).getElement());
            assertEquals("B1", qt.nw(qt.nw(qt.root)).getElement());
            assertEquals("B2", qt.ne(qt.nw(qt.root)).getElement());
            assertEquals("B3", qt.sw(qt.nw(qt.root)).getElement());
            assertEquals("B4", qt.se(qt.nw(qt.root)).getElement());

            assertEquals("C", qt.ne(qt.root).getElement());
            assertEquals("C1", qt.nw(qt.ne(qt.root)).getElement());
            assertEquals("C2", qt.ne(qt.ne(qt.root)).getElement());
            assertEquals("C3", qt.sw(qt.ne(qt.root)).getElement());
            assertEquals("C4", qt.se(qt.ne(qt.root)).getElement());

            assertEquals("D", qt.sw(qt.root).getElement());
            assertEquals("D1", qt.nw(qt.sw(qt.root)).getElement());
            assertEquals("D2", qt.ne(qt.sw(qt.root)).getElement());
            assertEquals("D3", qt.sw(qt.sw(qt.root)).getElement());
            assertEquals("D4", qt.se(qt.sw(qt.root)).getElement());

            assertEquals("E", qt.se(qt.root).getElement());
            assertEquals("E1", qt.nw(qt.se(qt.root)).getElement());
            assertEquals("E2", qt.ne(qt.se(qt.root)).getElement());
            assertEquals("E3", qt.sw(qt.se(qt.root)).getElement());
            assertEquals("E4", qt.se(qt.se(qt.root)).getElement());

        }
    }
}
