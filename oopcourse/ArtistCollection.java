package BitwaNaGlosy;
import java.util.*;
import java.io.*;

//przechowuje wszsytkich artystow podanych w parametrach;
//funkcja wrtieArtists wypisuje artystow w kolejnosci malejacej liczby
//uzytych slow; writeTopWords wypisze dla kazdego artysty 5 najczesciej uzywanych
//przez niego slow - tutaj kolejnosc artystow dowolna;
//jesli artysta nie ma swojego katalogu, to przyjmuje, ze zbior jego slow jest pusty
public class ArtistCollection {
	
	ArrayList<Artist> artists;
	
	ArtistCollection(ArrayList<String> artlist, File source) {
		artists = new ArrayList<Artist>();
		for (int i = 0; i < artlist.size(); i++) {
			System.out.println(artlist.get(i));
			File textDir = new File(source, artlist.get(i));
			if (textDir.exists())
				artists.add(new Artist(artlist.get(i), textDir));
			else
				artists.add(new Artist(artlist.get(i)));
		}
	}
	
	void writeArtists() {
		sortArtists();
		System.out.println("count:");
		for (int i = 0; i < artists.size(); i++)
			System.out.println(artists.get(i).name+" "+artists.get(i).wordcount);
		System.out.println();
	}
	
	void writeTopWords(Filters filter) {
		System.out.println("top5:");
		for (int i = 0; i < artists.size(); i++)
			artists.get(i).writeTop5(filter);
	}
	
	private void sortArtists() {
		Collections.sort(artists, new ArtistComparator());
	}
}
