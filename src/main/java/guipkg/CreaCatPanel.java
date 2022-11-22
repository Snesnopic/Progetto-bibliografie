package guipkg;

import javax.swing.*;
import java.awt.*;

public class CreaCatPanel extends JPanel {
	private final JTextField textField;
	private JButton backButton;
	private JButton createButton;
	private JList<String> superCatList;

	public CreaCatPanel(String[] categorie) {
		setBackground(new Color(23, 33, 43));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		setBackButton(new JButton("Indietro"));
		backButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.anchor = GridBagConstraints.WEST;
		gbc_backButton.insets = new Insets(0, 0, 5, 5);
		gbc_backButton.gridx = 1;
		gbc_backButton.gridy = 0;
		add(backButton, gbc_backButton);

		JLabel newCatLabel = new JLabel("Nuova categoria");
		newCatLabel.setForeground(Color.WHITE);
		newCatLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		GridBagConstraints gbc_newCatLabel = new GridBagConstraints();
		gbc_newCatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newCatLabel.gridx = 3;
		gbc_newCatLabel.gridy = 0;
		add(newCatLabel, gbc_newCatLabel);

		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 2;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);

		textField = new JTextField();
		textField.setCaretColor(Color.WHITE);
		textField.setForeground(Color.WHITE);
		textField.setBackground(new Color(14, 22, 23));
		textField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		backButton.setBackground(new Color(14, 22, 23));
		JLabel superCatLabel = new JLabel("Sopra-categoria");
		superCatLabel.setForeground(Color.WHITE);
		superCatLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_superCatLabel = new GridBagConstraints();
		gbc_superCatLabel.anchor = GridBagConstraints.EAST;
		gbc_superCatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_superCatLabel.gridx = 2;
		gbc_superCatLabel.gridy = 2;
		add(superCatLabel, gbc_superCatLabel);

		setSuperCatList(new JList<>());
		superCatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		superCatList.setListData(categorie);
		superCatList.setBackground(new Color(14, 22, 23));
		superCatList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_superCatList = new GridBagConstraints();
		gbc_superCatList.insets = new Insets(0, 0, 5, 5);
		gbc_superCatList.fill = GridBagConstraints.BOTH;
		gbc_superCatList.gridx = 3;
		gbc_superCatList.gridy = 2;
		add(superCatList, gbc_superCatList);

		setCreateButton(new JButton("Crea"));
		createButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		createButton.setForeground(new Color(255, 255, 255));
		createButton.setBackground(new Color(14, 22, 23));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.anchor = GridBagConstraints.NORTH;
		gbc_createButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_createButton.insets = new Insets(0, 0, 0, 5);
		gbc_createButton.gridx = 3;
		gbc_createButton.gridy = 3;
		add(createButton, gbc_createButton);
		superCatList.setForeground(Color.WHITE);
		backButton.setForeground(Color.WHITE);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JList<String> getSuperCatList() {
		return superCatList;
	}

	private void setSuperCatList(JList<String> jList) {
		this.superCatList = jList;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JButton getCreateButton() {
		return createButton;
	}

	public void setCreateButton(JButton createButton) {
		this.createButton = createButton;
	}

}