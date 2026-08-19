package ru.aston.hometask01;

public class Mutable {
    private int weight;

    public Mutable(int weight){
        this.weight = weight;
    }
    public Mutable(Mutable other){
        this.weight = other == null ? 0 : other.weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Mutable {weight = " + weight + '}';
    }
}