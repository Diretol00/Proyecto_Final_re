package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AgregarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarProducto frame = new AgregarProducto();
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
	public AgregarProducto() {
		setTitle("Agregar producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 426, 305);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(10, 11, 392, 253);
		contentPane.add(panel1);
		
		JLabel lblnom = new JLabel("Nombre");
		lblnom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblnom.setBounds(10, 10, 83, 26);
		panel1.add(lblnom);
		
		JLabel lblprecio = new JLabel("Precio");
		lblprecio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblprecio.setBounds(10, 47, 83, 26);
		panel1.add(lblprecio);
		
		JLabel lblmarca = new JLabel("Marca");
		lblmarca.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblmarca.setBounds(10, 84, 133, 26);
		panel1.add(lblmarca);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(155, 13, 173, 24);
		panel1.add(textField);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModelo.setBounds(10, 121, 133, 26);
		panel1.add(lblModelo);
		
		JLabel lblExistencias = new JLabel("Existencias");
		lblExistencias.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblExistencias.setBounds(10, 158, 133, 26);
		panel1.add(lblExistencias);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(155, 51, 173, 24);
		panel1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(155, 88, 173, 24);
		panel1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(155, 125, 173, 24);
		panel1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(155, 162, 173, 24);
		panel1.add(textField_4);
		
		JButton btnadd = new JButton("Agregar");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarProducto();
			}
		});
		btnadd.setBounds(10, 211, 133, 31);
		panel1.add(btnadd);
		btnadd.setFont(new Font("Tahoma", Font.BOLD, 13));
	}
	
	
	
	
	public void AgregarProducto() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String usr = "root";
			String password = "";
			java.sql.Connection con = DriverManager.getConnection(url, usr, password);
			Statement stm = con.createStatement();
			
			stm.executeUpdate("INSERT INTO producto(nombre, precio, marca, modelo, existencias) values ('"+textField.getText()+"', '"+textField_1.getText()+"', '"+textField_2.getText()+"', '"+textField_3.getText()+"', '"+textField_4.getText()+"')");
			JOptionPane.showMessageDialog(null, "Este producto ha sido agregado correctamente");
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			con.close();
			
		}catch(ClassNotFoundException ex) {
		}catch (SQLException f) {
			f.printStackTrace();
		}
	}
	
}
