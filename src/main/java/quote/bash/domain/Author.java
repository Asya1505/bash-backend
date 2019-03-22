package quote.bash.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "author_message")
@ToString(of = {"id", "author"})
@EqualsAndHashCode(of = {"id"})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.NoViewJson.class)
    private Long id;
    @JsonView(Views.ViewJson.class)
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
