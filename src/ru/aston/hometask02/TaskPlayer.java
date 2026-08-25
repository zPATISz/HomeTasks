package ru.aston.hometask02;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskPlayer {
    private final String STUD = "src/main/resources/stud.json";

    public void play(){
        // Чтение JSON файла
        String jsonString = null;
        try (FileReader fr = new FileReader(STUD)) {
            jsonString = fr.readAllAsString();
        } catch (IOException e) {
            System.out.println(e);
        }

        // Заполнение списка студентов и книг
        List<Student> students = new ArrayList<>();
        JSONArray studArr = new JSONArray(jsonString == null ? "" : jsonString);

        for (int i = 0; i < studArr.length(); i++) {
            JSONObject student = studArr.getJSONObject(i);

            List<Book> books = new ArrayList<>();
            JSONArray booksArr = student.getJSONArray("books");

            for (int j = 0; j < booksArr.length(); j++) {
                JSONObject book = booksArr.getJSONObject(j);
                books.add(new Book(
                        book.getString("name"),
                        book.getInt("pages"),
                        book.getInt("year")
                ));
            }

            students.add(new Student(
                    student.getString("name"),
                    student.getInt("age"),
                    books
            ));
        }

        // Единый поток stream
        students.stream()
                .peek(System.out::println) //перебираем каждого для вывода
                .flatMap(student -> student.getBooks().stream()) // получаем все книги
                .sorted(Book::compareTo) //сортируем по году
                .distinct() // исключаем повторы
                .filter(book -> book.getYear() > 2000) //фильтруем по году
                .limit(3) // ограничиваем количесиво
                .map(Book::getYear) // получаем года
                .findFirst() // заканчиваем с Optional
                .ifPresentOrElse( // по результату выводим нужное
                        year -> System.out.println("\nГод выпуска книги с min кол-вом страниц и выпущенная после 2000г.: " + year),
                        () -> System.out.println("\nКнига не найдена")
                );
    }
}
