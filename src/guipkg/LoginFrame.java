package guipkg;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ctrlpkg.Controller;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LoginFrame extends JFrame {
	private JPanel loginWindow;
	private JTextField IDField;
	private Controller c;

	public LoginFrame(Controller c) throws IOException
	{
        setIconImage(ImageIO.read(getClass().getResource("/logo.png")));
		this.setC(c);
		setBackground(Color.WHITE);
		setTitle("Login");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		loginWindow = new JPanel();
		loginWindow.setBackground(new Color(23, 33, 43));
		setContentPane(loginWindow);
		GridBagLayout gbl_loginWindow = new GridBagLayout();
		gbl_loginWindow.columnWidths = new int[]{0, 0, 0, 0};
		gbl_loginWindow.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_loginWindow.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_loginWindow.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		loginWindow.setLayout(gbl_loginWindow);
				
						JLabel uninaLogoLabel = new JLabel("");
						uninaLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
						uninaLogoLabel.setIcon(new StretchIcon(getClass().getClassLoader().getResource("unina_logo.png")));
						GridBagConstraints gbc_uninaLogoLabel = new GridBagConstraints();
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
				gbc_loginLabel.anchor = GridBagConstraints.NORTH;
				gbc_loginLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
				gbc_loginLabel.gridx = 1;
				gbc_loginLabel.gridy = 1;
				loginWindow.add(loginLabel, gbc_loginLabel);
		
				JButton loginButton = new JButton("OK");
				loginButton.setForeground(new Color(255, 255, 255));
				loginButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
				this.getRootPane().setDefaultButton(loginButton);
				loginButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						if(IDField.getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Errore di input");
						else
						{
							IDField.setText(IDField.getText().toUpperCase());
							try
							{
								if(c.login(IDField.getText()))
									JOptionPane.showMessageDialog(null, "Login riuscito");
								else
									JOptionPane.showMessageDialog(null, "Login fallito");
							}
							catch (HeadlessException | IOException e1)
							{
								JOptionPane.showMessageDialog(null,"Errore: "+e1.getMessage());
							}
						}
					}
				});
						
								JLabel IDLabel = new JLabel("ID Utente");
								IDLabel.setBackground(new Color(255, 255, 255));
								IDLabel.setForeground(new Color(255, 255, 255));
								IDLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
								GridBagConstraints gbc_IDLabel = new GridBagConstraints();
								gbc_IDLabel.fill = GridBagConstraints.HORIZONTAL;
								gbc_IDLabel.anchor = GridBagConstraints.NORTH;
								gbc_IDLabel.insets = new Insets(0, 0, 5, 5);
								gbc_IDLabel.gridx = 1;
								gbc_IDLabel.gridy = 2;
								loginWindow.add(IDLabel, gbc_IDLabel);
						
								IDField = new JTextField();
								IDField.setForeground(new Color(255, 255, 255));
								IDField.setHorizontalAlignment(SwingConstants.CENTER);
								IDField.setBackground(new Color(14, 22, 33));
								
										IDField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
										IDField.setToolTipText("Username");
										GridBagConstraints gbc_IDField = new GridBagConstraints();
										gbc_IDField.fill = GridBagConstraints.BOTH;
										gbc_IDField.insets = new Insets(0, 0, 5, 5);
										gbc_IDField.gridx = 1;
										gbc_IDField.gridy = 3;
										loginWindow.add(IDField, gbc_IDField);
				
						loginButton.setBackground(new Color(14, 22, 33));
						GridBagConstraints gbc_loginButton = new GridBagConstraints();
						gbc_loginButton.insets = new Insets(0, 0, 5, 5);
						gbc_loginButton.fill = GridBagConstraints.BOTH;
						gbc_loginButton.gridx = 1;
						gbc_loginButton.gridy = 4;
						loginWindow.add(loginButton, gbc_loginButton);
	}
	public void emptyFields()
	{
		IDField.setText("");
	}
	public Controller getC() {
		return c;
	}
	public void setC(Controller c) {
		this.c = c;
	}
}
