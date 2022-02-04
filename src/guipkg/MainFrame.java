package guipkg;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ctrlpkg.Controller;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class MainFrame extends JFrame {

	private JPanel mainPanel;
	private Controller c;
	private JTable ricercaTable;
	
	public MainFrame(Controller c) throws IOException
	{
		
		setBackground(new Color(0, 0, 0));
		setIconImage(ImageIO.read(getClass().getResource("/logo.png")));
		setTitle("Pagina principale");
		this.setC(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1280, 720);
		mainPanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		SidePanel sidePanel = new SidePanel(c.retrieveNome(), c.retrieveCognome());
		mainPanel.add(sidePanel, BorderLayout.WEST);
		this.getRootPane().setDefaultButton(sidePanel.logoutButton);
		sidePanel.logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.logout();
			}
		});
		WelcomePanel welcomePanel = new WelcomePanel(c.retrieveNome(),c.retrieveCognome());
		
		welcomePanel.setCitazioniTable(c.CitazioniToObjectMatrix(c.retrieveRiferimenti(c.retrieveCF()), 5));
		welcomePanel.setRiferimentiTable(c.RiferimentiToObjectMatrix(c.retrieveRiferimenti(c.retrieveCF()), 5));
		
		mainPanel.add(welcomePanel, BorderLayout.CENTER);
		
		
		
		
		
		
		
		
		
		
		
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
		
		
		ricercaTable = new JTable();
		ricercaTable.setForeground(new Color(255, 255, 255));
		ricercaTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		ricercaTable.setBackground(new Color(14, 22, 23));
		ricercaTable.setFillsViewportHeight(true);
		
		ricercaTablePanel.setViewportView(ricercaTable);
		/*
		welcomePanel.searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//se non ha messo una stringa da cercare o non ha selezionato tipi, dai errore
				if(searchField.getText().isEmpty()||(!isRisorsaCheckBox.isSelected()&& !isLibroCheckBox.isSelected()&&!isDataSetCheckBox.isSelected()&&!isArticoloCheckBox.isSelected()))
					JOptionPane.showMessageDialog(null, "Errore di input");
				else
				{
					recapRicercaLabel.setText("di "+searchField.getText()+" per "+getSelectedButton(bottoniRadio));
					boolean[] selezioni = new boolean[] {isRisorsaCheckBox.isSelected(), isLibroCheckBox.isSelected(),isDataSetCheckBox.isSelected(),isArticoloCheckBox.isSelected()};
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
		*/
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