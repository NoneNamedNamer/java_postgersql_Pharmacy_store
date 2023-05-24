package working_on.Appointments;

import working_on.DbConnection;
import working_on.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TableAppoints extends JFrame implements ActionListener{
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame frame = new JFrame("База данных аптеки - Назначения");
    JButton addButton = new JButton("Добавить");
    JButton getBackButton = new JButton("Вернуться в главное меню");
    JButton searchButton = new JButton("Найти");

    public TableAppoints() {
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setSize(new Dimension(1000, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AppointsTableModel atm = new AppointsTableModel();
        atm.read_data_app(conn, "pharmacy_db.Назначения");

        JTable appointsTable = new JTable(atm);
        JScrollPane scroll = new JScrollPane(appointsTable);
        appointsTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));

        frame.add(scroll, new GridBagConstraints(0, 0, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.add(getBackButton, new GridBagConstraints(0, 2, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.add(searchButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        addButton.addActionListener(this);
        getBackButton.addActionListener(this);
        searchButton.addActionListener(this);

        frame.setVisible(true);
        frame.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            frame.dispose();
            new addingRowsApp();
        } else if (e.getSource()==getBackButton) {
            frame.dispose();
            new MainPage();
        } else if (e.getSource()==searchButton) {
            new searchRowsApp();
        }
    }
}