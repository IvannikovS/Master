package edu.csf.oop.java.domino.gamemodel;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private GameNode root;

    public void firstMove(DominoChip chip, Direction direction) {
        if (!isEmpty()) {
            throw new IllegalStateException();
        }
        root = new GameNode(chip, direction, null, null);
    }
/** Проверка соостояния поля(пустое) */
    public boolean isEmpty() {
        return root == null;
    }

    /** Поиск концов при горизонтальной раскладке(без ветвлений)
     * Возвращает список концов*/
    public List<GameNode> gatherTails() {
        if(root == null) {
            return new ArrayList<>();
        }
        else {
            List<GameNode> result = new ArrayList<>();
            if (root.getWestNeighbor() == null && root.getEastNeighbor() == null) {
                result.add(root);
                return result;
            }
            else {
                GameNode current = root;
                while (current.getEastNeighbor() != null) {
                    current = current.getEastNeighbor();
                }
                result.add(current);
                current = root;
                while (current.getWestNeighbor() != null) {
                    current = current.getWestNeighbor();
                }
                result.add(current);
                return result;
            }
        }
    }
/** Отрисовка поля в консоли */
    public void paintField() {
        if (root == null) {
            System.out.println("EmptyField");
        }
        else {
            GameNode current = root;
            while (current.getWestNeighbor() != null) {
                current = current.getWestNeighbor();
            }
            while (current != null) {
                System.out.print(current);
                current = current.getEastNeighbor();
            }
            System.out.println();
        }
    }
}
