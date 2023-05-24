package working_on.Clients;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientsTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 38237145902853L;

    private final int columnCount = 8;
    private ArrayList<String[]> dataArrayList;

    public ClientsTableModel() {
        dataArrayList = new ArrayList<String[]>();
        for (int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Номер клиента";
            case 1: return "Фамилия";
            case 2: return "Имя";
            case 3: return "Отчество";
            case 4: return "Дата рождения";
            case 5: return "Адрес";
            case 6: return "Телефон";
            case 7: return "Категория";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void addData(String[] row) {
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    public void read_data_cl(Connection conn, String table_name) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            statement.executeQuery(query);
            rs = statement.executeQuery(query);
            dataArrayList.clear();
            while (rs.next()) {
                String[] row = {
                        rs.getString("Номер_клиента"),
                        rs.getString("Фамилия"),
                        rs.getString("Имя"),
                        rs.getString("Отчество"),
                        rs.getString("Дата_рождения"),
                        rs.getString("Адрес"),
                        rs.getString("Телефон"),
                        rs.getString("Категория")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_data(Connection conn, String table_name, int n_cl,
                            String fa, String name, String otch, String date_birth,
                            String address, String ph_num, String cat) {
        Statement statement;
        try {
            String query = String.format("insert into %s values(%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s') on conflict (Номер_клиента) do nothing;",
                    table_name, n_cl, fa, name, otch, date_birth, address, ph_num, cat);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted in database!");
        } catch (Exception w) {
            System.out.println(w);
        }
    }

    public void delete_data(Connection conn, String table_name, int n_cl) {
        Statement statement;
        try {
            String query = String.format("delete from %s where Номер_клиента = '%d'", table_name, n_cl);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row deleted from database!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }

    public void update_data(Connection conn, String table_name, String par, String what, int n_cl) {
        Statement statement;
        try {
            String query = String.format("update %s set %s='%s' where Номер_клиента = '%d'", table_name, par, what, n_cl);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row updated!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }

    public void search_data(Connection conn, String table_name, String param, String q) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s where %s='%s'", table_name, param, q);
            statement = conn.createStatement();
            statement.executeQuery(query);
            rs = statement.executeQuery(query);
            dataArrayList.clear();
            while (rs.next()) {
                String[] row = {
                        rs.getString("Номер_клиента"),
                        rs.getString("Фамилия"),
                        rs.getString("Имя"),
                        rs.getString("Отчество"),
                        rs.getString("Дата_рождения"),
                        rs.getString("Адрес"),
                        rs.getString("Телефон"),
                        rs.getString("Категория")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}