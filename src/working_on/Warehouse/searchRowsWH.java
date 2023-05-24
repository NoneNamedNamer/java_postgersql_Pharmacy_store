package working_on.Warehouse;

import working_on.DbConnection;
import working_on.Warehouse.searchWork.WHPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class searchRowsWH {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Поиск данных для склада");

    searchRowsWH() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        WHPanel whPanel = new WHPanel(conn);
        whPanel.init();

        forSearch.add(whPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}
