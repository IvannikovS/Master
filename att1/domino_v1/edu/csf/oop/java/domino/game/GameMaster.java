package edu.csf.oop.java.domino.game;

import edu.csf.oop.java.domino.Main;
import edu.csf.oop.java.domino.gamemodel.*;
import edu.csf.oop.java.domino.gameservice.GamerToService;
import edu.csf.oop.java.domino.gameservice.MoveProvider;
import edu.csf.oop.java.domino.gameservice.ServiceToGamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

public class GameMaster {

    private ArrayList<Gamer> gamers = new ArrayList<>();
    private GameField currentField;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GameMaster.class);

    public static final int START_AMOUNT = 7;


    public void RunGameRound(Gamer gamer1, Gamer gamer2, Gamer gamer3) {
        gamers.clear();
        gamers.add(gamer1);
        gamers.add(gamer2);
        gamers.add(gamer3);
        //gamers.add(gamer4);
        currentField = new GameField();
        LOGGER.debug("Создано игровое поле");
        System.out.println("Начало игры. Игроки: ");
        for (int i = 0; i < gamers.size(); i++) {
            System.out.print(gamers.get(i).getName());
            if (i < gamers.size() - 1) {
                System.out.print(", ");
            }
            gamers.get(i).dropChips();
        }

        System.out.println();
        ArrayList<DominoChip> packOfChips = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                packOfChips.add(new DominoChip(i, j));
            }
        }
        LOGGER.debug("Создана пачка фишек, количество в пачке = " + packOfChips.size());
        // раздаём по 7 случайных фишек
        Random random = new Random();

        for (int i = 0; i < START_AMOUNT;i++) {
            for (int j = 0; j < gamers.size();j++){
                System.out.print(gamers.get(j).getName() + " получил: ");
                int randomPos = random.nextInt(packOfChips.size());
                gamers.get(j).add(packOfChips.get(randomPos));
                System.out.print(packOfChips.get(randomPos));
                System.out.println();
                packOfChips.remove(randomPos);
            }
            System.out.println();
        }
        LOGGER.debug("После раздачи в пачке осталось " + packOfChips.size());
        // основной цикл игры
        boolean gameOn = true;
        while (gameOn) {
            gameOn = false;
            for (int i = 0; i < gamers.size(); i++) {
                System.out.println("Ходит " + gamers.get(i).getName());
                GamerToService gts = gamers.get(i).packToDTO();
                ServiceToGamer serviceToGamer = MoveProvider.findMove(currentField, gts);
                if (serviceToGamer.getIndexOfChip() == -1) {
                    System.out.println("Ход не найден");
                }
                else {


                    DominoChip dc = gamers.get(i).move(serviceToGamer.getIndexOfChip());
                    if (currentField.isEmpty()) {
                        currentField.firstMove(dc, serviceToGamer.getDirOfChip());
                    }
                    else {
                        new GameNode(dc,serviceToGamer.getDirOfChip(), serviceToGamer.getGameNode(), serviceToGamer.getRelativeDir());
                    }

                    System.out.println("Походил " + dc);
                    if(serviceToGamer.getRelativeDir()==null){
                        LOGGER.debug("Первая фишка");
                    }
                    else {

                        switch (serviceToGamer.getRelativeDir()) {
                            case EAST -> LOGGER.debug("Приставил справа");
                            case WEST -> LOGGER.debug("Приставил слева");
                            case NORTH -> LOGGER.debug("Приставил сверху");
                            case SOUTH -> LOGGER.debug("Приставил снизу");
                        }
                    }
                    currentField.paintField();
                    if (!gamers.get(i).hasChips()) {
                        int sum = 0;
                        for (int j = 0; j < gamers.size(); j++) {
                            if (j == i) {
                                continue;
                            }
                            System.out.println(gamers.get(j));
                            sum += gamers.get(j).countSum();
                        }
                        System.out.println(gamers.get(i).getName() + " выиграл и получил: " + sum);
                        gameOn = false;
                        break;
                    }
                    else {
                        gameOn = true;
                    }
                }
            }
            if (!gameOn) {
                System.out.println("Игра завершилась");
            }
            else {
                System.out.println("Следующий раунд");
            }
        }
    }
}
