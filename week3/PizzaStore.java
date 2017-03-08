package week3;

public class PizzaStore {

    public Pizza orderPizza(String type) {

        Pizza pizza = PizzaFactory.newPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}

class PizzaFactory {

    public static Pizza newPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        }
        if (type.equals("greek")) {
            pizza = new GreekPizza();
        }
        if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        }

        return pizza;
    }
}

class Pizza {

    public void prepare() {
    }

    public void box() {
    }

    public void cut() {
    }

    public void bake() {
    }
}

class CheesePizza extends Pizza {
}

class GreekPizza extends Pizza {
}

class PepperoniPizza extends Pizza {
}

