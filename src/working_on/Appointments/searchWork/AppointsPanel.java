package working_on.Appointments.searchWork;

import working_on.Appointments.AppointsTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AppointsPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 38437145902853L;
    Connection conn;

    AppointsTableModel atm = new AppointsTableModel();
    JTable appointsTable = new JTable(atm);
    JButton srcRow = new JButton("Принять");
    JLabel n1 = new JLabel("Столбец");
    JLabel n2 = new JLabel("По параметру");

    JTextField text1 = new JTextField();

    String[] pars = {"Номер_лекарства", "Номер_рецепта", "Количество"};
    JComboBox par = new JComboBox<>(pars);

    public AppointsPanel(Connection conn) {
        this.conn = conn;
        setLayout(new GridBagLayout());
    }

    public void init() {
        JScrollPane scroll = new JScrollPane(appointsTable);
        appointsTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
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
                atm.search_data_app(conn, "pharmacy_db.Назначения", String.valueOf(par.getSelectedItem()), text1.getText());
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
        }
    }
}