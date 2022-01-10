package guipkg;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import ctrlpkg.Controller;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel mainPane;
	private Controller c;
	private JTextField searchField;
	private JTable riferimentiTable;
	private JTable citazioniTable;
	
	public MainFrame(Controller c)
	{
		setResizable(false);
		this.setC(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1280, 720);
		mainPane = new JPanel();
		mainPane.setBackground(UIManager.getColor("Button.background"));
		mainPane.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPane);
		
		
		
		JPanel sidePanel = new JPanel();
		
		mainPane.add(sidePanel, BorderLayout.WEST);
		
		JLabel userLabel = new JLabel(c.retrieveCognome()+" "+c.retrieveNome());
		userLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		logoutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				c.logout();
			}
		});
		
		JLabel propicLabel = new JLabel("");
		StretchIcon propicIcon = new StretchIcon(getClass().getClassLoader().getResource("stock_propic.png"));
		propicLabel.setIcon(propicIcon);
		GroupLayout gl_sidePanel = new GroupLayout(sidePanel);
		gl_sidePanel.setHorizontalGroup(
			gl_sidePanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(userLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 110, Short.MAX_VALUE)
				.addComponent(propicLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				.addComponent(logoutButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
		);
		gl_sidePanel.setVerticalGroup(
			gl_sidePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidePanel.createSequentialGroup()
					.addComponent(propicLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 524, Short.MAX_VALUE)
					.addComponent(logoutButton))
		);
		sidePanel.setLayout(gl_sidePanel);
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(SystemColor.window);
		mainPane.add(welcomePanel, BorderLayout.CENTER);
		
		JLabel WelcomeLabel = new JLabel("Benvenuto, "+c.retrieveCognome()+" "+c.retrieveNome());
		WelcomeLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		searchField = new JTextField();
		searchField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("");
		StretchIcon searchIcon = new StretchIcon(getClass().getClassLoader().getResource("search_icon.png"));
		
		
		searchButton.setIcon(searchIcon);
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//
			}
		});
		
		JComboBox<String> categoriaComboBox = new JComboBox<String>();
		
		JComboBox<String> tipoComboBox = new JComboBox<String>();
		
		JLabel riferimentiLabel = new JLabel("Ultimi 5 riferimenti");
		riferimentiLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 28));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel citazioniLabel = new JLabel("Ultime 5 citazioni");
		citazioniLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 28));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_welcomePanel = new GroupLayout(welcomePanel);
		gl_welcomePanel.setHorizontalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(WelcomeLabel, GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
					.addGap(315))
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGap(470)
					.addComponent(citazioniLabel)
					.addContainerGap(877, Short.MAX_VALUE))
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGap(277)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addGroup(gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_welcomePanel.createSequentialGroup()
									.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tipoComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addGap(698))
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGap(461)
					.addComponent(riferimentiLabel, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
					.addGap(566))
		);
		gl_welcomePanel.setVerticalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGap(5)
					.addComponent(WelcomeLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(searchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(searchField))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tipoComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(riferimentiLabel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(citazioniLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);
		
		citazioniTable = new JTable();
		citazioniTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		Object[][] dataCit = c.listToObjectMatrix(c.retrieveCitazioni(c.retrieveCF()), 5);
		citazioniTable.setModel(new DefaultTableModel(
			/*new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},*/
				dataCit,
			new String[] {
				"Titolo", "Autori", "Data", "DOI/URL", "Categorie"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(citazioniTable);
		Object[][] dataRif = c.listToObjectMatrix(c.retrieveRiferimenti(c.retrieveCF()), 5);
		
		riferimentiTable = new JTable();
		riferimentiTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		riferimentiTable.setModel(new DefaultTableModel(
			/*new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},*/
				dataRif,
			new String[] {
				"Titolo", "Autori", "Data", "DOI/URL", "Categorie"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(riferimentiTable);
		welcomePanel.setLayout(gl_welcomePanel);
		
	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
