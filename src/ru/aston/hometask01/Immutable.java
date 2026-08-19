package ru.aston.hometask01;


public final class Immutable {
    private final String name;
    private final Mutable mutable;

    public Immutable(String name, Mutable mutable){
        this.name = name;
        this.mutable = mutable == null ? new Mutable(0) : new Mutable(mutable);
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Immutable {name = " + name + ", " + mutable + '}';
    }
}
