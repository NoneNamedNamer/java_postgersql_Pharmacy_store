package working_on.Appointments;

import working_on.Appointments.searchWork.AppointsPanel;
import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class searchRowsApp {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Поиск данных для назначений");
    searchRowsApp() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        AppointsPanel appointsPanel = new AppointsPanel(conn);
        appointsPanel.init();

        forSearch.add(appointsPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}