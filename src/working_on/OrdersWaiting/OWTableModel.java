package working_on.OrdersWaiting;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OWTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 38237145902853L;

    private final int columnCount = 10;
    private ArrayList<String[]> dataArrayList;

    public OWTableModel() {
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
            case 4: return "Номер заказа";
            case 5: return "Номер лекарства";
            case 6: return "Наименование";
            case 7: return "Форма";
            case 8: return "Количество";
            case 9: return "Нужно";
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

    public void search_data_ow(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT Клиенты.Номер_клиента, Клиенты.Фамилия, Клиенты.Имя, Клиенты.Отчество, Заказы.Номер_заказа, Изготовляемые_лекарства.Номер_лекарства, Изготовляемые_лекарства.Наименование, Изготовляемые_лекарства.Форма, Склад.Количество, Назначения.Количество AS \"Нужно\"\n" +
                    "FROM pharmacy_db.Склад INNER JOIN (((pharmacy_db.Клиенты INNER JOIN pharmacy_db.Рецепты ON Клиенты.Номер_клиента = Рецепты.Номер_клиента) INNER JOIN pharmacy_db.Заказы ON Рецепты.Номер_рецепта = Заказы.Номер_рецепта) INNER JOIN (pharmacy_db.Изготовляемые_лекарства INNER JOIN pharmacy_db.Назначения ON Изготовляемые_лекарства.Номер_лекарства = Назначения.Номер_лекарства) ON Рецепты.Номер_рецепта = Назначения.Номер_рецепта) ON Склад.Номер_товара = Изготовляемые_лекарства.Номер_на_складе\n" +
                    "WHERE (((Заказы.Статус)='В ожидании'));");
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
                        rs.getString("Номер_заказа"),
                        rs.getString("Номер_лекарства"),
                        rs.getString("Наименование"),
                        rs.getString("Форма"),
                        rs.getString("Количество"),
                        rs.getString("Нужно")
                };
                addData(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}