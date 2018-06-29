package BitwaNaGlosy;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

//klasa, w ktorej trzymane sa slowa, ktore maja nie byc liczone przy
// obliczaniu top5;
public class Filters {
	TreeSet<String> forbiddenWords;
	
	Filters(String[] fileNames) {
		forbiddenWords = new TreeSet<String>();
		for (int i = 0; i < fileNames.length; i++) {
			try {
				List<String> lines = Files.readAllLines(Paths.get(fileNames[i]), 
														StandardCharsets.UTF_8);
				System.out.println();
				for (String line : lines)
					forbiddenWords.add(line.toLowerCase());
			}
			catch(IOException e) {
				System.err.println("Couldn't read the file "+fileNames[i]+" .");
			}
		}
	}
}
