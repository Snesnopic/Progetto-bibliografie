package guipkg;

import ctrlpkg.Controller;
import datalpkg.Riferimento;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;

public class MainFrame extends JFrame {

	private final JPanel mainPanel;
	private JPanel currentPanel;
	private Integer id_rif;

	public MainFrame(Controller c) throws IOException {
		setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResource("/logo.png"))));
		setTitle("Pagina principale");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 0, 1280, 720);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);

		SidePanel sidePanel = new SidePanel(c.retrieveNome(), c.retrieveCognome());
		mainPanel.add(sidePanel, BorderLayout.WEST);

		sidePanel.getLogoutButton().addActionListener(e -> c.logout());
		Object[][] dataCit = c.CitazioniToObjectMatrix(c.retrieveRiferimenti(c.retrieveID()), 5);
		Object[][] dataRif = c.RiferimentiToObjectMatrix(c.retrieveRiferimenti(c.retrieveID()), 5);
		WelcomePanel welcomePanel = new WelcomePanel(c.retrieveNome(), c.retrieveCognome(), dataCit, dataRif,
				c.getCategorie(true));
		currentPanel = welcomePanel;

		getRootPane().setDefaultButton(welcomePanel.getSearchButton());

		mainPanel.add(currentPanel, BorderLayout.CENTER);
		welcomePanel.getSearchButton().addActionListener(e -> {
			if (welcomePanel.isSearchValid()) {
				String testo = welcomePanel.getSearchFieldText();
				String selectedButton = welcomePanel.getSelectedButton();
				boolean[] selezioni = welcomePanel.getSelezioni();
				Object[][] dataRicerca = c.RicercaToObjectMatrix(testo,
						welcomePanel.getCategoriaComboBox().getSelectedItem().toString(), selezioni, selectedButton);
				SearchResultPanel searchResultPanel = new SearchResultPanel(testo, selectedButton, dataRicerca);
				currentPanel.setVisible(false);
				currentPanel = searchResultPanel;
				mainPanel.add(currentPanel, BorderLayout.CENTER);
				searchResultPanel.getBackButton().addActionListener(e19 -> {
					mainPanel.remove(currentPanel);
					currentPanel = welcomePanel;
					currentPanel.setVisible(true);
				});
			} else {
				JOptionPane.showMessageDialog(null, "Errore di input");
			}
		});

		sidePanel.getCreaCatButton().addActionListener(e -> {
			if(!(currentPanel instanceof CreaCatPanel))
			{
				for (Component d : mainPanel.getComponents()) {
				    if (d instanceof JScrollPane)
				    {
				        mainPanel.remove(d);
				    }
				}
			CreaCatPanel creaCatPanel = new CreaCatPanel(c.getCategorie(false));
			currentPanel.setVisible(false);
			currentPanel = creaCatPanel;
			mainPanel.add(currentPanel);
			creaCatPanel.getBackButton().addActionListener(e18 -> {
				mainPanel.remove(currentPanel);
				currentPanel = welcomePanel;
				currentPanel.setVisible(true);
			});
			creaCatPanel.getCreateButton().addActionListener(e17 -> {
				if (creaCatPanel.getTextField().getText().isBlank())
					JOptionPane.showMessageDialog(null, "Errore di input");
				else {
					c.creaCategoria(creaCatPanel.getTextField().getText(),
							creaCatPanel.getSuperCatList().getSelectedValue(), c.retrieveID());
					creaCatPanel.getTextField().setText("");
					creaCatPanel.getSuperCatList().clearSelection();
					creaCatPanel.getSuperCatList().setListData(c.getCategorie(false));
					welcomePanel.getCategoriaComboBox().setModel(new DefaultComboBoxModel<>(c.getCategorie(false)));
				}
			});
			}
		});
		sidePanel.getCreaRifButton().addActionListener(e -> {
			if(!(currentPanel instanceof CreaRifPanel))
			{
			CreaRifPanel creaRifPanel = new CreaRifPanel(c.getUtenti(), c.getCategorie(false), c.getRiferimenti());
			JScrollPane scrollable = new JScrollPane(creaRifPanel);
			currentPanel.setVisible(false);
			currentPanel = creaRifPanel;
			mainPanel.add(scrollable);
			creaRifPanel.getBackButton().addActionListener(e16 -> {
				mainPanel.remove(currentPanel);
				mainPanel.remove(scrollable);
				currentPanel = welcomePanel;
				currentPanel.setVisible(true);
			});
			creaRifPanel.getCreateButton().addActionListener(e15 -> {
				if (creaRifPanel.isInputValid()) {
					String url = null;
					Integer doi;
					if (creaRifPanel.getDoiField().getText().isBlank())
						doi = null;
					else
						doi = Integer.parseInt(creaRifPanel.getDoiField().getText());
					if (creaRifPanel.getIsDigitalCheckBox().isSelected())
						url = creaRifPanel.getLinkField().getText();

					c.creaRiferimento(creaRifPanel.getNameField().getText(),
							Date.valueOf(creaRifPanel.getDatePicker().getDate()), creaRifPanel.getSelectedButton(), url,
							doi, creaRifPanel.getIsDigitalCheckBox().isSelected(),
							creaRifPanel.getDescriptionPane().getText(),
							creaRifPanel.getSelectedUsers(), creaRifPanel.getSelectedCategories(),
							creaRifPanel.getSelectedCitazioni());
					welcomePanel.refreshRifTable(c.RiferimentiToObjectMatrix(c.retrieveRiferimenti(c.retrieveID()), 5));
					welcomePanel.refreshCitTable(c.CitazioniToObjectMatrix(c.retrieveRiferimenti(c.retrieveID()), 5));
				} else
					JOptionPane.showMessageDialog(null, "Errore di input");
			});
		}
		});
		sidePanel.getViewRifButton().addActionListener(e -> {
			if(!(currentPanel instanceof ViewRifPanel))
			{
				for (Component d : mainPanel.getComponents()) {
				    if (d instanceof JScrollPane)
				    {
				        mainPanel.remove(d);
				    }
				}
			ViewRifPanel viewRifPanel = new ViewRifPanel(c.getRiferimentiByUser());
			currentPanel.setVisible(false);
			currentPanel = viewRifPanel;
			mainPanel.add(currentPanel);
			viewRifPanel.getBackButton().addActionListener(e14 -> {
				mainPanel.remove(currentPanel);
				currentPanel = welcomePanel;
				currentPanel.setVisible(true);
			});
			viewRifPanel.getDeleteButton().addActionListener(e13 -> {
				int result = JOptionPane.showConfirmDialog(null, "Sei sicuro che vuoi eliminare questo riferimento?",
						"Conferma eliminazione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					c.deleteRiferimento(c.retrieveID(), viewRifPanel.getMyRifList().getSelectedValue());
					viewRifPanel.getMyRifList().setListData(c.getRiferimentiByUser());
					welcomePanel.refreshRifTable(c.RiferimentiToObjectMatrix(c.retrieveRiferimenti(c.retrieveID()), 5));
					welcomePanel.refreshCitTable(c.CitazioniToObjectMatrix(c.retrieveRiferimenti(c.retrieveID()), 5));
				}
			});

			viewRifPanel.getEditButton().addActionListener(e12 -> {
				if (viewRifPanel.getMyRifList().isSelectionEmpty())
					JOptionPane.showMessageDialog(null, "Errore di input");
				else {
					Riferimento r = c.RetrieveRiferimento(viewRifPanel.getMyRifList().getSelectedValue());
					id_rif = r.getId_Rif();
					viewRifPanel.getTitleField().setText(viewRifPanel.getMyRifList().getSelectedValue());
					viewRifPanel.getDescrPane().setText(r.getDescrizione());
					viewRifPanel.getLinkField().setText(r.getURL());
					viewRifPanel.getIsDigitalCheckBox().setSelected(r.getDigitale());
					viewRifPanel.getDoiField().setText(r.getDOI().toString());
					viewRifPanel.getDatePicker().setDate(r.getDataCreazione().toLocalDate());
					viewRifPanel.getBottoniRadio().clearSelection();
					switch (r.getTipo()) {
					case "Articolo":
						viewRifPanel.getIsArticoloRadioButton().setSelected(true);
						break;
					case "Libro":
						viewRifPanel.getIsLibroRadioButton().setSelected(true);
						break;
					case "Rivista":
						viewRifPanel.getIsRisorsaRadioButton().setSelected(true);
						break;
					case "Fascicolo":
						viewRifPanel.getIsDataSetRadioButton().setSelected(true);
						break;
					case "Conferenza":
						viewRifPanel.getIsConferenzaRadioButton().setSelected(true);
						break;
					}
					viewRifPanel.setSelection(true);
				}
			});
			viewRifPanel.getConfirmEditButton().addActionListener(e1 -> {
				if (viewRifPanel.isInputValid())
					c.updateRiferimento(id_rif, viewRifPanel.getTitleField().getText(),
							viewRifPanel.getDescrPane().getText(), viewRifPanel.getLinkField().getText(),
							Integer.parseInt(viewRifPanel.getDoiField().getText()),
							viewRifPanel.getIsDigitalCheckBox().isSelected(),
							Date.valueOf(viewRifPanel.getDatePicker().getDate()), viewRifPanel.getSelectedButton());
			});
			}
		});
	}
}