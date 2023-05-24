package working_on;

import working_on.Appointments.TableAppoints;
import working_on.Clients.TableClients;
import working_on.Meds.TableMeds;
import working_on.Orders.TableOrders;
import working_on.OrdersWaiting.TableOrdersWaiting;
import working_on.Recipes.TableRecipes;
import working_on.Stats.TableStats;
import working_on.Warehouse.TableWH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage implements ActionListener {

    JFrame frame = new JFrame("База данных аптеки - Главное меню");
    JButton clTableButton = new JButton("Таблица клиентов"), recipesTableButton = new JButton("Таблица рецептов"),
            ordTableButton = new JButton("Таблица заказов"),
    appointsTableButton = new JButton("Таблица назначений"),
    medsTableButton = new JButton("Таблица лекарств"),
            whTableButton = new JButton("Склад"),
            statsTableButton = new JButton("Статистика"),
    owTableButton = new JButton("Заказы со статусом: В ожидании");
    JLabel mainText = new JLabel("Главное меню"),
    addFunctional = new JLabel("Дополнительный функционал");
    public MainPage() {
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clTableButton.setFocusable(false);
        clTableButton.addActionListener(this);

        recipesTableButton.setFocusable(false);
        recipesTableButton.addActionListener(this);

        ordTableButton.setFocusable(false);
        ordTableButton.addActionListener(this);

        appointsTableButton.setFocusable(false);
        appointsTableButton.addActionListener(this);

        medsTableButton.setFocusable(false);
        medsTableButton.addActionListener(this);

        whTableButton.setFocusable(false);
        whTableButton.addActionListener(this);

        statsTableButton.setFocusable(false);
        statsTableButton.addActionListener(this);

        owTableButton.setFocusable(false);
        owTableButton.addActionListener(this);

        frame.add(mainText, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(clTableButton, new GridBagConstraints(0, 1, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(recipesTableButton, new GridBagConstraints(0, 2, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(ordTableButton, new GridBagConstraints(0, 3, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(appointsTableButton, new GridBagConstraints(0, 4, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(medsTableButton, new GridBagConstraints(0, 5, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(whTableButton, new GridBagConstraints(0, 6, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.add(addFunctional, new GridBagConstraints(0, 7, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.add(statsTableButton, new GridBagConstraints(0, 8, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(owTableButton, new GridBagConstraints(0, 9, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==clTableButton) {
            frame.dispose();
            new TableClients();
        } else if (e.getSource()== recipesTableButton) {
            frame.dispose();
            new TableRecipes();
        } else if (e.getSource()==ordTableButton) {
            frame.dispose();
            new TableOrders();
        } else if (e.getSource()==appointsTableButton) {
            frame.dispose();
            new TableAppoints();
        } else if (e.getSource()==medsTableButton) {
            frame.dispose();
            new TableMeds();
        } else if (e.getSource()==whTableButton) {
            frame.dispose();
            new TableWH();
        } else if (e.getSource()==statsTableButton) {
            new TableStats();
        } else if (e.getSource()==owTableButton) {
            new TableOrdersWaiting();
        }
    }
}