import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class MainFrame extends JFrame {

	private JPanel mainPane;
	private Controller c;
	private JTextField searchField;
	public MainFrame(Controller c) throws IOException 
	{
		setResizable(false);
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		mainPane = new JPanel();
		mainPane.setBackground(UIManager.getColor("Button.background"));
		mainPane.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPane);
		
		
		
		JPanel sidePanel = new JPanel();
		
		mainPane.add(sidePanel, BorderLayout.WEST);
		
		JLabel userLabel = new JLabel("[Cognome,Nome]");
		userLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.logout();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_sidePanel = new GroupLayout(sidePanel);
		gl_sidePanel.setHorizontalGroup(
			gl_sidePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addComponent(userLabel, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
		);
		gl_sidePanel.setVerticalGroup(
			gl_sidePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_sidePanel.createSequentialGroup()
					.addComponent(userLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
					.addComponent(logoutButton))
		);
		sidePanel.setLayout(gl_sidePanel);
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(SystemColor.window);
		mainPane.add(welcomePanel, BorderLayout.CENTER);
		
		JLabel WelcomeLabel = new JLabel("Benvenuto, [Cognome,Nome]");
		WelcomeLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("");
		StretchIcon a = new StretchIcon(getClass().getClassLoader().getResource("search_icon.png"));
		
		searchButton.setIcon(a);
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JComboBox categoriaComboBox = new JComboBox();
		
		JComboBox tipoComboBox = new JComboBox();
		GroupLayout gl_welcomePanel = new GroupLayout(welcomePanel);
		gl_welcomePanel.setHorizontalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGap(3)
					.addComponent(WelcomeLabel, GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
					.addGap(3))
				.addGroup(Alignment.LEADING, gl_welcomePanel.createSequentialGroup()
					.addGap(363)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tipoComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(676))
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addComponent(searchField, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
							.addGap(2)
							.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(313))))
		);
		gl_welcomePanel.setVerticalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGap(5)
					.addComponent(WelcomeLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(searchButton, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tipoComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(560, Short.MAX_VALUE))
		);
		welcomePanel.setLayout(gl_welcomePanel);
		
	}
}
