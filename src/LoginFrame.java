import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

public class LoginFrame extends JFrame {
	private JPanel LoginWindow;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel PasswordLabel;
	private Controller c;
	
	public LoginFrame(Controller c){
		this.c=c;
		setBackground(Color.WHITE);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 350);
				
		LoginWindow = new JPanel();
		setContentPane(LoginWindow);	
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		usernameField.setToolTipText("Username");
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		
		JLabel LoginLabel = new JLabel("Effettua l'accesso");
		LoginLabel.setVerticalAlignment(SwingConstants.TOP);
		LoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LoginLabel.setFont(new Font("Yu Gothic Light", LoginLabel.getFont().getStyle(), LoginLabel.getFont().getSize() + 30));
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		
		PasswordLabel = new JLabel("Password");
		PasswordLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		
		JButton LoginButton = new JButton("OK");
		LoginButton.addActionListener(new ActionListener() {
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
		LoginButton.setBackground(Color.WHITE);
		
		JLabel PasswordForgotLabel = new JLabel("Hai scordato la password?");
		PasswordForgotLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_LoginWindow = new GroupLayout(LoginWindow);
		gl_LoginWindow.setHorizontalGroup(
			gl_LoginWindow.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_LoginWindow.createSequentialGroup()
					.addGap(157)
					.addComponent(LoginLabel, GroupLayout.PREFERRED_SIZE, 292, Short.MAX_VALUE)
					.addGap(156))
				.addGroup(gl_LoginWindow.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_LoginWindow.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LoginWindow.createSequentialGroup()
							.addGap(124)
							.addGroup(gl_LoginWindow.createParallelGroup(Alignment.LEADING)
								.addComponent(UsernameLabel)
								.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)))
						.addGroup(gl_LoginWindow.createSequentialGroup()
							.addGap(126)
							.addGroup(gl_LoginWindow.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
								.addComponent(PasswordLabel)
								.addComponent(LoginButton, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))))
					.addGap(137))
				.addGroup(gl_LoginWindow.createSequentialGroup()
					.addGap(282)
					.addComponent(PasswordForgotLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(277))
		);
		gl_LoginWindow.setVerticalGroup(
			gl_LoginWindow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LoginWindow.createSequentialGroup()
					.addComponent(LoginLabel, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
					.addGap(2)
					.addComponent(UsernameLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(PasswordLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PasswordForgotLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(174))
		);
		LoginWindow.setLayout(gl_LoginWindow);
		
	
	}
	void emptyFields()
	{
		usernameField.setText("");
		passwordField.setText("");
	}
}
