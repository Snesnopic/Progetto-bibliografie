package guipkg;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Enumeration;

public class ViewRifPanel extends JPanel {
	private final ButtonGroup bottoniRadio = new ButtonGroup();
	private JButton backButton;
	private JList<String> myRifList;
	private JButton deleteButton;
	private final JCheckBox isDigitalCheckBox;
	private final JRadioButton isArticoloRadioButton;
	private JRadioButton isDataSetRadioButton;
	private DatePicker datePicker;
	private final JRadioButton isLibroRadioButton;
	private final JRadioButton isRisorsaRadioButton;
	private JRadioButton isConferenzaRadioButton;
	private final JButton confirmEditButton;
	private final JButton editButton;
	private final JTextField titleField;
	private final JTextPane descrPane;
	private final JTextField linkField;
	private JTextField doiField;
	private final JTextField editoreTextField;
	private final JTextField edizioneTextField;
	private final JTextField luogoTextField;
	private final JTextField isbnTextField;
	private final JTextField pginiTextField;
	private final JTextField pgfinTextField;
	private final JTextField isnnTextField;

	public ViewRifPanel(String[] riferimenti) {
		setBackground(new Color(23, 33, 43));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
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

		JLabel viewRifLabel = new JLabel("Visualizza riferimenti");
		viewRifLabel.setForeground(Color.WHITE);
		viewRifLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		GridBagConstraints gbc_viewRifLabel = new GridBagConstraints();
		gbc_viewRifLabel.gridwidth = 4;
		gbc_viewRifLabel.insets = new Insets(0, 0, 5, 5);
		gbc_viewRifLabel.gridx = 3;
		gbc_viewRifLabel.gridy = 0;
		add(viewRifLabel, gbc_viewRifLabel);
		backButton.setBackground(new Color(14, 22, 23));
		JLabel selectRifLabel = new JLabel("Seleziona un riferimento");
		selectRifLabel.setForeground(Color.WHITE);
		selectRifLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_selectRifLabel = new GridBagConstraints();
		gbc_selectRifLabel.anchor = GridBagConstraints.EAST;
		gbc_selectRifLabel.fill = GridBagConstraints.VERTICAL;
		gbc_selectRifLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectRifLabel.gridx = 2;
		gbc_selectRifLabel.gridy = 1;
		add(selectRifLabel, gbc_selectRifLabel);

		setMyRifList(new JList<>());
		myRifList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myRifList.setListData(riferimenti);
		myRifList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_myRifList = new GridBagConstraints();
		gbc_myRifList.gridwidth = 4;
		gbc_myRifList.gridheight = 2;
		gbc_myRifList.insets = new Insets(0, 0, 5, 5);
		gbc_myRifList.fill = GridBagConstraints.BOTH;
		gbc_myRifList.gridx = 3;
		gbc_myRifList.gridy = 1;
		add(myRifList, gbc_myRifList);

		setDeleteButton(new JButton("Cancella"));

		deleteButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.fill = GridBagConstraints.BOTH;
		gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_deleteButton.gridx = 7;
		gbc_deleteButton.gridy = 1;
		add(deleteButton, gbc_deleteButton);

		editButton = new JButton("Modifica");
		editButton.setForeground(Color.WHITE);
		editButton.setBackground(new Color(14, 22, 33));
		editButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.anchor = GridBagConstraints.NORTH;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 7;
		gbc_editButton.gridy = 2;
		add(editButton, gbc_editButton);
		{
			JLabel titleLabel = new JLabel("Titolo");
			titleLabel.setForeground(Color.WHITE);
			titleLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_titleLabel = new GridBagConstraints();
			gbc_titleLabel.fill = GridBagConstraints.VERTICAL;
			gbc_titleLabel.anchor = GridBagConstraints.EAST;
			gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
			gbc_titleLabel.gridx = 2;
			gbc_titleLabel.gridy = 4;
			add(titleLabel, gbc_titleLabel);
		}
		{
			titleField = new JTextField();
			titleField.setEnabled(false);
			titleField.setCaretColor(Color.WHITE);
			titleField.setBackground(new Color(14, 22, 33));
			titleField.setForeground(Color.WHITE);
			titleField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			GridBagConstraints gbc_titleField = new GridBagConstraints();
			gbc_titleField.gridwidth = 4;
			gbc_titleField.insets = new Insets(0, 0, 5, 5);
			gbc_titleField.fill = GridBagConstraints.BOTH;
			gbc_titleField.gridx = 3;
			gbc_titleField.gridy = 4;
			add(titleField, gbc_titleField);
			titleField.setColumns(10);
		}
		{
			JLabel descriptionLabel = new JLabel("Descrizione");
			descriptionLabel.setForeground(Color.WHITE);
			descriptionLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
			gbc_descriptionLabel.fill = GridBagConstraints.VERTICAL;
			gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
			gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
			gbc_descriptionLabel.gridx = 2;
			gbc_descriptionLabel.gridy = 5;
			add(descriptionLabel, gbc_descriptionLabel);
		}
		{
			descrPane = new JTextPane();
			descrPane.setEnabled(false);
			descrPane.setCaretColor(Color.WHITE);
			descrPane.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			descrPane.setForeground(Color.WHITE);
			descrPane.setBackground(new Color(14, 22, 33));
			GridBagConstraints gbc_descrPane = new GridBagConstraints();
			gbc_descrPane.gridwidth = 4;
			gbc_descrPane.insets = new Insets(0, 0, 5, 5);
			gbc_descrPane.fill = GridBagConstraints.BOTH;
			gbc_descrPane.gridx = 3;
			gbc_descrPane.gridy = 5;
			add(descrPane, gbc_descrPane);
		}
		{
			JLabel dateLabel = new JLabel("Data");
			dateLabel.setForeground(Color.WHITE);
			dateLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_dateLabel = new GridBagConstraints();
			gbc_dateLabel.fill = GridBagConstraints.VERTICAL;
			gbc_dateLabel.anchor = GridBagConstraints.EAST;
			gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
			gbc_dateLabel.gridx = 2;
			gbc_dateLabel.gridy = 6;
			add(dateLabel, gbc_dateLabel);
		}
		setDatePicker(new DatePicker());
		datePicker.getComponentDateTextField().setCaretColor(Color.WHITE);
		datePicker.setBackground(new Color(14, 22, 33));
		datePicker.setForeground(Color.WHITE);
		datePicker.getComponentToggleCalendarButton().setForeground(Color.WHITE);
		datePicker.getComponentDateTextField().setForeground(Color.WHITE);
		datePicker.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		datePicker.getComponentDateTextField().setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		datePicker.getComponentDateTextField().setBackground(new Color(14, 22, 33));
		datePicker.getComponentDateTextField().setForeground(Color.WHITE);
		datePicker.getComponentToggleCalendarButton().setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		datePicker.getComponentToggleCalendarButton().setBackground(new Color(14, 22, 33));
		datePicker.getComponentToggleCalendarButton().setForeground(Color.WHITE);
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.gridwidth = 4;
		gbc_datePicker.fill = GridBagConstraints.BOTH;
		gbc_datePicker.insets = new Insets(0, 0, 5, 5);
		gbc_datePicker.gridx = 3;
		gbc_datePicker.gridy = 6;
		add(datePicker, gbc_datePicker);
		{
			JLabel linkLabel = new JLabel("URL");
			linkLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			linkLabel.setForeground(Color.WHITE);
			GridBagConstraints gbc_linkLabel = new GridBagConstraints();
			gbc_linkLabel.fill = GridBagConstraints.VERTICAL;
			gbc_linkLabel.anchor = GridBagConstraints.EAST;
			gbc_linkLabel.insets = new Insets(0, 0, 5, 5);
			gbc_linkLabel.gridx = 2;
			gbc_linkLabel.gridy = 7;
			add(linkLabel, gbc_linkLabel);
		}
		{
			linkField = new JTextField();
			linkField.setEnabled(false);
			linkField.setForeground(Color.WHITE);
			linkField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			linkField.setCaretColor(Color.WHITE);
			linkField.setBackground(new Color(14, 22, 33));
			GridBagConstraints gbc_linkField = new GridBagConstraints();
			gbc_linkField.gridwidth = 4;
			gbc_linkField.insets = new Insets(0, 0, 5, 5);
			gbc_linkField.fill = GridBagConstraints.BOTH;
			gbc_linkField.gridx = 3;
			gbc_linkField.gridy = 7;
			add(linkField, gbc_linkField);
			linkField.setColumns(10);
		}
		{
			isDigitalCheckBox = new JCheckBox("Digitale");
			isDigitalCheckBox.setEnabled(false);
			isDigitalCheckBox.setForeground(Color.WHITE);
			isDigitalCheckBox.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
			isDigitalCheckBox.setBackground(new Color(23, 33, 43));
			GridBagConstraints gbc_isDigitalCheckBox = new GridBagConstraints();
			gbc_isDigitalCheckBox.fill = GridBagConstraints.BOTH;
			gbc_isDigitalCheckBox.insets = new Insets(0, 0, 5, 5);
			gbc_isDigitalCheckBox.gridx = 7;
			gbc_isDigitalCheckBox.gridy = 7;
			add(isDigitalCheckBox, gbc_isDigitalCheckBox);
		}
		{
			JLabel doiLabel = new JLabel("DOI");
			doiLabel.setForeground(Color.WHITE);
			doiLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_doiLabel = new GridBagConstraints();
			gbc_doiLabel.fill = GridBagConstraints.VERTICAL;
			gbc_doiLabel.anchor = GridBagConstraints.EAST;
			gbc_doiLabel.insets = new Insets(0, 0, 5, 5);
			gbc_doiLabel.gridx = 2;
			gbc_doiLabel.gridy = 8;
			add(doiLabel, gbc_doiLabel);
		}
		{
			GridBagConstraints gbc_doiField = new GridBagConstraints();
			setDoiField(new JTextField());
			doiField.setCaretColor(Color.WHITE);
			doiField.setForeground(Color.WHITE);
			doiField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			doiField.setBackground(new Color(14, 22, 33));
			gbc_doiField.gridwidth = 4;
			gbc_doiField.insets = new Insets(0, 0, 5, 5);
			gbc_doiField.fill = GridBagConstraints.BOTH;
			gbc_doiField.gridx = 3;
			gbc_doiField.gridy = 8;
			add(doiField, gbc_doiField);
			doiField.setColumns(10);
		}
		{
			JLabel typeLabel = new JLabel("Tipo");
			typeLabel.setForeground(Color.WHITE);
			typeLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_typeLabel = new GridBagConstraints();
			gbc_typeLabel.fill = GridBagConstraints.VERTICAL;
			gbc_typeLabel.anchor = GridBagConstraints.EAST;
			gbc_typeLabel.insets = new Insets(0, 0, 5, 5);
			gbc_typeLabel.gridx = 2;
			gbc_typeLabel.gridy = 9;
			add(typeLabel, gbc_typeLabel);
		}
		{
			isArticoloRadioButton = new JRadioButton("Articolo");
			isArticoloRadioButton.setEnabled(false);
			bottoniRadio.add(isArticoloRadioButton);
			isArticoloRadioButton.setForeground(Color.WHITE);
			isArticoloRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			isArticoloRadioButton.setBackground(new Color(23, 33, 43));
			GridBagConstraints gbc_isArticoloRadioButton = new GridBagConstraints();
			gbc_isArticoloRadioButton.fill = GridBagConstraints.VERTICAL;
			gbc_isArticoloRadioButton.anchor = GridBagConstraints.WEST;
			gbc_isArticoloRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_isArticoloRadioButton.gridx = 3;
			gbc_isArticoloRadioButton.gridy = 9;
			add(isArticoloRadioButton, gbc_isArticoloRadioButton);
		}
		{
			isLibroRadioButton = new JRadioButton("Libro");
			isLibroRadioButton.setEnabled(false);
			bottoniRadio.add(isLibroRadioButton);
			isLibroRadioButton.setForeground(Color.WHITE);
			isLibroRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			isLibroRadioButton.setBackground(new Color(23, 33, 43));
			GridBagConstraints gbc_isLibroRadioButton = new GridBagConstraints();
			gbc_isLibroRadioButton.fill = GridBagConstraints.VERTICAL;
			gbc_isLibroRadioButton.anchor = GridBagConstraints.WEST;
			gbc_isLibroRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_isLibroRadioButton.gridx = 4;
			gbc_isLibroRadioButton.gridy = 9;
			add(isLibroRadioButton, gbc_isLibroRadioButton);
		}
		{
			isRisorsaRadioButton = new JRadioButton("Rivista");
			isRisorsaRadioButton.setEnabled(false);
			bottoniRadio.add(isRisorsaRadioButton);
			isRisorsaRadioButton.setForeground(Color.WHITE);
			isRisorsaRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			isRisorsaRadioButton.setBackground(new Color(23, 33, 43));
			GridBagConstraints gbc_isRisorsaRadioButton = new GridBagConstraints();
			gbc_isRisorsaRadioButton.fill = GridBagConstraints.VERTICAL;
			gbc_isRisorsaRadioButton.anchor = GridBagConstraints.WEST;
			gbc_isRisorsaRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_isRisorsaRadioButton.gridx = 5;
			gbc_isRisorsaRadioButton.gridy = 9;
			add(isRisorsaRadioButton, gbc_isRisorsaRadioButton);
		}
		{
			setIsDataSetRadioButton(new JRadioButton("Fascicolo"));
			isDataSetRadioButton.setEnabled(false);
			bottoniRadio.add(isDataSetRadioButton);
			isDataSetRadioButton.setForeground(Color.WHITE);
			isDataSetRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			isDataSetRadioButton.setBackground(new Color(23, 33, 43));
			GridBagConstraints gbc_isDataSetRadioButton = new GridBagConstraints();
			gbc_isDataSetRadioButton.fill = GridBagConstraints.VERTICAL;
			gbc_isDataSetRadioButton.anchor = GridBagConstraints.WEST;
			gbc_isDataSetRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_isDataSetRadioButton.gridx = 6;
			gbc_isDataSetRadioButton.gridy = 9;
			add(isDataSetRadioButton, gbc_isDataSetRadioButton);
		}
		{
			setIsConferenzaRadioButton(new JRadioButton("Conferenza"));
			isConferenzaRadioButton.setEnabled(false);
			bottoniRadio.add(isConferenzaRadioButton);
			isConferenzaRadioButton.setForeground(Color.WHITE);
			isConferenzaRadioButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			isConferenzaRadioButton.setBackground(new Color(23, 33, 43));
			GridBagConstraints gbc_isConferenzaRadioButton = new GridBagConstraints();
			gbc_isConferenzaRadioButton.fill = GridBagConstraints.VERTICAL;
			gbc_isConferenzaRadioButton.anchor = GridBagConstraints.WEST;
			gbc_isConferenzaRadioButton.insets = new Insets(0, 0, 5, 5);
			gbc_isConferenzaRadioButton.gridx = 7;
			gbc_isConferenzaRadioButton.gridy = 9;
			add(isConferenzaRadioButton, gbc_isConferenzaRadioButton);
		}
		{
			JLabel editoreLabel = new JLabel("Editore");
			editoreLabel.setForeground(Color.WHITE);
			editoreLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_editoreLabel = new GridBagConstraints();
			gbc_editoreLabel.anchor = GridBagConstraints.EAST;
			gbc_editoreLabel.insets = new Insets(0, 0, 5, 5);
			gbc_editoreLabel.gridx = 2;
			gbc_editoreLabel.gridy = 10;
			add(editoreLabel, gbc_editoreLabel);
		}
		{
			editoreTextField = new JTextField();
			GridBagConstraints gbc_editoreTextField = new GridBagConstraints();
			editoreTextField.setCaretColor(Color.WHITE);
			editoreTextField.setForeground(Color.WHITE);
			editoreTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			editoreTextField.setBackground(new Color(14, 22, 33));
			gbc_editoreTextField.gridwidth = 4;
			gbc_editoreTextField.insets = new Insets(0, 0, 5, 5);
			gbc_editoreTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_editoreTextField.gridx = 3;
			gbc_editoreTextField.gridy = 10;
			add(editoreTextField, gbc_editoreTextField);
			editoreTextField.setColumns(10);
		}
		{
			JLabel edizioneLabel = new JLabel("Edizione");
			edizioneLabel.setForeground(Color.WHITE);
			edizioneLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_edizioneLabel = new GridBagConstraints();
			gbc_edizioneLabel.anchor = GridBagConstraints.EAST;
			gbc_edizioneLabel.insets = new Insets(0, 0, 5, 5);
			gbc_edizioneLabel.gridx = 2;
			gbc_edizioneLabel.gridy = 11;
			add(edizioneLabel, gbc_edizioneLabel);
		}
		{
			edizioneTextField = new JTextField();
			GridBagConstraints gbc_edizioneTextField = new GridBagConstraints();
			edizioneTextField.setCaretColor(Color.WHITE);
			edizioneTextField.setForeground(Color.WHITE);
			edizioneTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			edizioneTextField.setBackground(new Color(14, 22, 33));
			gbc_edizioneTextField.gridwidth = 4;
			gbc_edizioneTextField.insets = new Insets(0, 0, 5, 5);
			gbc_edizioneTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_edizioneTextField.gridx = 3;
			gbc_edizioneTextField.gridy = 11;
			add(edizioneTextField, gbc_edizioneTextField);
			edizioneTextField.setColumns(10);
		}
		{
			JLabel luogoLabel = new JLabel("Luogo");
			luogoLabel.setForeground(Color.WHITE);
			luogoLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_luogoLabel = new GridBagConstraints();
			gbc_luogoLabel.anchor = GridBagConstraints.EAST;
			gbc_luogoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_luogoLabel.gridx = 2;
			gbc_luogoLabel.gridy = 12;
			add(luogoLabel, gbc_luogoLabel);
		}
		{
			luogoTextField = new JTextField();
			GridBagConstraints gbc_luogoTextField = new GridBagConstraints();
			luogoTextField.setCaretColor(Color.WHITE);
			luogoTextField.setForeground(Color.WHITE);
			luogoTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			luogoTextField.setBackground(new Color(14, 22, 33));
			gbc_luogoTextField.gridwidth = 4;
			gbc_luogoTextField.insets = new Insets(0, 0, 5, 5);
			gbc_luogoTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_luogoTextField.gridx = 3;
			gbc_luogoTextField.gridy = 12;
			add(luogoTextField, gbc_luogoTextField);
			luogoTextField.setColumns(10);
		}
		{
			JLabel isbnLabel = new JLabel("ISBN");
			isbnLabel.setForeground(Color.WHITE);
			isbnLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_isbnLabel = new GridBagConstraints();
			gbc_isbnLabel.anchor = GridBagConstraints.EAST;
			gbc_isbnLabel.insets = new Insets(0, 0, 5, 5);
			gbc_isbnLabel.gridx = 2;
			gbc_isbnLabel.gridy = 13;
			add(isbnLabel, gbc_isbnLabel);
		}
		{
			isbnTextField = new JTextField();
			GridBagConstraints gbc_isbnTextField = new GridBagConstraints();
			isbnTextField.setCaretColor(Color.WHITE);
			isbnTextField.setForeground(Color.WHITE);
			isbnTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			isbnTextField.setBackground(new Color(14, 22, 33));
			gbc_isbnTextField.gridwidth = 4;
			gbc_isbnTextField.insets = new Insets(0, 0, 5, 5);
			gbc_isbnTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_isbnTextField.gridx = 3;
			gbc_isbnTextField.gridy = 13;
			add(isbnTextField, gbc_isbnTextField);
			isbnTextField.setColumns(10);
		}
		{
			JLabel pginiLabel = new JLabel("Pag. Inizio");
			pginiLabel.setForeground(Color.WHITE);
			pginiLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_pginiLabel = new GridBagConstraints();
			gbc_pginiLabel.anchor = GridBagConstraints.EAST;
			gbc_pginiLabel.insets = new Insets(0, 0, 5, 5);
			gbc_pginiLabel.gridx = 2;
			gbc_pginiLabel.gridy = 14;
			add(pginiLabel, gbc_pginiLabel);
		}
		{
			pginiTextField = new JTextField();
			GridBagConstraints gbc_pginiTextField = new GridBagConstraints();
			pginiTextField.setCaretColor(Color.WHITE);
			pginiTextField.setForeground(Color.WHITE);
			pginiTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			pginiTextField.setBackground(new Color(14, 22, 33));
			gbc_pginiTextField.gridwidth = 4;
			gbc_pginiTextField.insets = new Insets(0, 0, 5, 5);
			gbc_pginiTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_pginiTextField.gridx = 3;
			gbc_pginiTextField.gridy = 14;
			add(pginiTextField, gbc_pginiTextField);
			pginiTextField.setColumns(10);
		}
		{
			JLabel pgfinLabel = new JLabel("Pag. Fine");
			pgfinLabel.setForeground(Color.WHITE);
			pgfinLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_pgfinLabel = new GridBagConstraints();
			gbc_pgfinLabel.anchor = GridBagConstraints.EAST;
			gbc_pgfinLabel.insets = new Insets(0, 0, 5, 5);
			gbc_pgfinLabel.gridx = 2;
			gbc_pgfinLabel.gridy = 15;
			add(pgfinLabel, gbc_pgfinLabel);
		}
		{
			pgfinTextField = new JTextField();
			GridBagConstraints gbc_pgfinTextField = new GridBagConstraints();
			pgfinTextField.setCaretColor(Color.WHITE);
			pgfinTextField.setForeground(Color.WHITE);
			pgfinTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			pgfinTextField.setBackground(new Color(14, 22, 33));
			gbc_pgfinTextField.gridwidth = 4;
			gbc_pgfinTextField.insets = new Insets(0, 0, 5, 5);
			gbc_pgfinTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_pgfinTextField.gridx = 3;
			gbc_pgfinTextField.gridy = 15;
			add(pgfinTextField, gbc_pgfinTextField);
			pgfinTextField.setColumns(10);
		}
		{
			JLabel isnnLabel = new JLabel("ISNN");
			isnnLabel.setForeground(Color.WHITE);
			isnnLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_isnnLabel = new GridBagConstraints();
			gbc_isnnLabel.anchor = GridBagConstraints.EAST;
			gbc_isnnLabel.insets = new Insets(0, 0, 5, 5);
			gbc_isnnLabel.gridx = 2;
			gbc_isnnLabel.gridy = 16;
			add(isnnLabel, gbc_isnnLabel);
		}
		{
			isnnTextField = new JTextField();
			isnnTextField.setCaretColor(Color.WHITE);
			isnnTextField.setForeground(Color.WHITE);
			isnnTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			isnnTextField.setBackground(new Color(14, 22, 33));
			GridBagConstraints gbc_isnnTextField = new GridBagConstraints();
			gbc_isnnTextField.gridwidth = 4;
			gbc_isnnTextField.insets = new Insets(0, 0, 5, 5);
			gbc_isnnTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_isnnTextField.gridx = 3;
			gbc_isnnTextField.gridy = 16;
			add(isnnTextField, gbc_isnnTextField);
			isnnTextField.setColumns(10);
		}
		{
			confirmEditButton = new JButton("Conferma");
			confirmEditButton.setEnabled(false);
			confirmEditButton.setForeground(Color.WHITE);
			confirmEditButton.setBackground(new Color(14, 22, 33));
			confirmEditButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
			GridBagConstraints gbc_confirmEditButton = new GridBagConstraints();
			gbc_confirmEditButton.fill = GridBagConstraints.BOTH;
			gbc_confirmEditButton.gridwidth = 4;
			gbc_confirmEditButton.insets = new Insets(0, 0, 5, 5);
			gbc_confirmEditButton.gridx = 3;
			gbc_confirmEditButton.gridy = 17;
			add(confirmEditButton, gbc_confirmEditButton);
		}
		isDigitalCheckBox.addChangeListener(e -> linkField.setEnabled(isDigitalCheckBox.isSelected()));
		doiField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
	}

	private void setIsConferenzaRadioButton(JRadioButton jRadioButton) {
		this.isConferenzaRadioButton = jRadioButton;
		isDataSetRadioButton.setEnabled(false);
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
		datePicker.setEnabled(false);
	}

	public JCheckBox getIsDigitalCheckBox() {
		return isDigitalCheckBox;
	}

	public JRadioButton getIsArticoloRadioButton() {
		return isArticoloRadioButton;
	}

	public JRadioButton getIsLibroRadioButton() {
		return isLibroRadioButton;
	}

	public JRadioButton getIsRisorsaRadioButton() {
		return isRisorsaRadioButton;
	}

	public JRadioButton getIsDataSetRadioButton() {
		return isDataSetRadioButton;
	}
	public JRadioButton getIsConferenzaRadioButton() {
		return isConferenzaRadioButton;
	}
	public void setIsDataSetRadioButton(JRadioButton isDataSetRadioButton) {
		this.isDataSetRadioButton = isDataSetRadioButton;
		isDataSetRadioButton.setEnabled(false);
	}

	public ButtonGroup getBottoniRadio() {
		return bottoniRadio;
	}

	public JButton getConfirmEditButton() {
		return confirmEditButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
		backButton.setForeground(Color.WHITE);
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton dltbtn) {
		deleteButton = dltbtn;
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(new Color(14, 22, 33));
	}

	public JList<String> getMyRifList() {
		return this.myRifList;
	}

	public void setMyRifList(JList<String> myRifList) {
		this.myRifList = myRifList;
		myRifList.setForeground(Color.WHITE);
		myRifList.setBackground(Color.BLACK);
	}

	public JTextField getTitleField() {
		return titleField;
	}

	public JTextPane getDescrPane() {
		return descrPane;
	}

	public JTextField getLinkField() {
		return linkField;
	}
	public JTextField getDoiField() {
		return doiField;
	}
	private void setDoiField(JTextField jTextField) {
		doiField = jTextField;
		doiField.setEnabled(false);
	}

	public void setSelection(boolean b) {
		doiField.setEnabled(b);
		linkField.setEnabled(b);
		titleField.setEnabled(b);
		isLibroRadioButton.setEnabled(b);
		isDataSetRadioButton.setEnabled(b);
		isArticoloRadioButton.setEnabled(b);
		isRisorsaRadioButton.setEnabled(b);
		isDigitalCheckBox.setEnabled(b);
		isConferenzaRadioButton.setEnabled(b);
		descrPane.setEnabled(b);
		datePicker.setEnabled(b);
		confirmEditButton.setEnabled(b);
		editoreTextField.setEnabled(b);
		isnnTextField.setEnabled(b);
		pginiTextField.setEnabled(b);
		pgfinTextField.setEnabled(b);
		isbnTextField.setEnabled(b);
		edizioneTextField.setEnabled(b);
		luogoTextField.setEnabled(b);
	}

	public boolean isInputValid() {
		return !datePicker.getDate().isAfter(LocalDate.now()) && !titleField.getText().isBlank()
				&& (!isDigitalCheckBox.isSelected() || !linkField.getText().isBlank());
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
}