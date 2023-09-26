/**
 * 
 */
package ca.lambtoncollege.TermProject;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Pravalika Bachugudam
 *
 */


public interface BookRepository extends CrudRepository<Book, Integer> {
	
	public Book findByGenre(String genre);

}
