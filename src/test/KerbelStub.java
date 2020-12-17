package test;

import model.IKernel;
import model.Order;

import java.util.Observable;
import java.util.concurrent.CountDownLatch;

public class KerbelStub implements IKernel {
    private static final int COUNT = 1;
    private Order orderItself = new Order();
    private CountDownLatch orderLatch = new CountDownLatch(COUNT);

    @Override
    public void connect() {

    }

    public CountDownLatch getOrderLatch() {
        return orderLatch;
    }

    public void Await() {
        try {
            orderLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Free() {
        orderLatch.countDown();
    }

    @Override
    public void disconnect() {

    }

    public void Reset() {
        orderLatch = new CountDownLatch(COUNT);
        orderItself = null;
    }

    @Override
    public void update(Observable event_source, Object arg) {
        orderItself = (Order) arg;
        Free();
    }

    public Order GetOrder() {
        return orderItself;
    }
}
