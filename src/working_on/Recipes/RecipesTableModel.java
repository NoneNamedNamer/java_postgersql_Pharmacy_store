package working_on.Recipes;

import javax.swing.table.AbstractTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RecipesTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 38237145902853L;

    private final int columnCount = 4;
    private ArrayList<String[]> dataArrayList;

    public RecipesTableModel() {
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
            case 0: return "Номер рецепта";
            case 1: return "Номер клиента";
            case 2: return "ФИО врача";
            case 3: return "Диагноз";
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

    public void read_data_rec(Connection conn, String table_name) {
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
                        rs.getString("Номер_рецепта"),
                        rs.getString("Номер_клиента"),
                        rs.getString("ФИО_врача"),
                        rs.getString("Диагноз")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_data_rec(Connection conn, String table_name, int n_rec,
                            int n_cl, String name_doc, String dia) {
        Statement statement;
        try {
            String query = String.format("insert into %s values(%d, '%d', '%s', '%s') on conflict (Номер_рецепта) do nothing;",
                    table_name, n_rec, n_cl, name_doc, dia);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted in database!");
        } catch (Exception w) {
            System.out.println(w);
        }
    }

    public void delete_data_rec(Connection conn, String table_name, int n_rec) {
        Statement statement;
        try {
            String query = String.format("delete from %s where Номер_рецепта = '%d'", table_name, n_rec);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row deleted from database!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }


    public void update_data_rec(Connection conn, String table_name, String par, String what, int n_rec) {
        Statement statement;
        try {
            String query = String.format("update %s set %s='%s' where Номер_рецепта = '%d'", table_name, par, what, n_rec);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row updated!");
        } catch (Exception r) {
            System.out.println(r);
        }
    }

    public void search_data_rec(Connection conn, String table_name, String param, String q) {
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
                        rs.getString("Номер_рецепта"),
                        rs.getString("Номер_клиента"),
                        rs.getString("ФИО_врача"),
                        rs.getString("Диагноз")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}