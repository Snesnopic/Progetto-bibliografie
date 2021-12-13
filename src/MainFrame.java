import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {

	private JPanel MainPane;
	private Controller c;
	public MainFrame(Controller c) 
	{
		this.c = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		MainPane = new JPanel();
		MainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		MainPane.setLayout(new BorderLayout(0, 0));
		setContentPane(MainPane);
		
		JSplitPane splitPane = new JSplitPane();
		MainPane.add(splitPane, BorderLayout.WEST);
	}

}
