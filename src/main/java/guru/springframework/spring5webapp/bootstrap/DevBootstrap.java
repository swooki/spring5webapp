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

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher hc = new Publisher("Harper Collinns", "Columbus, OH");
        Publisher worx = new Publisher("Worx", "Seoul, S.Korea");

        //Eric
        Book ddd = new Book("Domain Drieven Desing", "1234");
        Author eric = new Author("Eric", "Evans");
        ddd.getAuthors().add(eric);
        ddd.setPublisher(hc);
        eric.getBooks().add(ddd);

        publisherRepository.save(hc);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Book noEJB = new Book("J2EE Development without EJB", "23444");
        Author rod = new Author("Rod", "Johnson");
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(worx);
        rod.getBooks().add(noEJB);

        publisherRepository.save(worx);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

}
