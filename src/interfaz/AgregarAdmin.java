package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class AgregarAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JPasswordField contra;
	private JPasswordField repContra;
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarAdmin frame = new AgregarAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AgregarAdmin() {
		setTitle("Agregar Administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 212);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 11, 113, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 46, 113, 24);
		contentPane.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir contrase\u00F1a");
		lblRepetirContrasea.setBounds(10, 81, 113, 24);
		contentPane.add(lblRepetirContrasea);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(133, 13, 126, 20);
		contentPane.add(textField_2);
		
		btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String usr = "root";
					String password = "";
					java.sql.Connection con = DriverManager.getConnection(url, usr, password);
					Statement stm = con.createStatement();
					
					if(contra.getText().trim().equals(repContra.getText().trim())) {
						stm.executeUpdate("INSERT INTO usuario(nombre_usuario, contrasenha, tipo_usuario) values('"+textField_2.getText()+"', '"+contra.getText()+"', 'admin')");
						lblNewLabel_1.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					}
					
					con.close();
					
				}catch(ClassNotFoundException ex) {
					System.out.println("Error loading the J Driver");
				}catch (SQLException f) {
					System.out.println("Error connecting to DB");
					f.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 116, 113, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Agregado con exito!");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setBounds(10, 150, 249, 14);
		contentPane.add(lblNewLabel_1);
		
		contra = new JPasswordField();
		contra.setBounds(133, 48, 126, 20);
		contentPane.add(contra);
		
		repContra = new JPasswordField();
		repContra.setBounds(133, 83, 126, 20);
		contentPane.add(repContra);
		
		chckbxNewCheckBox = new JCheckBox("Ver contrase\u00F1a");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()) {
					contra.setEchoChar((char)0);
				}else {
					contra.setEchoChar('•');
				}
			}
		});
		chckbxNewCheckBox.setBounds(271, 47, 128, 23);
		contentPane.add(chckbxNewCheckBox);
	}

}
