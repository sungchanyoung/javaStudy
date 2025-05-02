package day4.homework;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private String title;
    private String author;
    private String genre;
    private int year;
    private int price;

}
