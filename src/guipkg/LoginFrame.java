package guipkg;
import ctrlpkg.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
	private JPanel loginWindow;
	private JTextField CFField;
	
	private Controller c;
	
	public LoginFrame(Controller c){
		setResizable(false);
		this.c=c;
		setBackground(Color.WHITE);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
				
		loginWindow = new JPanel();
		setContentPane(loginWindow);	
		
		CFField = new JTextField();
		CFField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		CFField.setToolTipText("Username");
		
		
		
		JLabel loginLabel = new JLabel("Effettua l'accesso");
		loginLabel.setVerticalAlignment(SwingConstants.TOP);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 41));
		
		JLabel CFLabel = new JLabel("Codice Fiscale");
		CFLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		
		JButton loginButton = new JButton("OK");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(CFField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Errore di input");		
				else
				{
					if(c.login(CFField.getText()))
						JOptionPane.showMessageDialog(null, "Login riuscito");
					else
						JOptionPane.showMessageDialog(null, "Login fallito");
				}
			}
		});
		loginButton.setBackground(Color.WHITE);
		GroupLayout gl_loginWindow = new GroupLayout(loginWindow);
		gl_loginWindow.setHorizontalGroup(
			gl_loginWindow.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_loginWindow.createSequentialGroup()
					.addGap(100)
					.addGroup(gl_loginWindow.createParallelGroup(Alignment.LEADING)
						.addComponent(loginButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
						.addGroup(gl_loginWindow.createSequentialGroup()
							.addComponent(CFLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 407, Short.MAX_VALUE))
						.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 439, Short.MAX_VALUE)
						.addComponent(CFField, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
					.addGap(100))
		);
		gl_loginWindow.setVerticalGroup(
			gl_loginWindow.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addGap(20)
					.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(CFLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CFField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(286))
		);
		loginWindow.setLayout(gl_loginWindow);
		
	
	}
	void emptyFields()
	{
		CFField.setText("");
	}
}
