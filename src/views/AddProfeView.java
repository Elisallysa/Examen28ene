package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.AlumnoDAO;
import dao.ProfeDAO;
import models.Alumno;
import models.Profesor;

public class AddProfeView {

	private JFrame frame;
	private JTextField tfNombre;
	private AlumnoDAO alumnoDAO;
	private ProfeDAO profeDAO;
	private JButton btnCrear;
	private ArrayList<Profesor> profes;
	private JTextField tfId;

	/**
	 * Create the application.
	 */
	public AddProfeView() {
		this.alumnoDAO = new AlumnoDAO();
		this.profeDAO = new ProfeDAO();
		this.profes = profeDAO.getAll();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 581, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		


		tfNombre = new JTextField();
		tfNombre.setText("nombre");
		tfNombre.setBounds(101, 123, 243, 26);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarProfe();
			}
		});
		btnCrear.setBounds(219, 366, 89, 23);
		frame.getContentPane().add(btnCrear);
		
		tfId = new JTextField();
		tfId.setText("id");
		tfId.setColumns(10);
		tfId.setBounds(101, 89, 243, 26);
		frame.getContentPane().add(tfId);
		
	}

	private void insertarProfe() {
		if(tfNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(btnCrear, "Introduce un nombre");
		} else {
			try {
				//NO ME DA TIEMPO A CONTROLAR QUE EL ID SEA UN NÚMERO ENTERO!!
				Profesor p = new Profesor(Integer.parseInt(tfId.getText()), tfNombre.getText());
				
				if (!profeDAO.isCreated(p)) {
					profes.add(p);
					profeDAO.insert(p);
					JOptionPane.showMessageDialog(btnCrear, "Profe creado");
					new MatriculaView();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(btnCrear, "El/la profe ya existe.");
				}
				

			} catch(Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(btnCrear, "La calificación debe ser decimal.");
			}
		}
	}
	

}
