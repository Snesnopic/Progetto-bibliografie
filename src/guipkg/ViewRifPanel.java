package guipkg;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class ViewRifPanel extends JPanel
{
	private JButton backButton;
	public ViewRifPanel()
	{
		setBackground(new Color(23, 33, 43));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		gbc_viewRifLabel.insets = new Insets(0, 0, 5, 5);
		gbc_viewRifLabel.gridx = 3;
		gbc_viewRifLabel.gridy = 0;
		add(viewRifLabel, gbc_viewRifLabel);
		backButton.setBackground(new Color(14, 22, 23));
		JLabel selectRifLabel = new JLabel("Seleziona un riferimento");
		selectRifLabel.setForeground(Color.WHITE);
		selectRifLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		GridBagConstraints gbc_selectRifLabel = new GridBagConstraints();
		gbc_selectRifLabel.anchor = GridBagConstraints.SOUTH;
		gbc_selectRifLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectRifLabel.gridx = 2;
		gbc_selectRifLabel.gridy = 1;
		add(selectRifLabel, gbc_selectRifLabel);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.anchor = GridBagConstraints.SOUTH;
		gbc_list.gridheight = 2;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridx = 3;
		gbc_list.gridy = 1;
		add(list, gbc_list);
		
		JButton deleteButton = new JButton("Cancella");
		deleteButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.anchor = GridBagConstraints.SOUTH;
		gbc_deleteButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_deleteButton.gridx = 4;
		gbc_deleteButton.gridy = 1;
		add(deleteButton, gbc_deleteButton);
		
		JButton editButton = new JButton("Modifica");
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.anchor = GridBagConstraints.NORTH;
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 4;
		gbc_editButton.gridy = 2;
		add(editButton, gbc_editButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
	}
	public JButton getBackButton() {
		return backButton;
	}
	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
		backButton.setForeground(Color.WHITE);
	}

}