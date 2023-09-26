/**
 * 
 */
package ca.lambtoncollege.TermProject;

import lombok.Data;

/**
 * @author user
 *
 */
@Data
public class PageCounter {
	
	private Integer pageCounter = 0;
	
	public void incrementPageCounter() {
		pageCounter += 1;	
	}
	
	public Integer getCurrentPageCount() {
		return pageCounter;
	}

}