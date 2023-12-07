package blog;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {

    public static final String AUTHOR = "Pablo Escobar";
    public static final String TEXT = "Amazing article !!!";

    @Test
    void it_should_add_a_comment() {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );

        article.addComment(TEXT, AUTHOR);

        assertThat(article.getComments())
                .hasSize(1)
                .anyMatch(comment -> comment.equals(new Comment(TEXT, AUTHOR, LocalDate.now())));
    }

    @Test
    void it_should_fail_when_adding_existing_comment() {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        article.addComment(TEXT, AUTHOR);

        assertThatThrownBy(() -> {
            article.addComment(TEXT, AUTHOR);
        }).isInstanceOf(CommentAlreadyExistException.class);
    }
}
