/**
 * 
 */
package ca.lambtoncollege.TermProject;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Pravalika Bachugudam
 *
 */

@Controller
public class BookController {
	
	//@Autowired
	//private BookCache bookCache;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private PageCounter pageCounter;
	
	@GetMapping("/addnewBook")
	public String addBook(Model model) {
		pageCounter.incrementPageCounter();
		model.addAttribute("count", pageCounter.getCurrentPageCount());
		model.addAttribute("bookscount", this.bookRepo.count());
		model.addAttribute("book", new Book());
		return "addBook";
	}
	
	@PostMapping("/addnewBook")
	public String addNewBook(@Valid Book toSave , BindingResult bindingResult) {
		pageCounter.incrementPageCounter();
		if(bindingResult.hasErrors()) {
			return "addBook";
		}
		else {
			this.bookRepo.save(toSave);
			//this.bookCache.storeBook(toSave);
			return "saved";
		}
	}
	
	@GetMapping("/allbooks")
	public String listAllBooks(Model model) {
		pageCounter.incrementPageCounter();
		model.addAttribute("count", pageCounter.getCurrentPageCount());
		model.addAttribute("bookscount", this.bookRepo.count());
		model.addAttribute("books", this.bookRepo.findAll());
		return "listbooks";
	}

}
