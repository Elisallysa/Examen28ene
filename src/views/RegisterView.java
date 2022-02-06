package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import models.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterView {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmPassword;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblConfirmarPassword;
	private JButton btnRegister;
	private UsuarioDAO usuarioDAO;
	private JLabel lblRegistrar;


	/**
	 * Create the application.
	 */
	public RegisterView() {
		initialize();
		this.usuarioDAO = new UsuarioDAO();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblRegistrar = new JLabel("Registro de usuarios");
		lblRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegistrar.setBounds(96, 11, 242, 37);
		frame.getContentPane().add(lblRegistrar);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(184, 82, 180, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(184, 113, 180, 20);
		frame.getContentPane().add(pwdPassword);
		
		pwdConfirmPassword = new JPasswordField();
		pwdConfirmPassword.setBounds(184, 144, 180, 20);
		frame.getContentPane().add(pwdConfirmPassword);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(23, 82, 108, 14);
		frame.getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(23, 113, 117, 14);
		frame.getContentPane().add(lblPassword);
		
		lblConfirmarPassword = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarPassword.setBounds(21, 144, 153, 14);
		frame.getContentPane().add(lblConfirmarPassword);
		
		btnRegister = new JButton("Registrar");
		
		btnRegister.addActionListener(new ActionListener() {
			//OnClick
			public void actionPerformed(ActionEvent e) {
			registration();
			}
		});
		
		pwdConfirmPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				registration();
			}
				
			}
		});
		
		btnRegister.setBounds(153, 201, 89, 23);
		frame.getContentPane().add(btnRegister);
	
	
	
	}
	
	public void registration() {
		String username = tfUsername.getText();
		String password = new String(pwdPassword.getPassword());
		String confirmarPassword = new String(pwdConfirmPassword.getPassword());
		
		if(password.equals(confirmarPassword)) {
			if(!username.isEmpty() && !password.isEmpty() && !confirmarPassword.isEmpty()) {
				//TODO 2. En el registro, consigue que valide que la contraseña tenga al menos 8 caracteres (1 pto.)
				
				if (password.length()>=8) {
					Usuario u = new Usuario(0, username, password);
					boolean alreadyRegisteredUser = usuarioDAO.consulta(u);
					if (!alreadyRegisteredUser) {
						usuarioDAO.register(u);
						JOptionPane.showMessageDialog(btnRegister, "Usuario registrado correctamente");
						new LoginView();
						frame.dispose();	
					} else {
						JOptionPane.showMessageDialog(btnRegister, "Este usuario ya está registrado.");
					}
				
				} else {
					JOptionPane.showMessageDialog(lblRegistrar, "La contraseña debe tener 8 o más caracteres.");
				}
				
				
			} else {
				JOptionPane.showMessageDialog(lblRegistrar, "Las contraseñas no coinciden");
			}
		} else {
			JOptionPane.showMessageDialog(lblRegistrar, "Rellena todos los campos");
		}
	}
}
