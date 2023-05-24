package working_on.Stats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class searchStatsPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 38437145902853L;
    Connection conn;

    StatsTableModel stm = new StatsTableModel();
    JTable statsTable = new JTable(stm);
    JButton srcRow = new JButton("Принять");
    JLabel cat = new JLabel("Категория");

    String[] pars = {"Не льготник", "Льготник"};
    JComboBox par = new JComboBox<>(pars);

    public searchStatsPanel(Connection conn) {
        this.conn = conn;
        setLayout(new GridBagLayout());
    }

    public void init() {
        JScrollPane scroll = new JScrollPane(statsTable);
        statsTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        add(scroll, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        add(cat, new GridBagConstraints(0, 1, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER,
                new Insets(1, 1, 1, 1), 0, 0));
        add(par, new GridBagConstraints(0, 2, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER,
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
                stm.search_data_stats(conn, String.valueOf(par.getSelectedItem()));
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
        }
    }
}