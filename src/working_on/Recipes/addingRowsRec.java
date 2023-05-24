package working_on.Recipes;

import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class addingRowsRec extends JFrame implements ActionListener {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forAdding = new JFrame("Добавление рецептов");
    JButton addingRow, getBackButton;
    JTextField text1, text2, text3, text4;
    JLabel n1, n2, n3, n4;

    public addingRowsRec() {
        forAdding.getContentPane().setLayout(new GridBagLayout());
        forAdding.setLocationRelativeTo(null);
        forAdding.setSize(new Dimension(1000, 400));
        forAdding.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addingRow = new JButton("Принять");
        getBackButton = new JButton("Назад");
        addingRow.addActionListener(this);
        getBackButton.addActionListener(this);

        n1 = new JLabel("Номер рецепта");
        n2 = new JLabel("Номер клиента");
        n3 = new JLabel("ФИО врача");
        n4 = new JLabel("Диагноз");

        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();
        text4 = new JTextField();

        text1.setPreferredSize(new Dimension(240, 40));
        text2.setPreferredSize(new Dimension(240, 40));
        text3.setPreferredSize(new Dimension(240, 40));
        text4.setPreferredSize(new Dimension(240, 40));

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
                RecipesTableModel arrec = new RecipesTableModel();
                arrec.insert_data_rec(conn, "pharmacy_db.Рецепты", Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()),
                        text3.getText(), text4.getText());
                JOptionPane.showMessageDialog(null, "Данные добавлены в базу! Примечание: " +
                                "если данные не добавлены, значит рецепт с данным номером рецепта уже существует.",
                        "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception w) {
                System.out.println(w);
                JOptionPane.showMessageDialog(null, "Проверьте " +
                                "корректность введённых данных. Поля номер рецепта и " +
                                "номер клиента должны быть вписаны. Если не помните " +
                                "номер клиента проверьте таблицу клиентов.", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==getBackButton) {
            forAdding.dispose();
            new TableRecipes();
        }
    }
}