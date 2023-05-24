package working_on.Stats;

import javax.swing.table.AbstractTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StatsTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 38237145902853L;

    private final int columnCount = 3;
    private ArrayList<String[]> dataArrayList;

    public StatsTableModel() {
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
            case 0: return "Категория";
            case 1: return "Количество заказов";
            case 2: return "Суммарная стоимость заказов";
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

    public void search_data_stats(Connection conn, String what) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT Клиенты.Категория, Count(Заказы.Статус) AS \"Количество заказов\", Sum(Заказы.Стоимость) AS \"Суммарная стоимость заказов\"\n" +
                    "FROM (pharmacy_db.Клиенты INNER JOIN pharmacy_db.Рецепты ON Клиенты.Номер_клиента = Рецепты.Номер_клиента) INNER JOIN pharmacy_db.Заказы ON Рецепты.Номер_рецепта = Заказы.Номер_рецепта where Категория='%s'\n" +
                    "GROUP BY Клиенты.Категория;", what);
            statement = conn.createStatement();
            statement.executeQuery(query);
            rs = statement.executeQuery(query);
            dataArrayList.clear();
            while (rs.next()) {
                String[] row = {
                        rs.getString("Категория"),
                        rs.getString("Количество заказов"),
                        rs.getString("Суммарная стоимость заказов")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}