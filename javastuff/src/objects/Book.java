package objects;

import java.awt.Color;

public class Book {
	private String title;
	private int numberOfPages;
	private Person author;
	private Color jacketColor;
	private boolean wasLitOnFire;
	private int height= (int) Math.floor(Math.random()*100) + 150;
	private int thickness;
	private boolean checkedOut = false;
	private long checkOutDate = 0;
	private long dueDate = 0;
	static public String[] conditions = {"This appears to be a brand new book! How lucky!", "This book is very gently used.","This book is clearly used","This book is starting to fall apart"};
	String description = conditions[0];
	int accumulatedUse = 0;
	
	public String getDescription() {
		return description;
	}

	public int getAccumulatedUse() {
		return accumulatedUse;
	}
	
	public void updateCondition(long timeOfReturn){
		accumulatedUse += (timeOfReturn - checkOutDate);
		description = conditions[accumulatedUse/30000];
	}
	
	public long getSecondsRemaining(){
		return (dueDate - System.currentTimeMillis())/1000;
		
	}
	
	public boolean isCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public long getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(long checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public long getDueDate() {
		return dueDate;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}

	public Book(String title, int numberOfPages, Person author){
		this.title = title;
		this.numberOfPages = numberOfPages;
		thickness = numberOfPages/10;
		this.author = author;
		jacketColor = Color.gray;
		wasLitOnFire = false;
	}
	
	public Color getColor() {
		return jacketColor;
	}
	
	public int getHeight() {
		return height;
	}


	public int getThickness() {
		return thickness;
	}

	public void setJacketColor(Color jacketColor) {
		this.jacketColor = jacketColor;
	}

	public Color getJacketColor() {
		return jacketColor;
	}

	public String toString(){
		if (wasLitOnFire) return title + " " + numberOfPages +" " + "The authors names cannot be made out";
		return title + " " + numberOfPages +" Pages " + author +" " + "Height is " +height;
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getNumberOfPages()
	{
		return numberOfPages;
		
	}

	public Person getAuthor() {
		return author;
	}
	
	public void setOnFire(){
		jacketColor = Color.black;
		title = "The title of this book is too charred to make out";
		wasLitOnFire = true;
	}
}
