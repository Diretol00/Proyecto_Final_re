package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class AgregarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public JLabel lblNewLabel_1;
	private JPasswordField contra;
	private JPasswordField repContra;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarCliente frame = new AgregarCliente();
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
	public AgregarCliente() {
		setTitle("Agregar Cliente");
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
		
		textField = new JTextField();
		textField.setBounds(133, 13, 126, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
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
						stm.executeUpdate("INSERT INTO usuario(nombre_usuario, contrasenha, tipo_usuario) values('"+textField.getText()+"', '"+contra.getText()+"', 'cliente')");
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
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setBounds(10, 150, 249, 14);
		contentPane.add(lblNewLabel_1);
		
		contra = new JPasswordField();
		contra.setBounds(133, 48, 126, 20);
		contentPane.add(contra);
		
		repContra = new JPasswordField();
		repContra.setBounds(133, 83, 126, 20);
		contentPane.add(repContra);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Ver contrase\u00F1a");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()) {
					contra.setEchoChar((char)0);
				}else {
					contra.setEchoChar('•');
				}
			}
		});
		chckbxNewCheckBox.setBounds(269, 47, 131, 23);
		contentPane.add(chckbxNewCheckBox);
	}
}
