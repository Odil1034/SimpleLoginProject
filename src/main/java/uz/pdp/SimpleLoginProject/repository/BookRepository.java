package uz.pdp.SimpleLoginProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.SimpleLoginProject.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(
            nativeQuery = true,
            value = "select b.* from Book b")
    List<Book> getBooksForPage(Pageable pageable);

    Page<Book> findAll(Pageable pageable);
}
