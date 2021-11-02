package guru.springframework.spring5webapp.bootstrap;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {
	
	private final Logger logger = getLogger(BootstrapData.class);

	private final AuthorRepository authRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
		
	public BootstrapData(AuthorRepository authRepository,
			BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authRepository = authRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Author eric  = new Author("Eric", "Evans");
		Book ddd = new Book ("Domain Driven Design", "3224-2424-24");
		
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authRepository.save(eric);
		bookRepository.save(ddd);
		
		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Dev without EJB","4435-4545");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);
		
		authRepository.save(rod);
		bookRepository.save(noEJB);
		
		logger.info("Started in bootstrap");
		logger.info("Number of books: {}", bookRepository.count());
		
		Publisher publisher = new Publisher("My Pub", "Valhalla Street","Valhalla", "Country", 2143);
		publisherRepository.save(publisher);
		logger.info("Number of publishers: {}", publisherRepository.count());
	}
}
