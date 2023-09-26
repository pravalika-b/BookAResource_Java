/**
 * 
 */
package ca.lambtoncollege.TermProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author user
 *
 */
@Configuration
public class PageCountConfig {
	
	@Bean
	public PageCounter getPageCount() {
		return new PageCounter();
	}

}