import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    protected JPanel miPanel;
    private JTextField txtUsuario;
    private JButton btnLogin;
    private JButton btnCancelar;
    private JPasswordField txtPassword;

    public login() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(txtPassword.getPassword());

                if (txtUsuario.getText().equals("user") && password.equals("4567")) {
                    // Bienvenida para el usuario
                    dispose();
                    abrirVentana("user");
                } else if (txtUsuario.getText().equals("admin") && password.equals("1234")) {
                    // Bienvenida para el administrador
                    dispose();
                    abrirVentana("admin");
                } else {
                    // Usuario o contraseña incorrectos
                    JOptionPane.showMessageDialog(miPanel, "Usuario o/y contraseña incorrectos.", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void abrirVentana(String tipoUsuario) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if ("user".equals(tipoUsuario)) {
                    ventanaPacientes vPacientes = new ventanaPacientes();
                    vPacientes.setContentPane(vPacientes.miPanel);
                    vPacientes.setSize(500, 500);
                    vPacientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    vPacientes.setVisible(true);
                } else if ("admin".equals(tipoUsuario)) {
                    ventanaDoctores vDoctores = new ventanaDoctores();
                    vDoctores.setContentPane(vDoctores.miPanel);
                    vDoctores.setSize(500, 500);
                    vDoctores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    vDoctores.setVisible(true);
                }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                login vLogin = new login();
                vLogin.setContentPane(vLogin.miPanel);
                vLogin.setSize(300, 300);
                vLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                vLogin.setVisible(true);
            }
        });
    }
}