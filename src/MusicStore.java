

import java.util.ArrayList;

public class MusicStore {
	private MyHashTable<String,Song> songtitletable = new MyHashTable<String,Song>(100);
	private MyHashTable<String, ArrayList<Song>> songartisttable = new MyHashTable<String,ArrayList<Song>>(100);
	private MyHashTable<Integer, ArrayList<Song>> yearsongtable = new MyHashTable<Integer,ArrayList<Song>>(100);
	public MusicStore(ArrayList<Song> songs) {

		for (Song eachsong:songs) {
			songtitletable.put(eachsong.getTitle(), eachsong);
		}

		for (Song eachsong:songs) {
			int songyear= eachsong.getYear();
			if (yearsongtable.get(songyear)==null || yearsongtable.get(songyear).size()==0) {
				ArrayList<Song> yeararray = new ArrayList<Song>();
				yeararray.add(eachsong);
				yearsongtable.put(songyear, yeararray);
			}else {
				yearsongtable.get(songyear).add(eachsong);
			}
		}
		for (Song eachsong:songs) {
			String artistname=eachsong.getArtist();

			if (songartisttable.get(artistname)==null || songartisttable.get(artistname).size()==0) {
				ArrayList<Song> arrayofsongs = new ArrayList<Song>();
				arrayofsongs.add(eachsong);
				songartisttable.put(artistname, arrayofsongs);
			}else {
				songartisttable.get(artistname).add(eachsong);
			}
		}



	}




	/**
	 * Add Song s to this MusicStore
	 */
	public void addSong(Song s) {
		
		songtitletable.put(s.getTitle(), s);
		String artistname = s.getArtist();
		if(this.songartisttable.get(artistname)==null) {
			ArrayList<Song> arrayofsongs = new ArrayList<Song>();
			arrayofsongs.add(s);
			songartisttable.put(artistname, arrayofsongs);
		}else {
			songartisttable.get(artistname).add(s);
		}	

		int songyear= s.getYear();
		if(yearsongtable.get(songyear)==null || yearsongtable.get(songyear).size()==0 ) {
			ArrayList<Song> yeararray = new ArrayList<Song>();
			yeararray.add(s);
			yearsongtable.put(songyear, yeararray);
		}else {
			yearsongtable.get(songyear).add(s);
		}

	}


	/**
	 * Search this MusicStore for Song by title and return any one song 
	 * by that title 
	 */
	public Song searchByTitle(String title) {
		/*which takes a String as input and returns the Song with the provided title. 
		 * If there are multiple songs with the same title, you may return any one of them. 
		 * Note that the input should match exactly the Song title. This method should be O(1). */

		return this.songtitletable.get(title);
	}

	/**
	 * Search this MusicStore for song by `artist' and return an 
	 * ArrayList of all such Songs.
	 */
	public ArrayList<Song> searchByArtist(String artist) {

		return songartisttable.get(artist);
	}

	/**
	 * Search this MusicSotre for all songs from a `year'
	 *  and return an ArrayList of all such  Songs  
	 */
	public ArrayList<Song> searchByYear(Integer year) {

		return yearsongtable.get(year);

	}
}
