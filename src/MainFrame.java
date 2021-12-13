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

public class MainFrame extends JFrame {

	private JPanel MainPane;
	private Controller c;
	private JTextField textField;
	public MainFrame(Controller c) throws IOException 
	{
		setResizable(false);
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		MainPane = new JPanel();
		MainPane.setBackground(UIManager.getColor("Button.background"));
		MainPane.setLayout(new BorderLayout(0, 0));
		setContentPane(MainPane);
		
		
		
		JPanel SidePanel = new JPanel();
		
		MainPane.add(SidePanel, BorderLayout.WEST);
		
		JLabel UserLabel = new JLabel("[Cognome,Nome]");
		UserLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		UserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton LogoutButton = new JButton("Logout");
		LogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.logout();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_SidePanel = new GroupLayout(SidePanel);
		gl_SidePanel.setHorizontalGroup(
			gl_SidePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(LogoutButton, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
				.addComponent(UserLabel, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
		);
		gl_SidePanel.setVerticalGroup(
			gl_SidePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_SidePanel.createSequentialGroup()
					.addComponent(UserLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
					.addComponent(LogoutButton))
		);
		SidePanel.setLayout(gl_SidePanel);
		
		JPanel WelcomePanel = new JPanel();
		WelcomePanel.setBackground(SystemColor.window);
		MainPane.add(WelcomePanel, BorderLayout.CENTER);
		
		JLabel WelcomeLabel = new JLabel("Benvenuto, [Cognome,Nome]");
		WelcomeLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		
		JButton searchButton = new JButton("");
		StretchIcon a = new StretchIcon(getClass().getClassLoader().getResource("search_icon.png"));
		
		searchButton.setIcon(a);
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_WelcomePanel = new GroupLayout(WelcomePanel);
		gl_WelcomePanel.setHorizontalGroup(
			gl_WelcomePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_WelcomePanel.createSequentialGroup()
					.addGap(358)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(313))
				.addGroup(gl_WelcomePanel.createSequentialGroup()
					.addGap(3)
					.addComponent(WelcomeLabel, GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
					.addGap(3))
		);
		gl_WelcomePanel.setVerticalGroup(
			gl_WelcomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_WelcomePanel.createSequentialGroup()
					.addGap(5)
					.addComponent(WelcomeLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_WelcomePanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(searchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
					.addContainerGap(592, Short.MAX_VALUE))
		);
		WelcomePanel.setLayout(gl_WelcomePanel);
		
	}
}
