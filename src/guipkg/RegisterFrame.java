package guipkg;

import ctrlpkg.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class RegisterFrame extends JFrame {
    private final JTextField nameTextField;
    private final JTextField cognomeTextField;

    public RegisterFrame(Controller c, int ID) throws IOException {
        getContentPane().setBackground(new Color(23, 33, 43));
        setIconImage(ImageIO.read(getClass().getResource("/logo.png")));
        setTitle("Registrati");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        JButton backButton = new JButton("Indietro");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.backToLogin();
            }
        });
        backButton.setBackground(new Color(14, 22, 23));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        GridBagConstraints gbc_backButton = new GridBagConstraints();
        gbc_backButton.insets = new Insets(0, 0, 5, 5);
        gbc_backButton.gridx = 0;
        gbc_backButton.gridy = 0;
        getContentPane().add(backButton, gbc_backButton);
        JLabel uninaLogoLabel = new JLabel("");
        uninaLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        uninaLogoLabel.setIcon(new StretchIcon(getClass().getClassLoader().getResource("unina_logo.png")));
        GridBagConstraints gbc_uninaLogoLabel = new GridBagConstraints();
        gbc_uninaLogoLabel.gridheight = 2;
        gbc_uninaLogoLabel.gridwidth = 4;
        gbc_uninaLogoLabel.fill = GridBagConstraints.BOTH;
        gbc_uninaLogoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_uninaLogoLabel.gridx = 1;
        gbc_uninaLogoLabel.gridy = 0;
        getContentPane().add(uninaLogoLabel, gbc_uninaLogoLabel);

        JLabel registerLabel = new JLabel("Registrati");
        registerLabel.setForeground(Color.WHITE);
        registerLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 41));
        GridBagConstraints gbc_registerLabel = new GridBagConstraints();
        gbc_registerLabel.gridwidth = 4;
        gbc_registerLabel.insets = new Insets(0, 0, 5, 5);
        gbc_registerLabel.gridx = 1;
        gbc_registerLabel.gridy = 2;
        getContentPane().add(registerLabel, gbc_registerLabel);

        JLabel idLabel = new JLabel("ID Utente");
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        GridBagConstraints gbc_idLabel = new GridBagConstraints();
        gbc_idLabel.anchor = GridBagConstraints.EAST;
        gbc_idLabel.insets = new Insets(0, 0, 5, 5);
        gbc_idLabel.gridx = 1;
        gbc_idLabel.gridy = 3;
        getContentPane().add(idLabel, gbc_idLabel);

        JTextField userIdTextField = new JTextField();
        userIdTextField.setCaretColor(Color.WHITE);
        userIdTextField.setForeground(Color.GRAY);
        userIdTextField.setText(Integer.toString(ID));
        userIdTextField.setEditable(false);
        userIdTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        userIdTextField.setBackground(new Color(14, 22, 33));
        GridBagConstraints gbc_userIdTextField = new GridBagConstraints();
        gbc_userIdTextField.gridwidth = 3;
        gbc_userIdTextField.insets = new Insets(0, 0, 5, 5);
        gbc_userIdTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_userIdTextField.gridx = 2;
        gbc_userIdTextField.gridy = 3;
        getContentPane().add(userIdTextField, gbc_userIdTextField);
        userIdTextField.setColumns(10);

        JLabel nameLabel = new JLabel("Nome");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        GridBagConstraints gbc_nameLabel = new GridBagConstraints();
        gbc_nameLabel.anchor = GridBagConstraints.EAST;
        gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_nameLabel.gridx = 1;
        gbc_nameLabel.gridy = 4;
        getContentPane().add(nameLabel, gbc_nameLabel);

        nameTextField = new JTextField();
        nameTextField.setCaretColor(Color.WHITE);
        nameTextField.setForeground(Color.WHITE);
        nameTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        GridBagConstraints gbc_nameTextField = new GridBagConstraints();
        gbc_nameTextField.gridwidth = 3;
        gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
        gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_nameTextField.gridx = 2;
        gbc_nameTextField.gridy = 4;
        nameTextField.setBackground(new Color(14, 22, 33));
        getContentPane().add(nameTextField, gbc_nameTextField);
        nameTextField.setColumns(10);

        JLabel cognomeLabel = new JLabel("Cognome");
        cognomeLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        cognomeLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc_cognomeLabel = new GridBagConstraints();
        gbc_cognomeLabel.anchor = GridBagConstraints.EAST;
        gbc_cognomeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_cognomeLabel.gridx = 1;
        gbc_cognomeLabel.gridy = 5;
        getContentPane().add(cognomeLabel, gbc_cognomeLabel);

        cognomeTextField = new JTextField();
        cognomeTextField.setCaretColor(Color.WHITE);
        cognomeTextField.setForeground(Color.WHITE);
        cognomeTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        cognomeTextField.setBackground(new Color(14, 22, 33));
        GridBagConstraints gbc_cognomeTextField = new GridBagConstraints();
        gbc_cognomeTextField.gridwidth = 3;
        gbc_cognomeTextField.insets = new Insets(0, 0, 5, 5);
        gbc_cognomeTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_cognomeTextField.gridx = 2;
        gbc_cognomeTextField.gridy = 5;
        getContentPane().add(cognomeTextField, gbc_cognomeTextField);
        cognomeTextField.setColumns(10);

        JButton okButton = new JButton("Crea utente");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nameTextField.getText().isBlank() || cognomeTextField.getText().isBlank())
                    JOptionPane.showMessageDialog(null, "Errore di input");
                else
                    c.creaUtente(ID, nameTextField.getText(), cognomeTextField.getText());
            }
        });
        okButton.setBackground(new Color(14, 22, 33));
        okButton.setForeground(Color.WHITE);
        okButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        GridBagConstraints gbc_okButton = new GridBagConstraints();
        gbc_okButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_okButton.gridwidth = 4;
        gbc_okButton.insets = new Insets(0, 0, 5, 5);
        gbc_okButton.gridx = 1;
        gbc_okButton.gridy = 6;
        getContentPane().add(okButton, gbc_okButton);

    }


}
