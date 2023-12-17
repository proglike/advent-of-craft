package blog;

import org.assertj.core.api.ThrowingConsumer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static blog.ArticleBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.instancio.Instancio.create;

class ArticleTests {
    private Article article;

    @Test
    void should_add_comment_in_an_article() throws CommentAlreadyExistException {
        when(article -> article.addComment(COMMENT_TEXT, AUTHOR));
        then(article -> {
            assertThat(article.getComments()).hasSize(1);
            assertComment(article.getComments().get(0), COMMENT_TEXT, AUTHOR);
        });
    }

    @Test
    void should_add_comment_in_an_article_containing_already_a_comment() throws Throwable {
        final var newComment = create(String.class);
        final var newAuthor = create(String.class);

        when(ArticleBuilder::commented, article -> article.addComment(newComment, newAuthor));
        then(article -> {
            assertThat(article.getComments()).hasSize(2);
            assertComment(article.getComments().getLast(), newComment, newAuthor);
        });
    }

    @Nested
    class Fail {
        @Test
        void when__adding_an_existing_comment() throws CommentAlreadyExistException {
            var article = anArticle()
                    .commented()
                    .build();

            assertThatThrownBy(() -> {
                article.addComment(article.getComments().get(0).text(), article.getComments().get(0).author());
            }).isInstanceOf(CommentAlreadyExistException.class);
        }
    }

    private static void assertComment(Comment comment, String commentText, String author) {
        assertThat(comment.text()).isEqualTo(commentText);
        assertThat(comment.author()).isEqualTo(author);
    }

    private void when(ArticleBuilder articleBuilder, ThrowingConsumer<Article> act) throws CommentAlreadyExistException {
        article = articleBuilder.build();
        act.accept(article);
    }

    private void when(ThrowingConsumer<Article> act) throws CommentAlreadyExistException {
        when(anArticle(), act);
    }

    private void when(Function<ArticleBuilder, ArticleBuilder> options, ThrowingConsumer<Article> act) throws Throwable {
        when(options.apply(anArticle()), act);
    }

    private void then(ThrowingConsumer<Article> act) {
        act.accept(article);
    }
}