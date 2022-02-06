package guipkg;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ctrlpkg.Controller;

public class MainFrame extends JFrame {

	private JPanel mainPanel;
	private Controller c;
	private JPanel currentPanel;
	public MainFrame(Controller c) throws IOException
	{
		setIconImage(ImageIO.read(getClass().getResource("/logo.png")));
		setTitle("Pagina principale");
		setC(c);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 0, 1280, 720);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);

		SidePanel sidePanel = new SidePanel(c.retrieveNome(), c.retrieveCognome());
		mainPanel.add(sidePanel, BorderLayout.WEST);

		sidePanel.getLogoutButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.logout();
			}
		});
		Object[][] dataCit = c.CitazioniToObjectMatrix(c.retrieveRiferimenti(c.retrieveCF()), 5);
		Object[][] dataRif = c.RiferimentiToObjectMatrix(c.retrieveRiferimenti(c.retrieveCF()), 5);
		WelcomePanel welcomePanel = new WelcomePanel(c.retrieveNome(),c.retrieveCognome(),dataCit,dataRif);
		currentPanel = welcomePanel;
		
		getRootPane().setDefaultButton(welcomePanel.getSearchButton());

		mainPanel.add(currentPanel, BorderLayout.CENTER);
		welcomePanel.getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(welcomePanel.isSearchValid())
				{
					String testo = welcomePanel.getSearchFieldText();
					String selectedButton = welcomePanel.getSelectedButton();
					boolean[] selezioni = welcomePanel.getSelezioni();
					Object[][] dataRicerca = c.RicercaToObjectMatrix(testo,welcomePanel.getCategoriaComboBox().getSelectedItem().toString(),selezioni,selectedButton);
					SearchResultPanel searchResultPanel = new SearchResultPanel(testo,selectedButton,dataRicerca);
					currentPanel.setVisible(false);
					currentPanel = searchResultPanel;
					mainPanel.add(currentPanel,BorderLayout.CENTER);
					searchResultPanel.getBackButton().addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							mainPanel.remove(currentPanel);
							currentPanel = welcomePanel;
							currentPanel.setVisible(true);
						}
					});
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Errore di input");
				}
			}
		});
		
		sidePanel.getCreaCatButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CreaCatPanel creaCatPanel = new CreaCatPanel();
				currentPanel.setVisible(false);
				currentPanel = creaCatPanel;
				mainPanel.add(currentPanel);
				creaCatPanel.getBackButton().addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						mainPanel.remove(currentPanel);
						currentPanel = welcomePanel;
						currentPanel.setVisible(true);
					}
				});
			}
		});
		sidePanel.getCreaRifButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CreaRifPanel creaRifPanel = new CreaRifPanel();
				currentPanel.setVisible(false);
				currentPanel = creaRifPanel;
				mainPanel.add(currentPanel);
				creaRifPanel.getBackButton().addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						mainPanel.remove(currentPanel);
						currentPanel = welcomePanel;
						currentPanel.setVisible(true);
					}
				});
			}
		});
		sidePanel.getViewRifButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ViewRifPanel viewRifPanel = new ViewRifPanel();
				currentPanel.setVisible(false);
				currentPanel = viewRifPanel;
				mainPanel.add(currentPanel);
				viewRifPanel.getBackButton().addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						mainPanel.remove(currentPanel);
						currentPanel = welcomePanel;
						currentPanel.setVisible(true);
					}
				});
			}
		});
		
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

}