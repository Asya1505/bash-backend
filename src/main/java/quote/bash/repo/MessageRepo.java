package quote.bash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import quote.bash.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}