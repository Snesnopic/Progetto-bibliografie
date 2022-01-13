package guipkg;
import ctrlpkg.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private JPanel loginWindow;
	private JTextField CFField;
	private Controller c;
	
	public LoginFrame(Controller c) throws IOException
	{
        setIconImage(ImageIO.read(getClass().getResource("/logo.png")));
		this.setC(c);
		setBackground(Color.WHITE);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
				
		loginWindow = new JPanel();
		loginWindow.setBackground(new Color(23, 33, 43));
		setContentPane(loginWindow);	
		
		CFField = new JTextField();
		CFField.setForeground(new Color(255, 255, 255));
		CFField.setHorizontalAlignment(SwingConstants.CENTER);
		CFField.setBackground(new Color(14, 22, 33));
		CFField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				switch(e.getKeyCode())
				{
					case '\n':
						if(CFField.getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Errore di input");		
						else
						{
							CFField.setText(CFField.getText().toUpperCase());
							try
							{
								if(c.login(CFField.getText()))
									JOptionPane.showMessageDialog(null, "Login riuscito");
								else
									JOptionPane.showMessageDialog(null, "Login fallito");
							}
							catch (HeadlessException | IOException e1)
							{
								JOptionPane.showMessageDialog(null,"Errore: "+e1.getMessage());
							}
						}
				}
			}
		});
		CFField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		CFField.setToolTipText("Username");
		
		
		
		JLabel loginLabel = new JLabel("Effettua l'accesso");
		loginLabel.setBackground(new Color(255, 255, 255));
		loginLabel.setForeground(new Color(255, 255, 255));
		loginLabel.setVerticalAlignment(SwingConstants.TOP);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 41));
		
		JLabel CFLabel = new JLabel("Codice Fiscale");
		CFLabel.setBackground(new Color(255, 255, 255));
		CFLabel.setForeground(new Color(255, 255, 255));
		CFLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		
		JButton loginButton = new JButton("OK");
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(CFField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Errore di input");
				else
				{
					CFField.setText(CFField.getText().toUpperCase());
					try
					{
						if(c.login(CFField.getText()))
							JOptionPane.showMessageDialog(null, "Login riuscito");
						else
							JOptionPane.showMessageDialog(null, "Login fallito");
					}
					catch (HeadlessException | IOException e1)
					{
						JOptionPane.showMessageDialog(null,"Errore: "+e1.getMessage());
					}
				}
			}
		});
		loginButton.setBackground(new Color(14, 22, 33));
		
		JLabel uninaLogoLabel = new JLabel("");
		uninaLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_loginWindow = new GroupLayout(loginWindow);
		gl_loginWindow.setHorizontalGroup(
			gl_loginWindow.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addGap(100)
					.addGroup(gl_loginWindow.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_loginWindow.createSequentialGroup()
							.addComponent(CFLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(414))
						.addGroup(gl_loginWindow.createSequentialGroup()
							.addGroup(gl_loginWindow.createParallelGroup(Alignment.TRAILING)
								.addComponent(loginButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
								.addComponent(CFField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
							.addGap(124))))
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addGroup(gl_loginWindow.createParallelGroup(Alignment.TRAILING)
						.addComponent(uninaLogoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
						.addComponent(loginLabel, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_loginWindow.setVerticalGroup(
			gl_loginWindow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginWindow.createSequentialGroup()
					.addComponent(uninaLogoLabel, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(loginLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CFLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CFField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		loginWindow.setLayout(gl_loginWindow);
		uninaLogoLabel.setIcon(new StretchIcon(getClass().getClassLoader().getResource("unina_logo.png")));
	}
	public void emptyFields()
	{
		CFField.setText("");
	}
	public Controller getC() {
		return c;
	}
	public void setC(Controller c) {
		this.c = c;
	}
}
