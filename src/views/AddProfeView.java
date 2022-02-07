package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import dao.ProfeDAO;

import models.Profesor;

public class AddProfeView {

	private JFrame frame;
	private JTextField tfNombre;
	private ProfeDAO profeDAO;
	private JButton btnCrear;
	private ArrayList<Profesor> profes;
	private JTextField tfId;
	private JLabel lblId;
	private JLabel lblNombre;

	/**
	 * Create the application.
	 */
	public AddProfeView() {
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
		tfNombre.setBounds(155, 123, 243, 26);
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
		tfId.setEditable(false);
		tfId.setText(String.valueOf(profes.size()+1));
		tfId.setColumns(10);
		tfId.setBounds(155, 89, 243, 26);
		frame.getContentPane().add(tfId);
		
		lblId = new JLabel("Id profe");
		lblId.setBounds(46, 95, 115, 13);
		frame.getContentPane().add(lblId);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 129, 115, 13);
		frame.getContentPane().add(lblNombre);
		
	}

	private void insertarProfe() {
		if(tfNombre.getText().isEmpty()) {
			JOptionPane.showMessageDialog(btnCrear, "Introduce un nombre");
		} else {
			try {
				Profesor p = new Profesor(Integer.parseInt(tfId.getText()), tfNombre.getText());
				if (!profeDAO.isCreated(p)) {
					profes.add(p);
					profeDAO.insert(p);
					JOptionPane.showMessageDialog(btnCrear, "Profe creado.");
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
