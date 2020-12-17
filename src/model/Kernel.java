package model;

import javax.swing.*;
import java.util.Observable;

public class Kernel implements IKernel {

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void update(Observable observable, Object o) {
        Order received = (Order) o;
        System.out.println(received.getClass());

        if (o instanceof Order) {
            JOptionPane.showMessageDialog(null, received.toString(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        if (o == null)
            JOptionPane.showMessageDialog(null, "Incorrect order format", "Error", JOptionPane.WARNING_MESSAGE);
    }

}
