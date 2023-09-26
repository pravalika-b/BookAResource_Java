/**
 * 
 */
package ca.lambtoncollege.TermProject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pravalika Bachugudam
 *
 */

public class BookCache {
	
	private List<Book> storeBooks;
	
	public BookCache() {
		storeBooks = new ArrayList<>();
	}
	
	public void storeBook(Book book) {
		this.storeBooks.add(book);
	}
	
	public List<Book> getAllBooks() {
		return this.storeBooks;
	}

}
