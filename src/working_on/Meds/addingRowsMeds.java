package working_on.Meds;

import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class addingRowsMeds extends JFrame implements ActionListener {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forAdding = new JFrame("Добавление лекарств");
    JButton addingRow, getBackButton;
    JTextField text1, text2, text3, text4, text5;
    JLabel n1, n2, n3, n4, n5;
    public addingRowsMeds() {
        forAdding.getContentPane().setLayout(new GridBagLayout());
        forAdding.setLocationRelativeTo(null);
        forAdding.setSize(new Dimension(1000, 400));
        forAdding.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addingRow = new JButton("Принять");
        getBackButton = new JButton("Назад");
        addingRow.addActionListener(this);
        getBackButton.addActionListener(this);

        n1 = new JLabel("Номер лекарства");
        n2 = new JLabel("Номер на складе");
        n3 = new JLabel("Наименование");
        n4 = new JLabel("Форма");
        n5 = new JLabel("Стоимость");

        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();
        text4 = new JTextField();
        text5 = new JTextField();

        text1.setPreferredSize(new Dimension(240, 40));
        text2.setPreferredSize(new Dimension(240, 40));
        text3.setPreferredSize(new Dimension(240, 40));
        text4.setPreferredSize(new Dimension(240, 40));
        text5.setPreferredSize(new Dimension(240, 40));

        forAdding.add(n1, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(n2, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(n3, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(n4, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(n5, new GridBagConstraints(0, 4, 1, 1, 1, 1,
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
        forAdding.add(text4, new GridBagConstraints(1, 3, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forAdding.add(text5, new GridBagConstraints(1, 4, 3, 1, 1, 1,
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
    public void actionPerformed(ActionEvent e) throws NullPointerException {
        if (e.getSource()==addingRow) {
            try {
                MedsTableModel armeds = new MedsTableModel();
                armeds.insert_data_meds(conn, "pharmacy_db.Изготовляемые_лекарства", Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()),
                        text3.getText(), text4.getText(), Integer.parseInt(text5.getText()));
                JOptionPane.showMessageDialog(null, "Данные добавлены в базу! " +
                                "Примечание: если данные не добавлены, значит номер " +
                                "добавляемого лекарства уже есть в базе или введён неверный индекс лекарства на складе.",
                        "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception w) {
                System.out.println(w);
                JOptionPane.showMessageDialog(null, "Проверьте " +
                                "корректность введённых данных. Поля номер лекарства и " +
                                "номер на складе со стоимостью должны быть вписаны.", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==getBackButton) {
            forAdding.dispose();
            new TableMeds();
        }
    }
}