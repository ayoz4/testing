package test;

import gui.OrderingDialog;
import model.*;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.testing.FestSwingTestCaseTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OrderingDialogTest extends FestSwingTestCaseTemplate {
    private static KerbelStub kernelStub = new KerbelStub();

    private static FrameFixture dialog;

    @RunsInEDT
    private static OrderingDialog runOrderDialogue() {
        return execute(new GuiQuery<OrderingDialog>() {
            protected OrderingDialog executeInEDT() {
                return new OrderingDialog(kernelStub);
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        setUpRobot();
        dialog = new FrameFixture(robot(), runOrderDialogue());
        dialog.show();
    }

    public void Enter() {
        kernelStub.Reset();
    }

    public void Leave() {
        CountDownLatch order_latch = ((KerbelStub) kernelStub).getOrderLatch();
        try {
            order_latch.await();
        } catch (InterruptedException e) {
            fail("Bad interruption.");
            e.printStackTrace();
        }
    }

    public void visitTextBox(String name, String text) {
        JTextComponentFixture the_box = dialog.textBox(name);
        the_box.setText(text);
        assertThat(the_box.text(), is(text));
    }

    public void visitComboBox(String name, String text) {
        if (text.isEmpty())
            return;
        JComboBoxFixture the_box = dialog.comboBox(name);
        the_box.selectItem(text);
        int idx = Arrays.asList(the_box.contents()).indexOf(text);
        assertThat(the_box.valueAt(idx), is(text));
    }

    @Test
    public void validation() {
        Order request_ = new Order("name, 40, 66", "11:45", "Pepperoni", "Low", "Cola", "Cheese");
        kernelStub.Reset();
        {
            visitTextBox(OrderingDialog.TF_ADDRESS, request_.getAddress().toString());
            visitTextBox(OrderingDialog.TF_TIME, request_.getTime().toString());

            // ComboBoxes
            visitComboBox(OrderingDialog.CB_PIZZA_NAME, request_.getPizzaType().toString());
            visitComboBox(OrderingDialog.CB_PIZZA_SIZE, request_.getPizzaSize().toString());
            visitComboBox(OrderingDialog.CB_EXTRA_DRINK, request_.getExtraDrink().toString());
            visitComboBox(OrderingDialog.CB_SAUCE, request_.getSauce().toString());

            // Click the button
            dialog.button(OrderingDialog.BTN_SUBMIT).click();
        }
        kernelStub.Await();

        Order actual = kernelStub.GetOrder();
        assertEquals(request_, actual);
    }

    @Test
    public void null_test() {
        kernelStub.Reset();
        {
            // Click the button
            dialog.button(OrderingDialog.BTN_SUBMIT).click();
        }
        kernelStub.Await();

        Order actual = kernelStub.GetOrder();

        assertNull("test", actual);
    }
}
