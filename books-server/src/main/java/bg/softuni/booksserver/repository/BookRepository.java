package bg.softuni.booksserver.repository;


import bg.softuni.booksserver.model.dto.BookDTO;
import bg.softuni.booksserver.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
