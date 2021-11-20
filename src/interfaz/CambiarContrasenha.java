package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CambiarContrasenha extends JFrame {

	private JPanel contentPane;
	public JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarContrasenha frame = new CambiarContrasenha();
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
	public CambiarContrasenha() {
		setTitle("Cambiar contrase\u00F1a");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a actual");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 132, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblContraseaNueva = new JLabel("Contrase\u00F1a nueva");
		lblContraseaNueva.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContraseaNueva.setBounds(10, 34, 132, 14);
		contentPane.add(lblContraseaNueva);
		
		JLabel lblRepetir = new JLabel("Repetir");
		lblRepetir.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRepetir.setBounds(10, 59, 132, 14);
		contentPane.add(lblRepetir);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 9, 132, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(152, 32, 132, 20);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(152, 57, 132, 20);
		contentPane.add(passwordField_2);
		
		JButton btnNewButton = new JButton("Cambiar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(10, 84, 132, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(10, 126, 282, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	
	
	public void CambiarClave(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String usr = "root";
			String password = "";
			java.sql.Connection con = DriverManager.getConnection(url, usr, password);
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select contrasenha from usuario where nombre_usuario='"+lblNewLabel_1.getText()+"' and contrasenha='"+passwordField.getText()+"'");
			
			if(rs.next() && passwordField_1.getText().trim().equals(passwordField_2.getText().trim())) {
				stm.executeUpdate("update usuario set contrasenha='"+passwordField_1.getText()+"' where nombre_usuario='"+lblNewLabel_1.getText()+"'");
				JOptionPane.showMessageDialog(null, "Clave cambiada correctamente");
				passwordField.setText("");
				passwordField_1.setText("");
				passwordField_2.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "Las claves no coinciden");
			}
			
			con.close();
			
		}catch(ClassNotFoundException ex) {
		}catch (SQLException f) {
			f.printStackTrace();
		}
	}
}
