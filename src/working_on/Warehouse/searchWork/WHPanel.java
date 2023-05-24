package working_on.Warehouse.searchWork;

import working_on.Warehouse.WHTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class WHPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 38437145902853L;
    Connection conn;

    WHTableModel whtm = new WHTableModel();
    JTable whTable = new JTable(whtm);
    JButton srcRow = new JButton("Принять");

    JLabel n1 = new JLabel("Столбец");
    JLabel n2 = new JLabel("По параметру");

    JTextField text1 = new JTextField();

    String[] pars = {"Номер_товара", "Количество", "Критическая_норма"};
    JComboBox par = new JComboBox<>(pars);

    public WHPanel(Connection conn) {
        this.conn = conn;
        setLayout(new GridBagLayout());
    }

    public void init() {
        JScrollPane scroll = new JScrollPane(whTable);
        whTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
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
                whtm.search_data_wh(conn, "pharmacy_db.Склад", String.valueOf(par.getSelectedItem()), text1.getText());
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
        }
    }
}