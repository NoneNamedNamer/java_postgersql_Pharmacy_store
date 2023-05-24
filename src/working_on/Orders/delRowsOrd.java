package working_on.Orders;

import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class delRowsOrd extends JFrame implements ActionListener {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forDel = new JFrame("Удаление заказов");
    JButton delRow, getBackButton;
    JTextField text1;
    JLabel n1;

    public delRowsOrd() {
        forDel.getContentPane().setLayout(new GridBagLayout());
        forDel.setLocationRelativeTo(null);
        forDel.setSize(new Dimension(400, 400));
        forDel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        delRow = new JButton("Принять");
        getBackButton = new JButton("Назад");
        delRow.addActionListener(this);
        getBackButton.addActionListener(this);

        n1 = new JLabel("Номер заказа");

        text1 = new JTextField();
        text1.setPreferredSize(new Dimension(240, 40));

        forDel.add(n1, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forDel.add(text1, new GridBagConstraints(1, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        forDel.add(delRow, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        forDel.add(getBackButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        forDel.setVisible(true);
        forDel.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==delRow) {
            try {
                OrdersTableModel drord = new OrdersTableModel();
                drord.delete_data_ord(conn, "pharmacy_db.Заказы", Integer.parseInt(text1.getText()));
                JOptionPane.showMessageDialog(null, "Данные удалены! Примечание: " +
                                "если данные не удалены, то проверьте корректность введённого индекса.",
                        "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception r) {
                System.out.println(r);
                JOptionPane.showMessageDialog(null, "Проверьте " +
                                "корректность введённых данных. Должен быть введён существующий индекс.",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==getBackButton) {
            forDel.dispose();
            new TableOrders();
        }
    }
}