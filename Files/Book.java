
public class Book {
	private String title;
	private String author;
	private String ISBN;
	private int year;
	private boolean isBorrowed;
	//Constructor
	public Book(String title, String author, String ISBN, int year ) {
		
		this.title=title;
		this.author=author;
		this.ISBN=ISBN;
		this.year=year;
		this.isBorrowed =false;
		
	}
	
	
	//getters and setters
	public String getTitle() {
		return title;
		
	}
	public void setTitle(String title) {
		this.title=title;
		}
	public String getAuthor() {
		return author;
		
	}
	public void setAuthor(String author) {
		this.author=author;
		}
	public String getISBN() {
		return ISBN;
		
	}
	public void setISBN(String ISBN) {
		this.ISBN=ISBN;
		}
	public int getYear() {
		return year;
		
	}
	public void setYear(int year) {
		this.year=year;
		}
	
	public boolean isBorrowed() {
		return isBorrowed;
		
	}
	public void setBorrowed(boolean borrowed) {
		this.isBorrowed=borrowed;
		
	}
	@Override
    public String toString() {
        return title + " by " + author + " (" + year + ") - ISBN: " + ISBN +
               (isBorrowed ? " [Borrowed]" : " [Available]");
    }

}
