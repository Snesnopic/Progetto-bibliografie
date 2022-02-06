package guipkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class SearchResultPanel extends JPanel
{
	private JButton backButton;
	public SearchResultPanel(String testo, String selectedButton, Object[][] dataRicerca)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
				JLabel searchResultLabel = new JLabel("Risultati ricerca");
				GridBagConstraints gbc_searchResultLabel = new GridBagConstraints();
				gbc_searchResultLabel.anchor = GridBagConstraints.NORTH;
				gbc_searchResultLabel.insets = new Insets(0, 0, 5, 5);
				gbc_searchResultLabel.gridx = 2;
				gbc_searchResultLabel.gridy = 0;
				add(searchResultLabel, gbc_searchResultLabel);

				searchResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
				searchResultLabel.setForeground(new Color(255, 255, 255));
				searchResultLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 36));
				JLabel recapRicercaLabel = new JLabel("di "+testo+" per "+selectedButton);
				GridBagConstraints gbc_recapRicercaLabel = new GridBagConstraints();
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
				gbc_ricercaTablePanel.fill = GridBagConstraints.VERTICAL;
				gbc_ricercaTablePanel.insets = new Insets(0, 0, 5, 5);
				gbc_ricercaTablePanel.gridx = 2;
				gbc_ricercaTablePanel.gridy = 2;
				add(ricercaTablePanel, gbc_ricercaTablePanel);

				ricercaTable.setForeground(new Color(255, 255, 255));
				ricercaTable.setFont(new Font("Yu Gothic UI", Font.PLAIN, 11));
				ricercaTable.setBackground(new Color(14, 22, 23));
				ricercaTable.setFillsViewportHeight(true);

				ricercaTablePanel.setViewportView(ricercaTable);
				ricercaTable.setModel(new DefaultTableModel(dataRicerca,new String[] {"Titolo", "Autori", "Data", "DOI/URL", "Categorie","Tipo"})
				{
					boolean[] columnEditables = new boolean[] {false, false, false, false, false,false};
					@Override
					public boolean isCellEditable(int row, int column)
					{
						return columnEditables[column];
					}
				});
				
								setBackButton(new JButton("Torna indietro"));
								backButton.setBackground(new Color(14, 22, 23));
								backButton.setForeground(Color.WHITE);
								backButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
								GridBagConstraints gbc_backButton = new GridBagConstraints();
								gbc_backButton.insets = new Insets(0, 0, 5, 5);
								gbc_backButton.gridx = 2;
								gbc_backButton.gridy = 3;
								add(backButton, gbc_backButton);

	}
	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	public JButton getBackButton() {
		return backButton;
	}
}
