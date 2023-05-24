package working_on.Recipes;

import working_on.DbConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class updRowsRec extends JFrame implements ActionListener {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forUpd = new JFrame("Обновление данных");

    JButton updRow, getBackButton;

    JLabel n1, n2, n3;

    JTextField text1, text2;

    JComboBox par;

    updRowsRec() {
        forUpd.getContentPane().setLayout(new GridBagLayout());
        forUpd.setSize(new Dimension(1000, 400));
        forUpd.setLocationRelativeTo(null);
        forUpd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        updRow = new JButton("Принять");
        getBackButton = new JButton("Назад");
        updRow.addActionListener(this);
        getBackButton.addActionListener(this);

        n1 = new JLabel("Столбец");
        n2 = new JLabel("На что");
        n3 = new JLabel("Номер рецепта");

        text1 = new JTextField();
        text2 = new JTextField();
        text1.setPreferredSize(new Dimension(240, 40));
        text2.setPreferredSize(new Dimension(240, 40));

        String[] pars = {"Номер_клиента", "ФИО_врача", "Диагноз"};
        par = new JComboBox<>(pars);

        forUpd.add(par, new GridBagConstraints(1, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forUpd.add(n1, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        forUpd.add(n2, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        forUpd.add(n3, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        forUpd.add(text1, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        forUpd.add(text2, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        forUpd.add(updRow, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        forUpd.add(getBackButton, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        forUpd.pack();
        forUpd.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==updRow) {
            try {
                RecipesTableModel urrec = new RecipesTableModel();
                urrec.update_data_rec(conn, "pharmacy_db.Рецепты", String.valueOf(par.getSelectedItem()), text1.getText(),  Integer.parseInt(text2.getText()));
                JOptionPane.showMessageDialog(null, "Данные изменены!",
                        "Подтверждение", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception r) {
                System.out.println(r);
                JOptionPane.showMessageDialog(null, "Проверьте корректность данных.",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==getBackButton) {
            forUpd.dispose();
            new TableRecipes();
        }
    }
}