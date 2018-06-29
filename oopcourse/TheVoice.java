package BitwaNaGlosy;
import java.util.*;
import java.io.*;

public class TheVoice {
	// Przyjmuje, ze parametr filters dotyczy TYLKO top5: wszelkie filtrowane
	//wyrazy nadal beda sie liczyly przy zliczaniu liczby wyrazow artysty;
	//count i top5 zmienia wartosc na true, jesli, odpowiednio, count i top5
	//sa parametrami programu; source to katalog, z ktorego pobrane zostana teksty;
	//filterNames przechowuje sciezki do plikow ze slowami, ktorych nie nalezy
	//brac pod uwage przy top5; artistList przechowuje nazwy artstow;
	//filterFiles przechowuje slowa, ktorych nie nalezy brac pod uwage przy top5
	//artists to kolekcja artystow
	public static void main(String[] args) {
		boolean count = false;
		boolean top5 = false;
		File source = null;
		String[] filterNames = null;
		ArrayList<String> artistList = new ArrayList<String>();
		Filters filterFiles = null;
		ArtistCollection artists;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].charAt(0) != '-')
				artistList.add(args[i]);
			else {
				if (args[i].startsWith("--source="))
					source = new File(args[i].substring(9));
				else if (args[i].startsWith("--processors=")) {
					if (args[i].contains("top5"))
						top5 = true;
					if (args[i].contains("count"))
						count = true;
				}
				else if (args[i].startsWith("--filters="))
					filterNames = args[i].substring(10).split(":");
			}
		}
		
		if (!source.exists()) {
			System.out.println("Couldn't find texts directory.");
			return;
		}
		
		if (count || top5) {
			if (filterNames != null)
				filterFiles = new Filters(filterNames);
			artists = new ArtistCollection(artistList, source);
			if (count)
				artists.writeArtists();
			if (top5)
				artists.writeTopWords(filterFiles);
		}
	}

}
