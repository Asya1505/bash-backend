package quote.bash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import quote.bash.domain.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    Author findByAuthor(String name);
}