package guipkg;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class CreaRifPanel extends JPanel {
	private ButtonGroup bottoniRadio;
	private JList<String> citazioniList;
	private JTextField nameField;
	private JTextField linkField;
	private JTextField doiField;
	private JButton backButton;
	private JTextPane descriptionPane;
	private JList<String> autoriList;
	private JList<String> categorieList;
	private DatePicker datePicker;
	private JTextPane notePane;
	private JCheckBox isDigitalCheckBox;
	private JButton createButton;

	public CreaRifPanel(String[] utenti, String[] categorie, String[] citazioni) {
		setBackground(new Color(23, 33, 43));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		setBackButton(new JButton("Indietro"));
		backButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.anchor = GridBagConstraints.WEST;
		gbc_backButton.insets = new Insets(0, 0, 5, 5);
		gbc_backButton.gridx = 1;
		gbc_backButton.gridy = 0;
		add(backButton, gbc_backButton);

		JLabel newRifLabel = new JLabel("Nuovo riferimento");
		newRifLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		newRifLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_newRifLabel = new GridBagConstraints();
		gbc_newRifLabel.gridwidth = 4;
		gbc_newRifLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newRifLabel.gridx = 3;
		gbc_newRifLabel.gridy = 0;
		add(newRifLabel, gbc_newRifLabel);
		backButton.setBackground(new Color(14, 22, 23));
		JLabel nameLabel = new JLabel("Nome riferimento");
		nameLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		nameLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 2;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);

		setNameField(new JTextField());
		nameField.setCaretColor(Color.WHITE);
		nameField.setForeground(Color.WHITE);
		nameField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		nameField.setBackground(new Color(14, 22, 33));
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 4;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 1;
		add(nameField, gbc_nameField);
		nameField.setColumns(10);

		JLabel descriptionLabel = new JLabel("Descrizione");
		descriptionLabel.setForeground(Color.WHITE);
		descriptionLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 2;
		gbc_descriptionLabel.gridy = 2;
		add(descriptionLabel, gbc_descriptionLabel);

		setDescriptionPane(new JTextPane());
		descriptionPane.setCaretColor(Color.WHITE);
		descriptionPane.setBorder(UIManager.getBorder("ComboBox.border"));
		descriptionPane.setForeground(Color.WHITE);
		descriptionPane.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_descriptionPane = new GridBagConstraints();
		gbc_descriptionPane.gridwidth = 4;
		gbc_descriptionPane.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionPane.fill = GridBagConstraints.BOTH;
		gbc_descriptionPane.gridx = 3;
		gbc_descriptionPane.gridy = 2;
		descriptionPane.setBackground(new Color(14, 22, 33));
		add(descriptionPane, gbc_descriptionPane);

		JLabel dateLabel = new JLabel("Data");
		dateLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		dateLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_dateLabel = new GridBagConstraints();
		gbc_dateLabel.anchor = GridBagConstraints.EAST;
		gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dateLabel.gridx = 2;
		gbc_dateLabel.gridy = 3;
		add(dateLabel, gbc_dateLabel);

		setDatePicker(new DatePicker());
		datePicker.getComponentDateTextField().setCaretColor(Color.WHITE);
		datePicker.setBackground(new Color(14, 22, 33));
		datePicker.setForeground(Color.WHITE);
		datePicker.getComponentToggleCalendarButton().setForeground(Color.WHITE);
		datePicker.getComponentDateTextField().setForeground(Color.WHITE);
		datePicker.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		datePicker.getComponentDateTextField().setText("01/01/2022");
		datePicker.getComponentDateTextField().setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		datePicker.getComponentDateTextField().setBackground(new Color(14, 22, 33));
		datePicker.getComponentDateTextField().setForeground(Color.WHITE);
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		datePicker.getComponentToggleCalendarButton().setBackground(new Color(14, 22, 33));
		datePicker.getComponentToggleCalendarButton().setForeground(Color.WHITE);
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.gridwidth = 4;
		gbc_datePicker.fill = GridBagConstraints.HORIZONTAL;
		gbc_datePicker.insets = new Insets(0, 0, 5, 5);
		gbc_datePicker.gridx = 3;
		gbc_datePicker.gridy = 3;
		add(datePicker, gbc_datePicker);

		JLabel linkLabel = new JLabel("URL");
		linkLabel.setForeground(Color.WHITE);
		linkLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_linkLabel = new GridBagConstraints();
		gbc_linkLabel.anchor = GridBagConstraints.EAST;
		gbc_linkLabel.insets = new Insets(0, 0, 5, 5);
		gbc_linkLabel.gridx = 2;
		gbc_linkLabel.gridy = 4;
		add(linkLabel, gbc_linkLabel);

		setLinkField(new JTextField());
		linkField.setCaretColor(Color.WHITE);
		linkField.setForeground(Color.WHITE);
		linkField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		linkField.setBackground(new Color(14, 22, 33));
		GridBagConstraints gbc_linkField = new GridBagConstraints();
		gbc_linkField.gridwidth = 4;
		gbc_linkField.insets = new Insets(0, 0, 5, 5);
		gbc_linkField.fill = GridBagConstraints.HORIZONTAL;
		gbc_linkField.gridx = 3;
		gbc_linkField.gridy = 4;
		add(linkField, gbc_linkField);
		linkField.setColumns(10);

		setIsDigitalCheckBox(new JCheckBox("Digitale"));
		isDigitalCheckBox.addChangeListener(e -> linkField.setEnabled(isDigitalCheckBox.isSelected()));
		isDigitalCheckBox.setForeground(Color.WHITE);
		isDigitalCheckBox.setBackground(new Color(23, 33, 43));
		isDigitalCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		isDigitalCheckBox.setSelected(true);
		GridBagConstraints gbc_isDigitalCheckBox = new GridBagConstraints();
		gbc_isDigitalCheckBox.anchor = GridBagConstraints.WEST;
		gbc_isDigitalCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_isDigitalCheckBox.gridx = 7;
		gbc_isDigitalCheckBox.gridy = 4;
		add(isDigitalCheckBox, gbc_isDigitalCheckBox);

		JLabel doiLabel = new JLabel("DOI");
		doiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		doiLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		doiLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_doiLabel = new GridBagConstraints();
		gbc_doiLabel.anchor = GridBagConstraints.EAST;
		gbc_doiLabel.insets = new Insets(0, 0, 5, 5);
		gbc_doiLabel.gridx = 2;
		gbc_doiLabel.gridy = 5;
		add(doiLabel, gbc_doiLabel);

		setDoiField(new JTextField());
		doiField.setCaretColor(Color.WHITE);
		doiField.setForeground(Color.WHITE);
		doiField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		doiField.setBackground(new Color(14, 22, 33));
		GridBagConstraints gbc_doiField = new GridBagConstraints();
		gbc_doiField.gridwidth = 4;
		gbc_doiField.insets = new Insets(0, 0, 5, 5);
		gbc_doiField.fill = GridBagConstraints.HORIZONTAL;
		gbc_doiField.gridx = 3;
		gbc_doiField.gridy = 5;
		add(doiField, gbc_doiField);
		doiField.setColumns(10);

		JLabel typeLabel = new JLabel("Tipo");
		typeLabel.setForeground(Color.WHITE);
		typeLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_typeLabel.gridx = 2;
		gbc_typeLabel.gridy = 6;
		add(typeLabel, gbc_typeLabel);

		JRadioButton isArticoloRadioButton = new JRadioButton("Articolo");
		isArticoloRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		isArticoloRadioButton.setForeground(Color.WHITE);
		isArticoloRadioButton.setBackground(new Color(23, 33, 43));
		GridBagConstraints gbc_isArticoloRadioButton = new GridBagConstraints();
		gbc_isArticoloRadioButton.anchor = GridBagConstraints.WEST;
		gbc_isArticoloRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_isArticoloRadioButton.gridx = 3;
		gbc_isArticoloRadioButton.gridy = 6;
		add(isArticoloRadioButton, gbc_isArticoloRadioButton);

		JRadioButton isLibroRadioButton = new JRadioButton("Libro");
		isLibroRadioButton.setBackground(new Color(23, 33, 43));
		isLibroRadioButton.setForeground(Color.WHITE);
		isLibroRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_isLibroRadioButton = new GridBagConstraints();
		gbc_isLibroRadioButton.anchor = GridBagConstraints.WEST;
		gbc_isLibroRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_isLibroRadioButton.gridx = 4;
		gbc_isLibroRadioButton.gridy = 6;
		add(isLibroRadioButton, gbc_isLibroRadioButton);

		JRadioButton isRisorsaRadioButton = new JRadioButton("Rivista");
		isRisorsaRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		isRisorsaRadioButton.setForeground(Color.WHITE);
		isRisorsaRadioButton.setBackground(new Color(23, 33, 43));
		GridBagConstraints gbc_isRisorsaRadioButton = new GridBagConstraints();
		gbc_isRisorsaRadioButton.anchor = GridBagConstraints.WEST;
		gbc_isRisorsaRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_isRisorsaRadioButton.gridx = 5;
		gbc_isRisorsaRadioButton.gridy = 6;
		add(isRisorsaRadioButton, gbc_isRisorsaRadioButton);

		JRadioButton isDataSetRadioButton = new JRadioButton("Fascicolo");
		isDataSetRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		isDataSetRadioButton.setForeground(Color.WHITE);
		isDataSetRadioButton.setBackground(new Color(23, 33, 43));
		GridBagConstraints gbc_isDataSetRadioButton = new GridBagConstraints();
		gbc_isDataSetRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_isDataSetRadioButton.gridx = 6;
		gbc_isDataSetRadioButton.gridy = 6;
		add(isDataSetRadioButton, gbc_isDataSetRadioButton);

		JRadioButton isConferenzaRadioButton = new JRadioButton("Conferenza");
		isConferenzaRadioButton.setBackground(new Color(23, 33, 43));
		isConferenzaRadioButton.setForeground(Color.WHITE);
		isConferenzaRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_isConferenzaRadioButton = new GridBagConstraints();
		gbc_isConferenzaRadioButton.anchor = GridBagConstraints.WEST;
		gbc_isConferenzaRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_isConferenzaRadioButton.gridx = 7;
		gbc_isConferenzaRadioButton.gridy = 6;
		add(isConferenzaRadioButton, gbc_isConferenzaRadioButton);
		
		JLabel noteLabel = new JLabel("Note autore");
		noteLabel.setForeground(Color.WHITE);
		noteLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_noteLabel = new GridBagConstraints();
		gbc_noteLabel.anchor = GridBagConstraints.EAST;
		gbc_noteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_noteLabel.gridx = 2;
		gbc_noteLabel.gridy = 7;
		add(noteLabel, gbc_noteLabel);

		setNotePane(new JTextPane());
		notePane.setBorder(UIManager.getBorder("ComboBox.border"));
		notePane.setCaretColor(Color.WHITE);
		notePane.setBackground(new Color(14, 22, 33));
		notePane.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_notePane = new GridBagConstraints();
		gbc_notePane.gridwidth = 4;
		gbc_notePane.insets = new Insets(0, 0, 5, 5);
		gbc_notePane.fill = GridBagConstraints.BOTH;
		gbc_notePane.gridx = 3;
		gbc_notePane.gridy = 7;
		add(notePane, gbc_notePane);

		JLabel autoriLabel = new JLabel("Autori");
		autoriLabel.setForeground(Color.WHITE);
		autoriLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_autoriLabel = new GridBagConstraints();
		gbc_autoriLabel.anchor = GridBagConstraints.EAST;
		gbc_autoriLabel.insets = new Insets(0, 0, 5, 5);
		gbc_autoriLabel.gridx = 2;
		gbc_autoriLabel.gridy = 8;
		add(autoriLabel, gbc_autoriLabel);

		setAutoriList(new JList<>());
		autoriList.setBackground(new Color(14, 22, 33));
		autoriList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		autoriList.setListData(utenti);
		GridBagConstraints gbc_autoriList = new GridBagConstraints();
		gbc_autoriList.gridwidth = 4;
		gbc_autoriList.insets = new Insets(0, 0, 5, 5);
		gbc_autoriList.fill = GridBagConstraints.BOTH;
		gbc_autoriList.gridx = 3;
		gbc_autoriList.gridy = 8;
		add(autoriList, gbc_autoriList);

		JLabel categorieLabel = new JLabel("Categorie");
		categorieLabel.setForeground(Color.WHITE);
		categorieLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_categorieLabel = new GridBagConstraints();
		gbc_categorieLabel.anchor = GridBagConstraints.EAST;
		gbc_categorieLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categorieLabel.gridx = 2;
		gbc_categorieLabel.gridy = 9;
		add(categorieLabel, gbc_categorieLabel);

		setCategorieList(new JList<>());
		categorieList.setBackground(new Color(14, 22, 33));
		categorieList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		categorieList.setListData(categorie);
		GridBagConstraints gbc_categorieList = new GridBagConstraints();
		gbc_categorieList.gridwidth = 4;
		gbc_categorieList.insets = new Insets(0, 0, 5, 5);
		gbc_categorieList.fill = GridBagConstraints.BOTH;
		gbc_categorieList.gridx = 3;
		gbc_categorieList.gridy = 9;
		categorieList.setBorder(UIManager.getBorder("ComboBox.border"));
		categorieList.setForeground(Color.WHITE);
		add(categorieList, gbc_categorieList);

		JLabel citazioniLabel = new JLabel("Citazioni");
		citazioniLabel.setForeground(Color.WHITE);
		citazioniLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_citazioniLabel = new GridBagConstraints();
		gbc_citazioniLabel.anchor = GridBagConstraints.EAST;
		gbc_citazioniLabel.insets = new Insets(0, 0, 5, 5);
		gbc_citazioniLabel.gridx = 2;
		gbc_citazioniLabel.gridy = 10;
		add(citazioniLabel, gbc_citazioniLabel);

		setCitazioniList(new JList<>());
		citazioniList.setForeground(Color.WHITE);
		citazioniList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		citazioniList.setBorder(UIManager.getBorder("ComboBox.border"));
		citazioniList.setBackground(new Color(14, 22, 33));
		citazioniList.setListData(citazioni);
		GridBagConstraints gbc_citazioniList = new GridBagConstraints();
		gbc_citazioniList.gridwidth = 4;
		gbc_citazioniList.insets = new Insets(0, 0, 5, 5);
		gbc_citazioniList.fill = GridBagConstraints.BOTH;
		gbc_citazioniList.gridx = 3;
		gbc_citazioniList.gridy = 10;
		add(citazioniList, gbc_citazioniList);

		setCreateButton(new JButton("Crea"));
		createButton.setForeground(Color.WHITE);
		createButton.setBackground(new Color(14, 22, 23));
		createButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.anchor = GridBagConstraints.NORTH;
		gbc_createButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_createButton.gridwidth = 4;
		gbc_createButton.insets = new Insets(0, 0, 5, 5);
		gbc_createButton.gridx = 3;
		gbc_createButton.gridy = 11;
		add(createButton, gbc_createButton);
		notePane.setForeground(Color.WHITE);
		setBottoniRadio(new ButtonGroup());
		isArticoloRadioButton.setSelected(true);
		bottoniRadio.add(isDataSetRadioButton);
		bottoniRadio.add(isRisorsaRadioButton);
		bottoniRadio.add(isLibroRadioButton);
		bottoniRadio.add(isArticoloRadioButton);
		bottoniRadio.add(isConferenzaRadioButton);
		doiField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});
	}

	public void setBottoniRadio(ButtonGroup bottoniRadio) {
		this.bottoniRadio = bottoniRadio;
	}

	public JCheckBox getIsDigitalCheckBox() {
		return isDigitalCheckBox;
	}

	public void setIsDigitalCheckBox(JCheckBox isDigitalCheckBox) {
		this.isDigitalCheckBox = isDigitalCheckBox;
	}

	public JTextPane getDescriptionPane() {
		return descriptionPane;
	}

	private void setDescriptionPane(JTextPane jTextPane) {
		this.descriptionPane = jTextPane;
	}

	public void setCategorieList(JList<String> categorieList) {
		this.categorieList = categorieList;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getLinkField() {
		return linkField;
	}

	public void setLinkField(JTextField linkField) {
		this.linkField = linkField;
	}

	public JTextField getDoiField() {
		return doiField;
	}

	public void setDoiField(JTextField doiField) {
		this.doiField = doiField;
	}

	public void setAutoriList(JList<String> autoriList) {
		this.autoriList = autoriList;
		autoriList.setBorder(UIManager.getBorder("ComboBox.border"));
		autoriList.setForeground(Color.WHITE);
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
		backButton.setForeground(Color.WHITE);
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

	public void setNotePane(JTextPane notePane) {
		this.notePane = notePane;
	}

	public JButton getCreateButton() {
		return createButton;
	}

	public void setCreateButton(JButton createButton) {
		this.createButton = createButton;
	}

	public boolean isInputValid() {
		return !datePicker.getDate().isAfter(LocalDate.now()) && !nameField.getText().isBlank()
				&& (!isDigitalCheckBox.isSelected() || !linkField.getText().isBlank())
				&& !categorieList.isSelectionEmpty();
	}

	public String getSelectedButton() {
		for (Enumeration<AbstractButton> buttons = bottoniRadio.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	public ArrayList<Integer> getSelectedUsers() {
		return getIntegers(autoriList);
	}

	public ArrayList<Integer> getSelectedCategories() {
		return getIntegers(categorieList);
	}

	private ArrayList<Integer> getIntegers(JList<String> categorieList) {
		int[] selectedIx = categorieList.getSelectedIndices();
		for (int i = 0; i < selectedIx.length; i++) {
			selectedIx[i]++;
		}
		List<Integer> tempList = Arrays.stream(selectedIx).boxed().toList();
		return new ArrayList<>(tempList);
	}

	public ArrayList<Integer> getSelectedCitazioni() {
		return getIntegers(citazioniList);
	}

	public void setCitazioniList(JList<String> citazioniList) {
		this.citazioniList = citazioniList;
	}
}