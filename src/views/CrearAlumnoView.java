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

public class CrearAlumnoView {

	private JFrame frame;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfCiclo;
	private JTextField tfMedia;
	private JLabel lblMatricula;
	private AlumnoDAO alumnoDAO;
	private ProfeDAO profeDAO;
	private JButton btnCrear;
	private JComboBox<String> cbProfe1;
	private JComboBox<String> cbProfe2;
	private ArrayList<Profesor> profes;

	/**
	 * Create the application.
	 */
	public CrearAlumnoView() {
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

		lblMatricula = new JLabel("Crear alumno");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblMatricula.setBounds(152, 11, 243, 45);
		frame.getContentPane().add(lblMatricula);


		tfNombre = new JTextField();
		tfNombre.setText("nombre");
		tfNombre.setBounds(101, 123, 243, 26);
		frame.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);

		tfApellidos = new JTextField();
		tfApellidos.setText("apellidos");
		tfApellidos.setColumns(10);
		tfApellidos.setBounds(101, 170, 243, 26);
		frame.getContentPane().add(tfApellidos);

		tfCiclo = new JTextField();
		tfCiclo.setToolTipText("ciclo");
		tfCiclo.setText("ciclo");
		tfCiclo.setColumns(10);
		tfCiclo.setBounds(101, 217, 243, 26);
		frame.getContentPane().add(tfCiclo);

		tfMedia = new JTextField();
		tfMedia.setToolTipText("media");
		tfMedia.setFont(new Font("Tahoma", Font.PLAIN, 26));
		tfMedia.setBounds(398, 123, 86, 70);
		frame.getContentPane().add(tfMedia);
		tfMedia.setColumns(10);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarAlumno();
			}
		});
		btnCrear.setBounds(219, 366, 89, 23);
		frame.getContentPane().add(btnCrear);
		
		cbProfe1 = new JComboBox<String>();
		cbProfe1.setEditable(true);
		cbProfe1.setBounds(101, 301, 151, 22);
		frame.getContentPane().add(cbProfe1);
		
		cbProfe2 = new JComboBox<String>();
		cbProfe2.setEditable(true);
		cbProfe2.setBounds(333, 301, 151, 22);
		frame.getContentPane().add(cbProfe2);
		fillProfes();
	}

	private void insertarAlumno() {
		if(tfApellidos.getText().isEmpty() || tfNombre.getText().isEmpty() || 
				tfCiclo.getText().isEmpty() || tfMedia.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(btnCrear, "Revisa todos los campos");
		} else {
			try {
				double media = Double.parseDouble(tfMedia.getText());
				Alumno a = new Alumno(0, tfNombre.getText(), tfApellidos.getText(),
						tfCiclo.getText(), media);
				
				// Sacar el valor de los profes de los comboboxes
				if(cbProfe1.getSelectedIndex() != cbProfe2.getSelectedIndex() - 1) {
					
					Profesor profe1 = profes.get(cbProfe1.getSelectedIndex());
				a.setProfe1(profe1);
				if(cbProfe2.getSelectedIndex() != 0 && cbProfe1.getSelectedIndex() != cbProfe2.getSelectedIndex() - 1) {
					Profesor profe2 =  profes.get(cbProfe2.getSelectedIndex()-1); //+2 porque el primero es Ninguno
					a.setProfe2(profe2);
				}
				} else {
					JOptionPane.showMessageDialog(btnCrear, "Los profesores deben ser diferentes");
				}
				
				
				
				alumnoDAO.insert(a);
				JOptionPane.showMessageDialog(btnCrear, "Alumno creado");
				new MatriculaView();
				frame.dispose();

			} catch(Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(btnCrear, "La calificación debe ser decimal.");
			}
		}
	}
	
	private void fillProfes() {
		cbProfe2.addItem("Ninguno");
		for(Profesor p : profes) {
			cbProfe1.addItem(p.getNombre());
			cbProfe2.addItem(p.getNombre());
		}
	}
}
