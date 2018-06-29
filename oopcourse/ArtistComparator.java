package BitwaNaGlosy;
import java.util.*;

//wersja dostosowana do klasy Artist; sort wywolane z tym komparartorem
//ustawi artystow w kolejnosci malejacej liczby uzytych slow;
public class ArtistComparator implements Comparator<Artist> {
	public int compare(Artist a1, Artist a2) {
		if (a1.wordcount > a2.wordcount)
			return -1;
		else if (a1.wordcount == a2.wordcount)
			return 0;
		else
			return 1;
	}
}
