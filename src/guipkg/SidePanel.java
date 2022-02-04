package guipkg;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SidePanel extends JPanel
{
	public JButton logoutButton;
	public SidePanel(String nome,String cognome)
	{
		setBackground(new Color(14, 22, 33));
		StretchIcon propicIcon = new StretchIcon(getClass().getClassLoader().getResource("stock_propic.png"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{125, 0};
		gridBagLayout.rowHeights = new int[]{125, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		JLabel propicLabel = new JLabel("");
		propicLabel.setIcon(propicIcon);
		GridBagConstraints gbc_propicLabel = new GridBagConstraints();
		gbc_propicLabel.fill = GridBagConstraints.BOTH;
		gbc_propicLabel.insets = new Insets(0, 0, 5, 0);
		gbc_propicLabel.gridx = 0;
		gbc_propicLabel.gridy = 0;
		add(propicLabel, gbc_propicLabel);
		JLabel userLabel = new JLabel(cognome+" "+nome);
		userLabel.setBackground(Color.WHITE);
		userLabel.setForeground(Color.WHITE);
		userLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 1;
		add(userLabel, gbc_userLabel);
		
		JLabel lblNewLabel = new JLabel("Crea riferimento");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Visualizza riferimenti");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		logoutButton = new JButton("Logout");
		
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setBackground(new Color(14, 22, 33));
		logoutButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		GridBagConstraints gbc_logoutButton = new GridBagConstraints();
		gbc_logoutButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_logoutButton.anchor = GridBagConstraints.NORTH;
		gbc_logoutButton.gridx = 0;
		gbc_logoutButton.gridy = 6;
		add(logoutButton, gbc_logoutButton);
		
	}
}
