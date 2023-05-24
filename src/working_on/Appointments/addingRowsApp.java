package working_on.Appointments;

import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class addingRowsApp extends JFrame implements ActionListener {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forAdding = new JFrame("Добавление назначений");
    JButton addingRow, getBackButton;
    JTextField text1, text2, text3;
    JLabel n1, n2, n3;

    public addingRowsApp() {
        forAdding.getContentPane().setLayout(new GridBagLayout());
        forAdding.setLocationRelativeTo(null);
        forAdding.setSize(new Dimension(1000, 400));
        forAdding.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addingRow = new JButton("Принять");
        getBackButton = new JButton("Назад");
        addingRow.addActionListener(this);
        getBackButton.addActionListener(this);

        n1 = new JLabel("Номер лекарства");
        n2 = new JLabel("Номер рецепта");
        n3 = new JLabel("Количество");

        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();

        text1.setPreferredSize(new Dimension(240, 40));
        text2.setPreferredSize(new Dimension(240, 40));
        text3.setPreferredSize(new Dimension(240, 40));

        forAdding.add(n1, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(n2, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(n3, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        forAdding.add(text1, new GridBagConstraints(1, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(text2, new GridBagConstraints(1, 1, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(text3, new GridBagConstraints(1, 2, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        forAdding.add(addingRow, new GridBagConstraints(0, 8, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(getBackButton, new GridBagConstraints(1, 8, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        forAdding.setVisible(true);
        forAdding.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addingRow) {
            try {
                AppointsTableModel arapp = new AppointsTableModel();
                arapp.insert_data_app(conn, "pharmacy_db.Назначения", Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()),
                        Integer.parseInt(text3.getText()));
                JOptionPane.showMessageDialog(null, "Данные добавлены в базу! Примечание: " +
                                "если данные не добавлены, значит номер лекарства или рецепта не существует. " +
                                "Для удостоверения данных проверьте таблицы изготовляемых лекарств или рецептов.",
                        "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception w) {
                System.out.println(w);
                JOptionPane.showMessageDialog(null, "Проверьте " +
                                "корректность введённых данных. Если не помните номер лекарства, " +
                                "рецепта проверьте таблицу изготовляемых лекарств или рецептов.", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==getBackButton) {
            forAdding.dispose();
            new TableAppoints();
        }
    }
}