
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random; 
/**
 * Class for testing your implementation of the HashTable class.
 * VERSION 1.1 Basic test modified to be more informative
 * VERSION 1.2 Stress put test added
 * VERSION 1.3 Stress tests for put,get and remove added and combined
 * VERSION 1.4 Iterator tests for correctness and efficiency implemented
 * VERSION 1.5 Fixed bugs and improved display.
 * VERSION 1.6 Fixed Iterator bugs and improved feedback 
 * VERSION 1.7 Improved Iterator tester
 */
public class HashTableTester2 {
    
    /**
     * Returns a list of songs to use for testing the hash table.
     * @return A list of songs to use for testing the hash table
     */
    private static ArrayList<Song> initSongList() {
        ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Le Premier Bonheur du Jour", "Os Mutantes", 1968));
        songs.add(new Song("Stretch Out And Wait", "The Smiths", 1987));
        songs.add(new Song("Scream", "Black Flag", 1984));
        songs.add(new Song("Europe, After the Rain", "Max Richter", 2002));
        songs.add(new Song("Why Are You Looking Grave?", "Mew", 2005));
        songs.add(new Song("Fallen Angel", "King Crimson", 1974));
        songs.add(new Song("The Song Of Ice and Fire", "Rhaegar", 1974));
        songs.add(new Song("The Bear and The Maiden Fair", "Tom O'Sevens", 2011));
        songs.add(new Song("The Dornishman's Wife", "Tom O'Sevens", 2011));
        songs.add(new Song("The Burning of the Ships", "Tom O'Sevens", 2011));
        songs.add(new Song("Jenny of Oldstones", "Tom O'Sevens", 2011));
        songs.add(new Song("King Without courage", "Tom O'Sevens", 2011)); 
        songs.add(new Song("The Mother's Tears", "Tom O'Sevens", 2011));
        songs.add(new Song("The Rains of Castermere", "Tom O'Sevens", 2011));
        songs.add(new Song("Two hearts that beat as one", "Tom O'Sevens", 2011));
        songs.add(new Song("Wolf in the Night", "Tom O'Sevens", 2011));
        songs.add(new Song("Fallen Leaves", "Marillion", 2002));
        songs.add(new Song("Flowers of springs", "Marillion", 2002));
        songs.add(new Song("On a Misty Morn", "Marillion", 2002));
        songs.add(new Song("Her Little Flower", "Dareon", 2001));
        songs.add(new Song("Milady's Supper", "Dareon", 2001));
        songs.add(new Song("Rat Cook", "Dareon", 2001));
        songs.add(new Song("The Night that ended", "Dareon", 2001));
        songs.add(new Song("When Willum's Wife was wet", "Dareon", 2001));
        songs.add(new Song("A thousad eyes and one", "Dareon", 2001));
        
        
        return songs;
    }
    
    /**
     * A method to create a list of songs
     * @param limit
     * @return
     */
    private static ArrayList<Song> multiSongList(int limit) { 
    	ArrayList<Song> songs = new ArrayList<Song>(); 
    	int max = 0; // max number of songs created
    	
    	if(limit < 10) { // ensure at least 1000 songs are created
    		max = 10; 
    	}
    	else { 
    		max = limit; 
    	}
    	
    	
    	// populate all songs
    	for(int i=0; i < max; i++) { 
    
    		String title = "Title #" + i;// create title
    		String author = "Author #" + i; // create author
    		int randYear = randomInt(1980,2010);// create year     		
    		Song song = new Song(title,author,randYear); // create song
    		songs.add(song); 
    	}
    	
    	return songs; 
    }
    

    /**
     * Creates a list og songs
     * @param numOfSongs
     * @param display
     * @return songsTable
     */
    private static MyHashTable<String,Song> multiSongList(int numOfSongs, boolean display) { 
  
    	Random randInt = new Random(100); // to always ge the same sequence
    	// put the songs
    	MyHashTable<String,Song> songsTable = new MyHashTable<String,Song>(numOfSongs); // init with max buckets
    	// put the songs
    	for(int i=0; i < numOfSongs; i++) { 
    		String title = "Title #" + i;// create title
    		String author = "Author #" + i; // create author
    		int randYear = randInt.nextInt((2010-1980)+1)+1980; // year within [1980,2010]   		
    		Song song = new Song(title,author,randYear); // create song
    		songsTable.put(title, song); // put the song
    		
    		if(display) { 
    			System.out.println("ADDED: " + song); 
    		}
    	}
    	
    	return songsTable; 
    }
     
    /**
     * Stress test the put method
     * @param numOfSongs
     * @param display If activated, more information will be printed
     * @return
     */
    private static MyHashTable<String,Song> putStressTest(int numOfSongs, boolean display) {  
    	
    	Random randInt = new Random(100); // to always get the same sequence
    	String results = ""; 
    	
    	results += "Putting "+ numOfSongs + " songs..."; 
    	MyHashTable<String,Song> songsTable = new MyHashTable<String,Song>(numOfSongs); // init with max buckets
    	// put the songs
    	for(int i=0; i < numOfSongs; i++) { 
    		String title = "Title #" + i;// create title
    		String author = "Author #" + i; // create author
    		int randYear = randInt.nextInt((2010-1980)+1)+1980; // year within [1980,2010]   		
    		Song song = new Song(title,author,randYear); // create song
    		songsTable.put(title, song); // put the song
    		
    		if(display) { 
    			System.out.println("ADDED: " + song); 
    		}
    	}
    	results += "Put " + numOfSongs + " songs test passed"; 
    	System.out.println(results);
    	    	
    	return songsTable; 
    }
    
    /**
     * Stress tests the get method
     * @param songsTable
     * @param display If activated, more information will be printed
     * @return
     */
    private static String getStressTest(MyHashTable<String,Song> songsTable, boolean display) { 
    	
    	int numOfSongs = songsTable.size(); 
    	String results = ""; 
    	results += "Getting " + numOfSongs + " songs..."; 
    	for(int i=0; i < songsTable.size(); i++) { 
    		String title = "Title #" + i; 
    		if(display) { 
        		System.out.println(songsTable.get(title));
    		} 
    		else { 
        		songsTable.get(title); 
    		}
    	}
    	results += "Get " + numOfSongs + " songs test passed"; 
    	System.out.println(results);
    	
    	return results; 
    }
    
    /**
     * Stress tests removal as well as efficiency
     * @param songsTable
     * @param display If activated, more information will be printed
     * @return
     */
    private static String removeStressTest(MyHashTable<String,Song> songsTable, boolean display) { 
    	
    	int numOfSongs = songsTable.size(); 
     	int initialSize = songsTable.size(); 
    	String results = ""; 
    	if(display) { 
        	results += "Table initial size: " + initialSize + "\n"; 
    	}
     	results += "Removing " + numOfSongs + "songs..."; 
    	for(int i=0; i < initialSize; i++) { 
    		String title = "Title #" + i; 
    		if(display) { 
        		System.out.println("SONG: " + songsTable.get(title));
        		songsTable.remove(title); 
        		if(songsTable.get(title) == null) { 
        			System.out.println("REMOVED = " + true);
        		}
        		else { 
        			System.out.println("REMOVED = " + false);
        		}
   		}
    		else { 
        		songsTable.remove(title);  
    		}
    	}
    	results += "Remove " + numOfSongs + " songs test passed"; 
    	if(songsTable.size() != 0) { 
    		System.out.println("WARNING: Not all songs were removed");
    		System.out.println("Table size: " + songsTable.size());
    	}
    	System.out.println(results);
    	
    	return null; 
    }
    
    private static ArrayList<String> getKeysTest(int numOfSongs, boolean display) { 
    	
    	Random randInt = new Random(100); // to always get the same sequence
    	String result = "";
    	int size = numOfSongs; 
    	result+= "\n***TEST: getKeys() Stress Test***\n\n"; 
    	result+= "NOTE: This test tests both correctness and efficiency\n\n"; 
    	MyHashTable<String,Song> songsTable = new MyHashTable<String,Song>(numOfSongs); // init with max buckets
    	ArrayList<String> expectedKeys = new ArrayList<String>(numOfSongs); // collect the expected keys
    	ArrayList<String> obtainedKeys = new ArrayList<String>(); // collect the expected keys
      	int numOfKeysExpected = numOfSongs; 
    	int numOfKeysObtained = 0; 
    	result += "Putting "+ numOfSongs + " songs..."; 
    	result += "Expected key list created\n"; 
    	for(int i=0; i < numOfSongs; i++) { 
    		String title = "Title #" + i;// create title
    		String author = "Author #" + i; // create author
    		int randYear = randInt.nextInt((2010-1980)+1)+1980; // year within [1980,2010]   		
    		Song song = new Song(title,author,randYear); // create song
    		expectedKeys.add(title); // add the title to the expected keys
    		songsTable.put(title, song); // put the song
    		if(display) { 
    			System.out.println("ADDED: " + song); 
    		}
    		numOfKeysExpected ++; 
    	}
    	obtainedKeys = songsTable.keys(); 
    	numOfKeysExpected = obtainedKeys.size(); 
    	Collections.sort(expectedKeys); 
    	Collections.sort(obtainedKeys);
    	if(expectedKeys.size() != obtainedKeys.size()) { 
    		result += "WARNING: Number of keys doesn't match\n"; 
    		result += "Number of expected keys: " + expectedKeys.size() + "\n"; 
    		result += "Number of keys obtained: " + obtainedKeys.size() +  "\n"; 
    		result += "Correctness failed\n";  
    	}
    	else {
    		result += "Number of expected keys: " + expectedKeys.size() + "\n"; 
    		result += "Number of keys obtained: " + obtainedKeys.size()  + "\n"; 
    		result += "Number of keys are equal\n";  
    	}
    	if(!expectedKeys.equals(obtainedKeys)) { 
    		result += "WARNING: Obtained doesn't match\n"; 
    		if(display) {  
    			result += "Expected Keys: \n";  
    			for(String key: expectedKeys) {
    				result += key + "\n"; 
    			}
        		result += "Obtained Keys: \n"; 
        		for(String key: obtainedKeys) {
    				result += key + "\n"; 
    			}
    		}
    		result += "Correctness failed\n";  
    	}
    	else { 
    		if(display) {  
    			result += "Expected Keys: \n"; 
    			for(String key: expectedKeys) {
    				result += key + "\n"; 
    			}
        		result += "Obtained Keys: \n"; 
        		for(String key: expectedKeys) {
    				result += key + "\n"; 
    			}
    		}
    		result += "Correctness passed\n"; 
    	}
    	
    	//***values test***
    	
 
    	
    	
    	System.out.println(result);
    	
    	return null; 
    }
    
    private static void getValuesTest(int numOfSongs, boolean display) { 
    	
    	Random randInt = new Random(100); // to always get the same sequence
    	String result = ""; 
    	result += "\n***TEST: getValues() Stress Test***\n\n";
    	MyHashTable<String,Song> songsTable = new MyHashTable<String,Song>(numOfSongs); // init with max buckets
     	ArrayList<Song> expectedSongs = new ArrayList<Song>();
    	ArrayList<Song> obtainedSongs = new ArrayList<Song>();
    	result += "Creating table with " + numOfSongs + " songs...\n"; 
    	result += "Note that repeated values will be put into the HashMap\n"; 
     	for(int i=0; i < numOfSongs; i++) { 
    		String title = "Title #" + i;// create title
    		String author = "Author #" + i; // create author
    		String author2 = "Author #" + i; // create author
    		String author3 = "Author #" + i; // create author
    		int randYear = randInt.nextInt((2010-1980)+1)+1980; // year within [1980,2010]   
    		Song song = new Song(title,author,randYear); // create song
    		Song song1 = new Song(title,author2,randYear); // create song
    		Song song2 = new Song(title,author3,randYear); // create song

    		expectedSongs.add(song); // add the song to expected songs
    		songsTable.put(title, song); // put the song
    		songsTable.put(title, song); // put the song
    		songsTable.put(title, song); // put the song
    		songsTable.put(title, song1); // put the song
    		songsTable.put(title, song1); // put the song
    		songsTable.put(title, song1); // put the songsongsTable.put(title, song); // put the song
    		songsTable.put(title, song2); // put the song
    		songsTable.put(title, song2); // put the song
    		songsTable.put(title, song2); // put the song
    		
    		if(display) { 
    			result += "ADDED: " + song + "\n";  
    			result += "ADDED: " + song1 + "\n"; 
    			result += "ADDED: " + song2 + "\n";  

    		}
    	}
     	obtainedSongs = songsTable.values();
        result += "table size: " +  songsTable.size() + "\n";
        result += "obtainedSongs values size: " + obtainedSongs.size() + "\n"; 
        result += "Expected size: " + numOfSongs + "\n"; 
        result += "Comparing equality of values....\n"; 
        boolean equal = true; 
        try { 
        	  for(int i=0; i < numOfSongs; i++) {  
              	Song song1 = expectedSongs.get(i); 
              	Song song2 = expectedSongs.get(i);
              	boolean sameTitle = song1.getTitle().equals(song2.getTitle()); 
              	boolean sameArtist = song1.getArtist().equals(song2.getArtist());
              	boolean sameYear = song1.getYear() == song2.getYear(); 
              	if(!(sameTitle && sameArtist && sameYear)) {  
              		equal = false; 
              	}
              	
              }
        } catch (NullPointerException e) { 
        	result += "WARNING: Table size not equal\n"; 
        	result += e; 
        	if(display) {  
        		result += e.getMessage(); 
            	result += e.getStackTrace(); 
        	}
        	result += "Test failed"; 
        }
        
        if(equal) { 
        	result += "Equality test passed"; 
        }
        System.out.println(result);
    }
    
    
 
    /**
     * Validates the functionality of the iterator in the MyHashTable class
     * @param songTable
     * @return
     */
    private static String iteratorStressTest(MyHashTable<String, Song> songTable, boolean display) { 
    	
    	String result = ""; 
    	Song song = null; 
    	int size = songTable.size(); 
    	
    	result+= "\n***TEST: Iterator Stress Test***\n\n"; 
    	result+= "NOTE: This test tests both correctness and efficiency\n\n"; 
    			
    	result += "Creating song list (n = " + size + ")...\n";
    	result += "initalizing songTable (n = " + size +  ")...\n"; 
    	result += "Initializing Iterator O(n)...\n"; 
    	Iterator<HashPair<String,Song>> iter1 = songTable.iterator(); // create iterator
    	result+= "Passed\n"; 
    	int count = 0; 
    	result += "Iterating through the songTable n*O(1)...\n"; 
       	try {
    	   	while(iter1.hasNext()) { // tests the hasNext function
        		song = iter1.next().getValue(); 
        		count++; 
        		if(display) { 
        			result += song + "\n"; 
        		}
        	}
    	}catch( Exception e) { 
    		System.out.println("ERROR WAS CATCHED");
    		e.printStackTrace();
    		System.out.println(e.getLocalizedMessage());
    		System.out.println(e.getCause());
    	}
       	try { 
       		iter1.next(); 
       	}
       	catch(Exception e) {     
       		result += "\nEXPECTED ERROR CATCHED: \n"; 
       		result += "This error was intended to happen\n";
       		result += e + "\n\n";
    		
//    		if(display) { 
//        		e.printStackTrace();
//    		}
    		
       	}
    	result += "hasNext() and next() tests passed\n" ; 
    	if(count != songTable.size()) { 
    		result += "WARNING: iterator did not go over all the elements in the table\n"; 
    		result += "Elements in table: " + songTable.size() ; 
    		result += "Elements iterated: " + count; 
    		result += "Correctness failed"; 
    	}
    	else { 
    		result += "Iterator sucessfully went over all the elements in the table\n"; 
    		result += "Elements in table: " + songTable.size() + "\n" ; 
    		result += "Elements iterated: " + count + "\n"; 
    		result += "Correctness passed\n"; 
    		result += "Efficiency passed "; 
    	}
    	
    	return result; 
    }
    
    private String musicStoreTest() { 
    	
    	ArrayList<Song> songs = multiSongList(10000000); // generate ten millions of songs
    	MusicStore myStore = new MusicStore(songs); // initialize MusicStore Object
    	
    	return null; 
    }
    
    // helper method
    /**
     * Generates a random number between input lower and upper limit, both included. 
     * @param lower
     * @param upper
     * @return random int in [lower,upper]
     */
    private static int randomInt(int lower, int upper) { 
    	int x = lower; 
    	int y = upper;
    	return (int) (Math.random()*(y-x+1) + x) ; 
    }
    
    public static void main(String[] args) {
        ArrayList<Song> songs = initSongList();
        MyHashTable<String,Song> songTable;
        int numBuckets = 7;
        // Initialize the hash table.   Key will be the song title.
        
        songTable = new MyHashTable<String,Song>(numBuckets);
        System.out.println("New MyHashtable created.....");
        System.out.println("Putting basic songs...\n");
        for (Song song: songs) {
            songTable.put(song.getTitle(), song);
        }
        
        System.out.println("Number of songs in the table: " + songTable.size());
        System.out.println("Number of buckets in the table: " + songTable.numBuckets());
        System.out.println("songTable: " + songTable.toString());
        
        
        // Try to retrieve a song
        StringBuffer errors = new StringBuffer();
        StringBuffer passed = new StringBuffer(); // to store the passed tests
        errors.append("\n*** MyHashTable Section***\n\n"); 
        passed.append("\n*** MyHashTable Section***\n\n"); 
        
        Song testSong0 = songTable.get("Scream");
        System.out.println(testSong0);
        if (testSong0 == null || !testSong0.getArtist().equals("Black Flag") || testSong0.getYear() != 1984) {
            errors.append("***TEST: Song Retrieving*** \n");
        	errors.append("Failed to retrieve song 'Scream'.\n\n");
        }
        else { 
            passed.append("***TEST: Song Retrieving***\n"); 
        	passed.append("Test song 'Scream' retrieved successfully.\n"); 
        	passed.append("Passed Song retrieving test 1.\n\n"); 
        }
        
        //  rehashing changes the capacity of the table, but not the number of entries
        Integer oldBucketCount = songTable.numBuckets();
        Integer oldSize = songTable.size();
        songTable.rehash();
        Integer newBucketCount = songTable.numBuckets();
        if( 2*oldBucketCount != newBucketCount || oldSize != songTable.size()){
        	errors.append("***TEST: Rehashing Test:*** \n"); 
        	errors.append("New bucket count = " + newBucketCount + "\n" );
            errors.append("Expected bucket count = " + 2*oldBucketCount + "\n");
            errors.append("New size = " + songTable.size() + "\n" );
            errors.append("Expected size = " + oldSize + "\n");
            passed.append("Failed Rehashing Test \n\n"); 
        }
        else{ 
        	passed.append("***TEST: Rehashing Test:*** \n"); 
            passed.append("New bucket count = " + newBucketCount + "\n" );
            passed.append("Expected bucket count = " + 2*oldBucketCount + "\n");
            passed.append("New size = " + songTable.size() + "\n" );
            passed.append("Expected size = " + oldSize + "\n");
            passed.append("Passed Rehashing Test \n\n"); 
        }
        
        // Try to retrieve a song
        Song testSong1 = songTable.get("Scream");
        System.out.println(testSong1);
        if (testSong1 == null || !testSong1.getArtist().equals("Black Flag") || testSong1.getYear() != 1984) {
            errors.append("***TEST: Retrieve and removal:*** \n"); 
        	errors.append("Failed to retrieve song 'Scream'.\n");
        }
        else {
            passed.append("***TEST: Retrieve and removal:*** \n"); 
        	passed.append("Song 'Scream' retrieved sucessfully.\n"); 
        // Try to remove a song
        Song removedSong = songTable.remove("Fallen Angel");
        Song retrievedSong = songTable.get("Fallen Angel");
        if (removedSong == null || !removedSong.getArtist().equals("King Crimson") || removedSong.getYear() != 1974 || retrievedSong != null) {
            errors.append("Failed to remove song 'Fallen Angel'.\n");
        }
        else { 
        	passed.append("Sond 'Fallen Angel' removed sucessfully"); 
        }
        
        }
        
        //***************** Music Store Basic Checks *****************//
        errors.append("\n*** MusicStore Section***\n\n"); 
        passed.append("\n\n*** MusicStore Section***\n\n"); 
        
        MusicStore store = new MusicStore(initSongList());
        ArrayList<Song> songsByTom = store.searchByArtist("Tom O'Sevens");
        if(songsByTom.size() != 9){
        	errors.append("***TEST: 9 Songs retrieval:***\n"); 
            errors.append("Failed to retrieve all 9 songs by Tom of sevenstreams\n");
        } 
        else { 
        	passed.append("***TEST: 9 Songs retrieval:***\n"); 
        	passed.append("All 9 songs by Tom of sevenstreams retrieved successfully\n"); 
        }
        System.out.println(songsByTom.size() + " songs by Tom :");
        songsByTom.forEach(song -> System.out.println("\t" + song));
        
        ArrayList<Song> songsFrom2002 = store.searchByYear(2002);
        if(songsFrom2002.size() != 4){
        	errors.append("\n***TEST: 4 Songs retrieval:***\n"); 
            errors.append("Failed to retrieve all 4 songs from 2002\n");
        }
        else { 
        	passed.append("\n***TEST: 4 Songs retrieval:***\n"); 
        	passed.append("All 4 songs from 2002 successfully retrieved\n");
        }
        
        System.out.println(songsFrom2002.size() + " songs from 2002: ");
        songsFrom2002.forEach(song -> System.out.println("\t" + song));
        
        
        //   PUT MORE TESTS HERE.
        
        // Display the test results
        System.out.println("\n---------------\nTEST 1: CORRECTNESS RESULTS:\n---------------\n");
        System.out.println("ERRORS LOG: \n");
        System.out.println(errors.toString());
        System.out.println("\nPASSED TESTS LOG:\n");
        System.out.println(passed.toString());
        System.out.println();
        
        
        System.out.println("---------------\nTEST 2: STRESS TESTS RESULTS:\n---------------\n");
                   
     	
    	System.out.println("WARNING: Tests must be completed in less than 30 sec");
    	System.out.println("WARNING: These tests don't test correctness\n");

        
    	System.out.println("***TEST: put() Stress Test O(1)***\n");
    	MyHashTable<String,Song> songsTable = putStressTest(10,false); 
    	MyHashTable<String,Song> songsTable2 = putStressTest(100,false); 
    	MyHashTable<String,Song> songsTable3 = putStressTest(500,false); 
    	MyHashTable<String,Song> songsTable4 = putStressTest(1000,false); 
    	MyHashTable<String,Song> songsTable5 = putStressTest(10000,false); 
    	MyHashTable<String,Song> songsTable6 = putStressTest(100000,false); 
    	MyHashTable<String,Song> songsTable7 = putStressTest(1000000,false); 
    	System.out.println();

    	System.out.println("***TEST: get() Stress Test O(1)***\n");
    	getStressTest(songsTable,false); 
    	getStressTest(songsTable2,false); 
    	getStressTest(songsTable3,false); 
    	getStressTest(songsTable4,false); 
    	getStressTest(songsTable5,false); 
    	getStressTest(songsTable6,false); 
    	getStressTest(songsTable7,false); 
    	System.out.println();
    	
    	System.out.println("***TEST: remove() Stress Test O(1)***\n");
    	removeStressTest(songsTable,false); 
    	removeStressTest(songsTable2,false); 
    	removeStressTest(songsTable3,false); 
    	removeStressTest(songsTable4,false); 
    	removeStressTest(songsTable5,false); 
    	removeStressTest(songsTable6,false); 
    	removeStressTest(songsTable7,false);     
    	
    	getKeysTest(10,true); 
    	getValuesTest(10,false); 
    	
    	songsTable = multiSongList(10,false); 
        System.out.println(iteratorStressTest(songsTable,true));
        
    
    }

   
}
