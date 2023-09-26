/**
 * 
 */
package ca.lambtoncollege.TermProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pravalika Bachugudam
 *
 */
@Configuration
public class BookCacheConfig {
	
	@Bean
	public BookCache getBookCache() {
		return new BookCache();
	}

}
