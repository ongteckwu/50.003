package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Uses the Subject interface to update all Observers

public class StockGrabber implements iSubject {
    // Each Stock Grabber Responsible for a single stock
    private ArrayList<Observer> observers;
    private String stockName;
    private double price;

    // Stock Grabber lazily initiated and singleton
    private static Map<String, StockGrabber> singletonMap = new HashMap<>();

    // Creates a new Stock Grabber for a given stock
    public static synchronized StockGrabber getInstance(String stockName) {
        StockGrabber instance = singletonMap.getOrDefault(stockName, null);
        if (instance == null) {
            instance = new StockGrabber(stockName);
            singletonMap.put(stockName, instance);
        }

        return instance;

    }
    private StockGrabber(String stockName) {
        this.stockName = stockName;
        // Creates an ArrayList to hold all observers

        observers = new ArrayList<Observer>();
    }

    public void register(Observer newObserver) {

        // Adds a new observer to the ArrayList
        observers.add(newObserver);

    }

    public boolean isRegistered(Observer observer) {
        return observers.indexOf(observer) != -1;
    }

    public void unregister(Observer deleteObserver) {

        // Get the index of the observer to delete

        int observerIndex = observers.indexOf(deleteObserver);

        // Print out message (Have to increment index to match)

        System.out.println("Observer " + (observerIndex + 1) + " deleted");

        // Removes observer from the ArrayList

        observers.remove(observerIndex);

    }

    public void notifyObserver() {

        // Cycle through all observers and notifies them of
        // price changes

        for (Observer observer : observers) {

            observer.update(stockName, price);

        }
    }


    public void setPrice(double newPrice) {

        this.price = newPrice;

        notifyObserver();
    }
}