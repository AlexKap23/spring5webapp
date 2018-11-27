package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 5/16/17.
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
	    Publisher dddPublisher = new Publisher("Alexandros","Somewhere in Mexico");
        Book  ddd = new Book("Domain Driven Design", "1234", dddPublisher);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
	    publisherRepository.save(dddPublisher);

	    //Rod
	    Author rod = new Author("Rod", "Johnson");
	    Publisher noEJBPublisher = new Publisher("Alexandros","Somewhere in Mexico");
	    Book noEJB = new Book("J2EE Development without EJB", "23444", noEJBPublisher);
	    rod.getBooks().add(noEJB);
	    noEJB.getAuthors().add(rod);

	    authorRepository.save(rod);
	    bookRepository.save(noEJB);
	    publisherRepository.save(noEJBPublisher);
    }
}
