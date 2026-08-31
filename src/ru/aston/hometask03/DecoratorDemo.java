package ru.aston.hometask03;

// Общий интерфейс для базовых напитков и декораторов
interface Beverage {
    String getDescription();
    double cost();
}

// Конкретный компонент 1
class Espresso implements Beverage {
    @Override
    public String getDescription() {
        return "Эспрессо";
    }

    @Override
    public double cost() {
        return 120.0;
    }
}

// Конкретный компонент 2
class HouseBlend implements Beverage {
    @Override
    public String getDescription() {
        return "Фирменный купаж";
    }

    @Override
    public double cost() {
        return 90.0;
    }
}

// Базовый класс декоратора — сам реализует Beverage и оборачивает другой Beverage
abstract class CondimentDecorator implements Beverage {
    protected final Beverage beverage;

    protected CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}

// Конкретный декоратор 1
class MilkDecorator extends CondimentDecorator {
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + молоко";
    }

    @Override
    public double cost() {
        return beverage.cost() + 20.0;
    }
}

// Конкретный декоратор 2
class SugarDecorator extends CondimentDecorator {
    public SugarDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + сахар";
    }

    @Override
    public double cost() {
        return beverage.cost() + 5.0;
    }
}

public class DecoratorDemo {

    public static void run() {
        // Просто эспрессо
        Beverage plainEspresso = new Espresso();
        printOrder(plainEspresso);

        // Эспрессо + молоко + сахар
        Beverage sweetLatte = new SugarDecorator(new MilkDecorator(new Espresso()));
        printOrder(sweetLatte);
    }

    private static void printOrder(Beverage beverage) {
        System.out.printf("%s — %.2f д.е.\n", beverage.getDescription(), beverage.cost());
    }

    public static void main(String[] args) {
        run();
    }
}