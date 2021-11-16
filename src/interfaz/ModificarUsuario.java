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
import javax.swing.DefaultComboBoxModel;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ModificarUsuario extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtrepass;
	private JPasswordField txtpass;
	private JTextField txtnom;
	private JTextField txtid;
	public JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario frame = new ModificarUsuario();
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
	public ModificarUsuario() {
		setType(Type.UTILITY);
		setTitle("Modificar Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 290);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 492, 253);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblid = new JLabel("ID");
		lblid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblid.setBounds(10, 10, 44, 26);
		panel1.add(lblid);
		
		JLabel lblnom = new JLabel("Nombre");
		lblnom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblnom.setBounds(10, 46, 83, 26);
		panel1.add(lblnom);
		
		JLabel lblpass = new JLabel("Contrase\u00F1a");
		lblpass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblpass.setBounds(10, 82, 83, 26);
		panel1.add(lblpass);
		
		JLabel lblrepass = new JLabel("Repetir contrase\u00F1a");
		lblrepass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblrepass.setBounds(10, 118, 133, 26);
		panel1.add(lblrepass);
		
		JComboBox cb = new JComboBox();
		cb.setModel(new DefaultComboBoxModel(new String[] {"admin", "cliente"}));
		cb.setBounds(10, 154, 133, 26);
		panel1.add(cb);
		
		txtrepass = new JPasswordField();
		txtrepass.setBounds(153, 123, 173, 24);
		panel1.add(txtrepass);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(153, 82, 173, 24);
		panel1.add(txtpass);
		
		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(153, 48, 173, 24);
		panel1.add(txtnom);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		txtid.setBounds(153, 15, 173, 24);
		panel1.add(txtid);
		
		JCheckBox chbox = new JCheckBox("Mostrar contrase\u00F1a");
		chbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chbox.isSelected()) {
					txtpass.setEchoChar((char)0);
					txtrepass.setEchoChar((char)0);
				}else {
					txtpass.setEchoChar('•');
					txtrepass.setEchoChar('•');
				}
			}
		});
		chbox.setBounds(346, 82, 140, 26);
		panel1.add(chbox);
		
		JButton btnalter = new JButton("Modificar");
		btnalter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String us = "root";
					String pw = "";
					
					java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
					
				
				java.sql.Statement stm = cnn.createStatement();
				
				ResultSet rs =  stm.executeQuery("Select * from usuario"
						+ " where id_usuario = '"+txtid.getText()+"'");
				
				if(rs.next()==true) {
					if(txtpass.getText().trim().equals(txtrepass.getText().trim())) {
					stm.executeUpdate("Update usuario set nombre_usuario = '"+txtnom.getText()+"',"
						+ "contrasenha = '"+txtpass.getText()+"',tipo_usuario = '"+cb.getSelectedItem()+"'"
								+ "where id_usuario = '"+txtid.getText()+"'");
				
				JOptionPane.showMessageDialog(panel1, "Se ha modificado el usuario correctamente");	
				
				txtid.setText(null);
				txtnom.setText(null);
				txtpass.setText(null);
				txtrepass.setText(null);
					}else {
						JOptionPane.showMessageDialog(panel1, "Las contraseñas no coinciden");
					}
				}else {
					JOptionPane.showMessageDialog(panel1, "Lo sentimos, no se ha podido modificar debido"
							+ " a que este usuario parece no existir");	
				}
					lblNewLabel.setVisible(false);
					cnn.close();
				}
					catch(ClassNotFoundException e1) {
						e1.printStackTrace();
					}catch(SQLException i) {
						i.printStackTrace();
					}
			}
		});
		btnalter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnalter.setBounds(10, 199, 133, 31);
		panel1.add(btnalter);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String usr = "root";
					String password = "";
					java.sql.Connection con = DriverManager.getConnection(url, usr, password);
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery("select * from usuario where id_usuario='"+txtid.getText()+"'");
					
					if(rs.next()) {
						txtid.setText(rs.getString("id_usuario"));
						txtnom.setText(rs.getString("nombre_usuario"));
						txtpass.setText(rs.getString("contrasenha"));
						txtrepass.setText(rs.getString("contrasenha"));
						cb.setSelectedItem(rs.getString("tipo_usuario"));
					}
					lblNewLabel.setVisible(true);
					con.close();
					
				}catch(ClassNotFoundException ex) {
				}catch (SQLException f) {
					f.printStackTrace();
				}
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBuscar.setBounds(153, 199, 133, 31);
		panel1.add(btnBuscar);
		
		lblNewLabel = new JLabel("Se ha encontrado con exito!");
		lblNewLabel.setVisible(false);
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(163, 160, 163, 14);
		panel1.add(lblNewLabel);
	}

}
