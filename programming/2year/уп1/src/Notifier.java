import java.util.*;

public class Notifier <T extends Notifiable>{
    Set<T> notifiables;

    void doNotifyAll(String message){
        for (T i:notifiables){
            i.notify(message);
        }
    }

    public Notifier(Set<T> notifiables) {
        this.notifiables = notifiables;
    }
}
