import org.json.JSONArray;
import org.json.JSONObject;
import ru.aston.hometask01.Mutable;
import ru.aston.hometask01.Immutable;
import ru.aston.hometask02.Book;
import ru.aston.hometask02.Student;

import java.sql.Array;

void main() {
    // Task 01
//    Mutable mutable = null;
//    Immutable immutable = new Immutable("Bob", mutable);
//    System.out.println("Создаем классы:");
//    System.out.println(mutable);
//    System.out.println(immutable);
//    System.out.println();
//    mutable = new Mutable(50);
//
//    System.out.println("Меняем мутабельный класс, который используется в имутабельном");
//    mutable.setWeight(70);
//    System.out.println();
//
//    System.out.println("Проверяем изменения в классах:");
//    System.out.println(mutable);
//    System.out.println(immutable);

    // Чтение JSON файла
    String jsonString = null;
    try (BufferedReader br = new BufferedReader(new FileReader("src/ru/aston/hometask02/stud.json"))) {
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        jsonString = sb.toString();
    } catch (IOException e) {
        System.out.println(e);
    }

    // Заполнение списка студентов и книг
    List<Student> students = new ArrayList<>();
    JSONArray studArr = new JSONArray(jsonString);

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
            )
    ;

}
