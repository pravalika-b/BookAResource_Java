/**
 * 
 */
package ca.lambtoncollege.TermProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Pravalika Bachugudam
 *
 */
@RestController
public class BookRestController {
	
	//@Autowired
	//private BookCache bookCache;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private PageCounter pageCounter;
	
	@Scheduled(fixedDelay=3000)
	@GetMapping("/currentcount")
	public Integer getCurrentCount() {
		return pageCounter.getCurrentPageCount();
	}
	
	@Scheduled(fixedDelay=3000)
	@GetMapping("/bookcount")
	public Long listAllBooks() {
		pageCounter.incrementPageCounter();
		return bookRepo.count();
	}
	
	@GetMapping("/findbygenre")
	public Book findByGenre(@RequestParam String genre) {
		pageCounter.incrementPageCounter();
		return this.bookRepo.findByGenre(genre);
	}

}
