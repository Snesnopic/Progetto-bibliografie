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
	private final JButton confirmEditButton;
	private final JButton editButton;
	private final JTextField titleField;
	private final JTextPane descrPane;
	private final JTextPane notePane;
	private final JTextField linkField;
	private JTextField doiField;

	public ViewRifPanel(String[] riferimenti) {
		setBackground(new Color(23, 33, 43));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0,
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
			isRisorsaRadioButton = new JRadioButton("Risorsa on-line");
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
			setIsDataSetRadioButton(new JRadioButton("Dataset"));
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
			JLabel noteLabel = new JLabel("Note autore");
			noteLabel.setForeground(Color.WHITE);
			noteLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
			GridBagConstraints gbc_noteLabel = new GridBagConstraints();
			gbc_noteLabel.fill = GridBagConstraints.VERTICAL;
			gbc_noteLabel.anchor = GridBagConstraints.EAST;
			gbc_noteLabel.insets = new Insets(0, 0, 5, 5);
			gbc_noteLabel.gridx = 2;
			gbc_noteLabel.gridy = 10;
			add(noteLabel, gbc_noteLabel);
			myRifList.setBackground(new Color(14, 22, 33));
		}
		{
			notePane = new JTextPane();
			notePane.setEnabled(false);
			notePane.setCaretColor(Color.WHITE);
			notePane.setForeground(Color.WHITE);
			notePane.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
			notePane.setBackground(new Color(14, 22, 33));
			GridBagConstraints gbc_notePane = new GridBagConstraints();
			gbc_notePane.gridwidth = 4;
			gbc_notePane.insets = new Insets(0, 0, 5, 5);
			gbc_notePane.fill = GridBagConstraints.BOTH;
			gbc_notePane.gridx = 3;
			gbc_notePane.gridy = 10;
			add(notePane, gbc_notePane);
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
			gbc_confirmEditButton.gridy = 11;
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

	}

	public JTextField getTitleField() {
		return titleField;
	}

	public JTextPane getDescrPane() {
		return descrPane;
	}

	public JTextPane getNotePane() {
		return notePane;
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
		descrPane.setEnabled(b);
		datePicker.setEnabled(b);
		notePane.setEnabled(b);
		confirmEditButton.setEnabled(b);
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