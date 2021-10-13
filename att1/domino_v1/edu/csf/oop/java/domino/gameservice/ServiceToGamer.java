package edu.csf.oop.java.domino.gameservice;

import edu.csf.oop.java.domino.gamemodel.Direction;
import edu.csf.oop.java.domino.gamemodel.GameNode;

public class ServiceToGamer {

    private final int indexOfChip;
    private final Direction dirOfChip;
    private final GameNode gameNode;
    private final Direction relativeDir;

    public ServiceToGamer(int indexOfChip, Direction dirOfChip, GameNode gameNode, Direction relativeDir) {
        this.indexOfChip = indexOfChip;
        this.dirOfChip = dirOfChip;
        this.gameNode = gameNode;
        this.relativeDir = relativeDir;
    }

    public int getIndexOfChip() {
        return indexOfChip;
    }

    public Direction getDirOfChip() {
        return dirOfChip;
    }

    public GameNode getGameNode() {
        return gameNode;
    }

    public Direction getRelativeDir() {
        return relativeDir;
    }
}
