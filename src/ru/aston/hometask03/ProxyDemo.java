package ru.aston.hometask03;



interface Image {
    void display();
}

// Реальный объект
class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Загрузка файла с диска: " + fileName + " (это долго)");
    }

    @Override
    public void display() {
        System.out.println("Отображение изображения: " + fileName);
    }
}

// Прокси
class ProxyImage implements Image {
    private final String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            System.out.println("Прокси: первое обращение, создаём RealImage");
            realImage = new RealImage(fileName);
        } else {
            System.out.println("Прокси: используем уже загруженный объект");
        }
        realImage.display();
    }
}

public class ProxyDemo {

    public static void run() {
        Image image = new ProxyImage("photo_high_res.png");

        System.out.println("Объект ProxyImage создан, но файл ещё не загружен");
        System.out.println("---");

        System.out.println("Первый вызов display():");
        image.display();

        System.out.println("---");
        System.out.println("Второй вызов display():");
        image.display();
    }

    public static void main(String[] args) {
        run();
    }
}