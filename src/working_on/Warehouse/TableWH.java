package working_on.Warehouse;

import working_on.DbConnection;
import working_on.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TableWH extends JFrame implements ActionListener {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame frame = new JFrame("Склад");
    JButton addButton = new JButton("Добавить");
    JButton delButton = new JButton("Удалить");
    JButton getBackButton = new JButton("Вернуться в главное меню");
    JButton updateButton = new JButton("Изменить");
    JButton searchButton = new JButton("Найти");

    public TableWH() {
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setSize(new Dimension(1000, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WHTableModel whtm = new WHTableModel();
        whtm.read_data_wh(conn, "pharmacy_db.Склад");

        JTable whTable = new JTable(whtm);
        JScrollPane scroll = new JScrollPane(whTable);
        whTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));

        frame.add(scroll, new GridBagConstraints(0, 0, 4, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(delButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(getBackButton, new GridBagConstraints(0, 2, 4, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(updateButton, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(searchButton, new GridBagConstraints(3, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        addButton.addActionListener(this);
        delButton.addActionListener(this);
        getBackButton.addActionListener(this);
        updateButton.addActionListener(this);
        searchButton.addActionListener(this);

        frame.setVisible(true);
        frame.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            frame.dispose();
            new addingRowsWH();
        } else if (e.getSource()==delButton) {
            frame.dispose();
            new delRowsWH();
        } else if (e.getSource()==getBackButton) {
            frame.dispose();
            new MainPage();
        } else if (e.getSource()==updateButton) {
            frame.dispose();
            new updRowsWH();
        } else if (e.getSource()==searchButton) {
            new searchRowsWH();
        }
    }
}