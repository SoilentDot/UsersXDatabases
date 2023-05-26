package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Registro extends JFrame implements ActionListener {

    private Connection connection;

    public Registro(Connection connection) {
        this.connection = connection;
        PanelR b = new PanelR(connection);
        add(b);

        int lx = (int) (1920 / 1.5);
        int ly = (int) (1080 / 1.5);

        setTitle("Registro de usuario");
        setSize(lx, ly);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.BLACK);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

    }
}
