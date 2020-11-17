public class ting {
    /*
     * This program is free software: you can redistribute it and/or modify
     * it under the terms of the GNU General Public License as published by
     * the Free Software Foundation, either version 3 of the License, or
     * (at your option) any later version.
     *
     * This program is distributed in the hope that it will be useful,
     * but WITHOUT ANY WARRANTY; without even the implied warranty of
     * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     * GNU General Public License for more details.
     *
     * You should have received a copy of the GNU General Public License
     * along with this program.  If not, see <http://www.gnu.org/licenses/>.
     */
package net.datastructures;

import java.util.ArrayList;
import java.util.Comparator;

    /**
     * An implementation of a map using a quad search tree.
     *
     * @author Brandon Myers
     */
    public class SpatialTreeMap<X,Y,V> extends AbstractMap<Coord<X,Y>,V> implements Sorted2DMap<X,Y,V>  {

        protected LinkedQuadTree<Entry<Coord<X,Y>,V>> tree = new LinkedQuadTree<>();
        private Comparator<Coord<X,Y>> comp;

        /** Constructs an empty map */
        public SpatialTreeMap() {
            this(new DefaultComparator<X>(), new DefaultComparator<Y>());
        }

        public SpatialTreeMap(Comparator<X> cx, Comparator<Y> cy) {
            this.compX = cx;
            this.compY = cy;
            tree.addRoot(null);       // create a sentinel leaf as root
        }

        // instance variable for an AbstractSortedMap
        /** The comparator defining the ordering of keys in the map. */
        private Comparator<X> compX;
        private Comparator<Y> compY;

        /** Determines whether a key is valid. */
        protected boolean checkKey(Coord<X,Y> key) throws IllegalArgumentException {
            try {
                return (compX.compare(key.getX(),key.getX())==0) && (compY.compare(key.getY(),key.getY())==0);   // see if key can be compared to itself
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Incompatible key");
            }
        }

        /**
         * Returns the number of entries in the map.
         * @return number of entries in the map
         */
        @Override
        public int size() {
            // COMPLETE
            // keep in mind only internal nodes have elements
            return (tree.size() -1) / 4;
        }

        /** Utility used when inserting a new entry at a leaf of the tree */
        private void expandExternal(Position<Entry<Coord<X,Y>,V>> p, Entry<Coord<X,Y>,V> entry) {
            tree.set(p, entry);            // store new entry at p
            tree.addNW(p, null);         // add new sentinel leaves as children
            tree.addNE(p, null);
            tree.addSW(p, null);
            tree.addSE(p, null);
        }

        protected Position<Entry<Coord<X,Y>,V>> root() { return tree.root(); }
        protected Position<Entry<Coord<X,Y>,V>> parent(Position<Entry<Coord<X,Y>,V>> p) { return tree.parent(p); }
        protected Position<Entry<Coord<X,Y>,V>> nw(Position<Entry<Coord<X,Y>,V>> p) { return tree.nw(p); }
        protected Position<Entry<Coord<X,Y>,V>> ne(Position<Entry<Coord<X,Y>,V>> p) { return tree.ne(p); }
        protected Position<Entry<Coord<X,Y>,V>> sw(Position<Entry<Coord<X,Y>,V>> p) { return tree.sw(p); }
        protected Position<Entry<Coord<X,Y>,V>> se(Position<Entry<Coord<X,Y>,V>> p) { return tree.se(p); }
        //protected Position<Entry<Coord<X,Y>,V>> sibling(Position<Entry<Coord<X,Y>,V>> p) { return tree.sibling(p); }
        protected boolean isRoot(Position<Entry<Coord<X,Y>,V>> p) { return tree.isRoot(p); }
        protected boolean isExternal(Position<Entry<Coord<X,Y>,V>> p) { return tree.isExternal(p); }
        protected boolean isInternal(Position<Entry<Coord<X,Y>,V>> p) { return tree.isInternal(p); }
        protected void set(Position<Entry<Coord<X,Y>,V>> p, Entry<Coord<X,Y>,V> e) { tree.set(p, e); }
        protected Entry<Coord<X,Y>,V> remove(Position<Entry<Coord<X,Y>,V>> p) { return tree.remove(p); }

        protected int compareX(Coord<X,Y> a, Entry<Coord<X,Y>,V> b) {
            return compX.compare(a.getX(),b.getKey().getX());
        }

        protected int compareY(Coord<X,Y> a, Entry<Coord<X,Y>,V> b) {
            return compY.compare(a.getY(),b.getKey().getY());
        }

        /**
         * Returns the position in p's subtree having the given key (or else the terminal leaf).
         * @param key  a target key
         * @param p  a position of the tree serving as root of a subtree
         * @return Position holding key, or last node reached during search
         */
        private Position<Entry<Coord<X,Y>,V>> treeSearch(Position<Entry<Coord<X,Y>,V>> p, Coord<X,Y> key) {
            if (isExternal(p))
                return p;                          // key not found; return the final leaf
            int compXValue = compareX(key, p.getElement());
            int compYValue = compareY(key, p.getElement());
            if (compXValue == 0  && compYValue == 0)
                return p;                          // key found; return its position
            else if (compXValue < 0) {
                if (compYValue < 0) {
                    return treeSearch(sw(p), key);
                } else {
                    return treeSearch(nw(p), key);
                }
            } else {
                if (compYValue < 0) {
                    return treeSearch(se(p), key);
                } else {
                    return treeSearch(ne(p), key);
                }
            }
        }

        /**
         * Returns the value associated with the specified key, or null if no such entry exists.
         * @param key  the key whose associated value is to be returned
         * @return the associated value, or null if no such entry exists
         */
        @Override
        public V get(Coord<X,Y> key) throws IllegalArgumentException {
            checkKey(key);                          // may throw IllegalArgumentException
            Position<Entry<Coord<X,Y>,V>> p = treeSearch(root(), key);
            if (isExternal(p)) return null;         // unsuccessful search
            return p.getElement().getValue();       // match found
        }

        /**
         * Associates the given value with the given key. If an entry with
         * the key was already in the map, this replaced the previous value
         * with the new one and returns the old value. Otherwise, a new
         * entry is added and null is returned.
         * @param key    key with which the specified value is to be associated
         * @param value  value to be associated with the specified key
         * @return the previous value associated with the key (or null, if no such entry)
         */
        @Override
        public V put(Coord<X,Y> key, V value) throws IllegalArgumentException {
            checkKey(key);
            Entry<Coord<X,Y>,V> newEntry = new MapEntry<>(key, value);
            Position<Entry<Coord<X,Y>,V>> p = treeSearch(root(), key);
            if (isExternal(p)) {
                expandExternal(p, newEntry);
                return null;
            } else {
                V old = p.getElement().getValue();
                set(p, newEntry);
                return old;
            }
        }

        /**
         * Removes the entry with the specified key, if present, and returns
         * its associated value. Otherwise does nothing and returns null.
         * @param key  the key whose entry is to be removed from the map
         * @return the previous value associated with the removed key, or null if no such entry exists
         */
        @Override
        public V remove(Coord<X,Y> key) throws IllegalArgumentException {
            throw new UnsupportedOperationException("Remove not supported in this Map");
        }

        // Support for iteration
        /**
         * Returns an iterable collection of all key-value entries of the map.
         *
         * @return iterable collection of the map's entries
         */
        @Override
        public Iterable<Entry<Coord<X,Y>,V>> entrySet() {
            ArrayList<Entry<Coord<X,Y>,V>> buffer = new ArrayList<>(size());
            for (Position<Entry<Coord<X,Y>,V>> p : tree.breadthfirst())
                if (tree.isInternal(p)) buffer.add(p.getElement());
            return buffer;
        }

        public Iterable<Entry<Coord<X,Y>,V>> subMapLinear(Coord<X,Y> nwCorner, Coord<X,Y> seCorner, Visitor<Entry<Coord<X,Y>,V>> visitor) throws IllegalArgumentException {
            checkKey(nwCorner);
            checkKey(seCorner);
            ArrayList<Entry<Coord<X,Y>,V>> buffer = new ArrayList<>(size());

            // use one of Tree's traversal methods to iterate through the elements of tree
            // for each one check whether it is within the bounding box
            // For each Position checked, call visitor.visit on it

            return buffer;
        }

        /**
         * Returns an iterable containing all entries with keys in the range from
         * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
         * @return iterable with keys in desired range
         * @throws IllegalArgumentException if <code>fromKey</code> or <code>toKey</code> is not compatible with the map
         */
        @Override
        public Iterable<Entry<Coord<X,Y>,V>> subMap(Coord<X,Y> nwCorner, Coord<X,Y> seCorner, Visitor<Entry<Coord<X,Y>,V>> visitor) throws IllegalArgumentException {
            checkKey(nwCorner);
            checkKey(seCorner);
            ArrayList<Entry<Coord<X,Y>,V>> buffer = new ArrayList<>(size());
            // only call subMapRecurse on a valid bounding box
            if (compX.compare(nwCorner.getX(), seCorner.getX()) <= 0 && compY.compare(nwCorner.getY(), seCorner.getY()) >= 0)
                subMapRecurse(nwCorner, seCorner, tree.root(), buffer, visitor);
            return buffer;
        }

        // utility to fill subMap buffer recursively
        private void subMapRecurse(Coord<X,Y> nwCorner, Coord<X,Y> seCorner, Position<Entry<Coord<X,Y>,V>> p,
                                   ArrayList<Entry<Coord<X,Y>,V>> buffer, Visitor<Entry<Coord<X,Y>,V>> visitor) {

            // COMPLETE
            // make sure to call visitor.visit on each Position that you look at
            if (isInternal(p))
                if (compareX(nwCorner, p.getElement()) < 0) {
                    if (compareY(nwCorner, p.getElement()) < 0) {
                        subMapRecurse(nwCorner, seCorner, ne(p), buffer, visitor);
                    } else {
                        subMapRecurse(nwCorner, seCorner, se(p), buffer, visitor);
                    }
                } else {
                    if (compareY(nwCorner, p.getElement()) < 0) {
                        subMapRecurse(nwCorner, seCorner, nw(p), buffer, visitor);
                    } else {
                        subMapRecurse(nwCorner, seCorner, sw(p), buffer, visitor);
                    }
                    if (compareX(seCorner, p.getElement()) < 0 && compareY(seCorner, p.getElement()) < 0) {
                        buffer.add(p.getElement());
                        visitor.visit(p);
                        if (compareX(seCorner, p.getElement()) < 0) {
                            if (compareY(seCorner, p.getElement()) < 0) {
                                subMapRecurse(nwCorner, seCorner, ne(p), buffer, visitor);
                            } else {
                                subMapRecurse(nwCorner, seCorner, se(p), buffer, visitor);
                            }
                        }
                    }
                }
        }


        // remainder of class is for debug purposes only
        /** Prints textual representation of tree structure (for debug purpose only). */
        //protected void dump() {
        public void dump() {
            dumpRecurse(tree.root(), 0, "ROOT");
        }

        /** This exists for debugging only */
        // use a XML tree viewer like https://jsonformatter.org/xml-viewer to see the nesting
        private void dumpRecurse(Position<Entry<Coord<X,Y>,V>> p, int depth, String from) {
            String indent = (depth == 0 ? "" : String.format("%" + (2*depth) + "s", ""));
            if (tree.isExternal(p))
                System.out.println(indent + "<leaf-"+from+"/>");
            else {
                System.out.println(indent + "<"+p.getElement().getKey()+"-"+from+">");
                dumpRecurse(tree.nw(p), depth+1, "NW");
                dumpRecurse(tree.ne(p), depth+1, "NE");
                dumpRecurse(tree.sw(p), depth+1,"SW");
                dumpRecurse(tree.se(p), depth+1, "SE");
                System.out.println(indent + "</"+p.getElement().getKey()+"-"+from+">");
            }
        }

        // for debugging - ought to be protected but we want to use it in our apps
        /*protected*/ public int treeHeight() {
            return tree.height(tree.root());
        }

    }


}
