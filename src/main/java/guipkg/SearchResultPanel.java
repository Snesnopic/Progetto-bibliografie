package guipkg;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchResultPanel extends JPanel {
	private JButton backButton;

	public SearchResultPanel(String testo, String selectedButton, Object[][] dataRicerca) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setBackButton(new JButton("Indietro"));
		backButton.setBackground(new Color(14, 22, 23));
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.insets = new Insets(0, 0, 5, 5);
		gbc_backButton.gridx = 1;
		gbc_backButton.gridy = 0;
		add(backButton, gbc_backButton);
		JLabel searchResultLabel = new JLabel("Risultati ricerca");
		GridBagConstraints gbc_searchResultLabel = new GridBagConstraints();
		gbc_searchResultLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchResultLabel.insets = new Insets(0, 0, 5, 5);
		gbc_searchResultLabel.gridx = 2;
		gbc_searchResultLabel.gridy = 0;
		add(searchResultLabel, gbc_searchResultLabel);
		searchResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchResultLabel.setForeground(new Color(255, 255, 255));
		searchResultLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 36));
		JLabel recapRicercaLabel = new JLabel("di " + testo + " per " + selectedButton);
		GridBagConstraints gbc_recapRicercaLabel = new GridBagConstraints();
		gbc_recapRicercaLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_recapRicercaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_recapRicercaLabel.anchor = GridBagConstraints.NORTH;
		gbc_recapRicercaLabel.gridx = 2;
		gbc_recapRicercaLabel.gridy = 1;
		add(recapRicercaLabel, gbc_recapRicercaLabel);
		recapRicercaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		recapRicercaLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 26));
		recapRicercaLabel.setForeground(new Color(255, 255, 255));
		setBackground(new Color(23, 33, 43));
		JTable ricercaTable = new JTable();
		JScrollPane ricercaTablePanel = new JScrollPane();
		GridBagConstraints gbc_ricercaTablePanel = new GridBagConstraints();
		gbc_ricercaTablePanel.fill = GridBagConstraints.BOTH;
		gbc_ricercaTablePanel.insets = new Insets(0, 0, 5, 5);
		gbc_ricercaTablePanel.gridx = 2;
		gbc_ricercaTablePanel.gridy = 2;
		add(ricercaTablePanel, gbc_ricercaTablePanel);
		ricercaTable.setForeground(new Color(255, 255, 255));
		ricercaTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
		ricercaTable.setBackground(new Color(14, 22, 23));
		ricercaTable.setFillsViewportHeight(true);
		ricercaTablePanel.setViewportView(ricercaTable);
		ricercaTable.setModel(new DefaultTableModel(dataRicerca,
				new String[] { "Titolo", "Autori", "Data", "URL", "DOI", "Categorie", "Tipo" }) {
			final boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
}
