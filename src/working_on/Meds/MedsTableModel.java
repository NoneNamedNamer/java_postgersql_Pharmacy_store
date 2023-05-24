package working_on.Meds;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MedsTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 38237145902853L;

    private final int columnCount = 5;
    private ArrayList<String[]> dataArrayList;

    public MedsTableModel() {
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
            case 0: return "Номер лекарства";
            case 1: return "Номер на складе";
            case 2: return "Наименование";
            case 3: return "Форма";
            case 4: return "Стоимость";
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

    public void read_data_meds(Connection conn, String table_name) {
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
                        rs.getString("Номер_лекарства"),
                        rs.getString("Номер_на_складе"),
                        rs.getString("Наименование"),
                        rs.getString("Форма"),
                        rs.getString("Стоимость")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_data_meds(Connection conn, String table_name, int n_med,
                            int n_on_wa, String name_med, String form, int price) {
        Statement statement;
        try {
            String query = String.format("insert into %s values(%d, '%d', '%s', '%s', '%d') on conflict (Номер_лекарства) do nothing;",
                    table_name, n_med, n_on_wa, name_med, form, price);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted in database!");
        } catch (Exception w) {
            System.out.println(w);
        }
    }

    public void delete_data_meds(Connection conn, String table_name, int n_med) {
        Statement statement;
        try {
            String query = String.format("delete from %s where Номер_лекарства = '%d'", table_name, n_med);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row deleted from database!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }

    public void update_data_meds(Connection conn, String table_name, String par, String what, int n_med) {
        Statement statement;
        try {
            String query = String.format("update %s set %s='%s' where Номер_лекарства = '%d'", table_name, par, what, n_med);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row updated!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }

    public void search_data_meds(Connection conn, String table_name, String param, String q) {
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
                        rs.getString("Номер_лекарства"),
                        rs.getString("Номер_на_складе"),
                        rs.getString("Наименование"),
                        rs.getString("Форма"),
                        rs.getString("Стоимость")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}