package gui;

import model.IKernel;
import model.Kernel;
import model.Order;
import model.OrderingEventSource;

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

    OrderingDialog(IKernel app) {
        super("Test");
        final OrderingEventSource event_src = new OrderingEventSource(app);

        pizzaNameCB.setModel(new DefaultComboBoxModel<String>(new String[]{"Пепперони", "Маргарита", "Гавайская"}));

        pizzaSizeCB.setModel(new DefaultComboBoxModel<String>(new String[]{"15cm", "25cm", "30cm"}));

        extraDrinksCB.setModel(new DefaultComboBoxModel<String>(new String[]{"Cola", "Pepsi", "Fanta"}));

        saucesCB.setModel(new DefaultComboBoxModel<String>(new String[]{"Cheese", "Teriyaki", "BBQ"}));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainpanel);
        this.pack();
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Order order = new Order(addressTF.getText(), timeTF.getText(),
                            (String) pizzaNameCB.getSelectedItem(),
                            (String) pizzaSizeCB.getSelectedItem(),
                            (String) extraDrinksCB.getSelectedItem(),
                            (String) saucesCB.getSelectedItem());
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
