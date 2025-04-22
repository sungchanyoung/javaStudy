package 도서관시스템;

import java.time.LocalDate;
import java.util.Objects;

public record BookRecord(
        String isbn,
        String title,
        String author,
        LocalDate publishDate,
        int pageCount
) {
    public BookRecord {
        Objects.requireNonNull(isbn, "isbn cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(author, "author cannot be null");
        Objects.requireNonNull(publishDate, "publishDate cannot be null");
        if (pageCount <= 0) {
            throw new IllegalArgumentException("pageCount must be greater than 0");
        }
    }
    public boolean isRecentPublication() {
        //isAfter 현재 날짜 이후인지 이전인지
        return publishDate.isAfter(LocalDate.now().minusYears(5));
    }
    public String getFormattedInfo(){
        return String.format("BookRecord{isbn='%s', title='%s', author='%s', publishDate=%s, pageCount=%d}",
                isbn, title, author, publishDate, pageCount);
    }


}
