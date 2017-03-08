package week3;

class Robot {
    String name;
    IBehaviour behaviour;

    public Robot(String name) {
        this.name = name;

    }

    public void behave() {
        //the robots behave differently
        behaviour.moveCommand();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBehavior(IBehaviour behavior) {
        //todo
        this.behaviour = behavior;

    }
}

interface IBehaviour {
    public void moveCommand();
}

class DefaultBehaviour implements IBehaviour {
    @Override
    public void moveCommand() {
        System.out.println("Moves forward");
    }
}

class AggressiveBehaviour implements IBehaviour {
    @Override
    public void moveCommand() {
        System.out.println("Moves forward aggressively");
    }
}

class DefensiveBehaviour implements IBehaviour {
    @Override
    public void moveCommand() {
        System.out.println("Moves forward defensively");
    }
}


public class RobotGame {

    public static void main(String[] args) {

        Robot r1 = new Robot("Big Robot");
        Robot r2 = new Robot("George v.2.1");
        Robot r3 = new Robot("R2");

        r1.setBehavior(new AggressiveBehaviour());
        r2.setBehavior(new DefensiveBehaviour());
        r3.setBehavior(new DefensiveBehaviour());

        r1.behave();
        r2.behave();
        r3.behave();

        //change the behaviors of each robot.
        r1.setBehavior(new DefensiveBehaviour());
        r2.setBehavior(new DefensiveBehaviour());
        r3.setBehavior(new DefensiveBehaviour());

        r1.behave();
        r2.behave();
        r3.behave();
    }
}