package edu.csf.oop.java.domino.gamemodel;

import java.util.Objects;

public class DominoChip {
    private final int firstNumber;
    private final int secondNumber;

    public DominoChip(int A, int B) {
        this.firstNumber = A;
        this.secondNumber = B;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DominoChip that = (DominoChip) o;
        return (firstNumber == that.firstNumber && secondNumber == that.secondNumber) || (firstNumber == that.secondNumber && secondNumber == that.firstNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumber, secondNumber) + Objects.hash(secondNumber, firstNumber);
    }

    public boolean isDouble(){
        return firstNumber == secondNumber;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    @Override
    public String toString() {
        return "[" +
                 +firstNumber +
                " : " + secondNumber +
                ']';
    }
}
