package quote.bash.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import quote.bash.domain.Message;
import quote.bash.domain.Views;
import quote.bash.domain.Author;
import quote.bash.repo.AuthorRepo;
import quote.bash.repo.MessageRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;
    private final AuthorRepo authorRepo;

    public MessageController(MessageRepo messageRepo, AuthorRepo authorRepo) {
        this.messageRepo = messageRepo;
        this.authorRepo = authorRepo;
    }

    @GetMapping
    @CrossOrigin(origins = {"http://localhost:3000"})
    @JsonView(Views.ViewJson.class)
    public Page<Message> list(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        return messageRepo.findAll(pageable);
    }

    @PostMapping
    @CrossOrigin(origins = {"http://localhost:3000"})
    public Message create(@RequestBody Message message) {
        Author findAuthor = authorRepo.findByAuthor(message.getAuthorName());
        if(findAuthor == null) {
            authorRepo.save(message.getAuthor());
        }else{
            message.setAuthor(findAuthor);
        }
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @GetMapping("/authors")
    @CrossOrigin(origins = {"http://localhost:3000"})
    @JsonView(Views.ViewJson.class)
    public List<Author> listAuthor() {
        return authorRepo.findAll(orderByNameAsc());
    }

    private Sort orderByNameAsc() {
        return new Sort(Sort.Direction.ASC, "author");
    }
}
