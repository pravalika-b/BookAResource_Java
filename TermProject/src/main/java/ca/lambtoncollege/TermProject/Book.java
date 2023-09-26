/**
 * 
 */
package ca.lambtoncollege.TermProject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pravalika Bachugudam
 *
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String bookName;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String authorName;
	
	private String genre;

}
