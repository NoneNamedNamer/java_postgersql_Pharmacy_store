package working_on.Recipes.searchWork;

import working_on.Recipes.RecipesTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class RecipesPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 38437145902853L;
    Connection conn;

    RecipesTableModel rtm = new RecipesTableModel();
    JTable recipesTable = new JTable(rtm);
    JButton srcRow = new JButton("Принять");
    JButton getBackButton = new JButton("Назад");

    JLabel n1 = new JLabel("Столбец");
    JLabel n2 = new JLabel("По параметру");

    JTextField text1 = new JTextField();

    String[] pars = {"Номер_рецепта", "Номер_клиента", "ФИО_врача", "Диагноз"};
    JComboBox par = new JComboBox<>(pars);

    public RecipesPanel(Connection conn) {
        this.conn = conn;
        setLayout(new GridBagLayout());
    }

    public void init() {
        JScrollPane scroll = new JScrollPane(recipesTable);
        recipesTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        add(scroll, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        add(par, new GridBagConstraints(1, 1, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        add(n1, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(n2, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(text1, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        add(srcRow, new GridBagConstraints(0, 3, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        srcRow.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == srcRow) {
            try {
                rtm.search_data_rec(conn, "pharmacy_db.Рецепты", String.valueOf(par.getSelectedItem()), text1.getText());
                recipesTable.repaint();
                Thread.sleep(100);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
        }
    }
}