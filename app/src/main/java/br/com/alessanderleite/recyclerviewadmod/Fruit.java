package br.com.alessanderleite.recyclerviewadmod;

public class Fruit {

    private String fruitName;
    private String fruitCalorie;

    public Fruit(String fruitName, String fruitCalorie) {
        this.fruitName = fruitName;
        this.fruitCalorie = fruitCalorie;
    }

    public String getFruitName() {
        return fruitName;
    }

    public String getFruitCalorie() {
        return fruitCalorie;
    }
}
