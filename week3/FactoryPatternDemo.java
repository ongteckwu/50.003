package week3;

import java.util.Scanner;

public class FactoryPatternDemo {

    public static void main(String[] args) {
        EnemyShipFactory shipFactory = new EnemyShipFactory();
        EnemyShip theEnemy = null;

        Scanner userInput = new Scanner(System.in);

        System.out.print("What type of ship? (U / R / B)");

        if (userInput.hasNextLine()) {

            String typeOfShip = userInput.nextLine();

            theEnemy = shipFactory.makeEnemyShip(typeOfShip);

            if (theEnemy != null) {

                doStuffEnemy(theEnemy);

            } else System.out.print("Please enter U, R, or B next time");

        }
    }

    public static void doStuffEnemy(EnemyShip anEnemyShip) {
        anEnemyShip.displayEnemyShip();
        anEnemyShip.followHeroShip();
        anEnemyShip.enemyShipShoots();
    }
}

//This is a factory thats only job is creating ships
//By encapsulating ship creation, we only have one
//place to make modifications

class EnemyShipFactory {
    public EnemyShip makeEnemyShip(String newShipType) {
        EnemyShip newShip = null;

        if (newShipType.equals("U")) {
            return new UFOEnemyShip();
        } else if (newShipType.equals("R")) {
            return new RocketEnemyShip();
        } else if (newShipType.equals("B")) {
            return new BigUFOEnemyShip();
        } else return null;
    }
}

abstract class EnemyShip {
    private String name;

    EnemyShip(String name) {
        this.name = name;
    }

    void displayEnemyShip() {
        System.out.println("I am a " + name);
    };
    void followHeroShip() {
        System.out.println(name + " following you");
    };
    abstract void enemyShipShoots();
}

class UFOEnemyShip extends EnemyShip {
    UFOEnemyShip() {
        super("UFO Ship");
    }
    public void enemyShipShoots() {
        System.out.println("***Shoots Aliens***");
    }
}

class RocketEnemyShip extends EnemyShip {
    RocketEnemyShip() {
        super("Rocket Ship");
    }

    public void enemyShipShoots() {
        System.out.println("***Shoots Rockets***");
    }
}

class BigUFOEnemyShip extends EnemyShip {
    public BigUFOEnemyShip() {
        super("Big UFO Ship");
    }

    public void enemyShipShoots() {
        System.out.println("***Shoots Aliens***");
    }
}