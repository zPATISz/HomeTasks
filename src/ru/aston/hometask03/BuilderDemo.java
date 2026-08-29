package ru.aston.hometask03;


class Pizza {
    // Обязательные параметры
    private final String size;

    // Опциональные параметры
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;
    private final boolean olives;

    // Конструктор приватный — создать Pizza можно только через Builder
    private Pizza(Builder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.mushrooms = builder.mushrooms;
        this.olives = builder.olives;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Пицца [" + size + "]:");
        if (cheese) sb.append(" сыр ");
        if (pepperoni) sb.append(" пепперони ");
        if (mushrooms) sb.append(" грибы ");
        if (olives) sb.append(" оливки ");
        return sb.toString();
    }

    public static class Builder {
        private final String size;

        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean mushrooms = false;
        private boolean olives = false;

        public Builder(String size) {
            this.size = size;
        }

        public Builder cheese(boolean value) {
            this.cheese = value;
            return this;
        }

        public Builder pepperoni(boolean value) {
            this.pepperoni = value;
            return this;
        }

        public Builder mushrooms(boolean value) {
            this.mushrooms = value;
            return this;
        }

        public Builder olives(boolean value) {
            this.olives = value;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}

// Демонстрация
public class BuilderDemo {

    public static void run() {
        Pizza margarita = new Pizza.Builder("средняя")
                .cheese(true)
                .build();

        Pizza meat = new Pizza.Builder("большая")
                .cheese(true)
                .pepperoni(true)
                .mushrooms(true)
                .build();

        Pizza vegetarian = new Pizza.Builder("маленькая")
                .cheese(true)
                .mushrooms(true)
                .olives(true)
                .build();

        System.out.println(margarita);
        System.out.println(meat);
        System.out.println(vegetarian);
    }

    public static void main(String[] args) {
        run();
    }
}
