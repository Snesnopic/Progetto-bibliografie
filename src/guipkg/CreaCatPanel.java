package guipkg;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class CreaCatPanel extends JPanel
{
	private JTextField textField;

	public CreaCatPanel() {
		setBackground(new Color(23, 33, 43));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 32, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton backButton = new JButton("Indietro");
		backButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.insets = new Insets(0, 0, 5, 5);
		gbc_backButton.gridx = 0;
		gbc_backButton.gridy = 0;
		add(backButton, gbc_backButton);
		
		JLabel newCatLabel = new JLabel("Nuova categoria");
		newCatLabel.setForeground(Color.WHITE);
		newCatLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 30));
		GridBagConstraints gbc_newCatLabel = new GridBagConstraints();
		gbc_newCatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newCatLabel.gridx = 2;
		gbc_newCatLabel.gridy = 0;
		add(newCatLabel, gbc_newCatLabel);
		
		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel superCatLabel = new JLabel("Sopra-categoria");
		superCatLabel.setForeground(Color.WHITE);
		superCatLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_superCatLabel = new GridBagConstraints();
		gbc_superCatLabel.anchor = GridBagConstraints.EAST;
		gbc_superCatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_superCatLabel.gridx = 1;
		gbc_superCatLabel.gridy = 2;
		add(superCatLabel, gbc_superCatLabel);
		
		JList superCatList = new JList();
		superCatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		superCatList.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		superCatList.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_superCatList = new GridBagConstraints();
		gbc_superCatList.insets = new Insets(0, 0, 5, 5);
		gbc_superCatList.fill = GridBagConstraints.BOTH;
		gbc_superCatList.gridx = 2;
		gbc_superCatList.gridy = 2;
		add(superCatList, gbc_superCatList);
		
		JButton createButton = new JButton("Crea");
		createButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_createButton.insets = new Insets(0, 0, 5, 5);
		gbc_createButton.gridx = 2;
		gbc_createButton.gridy = 3;
		add(createButton, gbc_createButton);

	}

}