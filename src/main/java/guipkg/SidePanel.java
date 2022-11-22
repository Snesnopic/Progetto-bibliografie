package guipkg;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
	private JButton logoutButton;
	private JButton creaRifButton;
	private JButton creaCatButton;
	private JButton viewRifButton;

	public SidePanel(String nome, String cognome) {
		setBackground(new Color(14, 22, 33));
		StretchIcon propicIcon = new StretchIcon(getClass().getClassLoader().getResource("stock_propic.png"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 125, 0 };
		gridBagLayout.rowHeights = new int[] { 125, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		JLabel propicLabel = new JLabel("");
		propicLabel.setIcon(propicIcon);
		GridBagConstraints gbc_propicLabel = new GridBagConstraints();
		gbc_propicLabel.fill = GridBagConstraints.BOTH;
		gbc_propicLabel.insets = new Insets(0, 0, 5, 0);
		gbc_propicLabel.gridx = 0;
		gbc_propicLabel.gridy = 0;
		add(propicLabel, gbc_propicLabel);
		JLabel userLabel = new JLabel(cognome + " " + nome);
		userLabel.setBackground(Color.WHITE);
		userLabel.setForeground(Color.WHITE);
		userLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 1;
		add(userLabel, gbc_userLabel);

		setCreaRifButton(new JButton("<HTML><U>Crea riferimento</U></HTML>"));
		creaRifButton.setForeground(Color.WHITE);
		creaRifButton.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		GridBagConstraints gbc_creaRifButton = new GridBagConstraints();
		gbc_creaRifButton.fill = GridBagConstraints.BOTH;
		gbc_creaRifButton.insets = new Insets(0, 0, 5, 0);
		gbc_creaRifButton.gridx = 0;
		gbc_creaRifButton.gridy = 2;
		add(creaRifButton, gbc_creaRifButton);

		setCreaCatButton(new JButton("<HTML><U>Crea categoria</U></HTML>"));
		creaCatButton.setForeground(Color.WHITE);
		creaCatButton.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		GridBagConstraints gbc_creaCatButton = new GridBagConstraints();
		gbc_creaCatButton.fill = GridBagConstraints.BOTH;
		gbc_creaCatButton.insets = new Insets(0, 0, 5, 0);
		gbc_creaCatButton.gridx = 0;
		gbc_creaCatButton.gridy = 3;
		add(creaCatButton, gbc_creaCatButton);
		creaCatButton.setBorderPainted(false);
		creaCatButton.setContentAreaFilled(false);
		setViewRifButton(new JButton("<HTML><U>Visualizza riferimenti</U></HTML>"));
		viewRifButton.setForeground(Color.WHITE);
		viewRifButton.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		GridBagConstraints gbc_viewRifButton = new GridBagConstraints();
		gbc_viewRifButton.fill = GridBagConstraints.BOTH;
		gbc_viewRifButton.insets = new Insets(0, 0, 5, 0);
		gbc_viewRifButton.gridx = 0;
		gbc_viewRifButton.gridy = 4;
		add(viewRifButton, gbc_viewRifButton);
		setLogoutButton(new JButton("Logout"));
		viewRifButton.setBorderPainted(false);
		creaRifButton.setContentAreaFilled(false);
		viewRifButton.setContentAreaFilled(false);
		creaRifButton.setBorderPainted(false);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(new Color(14, 22, 33));
		logoutButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		GridBagConstraints gbc_logoutButton = new GridBagConstraints();
		gbc_logoutButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_logoutButton.anchor = GridBagConstraints.NORTH;
		gbc_logoutButton.gridx = 0;
		gbc_logoutButton.gridy = 6;
		add(logoutButton, gbc_logoutButton);

	}

	public JButton getCreaRifButton() {
		return creaRifButton;
	}

	public void setCreaRifButton(JButton creaRifButton) {
		this.creaRifButton = creaRifButton;
	}

	public JButton getCreaCatButton() {
		return creaCatButton;
	}

	public void setCreaCatButton(JButton creaCatButton) {
		this.creaCatButton = creaCatButton;

	}

	public JButton getViewRifButton() {
		return viewRifButton;
	}

	public void setViewRifButton(JButton viewRifButton) {
		this.viewRifButton = viewRifButton;

	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public void setLogoutButton(JButton logoutButton) {
		this.logoutButton = logoutButton;
	}
}
