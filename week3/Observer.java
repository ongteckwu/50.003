package week3;

//The Observers update method is called when the Subject changes

import java.util.Map;

public interface Observer {
    public void update(String stockName, Double price);
}
