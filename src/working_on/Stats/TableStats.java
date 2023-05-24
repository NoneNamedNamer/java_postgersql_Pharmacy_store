package working_on.Stats;

import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class TableStats{
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Поиск данных по статистике из категорий покупателей");

    public TableStats() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        searchStatsPanel statsPanel = new searchStatsPanel(conn);
        statsPanel.init();

        forSearch.add(statsPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}