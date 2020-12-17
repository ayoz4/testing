package gui;

import model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderingDialog extends JFrame {
    private JPanel mainpanel;
    private JTextField addressTF;
    private JTextField timeTF;

    private JButton submit;
    private JComboBox<String> pizzaNameCB;
    private JComboBox<String> pizzaSizeCB;
    private JComboBox<String> extraDrinksCB;
    private JComboBox<String> saucesCB;

    public static final String TF_ADDRESS = "addressTF";
    public static final String TF_TIME = "timeTF";
    public static final String CB_PIZZA_NAME = "pizzaNameCB";
    public static final String CB_PIZZA_SIZE = "pizzaSizeCB";
    public static final String CB_EXTRA_DRINK = "extraDrinksCB";
    public static final String CB_SAUCE = "saucesCB";
    public static final String BTN_SUBMIT = "submit";

    public OrderingDialog(IKernel app) {
        super("Test");
        final OrderingEventSource event_src = new OrderingEventSource(app);

        addressTF.setName("addressTF");
        timeTF.setName("timeTF");
        pizzaNameCB.setName("pizzaNameCB");
        pizzaSizeCB.setName("pizzaSizeCB");
        extraDrinksCB.setName("extraDrinksCB");
        saucesCB.setName("saucesCB");
        submit.setName("submit");

        pizzaNameCB.setModel(new DefaultComboBoxModel(PizzaType.values()));
        pizzaNameCB.setSelectedItem(PizzaType.Pepperoni);

        pizzaSizeCB.setModel(new DefaultComboBoxModel(PizzaSize.values()));
        pizzaSizeCB.setSelectedItem(PizzaSize.Low);

        extraDrinksCB.setModel(new DefaultComboBoxModel(ExtraDrink.values()));
        extraDrinksCB.setSelectedItem(ExtraDrink.Cola);

        saucesCB.setModel(new DefaultComboBoxModel(Sauce.values()));
        saucesCB.setSelectedItem(Sauce.Cheese);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainpanel);
        this.pack();
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Order order = new Order(addressTF.getText(), timeTF.getText(),
                            (String) pizzaNameCB.getSelectedItem().toString(),
                            (String) pizzaSizeCB.getSelectedItem().toString(),
                            (String) extraDrinksCB.getSelectedItem().toString(),
                            (String) saucesCB.getSelectedItem().toString());
                    event_src.SendOrder(order);
                } catch (IllegalArgumentException ex) {
                    event_src.SendOrder(null);
                }
            }
        });
    }

    public static void main(String[] args) {
        IKernel app = new Kernel();
        OrderingDialog frame = new OrderingDialog(app);
        frame.setVisible(true);
    }
}
