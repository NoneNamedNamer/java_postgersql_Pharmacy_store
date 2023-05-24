package working_on.Orders.searchWork;

import working_on.Orders.OrdersTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class OrdersPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 38437145902853L;
    Connection conn;

    OrdersTableModel ctm = new OrdersTableModel();
    JTable ordersTable = new JTable(ctm);

    JButton srcRow = new JButton("Принять");

    JLabel n1 = new JLabel("Столбец");
    JLabel n2 = new JLabel("По параметру");

    JTextField text1 = new JTextField();

    String[] pars = {"Номер_заказа", "Номер_рецепта", "Дата_приема", "Дата_изготовления",
            "Дата_выдачи", "Стоимость", "Статус"};
    JComboBox par = new JComboBox<>(pars);

    public OrdersPanel(Connection conn) {
        this.conn = conn;
        setLayout(new GridBagLayout());
    }

    public void init() {
        JScrollPane scroll = new JScrollPane(ordersTable);
        ordersTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        add(scroll, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        add(par, new GridBagConstraints(1, 1, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        add(n1, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(n2, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(text1, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        add(srcRow, new GridBagConstraints(0, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        srcRow.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == srcRow) {
            try {
                ctm.search_data_ord(conn, "pharmacy_db.Заказы", String.valueOf(par.getSelectedItem()), text1.getText());
                repaint();
                Thread.sleep(1000);
            } catch (Exception r) {
                r.printStackTrace();
            }
        }
    }
}