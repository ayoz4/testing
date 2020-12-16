package model;

import java.util.Observable;

public class OrderingEventSource extends Observable {
    public OrderingEventSource(IKernel target) {
        addObserver(target);
    }

    public void SendOrder(Object data) {
        System.out.println(data);
        setChanged();
        notifyObservers(data);
    }
}
