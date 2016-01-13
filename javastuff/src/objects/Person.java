package objects;

import java.util.ArrayList;

public class Person {
		private String firstName;
		private String lastName;
		private String middleName = "";
		private static int MAX_ALLOWED_BOOKS = 3;
		private boolean male;
		private ArrayList<Book> checkedOutBooks;
		private Balance Balance;
		
		public static int getMAX_ALLOWED_BOOKS() {
			return MAX_ALLOWED_BOOKS;
		}

		public static void setMAX_ALLOWED_BOOKS(int mAX_ALLOWED_BOOKS) {
			MAX_ALLOWED_BOOKS = mAX_ALLOWED_BOOKS;
		}

		public boolean isMale() {
			return male;
		}

		public ArrayList<Book> getCheckedOutBooks() {
			return checkedOutBooks;
		}

//		public static void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
//			Person.checkedOutBooks = checkedOutBooks;
//		}
		
		
		
		public Person(String fName,String lName,boolean male){
			this.firstName = fName;
			this.lastName = lName;
			this.male = male;
			checkedOutBooks = new ArrayList<Book>();
			Balance = new Balance();
		}
		
		public Person(String fName,String middleName, String lName, boolean male){
			this.firstName = fName;
			this.middleName = middleName;
			this.lastName = lName;
			this.male = male;
			checkedOutBooks = new ArrayList<Book>();
			Balance = new Balance();
		}
		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String toString(){
			return firstName + " " + middleName +" " + lastName;
		}
		
		public void checkOutBook(Book book){
			book.setCheckedOut(true);
			book.setCheckOutDate(System.currentTimeMillis());
			book.setDueDate(System.currentTimeMillis()+30000);
			checkedOutBooks.add(book);
		}
		
		public void returnBook(Book book){
			book.setCheckedOut(false);
			book.updateCondition(System.currentTimeMillis());
			Balance.subtractLateFees(book.getDueDate()-System.currentTimeMillis());
			book.setCheckOutDate(0);
			book.setDueDate(0);
			checkedOutBooks.remove(book);
		}
		
		public void renewBook(Book book){
			book.setDueDate(System.currentTimeMillis()+30000);
		}
		
		public String getGenderPosessivePronoun(){
			if (male) return "his";
			return "her";
		}
		
		public String getLibraryDescription(){
			return firstName + " is thinking of borrowing a book" + "Balance = " + Balance.getAmount();
		}

		public Balance getBalance() {
			return Balance;
		}

		public void setBalance(Balance balance) {
			Balance = balance;
		}
		
}
