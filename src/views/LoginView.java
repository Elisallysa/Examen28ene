package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import models.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField pwdfPassword;
	private JButton btnLogin;
	private UsuarioDAO usuarioDAO;
	private JButton btnRegister;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
		this.usuarioDAO = new UsuarioDAO();
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				loginLogic();
			}
			}
		});
		tfUsername.setText("charmander");
		tfUsername.setBounds(109, 65, 186, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		pwdfPassword = new JPasswordField();
		pwdfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginLogic();
					}
			}
		});
		pwdfPassword.setBounds(109, 112, 186, 20);
		frame.getContentPane().add(pwdfPassword);
		
		btnLogin = new JButton("Login");
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			loginLogic();
			}
		});
		btnLogin.setBounds(109, 182, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		btnRegister = new JButton("Registrar");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterView();
				frame.dispose();
			}
		});
		btnRegister.setBounds(208, 182, 89, 23);
		frame.getContentPane().add(btnRegister);
	}
	
	public void loginLogic() {
		// Conectar con la BD y comprobar la tabla usuarios pa ver si furula.
		String username = tfUsername.getText();
		String password = new String(pwdfPassword.getPassword());
		Usuario usuario = new Usuario(0, username, password);
		boolean loginCorrecto = usuarioDAO.login(usuario);
		if(loginCorrecto) {
			JOptionPane.showMessageDialog(btnLogin, "Login correcto Bob.");
			new MatriculaView();
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(btnLogin, "Ah ah aaaah... login incorrecto...");
		}
	}
}
