package working_on.OrdersWaiting;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class searchOWPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 38437145902853L;
    Connection conn;

    OWTableModel owtm = new OWTableModel();
    JTable owTable = new JTable(owtm);

    public searchOWPanel(Connection conn) {
        this.conn = conn;
        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init() {
        JScrollPane scroll = new JScrollPane(owTable);
        owTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        add(scroll, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
    }

    @Override
    public void run() {
        while (true) {
            try {
                owtm.search_data_ow(conn);
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException r) {
                r.printStackTrace();
            }
        }
    }
}