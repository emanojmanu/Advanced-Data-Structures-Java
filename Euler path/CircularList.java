import java.awt.List;
import java.util.Iterator;

public class CircularList<T>
             implements Iterable<T> {/** Class Entry holds a single node of the list */
    public class Entry<T> {
        T element;
        Entry<T> next;
        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }
    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> header, tail;
    int size;
   // int flag=1;

    CircularList() {
        header = new Entry<>(null, null);
        tail = header;
        size = 0;
    }

    public Iterator<T> iterator() { return new SLLIterator<>(header); }
    private class SLLIterator<E>
        implements Iterator<E> {
	Entry<E> cursor, prev;
	SLLIterator(Entry<E> head) {
	    cursor = head;
	    prev = null;
	}

	public boolean hasNext() {
	    return cursor.next != header;
	}
	
	public E next() {
	    prev = cursor;
	    cursor = cursor.next;
	    return cursor.element;
	}

	// To do: error checking
	// What should cursor be set to after a remove?
	public void remove() {
	    prev.next = cursor.next;
	    prev = null;
	}
    }

    // Add new elements to the end of the list
    void add(T x) {
	tail.next = new Entry<>(x, header);
	tail = tail.next;
	size++;
    }

    void printList() {
   //   Entry<T> x = header.next;
	for(T item: this) {
	    System.out.print(item + " ");
	}

	System.out.println();
    }
}