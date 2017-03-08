package week3;

//Represents each Observer that is monitoring changes in the subject

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockObserver implements Observer {

    private Map<String, Double> stockPrices = new HashMap<>();

    // Static used as a counter

    private static int observerIDTracker = 0;

    // Used to track the observers

    private int observerID;

    // Registers for a range of stocks
    public StockObserver(String[] listOfStocks) {

        // Assign an observer ID and increment the static counter

        this.observerID = ++observerIDTracker;

        // Message notifies user of new observer

        System.out.println("New Observer " + this.observerID);

        for (int i = 0; i < listOfStocks.length; i++) {
            String stockName = listOfStocks[i];
            StockGrabber sg = StockGrabber.getInstance(stockName);
            sg.register(this);

        }
    }

    // Registers for a new stock, if stock has not been registered
    public void registerStock(String stockName) {
        StockGrabber sg = StockGrabber.getInstance(stockName);
        if (!sg.isRegistered(this))
            sg.register(this);
    }

    // Called to update all observers

    public void update(String stockName, Double price) {
        stockPrices.put(stockName, price);
        printThePrices();

    }

    public void printThePrices() {

        StringBuilder sb = new StringBuilder();
        sb.append(observerID + "\n");
        stockPrices.forEach((k, v) -> {
            sb.append(k + ": " + v + "\n");
        });
        System.out.println(sb.toString());

    }

}
