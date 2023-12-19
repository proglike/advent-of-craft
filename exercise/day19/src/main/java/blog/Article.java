package blog;

import io.vavr.collection.Seq;
import io.vavr.control.Try;

import java.time.LocalDate;

import static io.vavr.collection.List.of;

public class Article {
    private final String name;
    private final String content;
    private final Seq<Comment> comments;

    public Article(String name, String content) {
        this(name, content, of());
    }

    private Article(String name, String content, Seq<Comment> comments) {
        this.name = name;
        this.content = content;
        this.comments = comments;
    }

    private Article addComment(
            String text,
            String author,
            LocalDate creationDate) throws CommentAlreadyExistException {
        var comment = new Comment(text, author, creationDate);

        if (comments.contains(comment)) {
            throw new CommentAlreadyExistException();
        }
        return new Article(name, content, comments.append(comment));
    }

    public Article addComment(String text, String author) {
        return addComment(text, author, LocalDate.now());
    }

    public Seq<Comment> getComments() {
        return comments;
    }

    public Try<Article> addComment2(String commentText, String author) {
        var comment = new Comment(commentText, author, LocalDate.now());

        if (comments.contains(comment)) {
            return Try.failure(new CommentAlreadyExistException());
        }
        return Try.success(new Article(name, content, comments.append(comment)));
    }
}

