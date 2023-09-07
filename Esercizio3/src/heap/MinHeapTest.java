package heap;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

public class MinHeapTest {

	class CompareInteger implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    private MinHeap<Integer> heap;
    
	@Test
	public void test_remove_min_val() throws Exception {
		heap = new MinHeap<>(new CompareInteger());
        
        try {
        	heap.insert(24);
        	heap.insert(12);
        	heap.insert(30);
        	heap.insert(1);
        	assertEquals(1, (int)heap.remove());
        	assertTrue(heap.size() == 3);
        
        } 
        catch (Exception e) {
        	System.err.println(e.toString());
        }
  		
	}
	
    @Test
    public void test_size_empty() throws Exception {      
    	heap = new MinHeap<>(new CompareInteger());
        try {
        	assertEquals(0, heap.size());
        
        } 
        catch (Exception e) {
        	System.err.println(e.toString());
        }
    }
    
	@Test
	public void test_size_true() throws Exception {
		heap = new MinHeap<>(new CompareInteger());
        
        try {
        	heap.insert(245);
        	heap.insert(12);
        	heap.insert(30);
        	heap.insert(3);
        	assertTrue(heap.size() == 4);
        
        } 
        catch (Exception e) {
        	System.err.println(e.toString());
        }
  		
	}

	@Test
	public void test_size_false() throws Exception {
		heap = new MinHeap<>(new CompareInteger());
        
        try {
        	heap.insert(245);
        	heap.insert(12);
        	heap.insert(30);
        	heap.insert(3);
        	assertFalse(heap.size() == 2);
        
        } 
        catch (Exception e) {
        	System.err.println(e.toString());
        }
  		
	}
	
	@Test
	public void test_size_remove_element() throws Exception {
		heap = new MinHeap<>(new CompareInteger());
        
        try {
        	heap.insert(30);
        	heap.insert(3);
        	assertTrue(heap.size() == 2);
        	heap.remove();
        	assertTrue(heap.size() == 1);
        
        } 
        catch (Exception e) {
        	System.err.println(e.toString());
        }
  		
	}
	
}