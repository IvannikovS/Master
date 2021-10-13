package edu.csf.oop.java.domino.gameservice;

import edu.csf.oop.java.domino.gamemodel.Direction;
import edu.csf.oop.java.domino.gamemodel.DominoChip;
import edu.csf.oop.java.domino.gamemodel.GameField;
import edu.csf.oop.java.domino.gamemodel.GameNode;

import java.util.List;

public class MoveProvider {

    public static ServiceToGamer findMove(GameField gameField, GamerToService gts) {

        if (gameField.isEmpty()) {
            boolean foundFirstMove = false;
            for (int i = 0; i < gts.getData().length; i++) {
                if (gts.getFirst(i) == gts.getSecond(i)) {
//                    gameField.firstMove(gamer.setOfChips.get(i), Direction.NORTH);
//                    gamer.setOfChips.remove(i);
                    return new ServiceToGamer(i, Direction.NORTH, null, null);
                }
            }
            return new ServiceToGamer(-1, null,null,null);
        }
        else {
            List<GameNode> tails = gameField.gatherTails();
            boolean moveFound = false;
            for (int i = 0; i < tails.size(); i++ ) {
                if (tails.get(i).getEastNeighbor() == null) {
                    int dots = tails.get(i).dotsAtEast();
                    boolean enFound = false;
                    for (int j = 0; j < gts.getData().length;j++) {
//                        DominoChip dc = gamer.setOfChips.get(j);
                        int a = gts.getFirst(j);
                        int b = gts.getSecond(j);
                        if (a == b && a == dots) {
//                            new GameNode(dc, Direction.NORTH, tails.get(i), Direction.EAST);
//                            enFound = true;
//                            gamer.setOfChips.remove(j);
//                            break;
                            return new ServiceToGamer(j, Direction.NORTH, tails.get(i), Direction.EAST);
                        }
                        else if (a == dots) {
//                            new GameNode(dc, Direction.EAST, tails.get(i), Direction.EAST);
//                            enFound = true;
//                            gamer.setOfChips.remove(j);
//                            System.out.println("Move " + dc.toString());
//                            break;
                            return new ServiceToGamer(j, Direction.EAST, tails.get(i), Direction.EAST);
                        }
                        else if (b == dots) {
//                            new GameNode(dc, Direction.WEST, tails.get(i), Direction.EAST);
//                            enFound = true;
//                            gamer.setOfChips.remove(j);
//                            System.out.println("Move " + dc.toString());
//                            break;
                            return new ServiceToGamer(j, Direction.WEST, tails.get(i), Direction.EAST);
                        }
                    }
                }
                else  if (tails.get(i).getWestNeighbor() == null) {
                    int dots = tails.get(i).dotsAtWest();
//                    boolean wnFound = false;
                    for (int j = 0; j < gts.getData().length; j++) {
//                        DominoChip dc = gamer.setOfChips.get(j);
                        int a = gts.getFirst(j);
                        int b = gts.getSecond(j);
                        if (a == b && a == dots) {
//                            new GameNode(dc, Direction.NORTH, tails.get(i), Direction.WEST);
//                            wnFound = true;
//                            gamer.setOfChips.remove(j);
//                            break;
                            return new ServiceToGamer(j, Direction.NORTH, tails.get(i), Direction.WEST);
                        }
                        else if (a == dots) {
//                            new GameNode(dc, Direction.WEST, tails.get(i), Direction.WEST);
//                            wnFound = true;
//                            gamer.setOfChips.remove(j);
//                            System.out.println("Move " + dc.toString());
//                            break;
                            return new ServiceToGamer(j,Direction.WEST, tails.get(i), Direction.WEST);
                        }
                        else if (b == dots) {
//                            new GameNode(dc, Direction.EAST, tails.get(i), Direction.WEST);
//                            wnFound = true;
//                            gamer.setOfChips.remove(j);
//                            System.out.println("Move " + dc.toString());
//                            break;
                            return new ServiceToGamer(j, Direction.EAST, tails.get(i), Direction.WEST);
                        }
                    }
                }
            }
            return new ServiceToGamer(-1, null, null, null);
        }
    }
}
