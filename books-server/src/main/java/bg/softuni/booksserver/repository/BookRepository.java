package bg.softuni.booksserver.repository;

import bg.softuni.booksserver.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
