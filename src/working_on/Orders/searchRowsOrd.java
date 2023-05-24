package working_on.Orders;

import working_on.DbConnection;
import working_on.Orders.searchWork.OrdersPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class searchRowsOrd extends JFrame {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Поиск данных для заказов");

    public searchRowsOrd() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        OrdersPanel ordersPanel = new OrdersPanel(conn);
        ordersPanel.init();

        forSearch.add(ordersPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}