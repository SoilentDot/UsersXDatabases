package Vista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dashboard extends JPanel {

    private Connection connection;

    public Dashboard(int a, Connection connection) {
        this.connection = connection;

        setSize(1280, 720);
        setBackground(Color.decode("#1E1E1E"));
        setLayout(null);

        JLabel Escribe = new JLabel("Datos");
        // Font fuenteEscribe = new Font("Arial", Font.PLAIN, 20);
        // Administrador.setFont(fuenteEscribe);
        Escribe.setForeground(Color.CYAN);
        Escribe.setBounds(650, 40, 300, 30);
        add(Escribe);

        // JPaneles con bordes redondeados
        int tamXRP = 300;
        int posXRP = (1280 - tamXRP) / 2;

        PanelRedondo rPTexto = new PanelRedondo();
        rPTexto.setBounds(1280 / 2, 40, 600, 640);
        rPTexto.setBackground(Color.decode("#252526"));
        add(rPTexto);

        PanelRedondo rPIniciar2 = new PanelRedondo();
        rPIniciar2.setBounds(20, 620, 130, 50);
        rPIniciar2.setBackground(Color.decode("#252526"));
        add(rPIniciar2);
        setComponentZOrder(rPIniciar2, 0);

        /*
         * TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO
         */
        String texto = "";

        {
            try {
                String sql = "SELECT Texto FROM tabla_usuarios WHERE id = 1";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    texto = resultSet.getString("Texto");
                    System.out.println("El valor de la columna Texto es: " + texto);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showInputDialog(this);
            }
        }

        JTextArea campoTexto = new JTextArea(texto);
        campoTexto.setBounds(650, 65, 580, 590);
        campoTexto.setBackground(new Color(0, 0, 0, 0));
        campoTexto.setOpaque(false);
        campoTexto.setBorder(null);
        campoTexto.setCaretColor(Color.red);
        campoTexto.setForeground(Color.white);

        campoTexto.setLineWrap(true);
        campoTexto.setWrapStyleWord(true);

        campoTexto.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Acción a realizar cuando el mouse entra en el campo de texto
                System.out.println("Mouse entró en el campo de texto");

            }

        });
        campoTexto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("Se insertó texto en el campo de texto");
                try {
                    String sql = "UPDATE tabla_usuarios SET Texto = ? WHERE id = 1";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, campoTexto.getText());
                    statement.executeUpdate();
                    System.out.println("El texto se ha insertado en la base de datos correctamente.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showInputDialog(this);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Acción a realizar cuando se elimina texto del campo de texto
                System.out.println("Se eliminó texto del campo de texto");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método se usa generalmente para detectar cambios en atributos del
                // documento,
                // como los estilos de texto. Puede dejarlo vacío si no se necesita.
            }
        });

        if (a == 1) {
            // JLabel = Amdmin
            JLabel Administrador = new JLabel("Administrador");
            Font fuenteAdmin = new Font("Arial", Font.PLAIN, 20);
            Administrador.setFont(fuenteAdmin);
            Administrador.setForeground(Color.CYAN);
            Administrador.setBounds(20, 40, 300, 30);
            add(Administrador);

        } else {
            // JLabel = Usuario comun
            JLabel Administrador = new JLabel("Usuario");
            Font fuenteAdmin = new Font("Arial", Font.PLAIN, 20);
            Administrador.setFont(fuenteAdmin);
            Administrador.setForeground(Color.CYAN);
            Administrador.setBounds(20, 40, 300, 30);
            add(Administrador);
            campoTexto.setEditable(false);
        }
        add(campoTexto);
        setComponentZOrder(campoTexto, 0);

        // JLabel = Amdmin
        JLabel Cerrar = new JLabel("Cerrar sesión");
        Font fuenteAdmin = new Font("Arial", Font.PLAIN, 20);
        Cerrar.setFont(fuenteAdmin);
        Cerrar.setForeground(Color.CYAN);
        Cerrar.setBounds(25, 630, 300, 30);
        add(Cerrar);
        setComponentZOrder(Cerrar, 0);

        /*
         * BOTON BOTON BOTON BOTON BOTON BOTON BOTON BOTON BOTON BOTON
         */

        JButton boton = new JButton();

        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);

        boton.setBounds(20, 620, 130, 50);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                revalidate();
                repaint();
                PanelR b = new PanelR(connection);
                add(b);
            }
        });

        add(boton);
        setComponentZOrder(boton, 0);

    }

}
