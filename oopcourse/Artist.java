package BitwaNaGlosy;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

// konstruktor wczytuje slowa z dokumentow tekstowych do mapy, w ktorej
// klucze to slowa, a wartosci to liczby wystapien tych slow
public class Artist {
	String name;
	int wordcount;
	private HashMap<String, Integer> words;
	
	//ten konstruktor jest wywolany dla artysty, dla ktorego nie ma katalogu
	Artist(String artname) {
		name = artname;
		wordcount = 0;
		words = new HashMap<String, Integer>();
	}
	
	Artist(String artname, File source) {
		File[] listOfFiles = source.listFiles();
		
		wordcount = 0;
		name = artname;
		words = new HashMap<String, Integer>();
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				try {
					byte bytes[] = Files.readAllBytes(file.toPath());
					String text = new String(bytes, StandardCharsets.UTF_8);
					String[] textWords = text.split("[\\s,.;:?!()-]+");
					for (int j = 0; j < textWords.length; j++) {
						String word = textWords[j].toLowerCase();
						if (words.containsKey(word))
							words.put(word, words.get(word)+1);
						else {
							words.put(word, 1);
							wordcount++;
						}
					}
				} catch(IOException e) {
					System.err.println("Couldn't read the file "+file.getName()+" .");
				}
			}
		}
	}
	//wyszukuje i wypisuje 5 najbardziej popularnych slow danego artysty;
	//funkcja przechodzi po wszystkich wartosciach z mapy words i wstawia do 
	//5-elementowej tablicy jesli dany wyraz jest wsrod 5 najczesciej wystepujacych
	//dotad przejrzanych;
	void writeTop5(Filters filter) {
		String[] top5 = new String[5];
		
		for (String key : words.keySet()) {
			int i = 0;
			boolean inserted = false;
			if (filter == null || !filter.forbiddenWords.contains(key)) {
				while (i < 5 && !inserted) {
					if (top5[i] == null) {
						top5[i] = key;
						inserted = true;
					}
					else if (words.get(key) > words.get(top5[i])) {
						insert(top5, key, i);
						inserted = true;
					}
					else if (words.get(key) == words.get(top5[i]) && 
							key.compareTo(top5[i]) > 0) {
						insert(top5, key, i);
						inserted = true;
					}
					i++;
				}
			}
		}
		System.out.println(name+":");
		for (int i = 0; i < 5; i++)
			if (top5[i] != null)
				System.out.println(top5[i]+"="+words.get(top5[i]));
		System.out.println();
	}
	
	// wstawia w odpowiednie miejsce do tablicy i wszystkie na prawo od niej
	// przesuwa o 1 w prawo
	private void insert(String[] top5, String s, int index) {
		for (int i = 4; i > index; i--)
			top5[i] = top5[i-1];
		top5[index] = s;
	}
}
