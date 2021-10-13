package edu.csf.oop.java.domino.gameservice;


public class GamerToService {
    // Одна доминошка - один int
    private int[] data;
    private String name;

    public GamerToService(int[] data, String gamerName) {
        this.data = data;
        this.name = gamerName;
    }

    public int getFirst(int pos) {
        return data[pos]/10;
    }

    public int getSecond(int pos) {
        return data[pos] % 10;
    }

    public int[] getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}
