package working_on.Meds;

import working_on.DbConnection;
import working_on.Meds.searchWork.MedsPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class searchRowsMeds extends JFrame {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Поиск данных для лекарств");

    searchRowsMeds() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MedsPanel medsPanel = new MedsPanel(conn);
        medsPanel.init();

        forSearch.add(medsPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}