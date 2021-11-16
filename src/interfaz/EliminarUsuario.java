package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EliminarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	JLabel lblconf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarUsuario frame = new EliminarUsuario();
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
	public EliminarUsuario() {
		setTitle("Eliminar Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 208);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 326, 171);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.setBounds(21, 10, 215, 52);
		panel1.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(10, 10, 33, 32);
		panel2.add(lblid);
		lblid.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtid = new JTextField();
		txtid.setBounds(32, 16, 173, 24);
		panel2.add(txtid);
		txtid.setColumns(10);
		
		JButton btndelete = new JButton("Eliminar");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String us = "root";
					String pw = "";
					
					String query = "Delete from usuario where id_usuario = '"+txtid.getText()+"'";
					
					java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
					
				java.sql.Statement stm = cnn.createStatement();
				java.sql.Statement stm2 = cnn.createStatement();
				
				ResultSet rs =  stm.executeQuery("Select * from usuario"
						+ " where id_usuario = '"+txtid.getText()+"'");
				
				if(rs.next()==true) {
					int opt = JOptionPane.showConfirmDialog(panel1,"¿Estás seguro que quieres eliminar"
							+ " este usuario?","Selecciona si o no", JOptionPane.YES_NO_OPTION);
					if(opt == 0) {
						stm2.executeUpdate(query);
						JOptionPane.showMessageDialog(panel1, "El usuario fue eliminado"
								+ " exitosamente");
						
						txtid.setText(null);
					}
				}else {
					lblconf.setText("Este usuario no existe");
				}
					
					cnn.close();
				}
					catch(ClassNotFoundException e1) {
						e1.printStackTrace();
					}catch(SQLException i) {
						i.printStackTrace();
					}
			}
		});
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btndelete.setBounds(90, 119, 133, 31);
		panel1.add(btndelete);
		
		lblconf = new JLabel("");
		lblconf.setBounds(21, 72, 215, 24);
		panel1.add(lblconf);
	}

}
