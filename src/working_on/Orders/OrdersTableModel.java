package working_on.Orders;

import javax.swing.table.AbstractTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdersTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 38237145902853L;

    private final int columnCount = 7;
    private ArrayList<String[]> dataArrayList;

    public OrdersTableModel() {
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
            case 0: return "Номер заказа";
            case 1: return "Номер рецепта";
            case 2: return "Дата приема";
            case 3: return "Дата изготовления";
            case 4: return "Дата выдачи";
            case 5: return "Стоимость";
            case 6: return "Статус";
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

    public void read_data_ord(Connection conn, String table_name) {
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
                        rs.getString("Номер_заказа"),
                        rs.getString("Номер_рецепта"),
                        rs.getString("Дата_приема"),
                        rs.getString("Дата_изготовления"),
                        rs.getString("Дата_выдачи"),
                        rs.getString("Стоимость"),
                        rs.getString("Статус")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_data_ord(Connection conn, String table_name, int n_ord,
                            int n_rec, String date_rec, String date_make, String date_get,
                            int price, String status) {
        Statement statement;
        try {
            String query = String.format("insert into %s values(%d, '%d', '%s', '%s', '%s', '%d', '%s') on conflict (Номер_заказа) do nothing;",
                    table_name, n_ord, n_rec, date_rec, date_make, date_get, price, status);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted in database!");
        } catch (Exception w) {
            System.out.println(w);
        }
    }

    public void delete_data_ord(Connection conn, String table_name, int n_ord) {
        Statement statement;
        try {
            String query = String.format("delete from %s where Номер_заказа = '%d'", table_name, n_ord);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row deleted from database!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }

    public void update_data_ord(Connection conn, String table_name, String par, String what, int n_ord) {
        Statement statement;
        try {
            String query = String.format("update %s set %s='%s' where Номер_заказа = '%d'", table_name, par, what, n_ord);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row updated!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }

    public void search_data_ord(Connection conn, String table_name, String param, String q) {
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
                        rs.getString("Номер_заказа"),
                        rs.getString("Номер_рецепта"),
                        rs.getString("Дата_приема"),
                        rs.getString("Дата_изготовления"),
                        rs.getString("Дата_выдачи"),
                        rs.getString("Стоимость"),
                        rs.getString("Статус")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}