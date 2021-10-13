package edu.csf.oop.java.domino.gamemodel;

import java.util.ArrayList;
import edu.csf.oop.java.domino.gameservice.GamerToService;

public class Gamer {
    private final ArrayList<DominoChip> setOfChips;
    private final String name;


    public String getName() {
        return name;
    }

    public Gamer(String name) {
        setOfChips = new ArrayList<>();
        this.name = name;
    }

    public int countSum() {
        int sum = 0;
        for (int i = 0; i < setOfChips.size(); i++){
            sum += (setOfChips.get(i).getFirstNumber() + setOfChips.get(i).getSecondNumber());
        }
        return sum;
    }

    public void dropChips() {
        setOfChips.clear();
    }

    public DominoChip move(int index) {
        DominoChip dc = setOfChips.get(index);
        setOfChips.remove(index);
        return dc;
    }

    public void add(DominoChip chip) {
        setOfChips.add(chip);
    }

    public boolean hasChips(){
        if (setOfChips.size() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public GamerToService packToDTO(){
        int[] copyOfSet = new int[setOfChips.size()];
        for (int i = 0; i < setOfChips.size();i++){
            copyOfSet[i] = 10*setOfChips.get(i).getFirstNumber() + setOfChips.get(i).getSecondNumber();
        }
        return new GamerToService(copyOfSet, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        for (int i = 0; i < setOfChips.size(); i++) {
            sb.append(setOfChips.get(i));
        }
        return sb.toString();
    }
}
