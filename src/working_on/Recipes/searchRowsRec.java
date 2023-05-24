package working_on.Recipes;

import working_on.DbConnection;
import working_on.Recipes.searchWork.RecipesPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class searchRowsRec extends JFrame {
    DbConnection db = new DbConnection();
    Connection conn = db.connect_to_db("pharmacy_base", "postgres", "qwe123");
    JFrame forSearch = new JFrame("Поиск данных для рецептов");

    searchRowsRec() {
        forSearch.getContentPane().setLayout(new BorderLayout());
        forSearch.setSize(new Dimension(1000, 400));
        forSearch.setLocationRelativeTo(null);
        forSearch.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        RecipesPanel recipesPanel = new RecipesPanel(conn);
        recipesPanel.init();

        forSearch.add(recipesPanel, BorderLayout.CENTER);

        forSearch.pack();
        forSearch.setVisible(true);
    }
}