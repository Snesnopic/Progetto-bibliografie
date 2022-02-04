package guipkg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class WelcomePanel extends JPanel
{
	
	public JButton searchButton;
	public JTable citazioniTable;
	public JTable riferimentiTable;
	public WelcomePanel(String nome,String cognome)
	{
		setBackground(new Color(23, 33, 43));
		StretchIcon searchIcon = new StretchIcon(getClass().getClassLoader().getResource("search_icon.png"));
		ButtonGroup bottoniRadio = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 0, 0};
		gridBagLayout.rowHeights = new int[]{23, 50, 16, 20, 16, 0, 0, 0, 100, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		JLabel welcomeLabel = new JLabel("Benvenuto, "+cognome+" "+nome);
		
		
		
		welcomeLabel.setBackground(new Color(255, 255, 255));
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_welcomeLabel = new GridBagConstraints();
		gbc_welcomeLabel.gridwidth = 10;
		gbc_welcomeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_welcomeLabel.gridx = 0;
		gbc_welcomeLabel.gridy = 0;
		add(welcomeLabel, gbc_welcomeLabel);
		JTextField searchField = new JTextField();
		
		searchField.setBackground(new Color(14, 22, 33));
		searchField.setForeground(new Color(255, 255, 255));
		searchField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setColumns(1);
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.gridwidth = 7;
		gbc_searchField.fill = GridBagConstraints.BOTH;
		gbc_searchField.insets = new Insets(0, 0, 5, 5);
		gbc_searchField.gridx = 1;
		gbc_searchField.gridy = 1;
		add(searchField, gbc_searchField);
		JButton searchButton_1 = new JButton("");
		
		searchButton_1.setBackground(SystemColor.window);
		searchButton_1.setContentAreaFilled(false);
		searchButton_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		searchButton_1.setIcon(searchIcon);
		GridBagConstraints gbc_searchButton_1 = new GridBagConstraints();
		gbc_searchButton_1.fill = GridBagConstraints.BOTH;
		gbc_searchButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton_1.gridx = 8;
		gbc_searchButton_1.gridy = 1;
		add(searchButton_1, gbc_searchButton_1);
		JCheckBox isArticoloCheckBox = new JCheckBox("Articoli");
		
		isArticoloCheckBox.setForeground(Color.WHITE);
		isArticoloCheckBox.setBackground(new Color(23, 33, 43));
		isArticoloCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isArticoloCheckBox.setSelected(true);
		
		
		
		GridBagConstraints gbc_isArticoloCheckBox = new GridBagConstraints();
		gbc_isArticoloCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_isArticoloCheckBox.gridx = 1;
		gbc_isArticoloCheckBox.gridy = 2;
		add(isArticoloCheckBox, gbc_isArticoloCheckBox);
		JCheckBox isLibroCheckBox = new JCheckBox("Libri");
		
		isLibroCheckBox.setForeground(Color.WHITE);
		isLibroCheckBox.setBackground(new Color(23, 33, 43));
		isLibroCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		isLibroCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isLibroCheckBox.setSelected(true);
		GridBagConstraints gbc_isLibroCheckBox = new GridBagConstraints();
		gbc_isLibroCheckBox.anchor = GridBagConstraints.WEST;
		gbc_isLibroCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_isLibroCheckBox.gridx = 2;
		gbc_isLibroCheckBox.gridy = 2;
		add(isLibroCheckBox, gbc_isLibroCheckBox);
		JCheckBox isRisorsaCheckBox = new JCheckBox("Risorse");
		
		isRisorsaCheckBox.setForeground(Color.WHITE);
		isRisorsaCheckBox.setBackground(new Color(23, 33, 43));
		isRisorsaCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isRisorsaCheckBox.setSelected(true);
		GridBagConstraints gbc_isRisorsaCheckBox = new GridBagConstraints();
		gbc_isRisorsaCheckBox.anchor = GridBagConstraints.WEST;
		gbc_isRisorsaCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_isRisorsaCheckBox.gridx = 3;
		gbc_isRisorsaCheckBox.gridy = 2;
		add(isRisorsaCheckBox, gbc_isRisorsaCheckBox);
		JCheckBox isDataSetCheckBox = new JCheckBox("Dataset");
		
		isDataSetCheckBox.setForeground(Color.WHITE);
		isDataSetCheckBox.setBackground(new Color(23, 33, 43));
		isDataSetCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		isDataSetCheckBox.setSelected(true);
		GridBagConstraints gbc_isDataSetCheckBox = new GridBagConstraints();
		gbc_isDataSetCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_isDataSetCheckBox.gridx = 4;
		gbc_isDataSetCheckBox.gridy = 2;
		add(isDataSetCheckBox, gbc_isDataSetCheckBox);
		JComboBox<String> categoriaComboBox = new JComboBox<>();
		
		categoriaComboBox.setForeground(new Color(255, 255, 255));
		categoriaComboBox.setBackground(new Color(14, 22, 33));
		categoriaComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Qualsiasi"}));
		categoriaComboBox.setMaximumRowCount(100);
		categoriaComboBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		categoriaComboBox.setToolTipText("Scegli categoria");
		GridBagConstraints gbc_categoriaComboBox = new GridBagConstraints();
		gbc_categoriaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoriaComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_categoriaComboBox.gridx = 5;
		gbc_categoriaComboBox.gridy = 2;
		add(categoriaComboBox, gbc_categoriaComboBox);
		JLabel cercaPerLabel = new JLabel("Cerca per...");
		cercaPerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		cercaPerLabel.setBackground(new Color(255, 255, 255));
		cercaPerLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		cercaPerLabel.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_cercaPerLabel = new GridBagConstraints();
		gbc_cercaPerLabel.gridwidth = 2;
		gbc_cercaPerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cercaPerLabel.gridx = 1;
		gbc_cercaPerLabel.gridy = 3;
		add(cercaPerLabel, gbc_cercaPerLabel);
		JRadioButton titoloRadioButton = new JRadioButton("Titolo");
		bottoniRadio.add(titoloRadioButton);
		
		titoloRadioButton.setForeground(Color.WHITE);
		titoloRadioButton.setBackground(new Color(23, 33, 43));
		titoloRadioButton.setSelected(true);
		titoloRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		titoloRadioButton.setActionCommand(titoloRadioButton.getName());
		GridBagConstraints gbc_titoloRadioButton = new GridBagConstraints();
		gbc_titoloRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_titoloRadioButton.gridx = 1;
		gbc_titoloRadioButton.gridy = 4;
		add(titoloRadioButton, gbc_titoloRadioButton);
		JRadioButton autoreRadioButton = new JRadioButton("Autore");
		bottoniRadio.add(autoreRadioButton);
		
		autoreRadioButton.setForeground(Color.WHITE);
		autoreRadioButton.setBackground(new Color(23, 33, 43));
		autoreRadioButton.setActionCommand(autoreRadioButton.getName());
		autoreRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_autoreRadioButton = new GridBagConstraints();
		gbc_autoreRadioButton.anchor = GridBagConstraints.WEST;
		gbc_autoreRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_autoreRadioButton.gridx = 2;
		gbc_autoreRadioButton.gridy = 4;
		add(autoreRadioButton, gbc_autoreRadioButton);
		JRadioButton DOIRadioButton = new JRadioButton("DOI");
		
		
		bottoniRadio.add(DOIRadioButton);
		
		DOIRadioButton.setForeground(Color.WHITE);
		DOIRadioButton.setBackground(new Color(23, 33, 43));
		DOIRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		DOIRadioButton.setActionCommand(DOIRadioButton.getName());
		GridBagConstraints gbc_DOIRadioButton = new GridBagConstraints();
		gbc_DOIRadioButton.anchor = GridBagConstraints.WEST;
		gbc_DOIRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_DOIRadioButton.gridx = 3;
		gbc_DOIRadioButton.gridy = 4;
		add(DOIRadioButton, gbc_DOIRadioButton);
		JLabel riferimentiLabel = new JLabel("Ultimi 5 riferimenti");
		
		riferimentiLabel.setBackground(new Color(255, 255, 255));
		riferimentiLabel.setForeground(Color.WHITE);
		riferimentiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		riferimentiLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 28));
		GridBagConstraints gbc_riferimentiLabel = new GridBagConstraints();
		gbc_riferimentiLabel.gridwidth = 10;
		gbc_riferimentiLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_riferimentiLabel.insets = new Insets(0, 0, 5, 0);
		gbc_riferimentiLabel.gridx = 0;
		gbc_riferimentiLabel.gridy = 5;
		add(riferimentiLabel, gbc_riferimentiLabel);
		JScrollPane riferimentiTablePanel = new JScrollPane();
		JTable riferimentiTable_1 = new JTable();
		
		
		riferimentiTable_1.setFillsViewportHeight(true);
		riferimentiTable_1.setBackground(new Color(14, 22, 33));
		riferimentiTable_1.setForeground(new Color(255, 255, 255));
		riferimentiTable_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		riferimentiTablePanel.setViewportView(riferimentiTable_1);
		GridBagConstraints gbc_riferimentiTablePanel = new GridBagConstraints();
		gbc_riferimentiTablePanel.gridwidth = 7;
		gbc_riferimentiTablePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_riferimentiTablePanel.anchor = GridBagConstraints.NORTH;
		gbc_riferimentiTablePanel.insets = new Insets(0, 0, 5, 5);
		gbc_riferimentiTablePanel.gridx = 1;
		gbc_riferimentiTablePanel.gridy = 6;
		add(riferimentiTablePanel, gbc_riferimentiTablePanel);
		JLabel citazioniLabel = new JLabel("Ultime 5 citazioni");
		
		citazioniLabel.setBackground(new Color(255, 255, 255));
		citazioniLabel.setForeground(Color.WHITE);
		citazioniLabel.setHorizontalAlignment(SwingConstants.CENTER);
		citazioniLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 28));
		GridBagConstraints gbc_citazioniLabel = new GridBagConstraints();
		gbc_citazioniLabel.gridwidth = 10;
		gbc_citazioniLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_citazioniLabel.insets = new Insets(0, 0, 5, 0);
		gbc_citazioniLabel.gridx = 0;
		gbc_citazioniLabel.gridy = 7;
		add(citazioniLabel, gbc_citazioniLabel);
		JScrollPane citazioniTablePanel = new JScrollPane();
		JTable citazioniTable_1 = new JTable();
		
		citazioniTable_1.setFillsViewportHeight(true);
		citazioniTable_1.setBackground(new Color(14, 22, 33));
		citazioniTable_1.setForeground(Color.WHITE);
		citazioniTablePanel.setViewportView(citazioniTable_1);
		citazioniTable_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_citazioniTablePanel = new GridBagConstraints();
		gbc_citazioniTablePanel.anchor = GridBagConstraints.NORTH;
		gbc_citazioniTablePanel.gridwidth = 7;
		gbc_citazioniTablePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_citazioniTablePanel.insets = new Insets(0, 0, 5, 5);
		gbc_citazioniTablePanel.gridx = 1;
		gbc_citazioniTablePanel.gridy = 8;
		add(citazioniTablePanel, gbc_citazioniTablePanel);
		
		
		
	}
	public void setCitazioniTable(Object[][] dataCit)
	{
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
	}
	public void setRiferimentiTable(Object[][] dataRif)
	{
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
	}
}
