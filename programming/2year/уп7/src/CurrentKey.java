import java.util.*;

public class CurrentKey implements Observable {
    private List<Observer> observers;
    String key;
    public CurrentKey() {
        observers = new LinkedList<>();
    }
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(key);
    }
    public void pressKey(String key){
        this.key=key;
        notifyObservers();
    }
}
