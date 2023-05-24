package working_on.Clients;

import working_on.Clients.searchWork.ClientsPanel;
import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class searchRowsCl extends JFrame {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Поиск данных для клиентов");

    searchRowsCl() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ClientsPanel clientsPanel = new ClientsPanel(conn);
        clientsPanel.init();

        forSearch.add(clientsPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}