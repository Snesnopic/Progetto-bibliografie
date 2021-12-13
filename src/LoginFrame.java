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
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
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
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		usernameField.setToolTipText("Username");
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		
		
		
		JLabel loginLabel = new JLabel("Effettua l'accesso");
		loginLabel.setVerticalAlignment(SwingConstants.TOP);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 41));
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		
		JButton loginButton = new JButton("OK");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				boolean error = false;
				String messaggio = "";
				if(usernameField.getText().isEmpty())
				{
					messaggio = "\nCampo username vuoto!";
					error = true;
				}
				if(passwordField.getPassword().length==0)
				{
					messaggio = messaggio +"\nCampo password vuoto!";
					error = true;
				}
				if(error)
					JOptionPane.showMessageDialog(null, "Errore di input"+messaggio);
				else
				{
					if(c.login(usernameField.getText(), passwordField.getPassword()))
						JOptionPane.showMessageDialog(null, "Login riuscito");
					else
						JOptionPane.showMessageDialog(null, "Login fallito");
				}
			}
		});
		loginButton.setBackground(Color.WHITE);
		
		JLabel ppasswordForgotLabel = new JLabel("Hai scordato la password?");
		ppasswordForgotLabel.setForeground(Color.BLUE);
		ppasswordForgotLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Mi dispiace");
			}
		});
		ppasswordForgotLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		ppasswordForgotLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_loginWindow = new GroupLayout(loginWindow);
		gl_loginWindow.setHorizontalGroup(
			gl_loginWindow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_loginWindow.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_loginWindow.createSequentialGroup()
							.addGap(100)
							.addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
						.addGroup(gl_loginWindow.createSequentialGroup()
							.addGap(100)
							.addGroup(gl_loginWindow.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_loginWindow.createSequentialGroup()
									.addComponent(usernameLabel)
									.addPreferredGap(ComponentPlacement.RELATED, 387, Short.MAX_VALUE))
								.addComponent(passwordLabel)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
								.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 439, Short.MAX_VALUE))))
					.addGap(100))
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addGap(100)
					.addComponent(ppasswordForgotLabel, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
					.addGap(100))
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addGap(100)
					.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
					.addGap(100))
		);
		gl_loginWindow.setVerticalGroup(
			gl_loginWindow.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addGap(20)
					.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(usernameLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ppasswordForgotLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(240))
		);
		loginWindow.setLayout(gl_loginWindow);
		
	
	}
	void emptyFields()
	{
		usernameField.setText("");
		passwordField.setText("");
	}
}
