package working_on.Recipes;

import working_on.DbConnection;
import working_on.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TableRecipes extends JFrame implements ActionListener{
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame frame = new JFrame("База данных аптеки - Рецепты");
    JButton addButton = new JButton("Добавить");
    JButton delButton = new JButton("Удалить");
    JButton getBackButton = new JButton("Вернуться в главное меню");
    JButton updateButton = new JButton("Изменить");
    JButton searchButton = new JButton("Найти");

    public TableRecipes() {
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setSize(new Dimension(1000, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RecipesTableModel rtm = new RecipesTableModel();
        rtm.read_data_rec(conn, "pharmacy_db.Рецепты");

        JTable clientsTable = new JTable(rtm);
        JScrollPane scroll = new JScrollPane(clientsTable);
        clientsTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));

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
            new addingRowsRec();
        } else if (e.getSource()==delButton) {
            frame.dispose();
            new delRowsRec();
        } else if (e.getSource()==getBackButton) {
            frame.dispose();
            new MainPage();
        } else if (e.getSource()==updateButton) {
            frame.dispose();
            new updRowsRec();
        } else if (e.getSource()==searchButton) {
            new searchRowsRec();
        }
    }
}