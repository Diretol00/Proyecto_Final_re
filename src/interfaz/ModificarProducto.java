package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	public JLabel lblNewLabel;
	private JButton btnBuscar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblEncontradoConExito;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarProducto frame = new ModificarProducto();
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
	public ModificarProducto() {
		setTitle("Modificar producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 426, 305);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnadd = new JButton("Modificar");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String usr = "root";
					String password = "";
					java.sql.Connection con = DriverManager.getConnection(url, usr, password);
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery("select * from producto where id_producto='"+textField.getText()+"'");
					
					if(rs.next()) {
						stm.executeUpdate("update producto set nombre='"+textField_1.getText()+"', precio='"+Integer.parseInt(textField_2.getText())+"', marca='"+textField_3.getText()+"', modelo='"+textField_4.getText()+"', existencias='"+Integer.parseInt(textField_5.getText())+"' where id_producto='"+textField.getText()+"'");
					}
					
					lblNewLabel.setVisible(true);
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					con.close();
					
				}catch(ClassNotFoundException ex) {
				}catch (SQLException f) {
					f.printStackTrace();
				}
			}
		});
		btnadd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnadd.setBounds(10, 198, 104, 26);
		contentPane.add(btnadd);
		
		lblNewLabel = new JLabel("Modificado con exito!");
		lblNewLabel.setVisible(false);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(124, 205, 173, 14);
		contentPane.add(lblNewLabel);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String usr = "root";
					String password = "";
					java.sql.Connection con = DriverManager.getConnection(url, usr, password);
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery("select * from producto where id_producto='"+textField.getText()+"'");
					
					if(rs.next()) {
						textField.setText(rs.getString("id_producto"));
						textField_1.setText(rs.getString("nombre"));
						textField_2.setText(rs.getString("precio"));
						textField_3.setText(rs.getString("marca"));
						textField_4.setText(rs.getString("modelo"));
						textField_5.setText(rs.getString("existencias"));
					}
					lblEncontradoConExito.setVisible(true);
					
					con.close();
					
				}catch(ClassNotFoundException ex) {
				}catch (SQLException f) {
					f.printStackTrace();
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBuscar.setBounds(10, 229, 104, 26);
		contentPane.add(btnBuscar);
		
		lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 27, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 52, 104, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 77, 104, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Marca");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 102, 104, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Modelo");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(10, 127, 104, 14);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Existencias");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(10, 152, 104, 14);
		contentPane.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(107, 25, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(107, 52, 173, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(107, 75, 173, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(107, 100, 173, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(107, 125, 173, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(107, 150, 173, 20);
		contentPane.add(textField_5);
		
		lblEncontradoConExito = new JLabel("Encontrado con exito!");
		lblEncontradoConExito.setVisible(false);
		lblEncontradoConExito.setForeground(Color.CYAN);
		lblEncontradoConExito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEncontradoConExito.setBounds(124, 236, 173, 14);
		contentPane.add(lblEncontradoConExito);
	}

}
