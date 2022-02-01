package guipkg;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import ctrlpkg.Controller;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel mainPanel;
	private Controller c;
	private JTextField searchField;
	private JTable riferimentiTable;
	private JTable citazioniTable;
	private JTable ricercaTable;
	
	public MainFrame(Controller c) throws IOException
	{
		JCheckBox isArticoloCheckBox = new JCheckBox("Articoli");
		isArticoloCheckBox.setForeground(Color.WHITE);
		isArticoloCheckBox.setBackground(new Color(23, 33, 43));
		isArticoloCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isArticoloCheckBox.setSelected(true);
		JCheckBox isRivistaCheckBox = new JCheckBox("Riviste");
		isRivistaCheckBox.setForeground(Color.WHITE);
		isRivistaCheckBox.setBackground(new Color(23, 33, 43));
		isRivistaCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isRivistaCheckBox.setSelected(true);
		JCheckBox isLibroCheckBox = new JCheckBox("Libri");
		isLibroCheckBox.setForeground(Color.WHITE);
		isLibroCheckBox.setBackground(new Color(23, 33, 43));
		isLibroCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		isLibroCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isLibroCheckBox.setSelected(true);
		JCheckBox isRisorsaCheckBox = new JCheckBox("Risorse");
		isRisorsaCheckBox.setForeground(Color.WHITE);
		isRisorsaCheckBox.setBackground(new Color(23, 33, 43));
		isRisorsaCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isRisorsaCheckBox.setSelected(true);
		JCheckBox isDataSetCheckBox = new JCheckBox("Dataset");
		isDataSetCheckBox.setForeground(Color.WHITE);
		isDataSetCheckBox.setBackground(new Color(23, 33, 43));
		isDataSetCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isDataSetCheckBox.setSelected(true);
		JCheckBox isConferenzaCheckBox = new JCheckBox("Conferenza");
		isConferenzaCheckBox.setForeground(Color.WHITE);
		isConferenzaCheckBox.setBackground(new Color(23, 33, 43));
		isConferenzaCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isConferenzaCheckBox.setSelected(true);
		setBackground(new Color(0, 0, 0));
		setIconImage(ImageIO.read(getClass().getResource("/logo.png")));
		setTitle("Pagina principale");
		this.setC(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1280, 720);
		mainPanel = new JPanel();
		mainPanel.setBackground(UIManager.getColor("Button.background"));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(new Color(14, 22, 33));
		mainPanel.add(sidePanel, BorderLayout.WEST);
		JLabel userLabel = new JLabel(c.retrieveCognome()+" "+c.retrieveNome());
		userLabel.setBackground(new Color(255, 255, 255));
		userLabel.setForeground(new Color(255, 255, 255));
		userLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JButton logoutButton = new JButton("Logout");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setBackground(new Color(14, 22, 33));
		logoutButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
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
			gl_sidePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidePanel.createParallelGroup(Alignment.TRAILING)
					.addComponent(logoutButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addComponent(propicLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addComponent(userLabel, Alignment.LEADING, 0, 0, Short.MAX_VALUE))
		);
		gl_sidePanel.setVerticalGroup(
			gl_sidePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidePanel.createSequentialGroup()
					.addComponent(propicLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 502, Short.MAX_VALUE)
					.addComponent(logoutButton))
		);
		sidePanel.setLayout(gl_sidePanel);
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(23, 33, 43));
		mainPanel.add(welcomePanel, BorderLayout.CENTER);
		JLabel WelcomeLabel = new JLabel("Benvenuto, "+c.retrieveCognome()+" "+c.retrieveNome());
		WelcomeLabel.setBackground(new Color(255, 255, 255));
		WelcomeLabel.setForeground(new Color(255, 255, 255));
		WelcomeLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchField = new JTextField();
		searchField.setBackground(new Color(14, 22, 33));
		searchField.setForeground(new Color(255, 255, 255));
		searchField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setColumns(1);
		JButton searchButton = new JButton("");
		searchButton.setBackground(SystemColor.window);
		searchButton.setContentAreaFilled(false);
		searchButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		StretchIcon searchIcon = new StretchIcon(getClass().getClassLoader().getResource("search_icon.png"));
		searchButton.setIcon(searchIcon);
		searchButton.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JComboBox<String> categoriaComboBox = new JComboBox<String>();
		categoriaComboBox.setForeground(new Color(255, 255, 255));
		categoriaComboBox.setBackground(new Color(14, 22, 33));
		categoriaComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Qualsiasi"}));
		categoriaComboBox.setMaximumRowCount(100);
		categoriaComboBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		categoriaComboBox.setToolTipText("Scegli categoria");
		JLabel riferimentiLabel = new JLabel("Ultimi 5 riferimenti");
		riferimentiLabel.setBackground(new Color(255, 255, 255));
		riferimentiLabel.setForeground(Color.WHITE);
		riferimentiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		riferimentiLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 28));
		JScrollPane riferimentiTablePanel = new JScrollPane();
		JLabel citazioniLabel = new JLabel("Ultime 5 citazioni");
		citazioniLabel.setBackground(new Color(255, 255, 255));
		citazioniLabel.setForeground(Color.WHITE);
		citazioniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		citazioniLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 28));
		
		JRadioButton titoloRadioButton = new JRadioButton("Titolo");
		titoloRadioButton.setForeground(Color.WHITE);
		titoloRadioButton.setBackground(new Color(23, 33, 43));
		titoloRadioButton.setSelected(true);
		titoloRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		titoloRadioButton.setActionCommand(titoloRadioButton.getName());
		JRadioButton autoreRadioButton = new JRadioButton("Autore");
		autoreRadioButton.setForeground(Color.WHITE);
		autoreRadioButton.setBackground(new Color(23, 33, 43));
		autoreRadioButton.setActionCommand(autoreRadioButton.getName());
		autoreRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		JScrollPane citazioniTablePanel = new JScrollPane();
		JRadioButton DOIRadioButton = new JRadioButton("DOI");
		DOIRadioButton.setForeground(Color.WHITE);
		DOIRadioButton.setBackground(new Color(23, 33, 43));
		DOIRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		DOIRadioButton.setActionCommand(DOIRadioButton.getName());
		
		JLabel cercaPerLabel = new JLabel("Cerca per...");
		cercaPerLabel.setBackground(new Color(255, 255, 255));
		cercaPerLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		cercaPerLabel.setForeground(new Color(255, 255, 255));
		GroupLayout gl_welcomePanel = new GroupLayout(welcomePanel);
		gl_welcomePanel.setHorizontalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addGap(277)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(WelcomeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_welcomePanel.createSequentialGroup()
									.addGroup(gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(isRisorsaCheckBox)
										.addComponent(isArticoloCheckBox))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
										.addComponent(isDataSetCheckBox)
										.addComponent(isRivistaCheckBox))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
										.addComponent(isConferenzaCheckBox)
										.addGroup(gl_welcomePanel.createSequentialGroup()
											.addComponent(isLibroCheckBox)
											.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
											.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addComponent(searchField, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_welcomePanel.createSequentialGroup()
									.addComponent(cercaPerLabel, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
									.addGap(112))
								.addGroup(gl_welcomePanel.createSequentialGroup()
									.addComponent(titoloRadioButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(autoreRadioButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									
									.addComponent(DOIRadioButton)
									.addGap(49))
								.addComponent(riferimentiLabel, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
								.addComponent(riferimentiTablePanel, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
								.addComponent(citazioniLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
								.addComponent(citazioniTablePanel, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))))
					.addGap(217))
		);
		gl_welcomePanel.setVerticalGroup(
			gl_welcomePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_welcomePanel.createSequentialGroup()
					.addComponent(WelcomeLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_welcomePanel.createSequentialGroup()
							.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_welcomePanel.createSequentialGroup()
									.addGap(5)
									.addGroup(gl_welcomePanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(isArticoloCheckBox)
										.addComponent(isRivistaCheckBox)
										.addComponent(isLibroCheckBox))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_welcomePanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(isConferenzaCheckBox)
										.addComponent(isDataSetCheckBox)
										.addComponent(isRisorsaCheckBox))
									.addGap(15)
									.addComponent(cercaPerLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_welcomePanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_welcomePanel.createParallelGroup(Alignment.BASELINE)
											.addComponent(autoreRadioButton)
											.addComponent(DOIRadioButton))
										.addComponent(titoloRadioButton)))
								.addGroup(gl_welcomePanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(categoriaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addComponent(riferimentiLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(riferimentiTablePanel, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(citazioniLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(citazioniTablePanel, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
					.addGap(47))
		);
		
		citazioniTable = new JTable();
		Object[][] dataCit = c.CitazioniToObjectMatrix(c.retrieveRiferimenti(c.retrieveCF()), 5);
		citazioniTable.setModel(new DefaultTableModel(dataCit,new String[] {"Titolo", "Autori", "Data", "DOI/URL", "Categorie","Tipo","Riferimento citato"})
		{
			boolean[] columnEditables = new boolean[] 
			{
				false, false, false, false,false, false,false
			};
			public boolean isCellEditable(int row, int column)
			{
				return columnEditables[column];
			}
		});
		citazioniTable.setFillsViewportHeight(true);
		citazioniTable.setBackground(new Color(14, 22, 33));
		citazioniTable.setForeground(Color.WHITE);
		citazioniTablePanel.setViewportView(citazioniTable);
		citazioniLabel.setLabelFor(citazioniTable);
		citazioniTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		
		
		Object[][] dataRif = c.RiferimentiToObjectMatrix(c.retrieveRiferimenti(c.retrieveCF()), 5);
		
		riferimentiTable = new JTable();
		riferimentiTable.setModel(new DefaultTableModel(dataRif,new String[] {"Titolo", "Autori", "Data", "DOI/URL", "Categorie","Tipo"})
		{
			boolean[] columnEditables = new boolean[]
			{
				false, false, false, false, false,false
			};
			public boolean isCellEditable(int row, int column)
			{
				return columnEditables[column];
			}
		});
		riferimentiTable.setFillsViewportHeight(true);
		riferimentiTable.setBackground(new Color(14, 22, 33));
		riferimentiTable.setForeground(new Color(255, 255, 255));
		riferimentiLabel.setLabelFor(riferimentiTable);
		riferimentiTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		
		riferimentiTablePanel.setViewportView(riferimentiTable);
		
		welcomePanel.setLayout(gl_welcomePanel);
		ButtonGroup bottoniRadio = new ButtonGroup();
		bottoniRadio.add(DOIRadioButton);
		bottoniRadio.add(autoreRadioButton);
		bottoniRadio.add(titoloRadioButton);
		
		
		
		
		
		JPanel searchResultPanel = new JPanel();
		searchResultPanel.setBackground(new Color(23, 33, 43));
		
		
		JLabel searchResultLabel = new JLabel("Risultati ricerca");
		searchResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchResultLabel.setForeground(new Color(255, 255, 255));
		searchResultLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 36));
		
		JLabel recapRicercaLabel = new JLabel("di <ricerca> per <tipo>");
		recapRicercaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recapRicercaLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 26));
		recapRicercaLabel.setForeground(new Color(255, 255, 255));
		
		JScrollPane ricercaTablePanel = new JScrollPane();
		GroupLayout gl_searchResultPanel = new GroupLayout(searchResultPanel);
		gl_searchResultPanel.setHorizontalGroup(
			gl_searchResultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_searchResultPanel.createSequentialGroup()
					.addGap(331)
					.addGroup(gl_searchResultPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(ricercaTablePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
						.addComponent(recapRicercaLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
						.addComponent(searchResultLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
					.addGap(330))
		);
		gl_searchResultPanel.setVerticalGroup(
			gl_searchResultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchResultPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(searchResultLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(recapRicercaLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ricercaTablePanel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(270, Short.MAX_VALUE))
		);
		
		ricercaTable = new JTable();
		ricercaTable.setForeground(new Color(255, 255, 255));
		ricercaTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		ricercaTable.setBackground(new Color(14, 22, 23));
		ricercaTable.setFillsViewportHeight(true);
		
		ricercaTablePanel.setViewportView(ricercaTable);
		searchResultPanel.setLayout(gl_searchResultPanel);
		
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//se non ha messo una stringa da cercare o non ha selezionato tipi, dai errore
				if(searchField.getText().isEmpty()||(!isRisorsaCheckBox.isSelected()&& !isLibroCheckBox.isSelected()&&!isDataSetCheckBox.isSelected()&&!isRivistaCheckBox.isSelected()&&
				!isConferenzaCheckBox.isSelected()&&!isArticoloCheckBox.isSelected()))
					JOptionPane.showMessageDialog(null, "Errore di input");
				else
				{
					recapRicercaLabel.setText("di "+searchField.getText()+" per "+getSelectedButton(bottoniRadio));
					boolean[] selezioni = new boolean[] {isRisorsaCheckBox.isSelected(), isLibroCheckBox.isSelected(),isDataSetCheckBox.isSelected(),isRivistaCheckBox.isSelected(),
							isConferenzaCheckBox.isSelected(),isArticoloCheckBox.isSelected()};
					Object[][] dataRicerca = c.RicercaToObjectMatrix(searchField.getText(),categoriaComboBox.getSelectedItem().toString(),selezioni,getSelectedButton(bottoniRadio));
					ricercaTable.setModel(new DefaultTableModel(dataRicerca,new String[] {"Titolo", "Autori", "Data", "DOI/URL", "Categorie","Tipo"})
					{
						boolean[] columnEditables = new boolean[] {false, false, false, false, false,false};
						public boolean isCellEditable(int row, int column)
						{
							return columnEditables[column];
						}
					});
					mainPanel.add(searchResultPanel, BorderLayout.CENTER);
					welcomePanel.setVisible(false);
				}
			}
		});
		
	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}
	public String getSelectedButton(ButtonGroup btgrp)
	{  
	    for (Enumeration<AbstractButton> buttons = btgrp.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();
	        if (button.isSelected()) {
	                return button.getText();
	        }
	    }
	    return null;
	}
}