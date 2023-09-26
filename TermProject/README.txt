Spring MVC Application for Library Management

http://localhost:8080/allbooks

Book A Resource
---------------
This application has two pages:

1.A page with a form where admin have to input information of book details(Register Book)
2.A page to display the list of all books(Books)

Requirements:
------------

1. Book.java
   ----------
The book class has 4 fields:

* Book Id
* Book Name
* Author Name
* Genre

2. The form is validated on the server side in Book.java

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

3. Contents in the form is saved to database if validated .Else form view "addbook" is returned 
   This feature is implemented in BookController.java in @PostMapping("/addnewBook")

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

4. A page called Books is created where user and admin can see the list of books in the library
   This is implemented in BookController.java in @GetMapping("/allbooks") which returns "listbooks"

5. Additional feature is implemented to filter the list of books by genre in BookRepository.java and BookRestController
   http://localhost:8080/findbygenre?genre=Romance

6. Implemented an API that returns the number of page hits since the server was online using PageCounter.java, PageCountConfig.java
   Its defined in BookRestController.java as follows
   	@GetMapping("/currentcount")
	public Integer getCurrentCount() {
		return pageCounter.getCurrentPageCount();
	}
7. This api is called asynchronously every 3 seconds and the results displayed on every page. (/addnewBook, /allbooks)
   For this @EnableScheduling is used in TermProjectApplication.java
   @Scheduled(fixedDelay=3000) is used for scheduling in BookRestController.java
   
   Also implemented additional feature to also display the count of all books in the library. This is also displayed on every page and 
   the API is called every 3 seconds
   
   In every page , following is displayed 

   Total books in the library :
   Total Number of Page Hits : 

8. Dependency Injection:
   
   Dependency injection is implemented as follows:
   1. pageCounter.incrementPageCounter() method created in PageCounter.java is implemented in BookController.java.
   2. It is also used in BookRestController.java in listAllBooks() and findByGenre() method to increment the page 
     hit count whenever the server is hit

9. lombok has been used in data classes :
    * Book.java 
    * PageCounter.java
10. HTML and CSS has been developed for Aesthetically pleasing website
 
    styles.css is in the path /TermProject/src/main/resources/static/styles/styles.css

    Navigation bar is used to include both the pages Register Book and Books .It is used to switch between the pages
    
    Templates:
    ----------
    * addBook.html
    * listbooks.html
    * saved.html
    
    Styles:
    -------
    * styles.css

11. TESTING
    -------
    4 Unit tests has been implemented 
    
	1. addBookTest() -- This test case is for GetMapping("/addnewBook") for loading the corresponding html page properly.
	2. addPostTest() -- This test case tests for the server-side validation. 
			    This method is for PostMapping("/addnewBook")route.
        3. addPostTest_InvalidBook() -- This test case is developed for invalid data for PostMapping("/addnewBook") route.
	4. listBooksTest() -- This test case method tests whether the saved data and data being displayed in all books page is matched
             
  


  
   
	
