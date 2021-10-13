package edu.csf.oop.java.domino;
import edu.csf.oop.java.domino.game.GameMaster;
import edu.csf.oop.java.domino.gamemodel.Gamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    static { System.setProperty("logback.configurationFile", "config/logback.xml");}
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {


        LOGGER.debug("""
                
                ==========================================
                #      Начинаем игру для 3 человек!      #
                ==========================================""");

        

        Gamer gamer1 = new Gamer("Иван");
        Gamer gamer2 = new Gamer("Коля");
        Gamer gamer3 = new Gamer("Владимир");

        GameMaster gameMaster = new GameMaster();
        gameMaster.RunGameRound(gamer1, gamer2, gamer3);
    }
}
