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
import java.util.Timer;
import java.util.TimerTask;

public class PanelR extends JPanel {
    final int[] posYEU = { 170, 0 };
    final int[] posYEP = { 240, 0 };
    private Connection connection;
    JLabel etiquetaUsuario;
    JLabel etiquetaPassword;
    JButton boton;
    JButton BotonRegistro;

    JTextField campoUsuario;
    JPasswordField campoPassword;
    JPasswordField campoCPassword;

    public PanelR(Connection connection) {
        this.connection = connection;
        setSize(1280, 720);
        setBackground(Color.decode("#1E1E1E"));
        setLayout(null);

        // int lx = 1280; // (int) (1920 / 1.5);
        // int ly = 720; // (int) (1080 / 1.5);

        // int lxx = lx / 3;

        // JLabel = Iniciar sesión
        JLabel iniciarS = new JLabel("Iniciar sesión");
        Font fuenteS = new Font("Arial", Font.PLAIN, 30);
        iniciarS.setFont(fuenteS);
        iniciarS.setForeground(Color.CYAN);

        // Obtener el tamaño en X del JLabel
        int iX = iniciarS.getPreferredSize().width;
        // Ancho del JPanel - ancho del label / 2 = Texto centrado
        int labelX = (getWidth() - iX) / 2;
        iniciarS.setBounds(labelX - 4, 100, iX + 5, 30);
        add(iniciarS);

        // JLabel = Entrar
        JLabel Entrar = new JLabel("Entrar");
        Font fuenteE = new Font("Arial", Font.PLAIN, 20);
        Entrar.setFont(fuenteE);
        Entrar.setForeground(Color.CYAN);
        int iXE = Entrar.getPreferredSize().width;
        int labelXE = (getWidth() - iXE) / 2;
        Entrar.setBounds(labelXE - 4, 300, iXE + 5, 30);
        add(Entrar);
        setComponentZOrder(Entrar, 0);
        Entrar.repaint();

        JLabel Registro = new JLabel("Registrarse");
        Font fuenteR = new Font("Arial", Font.PLAIN, 20);
        Registro.setFont(fuenteR);
        Registro.setForeground(Color.CYAN);
        int iXR = Entrar.getPreferredSize().width;
        int labelXR = (getWidth() - iXR) / 2;
        Registro.setBounds(labelXR - 29, 370, iXR + 50, 30);
        Registro.setVisible(false);
        add(Registro);
        setComponentZOrder(Registro, 0);

        // JPaneles con bordes redondeados
        int tamXRP = 300;
        int posXRP = (1280 - tamXRP) / 2;

        PanelRedondo rPUsuario = new PanelRedondo();
        rPUsuario.setBounds(posXRP - 4, 150, tamXRP, 50);
        rPUsuario.setBackground(Color.decode("#252526"));
        add(rPUsuario);

        PanelRedondo rPContrasena = new PanelRedondo();
        rPContrasena.setBounds(posXRP - 4, 220, tamXRP, 50);
        rPContrasena.setBackground(Color.decode("#252526"));
        add(rPContrasena);

        PanelRedondo rPIniciar = new PanelRedondo();
        rPIniciar.setBounds(posXRP - 4, 290, tamXRP, 50);
        rPIniciar.setBackground(Color.decode("#252526"));
        add(rPIniciar);

        PanelRedondo rPIniciar2 = new PanelRedondo();
        rPIniciar2.setBounds(posXRP - 4, 360, tamXRP, 50);
        rPIniciar2.setBackground(Color.decode("#252526"));
        rPIniciar2.setVisible(false);
        add(rPIniciar2);

        /*
         * USUARIO USUARIO USUARIO USUARIO USUARIO USUARIO USUARIO USUARIO
         */

        etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setBounds(500, posYEU[0], 50, 15);
        etiquetaUsuario.setForeground(Color.cyan);
        add(etiquetaUsuario);
        setComponentZOrder(etiquetaUsuario, 0);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(500, 170, 250, 20);
        campoUsuario.setBackground(new Color(0, 0, 0, 0));
        campoUsuario.setOpaque(false);
        campoUsuario.setBorder(null);
        campoUsuario.setCaretColor(Color.red);
        campoUsuario.setForeground(Color.white);

        campoUsuario.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Acción a realizar cuando el mouse entra en el campo de texto
                System.out.println("Mouse entró en el campo de texto");
                if (posYEU[1] != 2) {
                    moverUsuario(0);
                }
                ;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Acción a realizar cuando el mouse sale del campo de texto
                System.out.println("Mouse salió del campo de texto");
                if (posYEU[1] != 2) {
                    moverUsuario(1);
                }
                ;
            }

        });
        campoUsuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Acción a realizar cuando se inserta texto en el campo de texto
                System.out.println("Se insertó texto en el campo de texto");
                moverUsuario(2);
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
        add(campoUsuario);
        setComponentZOrder(campoUsuario, 0);

        /*
         * CONTRASEÑA CONTRASEÑA CONTRASEÑA CONTRASEÑA CONTRASEÑA CONTRASEÑA CONTRASEÑA
         */
        etiquetaPassword = new JLabel("Contraseña:");
        etiquetaPassword.setBounds(500, posYEP[0], 100, 10);
        etiquetaPassword.setForeground(Color.cyan);
        add(etiquetaPassword);
        setComponentZOrder(etiquetaPassword, 0);

        JLabel etiquetaCPassword = new JLabel("Confirmar contraseña:");
        etiquetaCPassword.setBounds(500, 300, 150, 10);
        etiquetaCPassword.setForeground(Color.cyan);
        add(etiquetaCPassword);
        etiquetaCPassword.setVisible(false);
        setComponentZOrder(etiquetaCPassword, 0);

        campoPassword = new JPasswordField();
        campoPassword.setBounds(500, 240, 250, 20);
        campoPassword.setBackground(new Color(0, 0, 0, 0));
        campoPassword.setOpaque(false);
        campoPassword.setBorder(null);
        campoPassword.setCaretColor(Color.red);
        campoPassword.setForeground(Color.white);

        campoCPassword = new JPasswordField();
        campoCPassword.setBounds(500, 310, 250, 20);
        campoCPassword.setBackground(new Color(0, 0, 0, 0));
        campoCPassword.setOpaque(false);
        campoCPassword.setBorder(null);
        campoCPassword.setCaretColor(Color.red);
        campoCPassword.setForeground(Color.white);
        add(campoCPassword);
        setComponentZOrder(campoCPassword, 0);

        campoPassword.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Acción a realizar cuando el mouse entra en el campo de texto
                System.out.println("Mouse entró en el campo de texto");
                if (posYEP[1] != 2) {
                    moverContrasena(0);
                }
                ;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Acción a realizar cuando el mouse sale del campo de texto
                System.out.println("Mouse salió de");
                if (posYEP[1] != 2) {
                    moverContrasena(1);
                }
                ;
            }

        });
        campoPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Acción a realizar cuando se inserta texto en el campo de texto
                System.out.println("Se insertó texto en contra");
                moverContrasena(2);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Acción a realizar cuando se elimina texto del campo de texto
                System.out.println("Se eliminó ");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método se usa generalmente para detectar cambios en atributos del
                // documento,
                // como los estilos de texto. Puede dejarlo vacío si no se necesita.
            }
        });
        add(campoPassword);
        setComponentZOrder(campoPassword, 0);

        /*
         * BOTON BOTON BOTON BOTON BOTON BOTON BOTON BOTON BOTON BOTON
         */

        // ToggleButton
        JLabel Registrarse = new JLabel("Registrarse");
        Font FuenteRI = new Font(TOOL_TIP_TEXT_KEY, Font.PLAIN, 10);
        Registrarse.setFont(FuenteRI);
        Registrarse.setBounds(500, 270, 80, 20);
        Registrarse.setForeground(Color.cyan);
        add(Registrarse);
        setComponentZOrder(Registrarse, 0);

        JToggleButton RIS = new JToggleButton();
        RIS.setBounds(500, 270, 60, 20);
        RIS.setOpaque(false);
        RIS.setContentAreaFilled(false);
        RIS.setBorderPainted(false);
        RIS.setFocusPainted(false);
        RIS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (RIS.isSelected()) {
                    // Cambiar a registrarse
                    Registrarse.setText("Iniciar sesión");
                    etiquetaCPassword.setVisible(true);
                    Entrar.setVisible(false);
                    Registro.setVisible(true);
                    rPIniciar2.setVisible(true);
                    BotonRegistro.setVisible(true);

                    etiquetaCPassword.repaint();
                    Entrar.repaint();
                    campoCPassword.setBounds(500, 310, 250, 20);
                    setComponentZOrder(campoCPassword, 0);
                    campoCPassword.setVisible(true);

                } else {
                    Registrarse.setText("Registrarse");
                    etiquetaCPassword.setVisible(false);
                    Entrar.setVisible(true);
                    Registro.setVisible(false);
                    rPIniciar2.setVisible(false);
                    campoCPassword.setVisible(false);
                    BotonRegistro.setVisible(false);

                    etiquetaCPassword.repaint();
                    Entrar.repaint();
                }
            }
        });

        add(RIS);

        boton = new JButton();
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setBounds(posXRP - 4, 290, tamXRP, 45);

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (!RIS.isSelected()) {
                    System.out.println("Entro al boton");
                    rPIniciar.setBackground(Color.decode("#767676"));
                    etiquetaCPassword.repaint();
                    Entrar.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse salió del botón");
                rPIniciar.setBackground(Color.decode("#252526"));
                etiquetaCPassword.repaint();
                Entrar.repaint();

            }

        });
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IniciarSesion();

            }
        });

        add(boton);
        setComponentZOrder(boton, 0);

        BotonRegistro = new JButton();
        BotonRegistro.setOpaque(false);
        BotonRegistro.setContentAreaFilled(false);
        BotonRegistro.setBorderPainted(false);
        BotonRegistro.setFocusPainted(false);
        BotonRegistro.setBounds(posXRP - 4, 360, tamXRP, 50);
        BotonRegistro.setVisible(false);

        BotonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrarse();
            }
        });
        add(BotonRegistro);

        Entrar.repaint();
        etiquetaCPassword.repaint();
    }

    public void moverUsuario(int a) {
        posYEU[1] = a;
        if (posYEU[1] == 0) {

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (count <= 10) {
                        posYEU[0] -= 1;
                        etiquetaUsuario.setBounds(500, posYEU[0], 50, 10);
                        etiquetaUsuario.repaint();
                        System.out.println(posYEU[0]);
                        count++;
                    } else {
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 0, 5);
        }
        if (posYEU[1] == 1) {

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (count <= 10) {
                        posYEU[0] += 1;
                        etiquetaUsuario.setBounds(500, posYEU[0], 50, 10);
                        etiquetaUsuario.repaint();
                        System.out.println(posYEU[0]);
                        count++;
                    } else {
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 0, 2);
        }
        if (posYEU[0] == 170 && posYEU[1] == 2) {

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (count <= 10) {
                        posYEU[0] -= 1;
                        etiquetaUsuario.setBounds(500, posYEU[0], 50, 10);
                        etiquetaUsuario.repaint();
                        System.out.println(posYEU[0]);
                        count++;
                    } else {
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 0, 2);
        }
    }

    public void moverContrasena(int a) {
        posYEP[1] = a;
        if (posYEP[1] == 0) {

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (count <= 10) {
                        posYEP[0] -= 1;
                        etiquetaPassword.setBounds(500, posYEP[0], 80, 10);
                        etiquetaPassword.repaint();
                        System.out.println(posYEP[0]);
                        count++;
                    } else {
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 0, 2);
        }
        if (posYEP[1] == 1) {

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (count <= 10) {
                        posYEP[0] += 1;
                        etiquetaPassword.setBounds(500, posYEP[0], 80, 10);
                        etiquetaPassword.repaint();
                        System.out.println(posYEP[0]);
                        count++;
                    } else {
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 0, 5);
        }
        if (posYEP[0] == 240 && posYEP[1] == 2) {

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (count <= 10) {
                        posYEP[0] -= 1;
                        etiquetaPassword.setBounds(500, posYEP[0], 50, 10);
                        etiquetaPassword.repaint();
                        System.out.println(posYEP[0]);
                        count++;
                    } else {
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 0, 2);
        }
    }

    void Registrarse() {

        String user = campoUsuario.getText();

        char[] con = campoPassword.getPassword();
        String contrasena = new String(con);

        char[] conC = campoCPassword.getPassword();
        String contrasenaConfirmar = new String(conC);

        System.out.println("Usuario: " + user + " |Contraseña: " + contrasena + " |Confirmar Contraseña: "
                + contrasenaConfirmar);

        if (user.isEmpty() || contrasena.isEmpty() || contrasenaConfirmar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!contrasena.equals(contrasenaConfirmar)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sql = "INSERT INTO tabla_usuarios (usuario, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, contrasena);

            int filasAfectadas = statement.executeUpdate();
            System.out.println(filasAfectadas + "");

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar el registro", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void IniciarSesion() {
        String user = campoUsuario.getText();

        char[] contrasena = campoPassword.getPassword();
        String contrasenaString = new String(contrasena);

        try {
            String sql = "SELECT * FROM tabla_usuarios WHERE usuario = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, contrasenaString);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // El usuario y la contraseña coinciden, realizar acciones adicionales aquí
                System.out.println("Inicio de sesión exitoso");
                int Rol = resultSet.getInt("Rol");

                removeAll();
                revalidate();
                repaint();
                if (Rol == 1) {
                    Dashboard z = new Dashboard(Rol, connection);
                    System.out.println("Iniciaste como Admin");
                    add(z);
                } else {
                    Dashboard z = new Dashboard(Rol, connection);
                    System.out.println("Iniciaste sin permisos");
                    add(z);
                }
                System.out.println(Rol);

            } else {
                // El usuario y/o la contraseña no coinciden, mostrar mensaje de error
                System.out.println("Inicio de sesión fallido. Usuario o contraseña incorrectos");
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("Usuario: " + user + " |Contraseña: " + contrasenaString);

    }

}
