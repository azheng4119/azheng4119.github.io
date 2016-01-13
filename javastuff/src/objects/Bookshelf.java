package objects;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

public class Bookshelf {



	public static void main(String[] args) {
		Person person1 = new Person("Andy", "Zheng",true);
		Person person2 = new Person("Philip", "Zheng",true);
		ArrayList<Person> libraryCardHolders = new ArrayList<Person>();
		libraryCardHolders.add(person1);
		libraryCardHolders.add(person2);

		Person author1 = new Person("Noah","Webster",true);
		Person author2 = new Person("Andrew","Burgees",true);
		Person author3 = new Person("Philip","K.","Dick",true);

		Book book1 = new Book("Dictionary",1001,author1);
		book1.setJacketColor(Color.cyan);
		Book book2 = new Book("Clockwork Orange",749,author2);
		book2.setJacketColor(Color.orange);
		Book book3 = new Book("Do Androids Dream of Electric Sheep",500,author3);
		book3.setJacketColor(Color.blue);
		Book book5 = new Book("The Miniority Report",589,author3);
		book5.setJacketColor(Color.yellow);

		ArrayList<Book> shelf = new ArrayList<Book>();
		shelf.add(book1);
		shelf.add(book2);
		shelf.add(book3);
		shelf.add(new Book("The man in the high castle",600,author2));
		shelf.get(3).setJacketColor(new Color(255,2,0));
		shelf.add(0,book5);

		sortByAuthor(true,shelf);

		printAll(shelf);

		Library lib = new Library(shelf, libraryCardHolders);//use "books" or "shelf" whatever your ArrayList is 
		lib.setSize(new Dimension(1000,500));
		lib.setVisible(true);
		lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void sortByAuthor(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
			public int compare(Book a, Book b) {
				String aLast = a.getAuthor().getLastName();
				String bLast = b.getAuthor().getLastName();
				if (ascending) return aLast.compareTo(bLast);
				return bLast.compareTo(aLast);

			}
		});
	}

	public static void sortByTitle(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
			public int compare(Book a, Book b) {
				String aLast = a.getTitle();
				String bLast = b.getTitle();
				if (ascending) return aLast.compareTo(bLast);
				return bLast.compareTo(aLast);

			}
		});
	}

	public static void sortByPageNumber(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
			public int compare(Book a, Book b) {
				if (ascending) return b.getNumberOfPages() - a.getNumberOfPages();
				return a.getNumberOfPages() - b.getNumberOfPages();

			}
		});
	}

	public static void sortByHeight(final boolean ascending, ArrayList<Book> shelf){
		Collections.sort(shelf, new Comparator<Book>() {
			public int compare(Book a, Book b) {
				if (ascending) return b.getHeight() - a.getHeight();
				return a.getHeight() - b.getHeight();

			}
		});
	}

	public static void removeWords(String a, ArrayList<Book> b){
		for (int i = 0; i < b.size(); i++){
			if (b.get(i).getTitle().toLowerCase().indexOf(a) >= 0){
				b.remove(i);
				i--;
			}
		}
	}

	private static void printAll(ArrayList<?> arrayList){
		for (int a = 0; a < arrayList.size(); a++){
			System.out.println("The book at number " + a + " is: " + arrayList.get(a));
		}

		//		ArrayList<Book> creepyBooks = new ArrayList<Book>();
		//		for (Book b : shelf){
		//			if (b.getAuthor() == author3){
		//				creepyBooks.add(b);
		//				b.setOnFire();
		//			}
		//		}
	}

}
