package doublylinkedlistapp;

import java.util.*;

/**
 * A Linked List that can traverse forwards and backwards between nodes.
 * @author Dominico Galati, Caitlin Abuel
 * @param <T> Generic Type parameter
 */
public class DoublyLinkedList<T> {

    Node head, tail;
    int size;

    /**
     * DLL constructor initializes the head and tail as null
     */
    DoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Creates nodes which will be used in the DLL.
     *
     * @param <T> Generic type parameter
     */
    public class Node<T> {

        public T data;
        Node prev;
        Node next;

        /**
         * Constructor for the Node class.
         *
         * @param data The data of the node
         */
        public Node(T data) {
            this.data = data;
        }

        /**
         * Overrides the toString for the node class
         *
         * @return The data if the node
         */
        public String toString() {
            return "" + data;
        }
    }

    /**
     * Overrides the toString for the DLL.
     *
     * @return returns the contents of the list enclosed in square brackets and
     * separated by commas.
     */
    @Override
    public String toString() {
        String out;
        Node contents = head;
        out = "[";
        while (contents != null) { // Could lead to an infinite loop
            out += contents.data;
            contents = contents.next;
            if (contents != null) {
                out += ", ";
            }
        }
        out += "]";
        return out;
    }

    /**
     * Adds a node to the end of the DLL.
     *
     * @param value The data of the node.
     */
    void addLast(T value) {
        Node last = new Node(value);
        if (head == null && tail == null) {
            head = last;
        } else {
            tail.next = last;
            last.prev = tail;
        }
        tail = last;
        size++;
    }

    /**
     * Adds a node to the beginning of the DLL.
     *
     * @param value The data of the node.
     */
    void addFirst(T value) {
        Node first = new Node(value);
        if (head == null && tail == null) {
            tail = first;
        }
        first.next = head;
        head = first;
        size++;
    }

    /**
     * Retrieves the first element of the DLL and throws an exception if the DLL
     * is empty.
     *
     * @return The first element of the DLL
     */
    T getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        } else {
            return (T) head.data;
        }
    }

    /**
     * Retrieves the last element of the DLL and throws an exception if the DLL
     * is empty.
     *
     * @return The last element of the DLL
     */
    T getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        } else {
            return (T) tail.data;
        }
    }

    /**
     * Removes the first element of the DLL and throws an exception if the DLL
     * is empty.
     */
    void removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node newHead = head.next;
        head = newHead;
        head.prev = null;
        size--;
    }

    /**
     * Removes the last element of the DLL and throws an exception if the DLL is
     * empty.
     */
    void removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        Node newTail = tail.prev;
        tail = newTail;
        tail.next = null;
        size--;
    }

    /**
     * Determines the size of the DLL
     *
     * @return The size of the DLL.
     */
    int size() {
        return size;
    }

    /**
     * Retrieves the element of the given index.
     *
     * @param index The index of the desired element.
     * @return The element at the given index.
     */
    T get(int index) {
        if (index > size) {
            throw new NoSuchElementException("Index: " + index + "  Size: " + size);
        }
        Node contents = head;
        for (int i = 0; i != index; i++) {
            contents = contents.next;
        }
        return (T) contents.data;
    }

    /**
     * Retrieves the index of the given element.
     *
     * @param value The element within the DLL.
     * @return The index of the element.
     */
    int indexOf(T value) {
        Node contents = head;
        for (int i = 0; i < size; i++) {
            if (value.equals(contents.data)) {
                return i;
            } else {
                contents = contents.next;
            }
        }
        return -1;
    }

    /**
     * @return An instance of a DoublyListIterator
     */
    DoublyListIterator<T> listIterator() {
        DoublyListIterator iter = new DoublyListIterator();
        return iter;
    }

    /**
     * An iterator class for the DLL.
     *
     * @param <T> Generic type parameter
     */
    public class DoublyListIterator<T> implements ListIterator<T> {

        Node cNode; // cursor node
        private int end = 0; // used in next()

        /**
         * Constructor for the DLL iterator sets the cursor node to the head.
         */
        DoublyListIterator() {
            cNode = head;
        }

        /**
         * Adds a new node to the DLL through the iterator.
         *
         * @param e The node to be added.
         */
        @Override
        public void add(T e) {
            end = 0;
            Node newNode = new Node(e);
            if (cNode.prev != null) {
                Node tmp1 = cNode.prev;
                Node tmp2 = cNode;
                tmp1.next = newNode;
                newNode.next = tmp2;
                tmp2.prev = newNode;
                newNode.prev = tmp1;
                //pNode = null;
            } else {
                newNode.next = head;
                head = newNode;
            }

        }

        /**
         * Determines if there is a next element in the DLL.
         *
         * @return True if there is a next element, false otherwise.
         */
        @Override
        public boolean hasNext() {
            if (cNode.next != null) {
                return true;
            }
            return false;
        }

        /**
         * Determines if there is a previous element in the DLL.
         *
         * @return True if there is a previous element, false otherwise.
         */
        @Override
        public boolean hasPrevious() {
            if (cNode.prev != null) {
                return true;
            }
            return false;
        }

        /**
         * Prints the current node then moves the cursor to the next node.
         * Throws an exception if there is no next node.
         *
         * @return The current node.
         */
        @Override
        public T next() {

            Node tmpNode;

            if (end == 1) {
                throw new NoSuchElementException();
            }
            if (cNode.next == null) {
                tmpNode = cNode;
                end = 1;
                return (T) tmpNode;
            }
            tmpNode = cNode;
            cNode = cNode.next;

            return (T) tmpNode;
        }

        /**
         * Prints the current node and moves the cursor to the previous node.
         * Throws an exception if there us no previous node.
         *
         * @return The current node
         */
        @Override
        public T previous() {
            if (cNode.prev == null) {
                throw new NoSuchElementException();
            }
            cNode = cNode.prev;
            return (T) cNode;

        }

        /**
         * Removes the node that the cursor is currently on.
         */
        @Override
        public void remove() {
            previous();
            Node tmp1 = cNode.prev;
            Node tmp2 = cNode;
            Node tmp3 = cNode.next;

            tmp1.next = tmp3;
            tmp3.prev = tmp1;

        }

        /**
         * Sets the node that the cursor is currently on to the given data.
         *
         * @param e The data to set to the current node.
         */
        @Override
        public void set(T e) {
            previous();
            cNode.data = e;
            next();
        }

    }

}
