/**
 * 
 */
package ca.lambtoncollege.TermProject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Pravalika Bachugudam
 *
 */
@WebMvcTest(BookController.class)
public class BookControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookCache bookCache;
	
	@MockBean
	private BookRepository bookRepo;
	
	@MockBean
	private PageCounter pageCounter;
	
	@Test
	public void addBookTest() throws Exception {
		mockMvc.perform(get("/addnewBook"))
		//.andDo(print());	
		.andExpect(status().isOk())
		.andExpect(view().name("addBook"))
		.andExpect(model().attribute("count", pageCounter.getCurrentPageCount()))
		.andExpect(model().attribute("bookscount", this.bookRepo.count()))
		.andExpect(model().attribute("book", new Book()));
	}
	
	@Test
	public void addPostTest() throws Exception {
		Book expectedBook = new Book(null, "Half Girlfriend", "Chetan Bhagat", "Romance");
		mockMvc.perform(post("/addnewBook").param("bookName", "Half Girlfriend").param("authorName", "Chetan Bhagat").param("genre", "Romance"))
		.andExpect(status().isOk())
		.andExpect(view().name("saved"))
		.andExpect(model().attribute("book", expectedBook));
		
		Mockito.verify(bookRepo).save(eq(expectedBook));
	}
	
	@Test
	public void addPostTest_InvalidBook() throws Exception {
		Book expectedBook = new Book(null, "", "C", "Romance");
		
		mockMvc.perform(post("/addnewBook").param("bookName", "").param("authorName", "C").param("genre", "Romance"))
		.andExpect(status().isOk())
		.andExpect(view().name("addBook"))
		.andExpect(model().attribute("book", expectedBook));
		
		Mockito.verifyNoInteractions(bookRepo);
	}
	
	@Test
	public void listBooksTest() throws Exception {
		List<Book> expectedList = new ArrayList<>();
		expectedList.add(new Book(null, "Half Girlfriend", "Chetan Bhagat", "Romance"));
		
		Mockito.when(bookRepo.findAll()).thenReturn(expectedList);
		
		mockMvc.perform(get("/allbooks"))
		.andExpect(status().isOk())
		.andExpect(view().name("listbooks"))
		.andExpect(model().attribute("books", expectedList));
	}
}
