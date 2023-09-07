package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class MinHeap<T> {
    private final ArrayList<T> Heap;
    private final Map<T, Integer> map;
    private Comparator<? super T> comparator = null;
    
/* 
 * CREATION OF A MINIMUM EMPTY HEAP 
 * @param comparator: used to compare two elements into the heap
 * 
 */ 

public MinHeap(Comparator<? super T> comparator) throws MinHeapException {
	if(comparator == null) {
		throw new IllegalArgumentException("comparator cannot be null");
	}
	
    Heap = new ArrayList<>();
    map = new Hashtable<>();
    this.comparator = comparator;
}

/* 
 * INSERTION OF AN ELEMENT INTO THE HEAP
 * @param element: element to insert
 * 
 */

public void insert(T element) throws MinHeapException {
	if(element == null) {
		throw new IllegalArgumentException("element cannot be null");
	}
	
	if(this.map.putIfAbsent(element, this.map.size()) != null) {
		throw new MinHeapException("add: element is already in the MinHeap");
	}

    Heap.add(element);
    map.put(element, Heap.size()-1);
    int current = Heap.size() - 1;
        
    while(current > 0 && (this.comparator).compare(element, parent(element)) <  0) {
    	swap(element, parent(element));
    }	
}

/* 
 * SWAP TWO ELEMENTS
 * @param i and @param j: takes in input two elements that are swapped
 * 
 */

private void swap(T i, T j) throws MinHeapException {
    int i2 = map.get(i);
    int j2 = map.get(j);
    
    if(i2 >= Heap.size() || j2 >= Heap.size())
        throw new IndexOutOfBoundsException(String.format("i = %d, j = %d, size = %d", 
        i, j, Heap.size()));
    
    T tmp = Heap.get(i2);
    Heap.set(i2, j);
    Heap.set(j2, tmp);
    map.put(j, i2);
    map.put(tmp, j2);
}

/* 
 * RETURN ELEMENT OF THE HEAP 
 * @param i: position of the element to be returned
 * 
 */

public T get(int i){return Heap.get(i);}
public int getPosition(T element) {return map.get(element);}

/* 
 * CLEAR HEAP 
 * empties the heap, erasing the elements inside
 * 
 */

public void clear(){Heap.clear();}

/* RETURN TRUE IF THE HEAP IS EMPTY */
public boolean isEmpty(){return Heap.isEmpty();}

/* RETURN THE TOP ELEMENT OF THE HEAP */
public T top() {
    if(Heap.isEmpty()) {return null;}
    return Heap.get(0);
}

/* RETURN THE LAST ELEMENT OF THE HEAP */
private T last(){return Heap.get(Heap.size() - 1);}

/* RETURN OF HEAP SIZE */
public int size(){return Heap.size();}

/* RETURN OF THE PARENT OF AN ELEMENT */
public T parent(T element) throws MinHeapException {
    if(element == null){throw new MinHeapException("parent: element must be != null");}   
    if(!this.map.containsKey(element)){return element;}
        
    int i = map.get(element);
    if(i == 0){return element;} 
    return Heap.get(pIndex(i));
}

public int pIndex(int i) {
    if(i % 2 != 0){return (i / 2);}   
    return (i-1) / 2;
}

public boolean hasParent(int i) {
    if (i != 0)
      return true;
    return false;
  }

/* RETURN OF THE LEFT CHILD OF AN ELEMENT */
public T getLeft(T element) {
    int i = map.get(element);

    if(((2 * i) + 1) < Heap.size()) {
       return Heap.get((2 * i) + 1);
    } else {
       return element;
    }      
}

/* RETURN OF THE RIGHT CHILD OF AN ELEMENT */
public T getRight(T element) {
    int i = map.get(element);

    if(((2* i) + 2) < Heap.size()) {
       return Heap.get((2 * i) + 2);
    }else {
    	return element;
    }      
}

/* ELEMENT EXTRACTION WITH MINIMUM VALUE */
private T min(T o1, T o2) throws MinHeapException {
    return (((this.comparator).compare(o1, o2) < 0) ? o1 : o2);
}

private void heapify(T element) throws MinHeapException {
    if(element == null)
        throw new MinHeapException("element: element must be != null");
    
    T m = min(element, min(getLeft(element), getRight(element)));

    if((this.comparator).compare(element, m) != 0) {
        swap(m, element);

        heapify(element);
    }
}

/* REMOVE AN ELEMENT */
public T remove() throws MinHeapException {
	swap(top(), last());
    T first = Heap.remove(Heap.size() - 1);
    
    if(Heap.size() > 1){heapify(top());}
    
    return first;
}

/* DECREASES KEY VALUE OF KEY AT INDEX I TO NEW_VAL */
public void decrease(T element, T newElement) throws MinHeapException {
    if(!map.containsKey(element))
        throw new MinHeapException(
                "decrease(element, newElement): cannot decrease an element that is not in the MinHeap");

    int index = map.get(element);
    map.remove(element);
    map.put(newElement, index);
    Heap.set(index, newElement);
    moveUp(index, element);
    
    if((this.comparator).compare(newElement, parent(newElement)) >= 0)
        heapify(newElement);
}

private void moveUp(int index, T element) throws MinHeapException {
    while(hasParent(index) && (this.comparator).compare(parent(element), Heap.get(index)) > 0) {
      T i = Heap.get(index);
      swap(i, parent(element));
      index = pIndex(index);
    }
    map.put(Heap.get(index), index);
}

public Object[] toArray(){return Heap.toArray();}
    
}