package working_on.OrdersWaiting;

import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class TableOrdersWaiting {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Заказы в ожидании");

    public TableOrdersWaiting() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        searchOWPanel owPanel = new searchOWPanel(conn);
        owPanel.init();

        forSearch.add(owPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}