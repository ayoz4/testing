package test;

import gui.OrderingDialog;
import model.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.testing.FestSwingTestCaseTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ParametrizedOrderingDialogTest extends FestSwingTestCaseTemplate {
    private FrameFixture dialog_;
    private static KerbelStub kernelStub_ = new KerbelStub();
    private OrderStub request_;
    private OrderStub expected_;

    public ParametrizedOrderingDialogTest(OrderStub request, OrderStub expected) {
        request_ = request;
        expected_ = expected;
    }

    @RunsInEDT
    private static OrderingDialog runOrderDialogue() {
        return execute(new GuiQuery<>() {
            protected OrderingDialog executeInEDT() {
                return new OrderingDialog(kernelStub_);
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        setUpRobot();
        dialog_ = new FrameFixture(robot(), runOrderDialogue());
        dialog_.show();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        OrderStub ordinary = new OrderStub("name, 40, 66", "11:45", "Pepperoni", "Low", "Cola", "Cheese");

        OrderStub order = new OrderStub();
        order.address = "";
        order.time = "";
        order.pizzaType = PizzaType.Pepperoni.toString();
        order.pizzaSize = PizzaSize.Low.toString();
        order.extraDrink = ExtraDrink.Cola.toString();
        order.sauce = Sauce.Cheese.toString();

        return Arrays.asList(new Object[][]{
                {new OrderStub("", "", "Pepperoni", "Low", "Cola", "Cheese"), null},
                {ordinary, ordinary},
        });
    }

    @Test
    public void test() {
        kernelStub_.Reset();
        {
            // TextBoxes
            visitTextBox(OrderingDialog.TF_ADDRESS, request_.address);
            visitTextBox(OrderingDialog.TF_TIME, request_.time);

            // ComboBoxes
            visitComboBox(OrderingDialog.CB_PIZZA_NAME, request_.pizzaType);
            visitComboBox(OrderingDialog.CB_PIZZA_SIZE, request_.pizzaSize);
            visitComboBox(OrderingDialog.CB_EXTRA_DRINK, request_.extraDrink);
            visitComboBox(OrderingDialog.CB_SAUCE, request_.sauce);

            // Click the button
            dialog_.button(OrderingDialog.BTN_SUBMIT).click();
        }
        kernelStub_.Await();

        OrderStub actual = OrderStub.FromOrder(kernelStub_.GetOrder());

        System.out.println("expected " + expected_ + " actual " + actual);

        assertEquals(expected_, actual);
    }

    public void visitTextBox(String name, String text) {
        JTextComponentFixture the_box = dialog_.textBox(name);
        the_box.setText(text);
        assertThat(the_box.text(), is(text));
    }

    /** @author Ivan Yakimov */
    public void visitComboBox(String name, String text) {
        if (text.isEmpty())
            return;
        JComboBoxFixture the_box = dialog_.comboBox(name);
        the_box.selectItem(text);
        int idx = Arrays.asList(the_box.contents()).indexOf(text);
        assertThat(the_box.valueAt(idx), is(text));
    }

    static final class OrderStub {
        public String address, time, pizzaType, pizzaSize, extraDrink, sauce;

        public static OrderStub FromOrder(Order order) {
            if (order == null) {
                return null;
            }

            OrderStub stub = new OrderStub();
            stub.address = order.getAddress().toString();
            stub.time = order.getTime().toString();
            stub.pizzaType = order.getPizzaType().toString();
            stub.pizzaSize = order.getPizzaSize().toString();
            stub.extraDrink = order.getExtraDrink().toString();
            stub.sauce = order.getSauce().toString();

            return stub;
        }

        public OrderStub(String address, String time, String pizzaType, String pizzaSize, String extraDrink, String sauce) {
            this.address = address;
            this.time = time;
            this.pizzaType = pizzaType;
            this.pizzaSize = pizzaSize;
            this.extraDrink = extraDrink;
            this.sauce = sauce;
        }

        public OrderStub() {}

        @Override
        public boolean equals(Object obj) {
            if (obj == null) { return false; }
            if (obj == this) { return true; }
            if (obj.getClass() != getClass()) { return false; }
            OrderStub order = (OrderStub)obj;
            return new EqualsBuilder()
                    .append(address, order.address)
                    .append(time, order.time)
                    .append(pizzaType, order.pizzaType)
                    .append(pizzaSize, order.pizzaSize)
                    .append(extraDrink, order.extraDrink)
                    .append(sauce, order.sauce)
                    .isEquals();
        }


        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append(address)
                    .append(time)
                    .append(pizzaType)
                    .append(pizzaSize)
                    .append(extraDrink)
                    .append(sauce)
                    .toString();
        }
    }
}
