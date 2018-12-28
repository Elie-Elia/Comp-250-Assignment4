

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        numBuckets = initialCapacity;
        numEntries=0;
        buckets = new ArrayList<LinkedList<HashPair<K,V>>>(initialCapacity);
        for(int i=0 ; i<initialCapacity ; i++){
            buckets.add(new LinkedList<HashPair<K,V>>());
        }
        // System.out.println("the size is "+buckets.size() + " for a capacity of " + initialCapacity );
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
    	
    	int hashcode = hashFunction(key);
    	LinkedList<HashPair<K,V>> hashpairlist = buckets.get(hashcode);
    	HashPair<K,V> hashtoadd = new HashPair<K,V>(key,value);
    	
    	for (HashPair<K,V> hashpair : hashpairlist) {
    		if (hashpair.getKey().equals(key)){
    			V oldvalue=hashpair.getValue();
    			hashpair.setValue(value);
    			return oldvalue;
    		}
    	}
    	numEntries++;
    	buckets.get(hashcode).add(hashtoadd);
    	double loadfactor = ((double)numEntries)/numBuckets;
    	if (loadfactor >= MAX_LOAD_FACTOR) {
    		this.rehash();
    	}
    	return null;
    	}
        
    
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        int hashCode = hashFunction(key);
        
        
        Iterator<HashPair<K, V>> iterator = buckets.get(hashCode).iterator();
        while(iterator.hasNext()){
            HashPair<K,V> current = iterator.next();
            if(current.getKey().equals(key))
                return current.getValue();
        }
        return null;  
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
    	/*A remove() method that takes a key as input and removes from the table the entry (i.e.
    	the HashPair) associated to this key. The method should return the value associated to
    			the key. If the key is not found, then the method returns null. This method should run
    			in O(1) on average.*/

    	V value = null;
    
        int hashcode = hashFunction(key);
        Iterator<HashPair<K, V>> iterator = buckets.get(hashcode).iterator();
        if (buckets.get(hashcode).isEmpty()==false) {
        	 while(iterator.hasNext()){
                 HashPair<K,V> current = iterator.next();
                 if(current.getKey().equals(key)) {
                	 value=current.getValue();
                	 iterator.remove();
                	 numEntries--;
        
                 }
                 
        	 }
        }
        	
        
        return value;
        
        
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
    	
//    	/*A rehash() method which takes no input and modifies the table so that it contains
//double the number of buckets. This method should be O(n) where n the number of
//entries in the table.*/
//    	
    	
//    	/*A rehash() method which takes no input and modifies the table so that it contains
//double the number of buckets. This method should be O(n) where n the number of
//entries in the table.*/
//    	
    	
    	int newbucks =  numBuckets*2;
    	
    	ArrayList<LinkedList<HashPair<K, V>>> rehash = new ArrayList<LinkedList<HashPair<K,V>>>(newbucks);
    	 for(int k=0 ; k<newbucks ; k++){
             rehash.add(new LinkedList<HashPair<K,V>>());
         }
    	for (int i=0;i<numBuckets;i++) {
    		if(buckets.get(i).size() > 0) {
    		Iterator<HashPair<K, V>> iterator = buckets.get(i).iterator();
            while(iterator.hasNext()){
                HashPair<K,V> currentpair = iterator.next();
                int hashValue = Math.abs(currentpair.getKey().hashCode()) %newbucks;
                rehash.get(hashValue).add(currentpair);
                     }
    		}
    	}
    	buckets=rehash;
    	numBuckets=buckets.size();
    	
    }
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
    	/* A keys() method which takes no input and returns an ArrayList containing all the keys in the table. The keys in the returned ArrayList may be in any order. This method
should be O(n) where n the number of entries in the table. */
        ArrayList<K> keys = new ArrayList<K>();
        //Arraylist called keys to store the keys
        
        //for every element in the arraylist
        for (int i=0;i<buckets.size();i++){
        	//initialize an iterator over the current index in the bucket arraylist
        	Iterator<HashPair<K, V>> iterator = buckets.get(i).iterator();
        	if (buckets.get(i).isEmpty() == false) {
        		
        		while(iterator.hasNext()) {
        			HashPair<K,V> current = iterator.next();
        			keys.add(current.getKey());
        		}		
        }
        }
        return keys;
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
    	
    	
    	MyHashTable<V, Integer> tableofvalues = new MyHashTable<V, Integer>(numEntries);

		for (int j = 0; j < numBuckets; j++) {
			if (buckets.get(j).size() > 0) {
				Iterator<HashPair<K, V>> thisiterator = buckets.get(j).iterator();
				while (thisiterator.hasNext()) {
					HashPair<K, V> currentpair = thisiterator.next();
					tableofvalues.put(currentpair.getValue(), 0);
				}
			}
		}

		ArrayList<V> values = new ArrayList<V>();
		values = tableofvalues.keys();

		return values;
	}

    
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> list;
        
        
        private MyHashIterator() {
        	list = new LinkedList<HashPair<K,V>>();
        	for (LinkedList<HashPair<K,V>> pairs : buckets) {
        		for (HashPair<K,V> eachpair:pairs) {
        			list.add(eachpair);
        		}
        	}
        }
        
        @Override
        public boolean hasNext() {
        	
        	
           boolean hasnext = !list.isEmpty();
           return hasnext;
        }
        
        @Override
        public HashPair<K,V> next() {
        	
        	
        	
            HashPair<K,V> pair = list.removeFirst();
            return pair;
        }
        
    }
}
