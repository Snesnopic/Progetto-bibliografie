package guipkg;

import ctrlpkg.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private final JPanel loginWindow;
    private final JTextField IDField;


    public LoginFrame(Controller c) throws IOException {
        setIconImage(ImageIO.read(getClass().getResource("/logo.png")));

        setBackground(Color.WHITE);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);

        loginWindow = new JPanel();
        loginWindow.setBackground(new Color(23, 33, 43));
        setContentPane(loginWindow);
        GridBagLayout gbl_loginWindow = new GridBagLayout();
        gbl_loginWindow.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_loginWindow.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_loginWindow.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_loginWindow.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        loginWindow.setLayout(gbl_loginWindow);
        JLabel uninaLogoLabel = new JLabel("");
        uninaLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        uninaLogoLabel.setIcon(new StretchIcon(getClass().getClassLoader().getResource("unina_logo.png")));
        GridBagConstraints gbc_uninaLogoLabel = new GridBagConstraints();
        gbc_uninaLogoLabel.gridwidth = 2;
        gbc_uninaLogoLabel.fill = GridBagConstraints.BOTH;
        gbc_uninaLogoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_uninaLogoLabel.gridx = 1;
        gbc_uninaLogoLabel.gridy = 0;
        loginWindow.add(uninaLogoLabel, gbc_uninaLogoLabel);
        JLabel loginLabel = new JLabel("Effettua l'accesso");
        loginLabel.setBackground(new Color(255, 255, 255));
        loginLabel.setForeground(new Color(255, 255, 255));
        loginLabel.setVerticalAlignment(SwingConstants.TOP);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 41));
        GridBagConstraints gbc_loginLabel = new GridBagConstraints();
        gbc_loginLabel.gridwidth = 2;
        gbc_loginLabel.anchor = GridBagConstraints.NORTH;
        gbc_loginLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
        gbc_loginLabel.gridx = 1;
        gbc_loginLabel.gridy = 1;
        loginWindow.add(loginLabel, gbc_loginLabel);
        JButton loginButton = new JButton("Effettua l'accesso");
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        this.getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (IDField.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Errore di input");
                else {
                    IDField.setText(IDField.getText().toUpperCase());
                    try {
                        c.login(IDField.getText());

                    } catch (HeadlessException | IOException e1) {
                        JOptionPane.showMessageDialog(null, "Errore: " + e1.getMessage());
                    }
                }
            }
        });

        JLabel IDLabel = new JLabel("ID Utente");
        IDLabel.setBackground(new Color(255, 255, 255));
        IDLabel.setForeground(new Color(255, 255, 255));
        IDLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        GridBagConstraints gbc_IDLabel = new GridBagConstraints();
        gbc_IDLabel.fill = GridBagConstraints.BOTH;
        gbc_IDLabel.insets = new Insets(0, 0, 5, 5);
        gbc_IDLabel.gridx = 1;
        gbc_IDLabel.gridy = 2;
        loginWindow.add(IDLabel, gbc_IDLabel);

        JButton registerButton = new JButton("<HTML><U>Registrati</U></HTML>");
        registerButton.setBorder(null);
        registerButton.setMargin(new Insets(2, 14, 2, 0));
        registerButton.setInheritsPopupMenu(true);
        registerButton.setContentAreaFilled(false);
        registerButton.setBorderPainted(false);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        GridBagConstraints gbc_registerButton = new GridBagConstraints();
        gbc_registerButton.fill = GridBagConstraints.VERTICAL;
        gbc_registerButton.anchor = GridBagConstraints.EAST;
        gbc_registerButton.insets = new Insets(0, 0, 5, 5);
        gbc_registerButton.gridx = 2;
        gbc_registerButton.gridy = 2;
        loginWindow.add(registerButton, gbc_registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.register();
            }
        });

        IDField = new JTextField();
        IDField.setCaretColor(Color.WHITE);
        IDField.setForeground(new Color(255, 255, 255));
        IDField.setHorizontalAlignment(SwingConstants.CENTER);
        IDField.setBackground(new Color(14, 22, 33));

        IDField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        IDField.setToolTipText("Username");
        IDField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        GridBagConstraints gbc_IDField = new GridBagConstraints();
        gbc_IDField.gridwidth = 2;
        gbc_IDField.fill = GridBagConstraints.BOTH;
        gbc_IDField.insets = new Insets(0, 0, 5, 5);
        gbc_IDField.gridx = 1;
        gbc_IDField.gridy = 3;
        loginWindow.add(IDField, gbc_IDField);

        loginButton.setBackground(new Color(14, 22, 33));
        GridBagConstraints gbc_loginButton = new GridBagConstraints();
        gbc_loginButton.gridwidth = 2;
        gbc_loginButton.insets = new Insets(0, 0, 5, 5);
        gbc_loginButton.fill = GridBagConstraints.BOTH;
        gbc_loginButton.gridx = 1;
        gbc_loginButton.gridy = 4;
        loginWindow.add(loginButton, gbc_loginButton);
    }

    public void emptyFields() {
        IDField.setText("");
    }
}
