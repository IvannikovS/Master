package edu.csf.oop.java.domino.gamemodel;


public class GameNode {
    private final DominoChip chip;
    private Direction direction;
    private GameNode eastNeighbor;
    private GameNode westNeighbor;
    private GameNode northNeighbor;
    private GameNode southNeighbor;



    // конструктор принимает фишку, её направоение, уже существующая фишка(к которой пристраиваемся)
    // relativeDir - относительное направление(важно с какой стороны мы пристаиваемся)
    public GameNode(DominoChip chip, Direction direction, GameNode lastChip, Direction relativeDir) {
        this.chip = chip;
        this.direction = direction;
        if(lastChip == null) {
            return;
        }
        switch (relativeDir) {
            case EAST -> {
                lastChip.eastNeighbor = this;
                this.westNeighbor = lastChip;
            }
            case WEST -> {
                lastChip.westNeighbor = this;
                this.eastNeighbor = lastChip;
            }
            case NORTH -> {
                lastChip.northNeighbor = this;
                this.southNeighbor = lastChip;
            }
            case SOUTH -> {
                lastChip.southNeighbor = this;
                this.northNeighbor = lastChip;
            }
        }
    }

    /** Возвращает точки с восточной части */
    public int dotsAtEast(){
        if(direction == Direction.WEST) {
            return chip.getFirstNumber();
        }
        else {
            return chip.getSecondNumber();
        }
    }

    /** Возвращает точки с западной части  */
    public int dotsAtWest() {
        if (direction == Direction.WEST) {
            return chip.getSecondNumber();
        } else {
            return chip.getFirstNumber();
        }
    }

    @Override
    public String toString() {
        if (direction == Direction.EAST || chip.isDouble()) {
            return chip.toString();
        }
        else {
            return "[" +
                    + chip.getSecondNumber() +
                    " : " + chip.getFirstNumber() +
                    ']';
        }
    }

    public void findTails(GameNode from){
        if (this.chip.isDouble()) {
            switch (direction) {
                case EAST, WEST -> {
                    if (northNeighbor == null) {
                        System.out.println("NorthFrom " + this.chip);
                    } else if (northNeighbor != from) {
                        northNeighbor.findTails(this);
                    }
                    if (southNeighbor == null) {
                        System.out.println("SouthFrom " + this.chip);
                    } else if (southNeighbor != from) {
                        southNeighbor.findTails(this);
                    }
                }
                case NORTH, SOUTH -> {
                    if (eastNeighbor == null) {
                        System.out.println("EastFrom " + this.chip);
                    }
                    else if (eastNeighbor != from) {
                        eastNeighbor.findTails(this);
                    }
                    if (westNeighbor == null) {
                        System.out.println("WestFrom" + this.chip);
                    }
                    else if (westNeighbor != from) {
                        westNeighbor.findTails(this);
                    }
                }
            }
        }
        else {
            switch (direction) {
                case EAST, WEST -> {
                    if (eastNeighbor == null) {
                        System.out.println("EastFrom " + this);
                    } else if (eastNeighbor != from) {
                        eastNeighbor.findTails(this);
                    }
                    if (westNeighbor == null) {
                        System.out.println("WestFrom " + this.chip);
                    } else if (westNeighbor != from) {
                        westNeighbor.findTails(this);
                    }
                }
                case NORTH, SOUTH -> {
                    if (northNeighbor == null) {
                        System.out.println("NorthFrom " + this.chip);
                    }
                    else if (northNeighbor != from) {
                        northNeighbor.findTails(this);
                    }
                    if (southNeighbor == null) {
                        System.out.println("SouthFrom" + this.chip);
                    }
                    else if (southNeighbor != from) {
                        southNeighbor.findTails(this);
                    }
                }
            }
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public GameNode getEastNeighbor() {
        return eastNeighbor;
    }

    public GameNode getWestNeighbor() {
        return westNeighbor;
    }

    public GameNode getNorthNeighbor() {
        return northNeighbor;
    }

    public GameNode getSouthNeighbor() {
        return southNeighbor;
    }
}
