package net.catibog.bookshop.catalogservice.web;

import net.catibog.bookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {
    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = Book.of("1234567890", "Title", "Author", 9.90, "Polarsophia");
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(book.id());
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher").isEqualTo(book.publisher());
        assertThat(jsonContent).extractingJsonPathStringValue("@.created_date").isEqualTo(book.createdDate());
        assertThat(jsonContent).extractingJsonPathStringValue("@.last_modified_date").isEqualTo(book.lastModifiedDate());

    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
            {
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "price": 9.90
            }
            """;
        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(Book.of("1234567890", "Title", "Author", 9.90, null));
    }
}
