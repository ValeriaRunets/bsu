import java.util.*;

public class Action implements Observable {
    private List<Observer> observers;
    public Action() {
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
            observer.update();
    }
}
