package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EliminarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	public JLabel lblconf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarProducto frame = new EliminarProducto();
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
	public EliminarProducto() {
		setTitle("Eliminar producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 208);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.setBounds(21, 10, 215, 52);
		contentPane.add(panel2);
		
		JLabel lblid = new JLabel("ID");
		lblid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblid.setBounds(10, 10, 33, 32);
		panel2.add(lblid);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		txtid.setBounds(32, 16, 173, 24);
		panel2.add(txtid);
		
		JButton btndelete = new JButton("Eliminar");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String us = "root";
					String pw = "";
					
					String query = "Delete from producto where id_producto = '"+txtid.getText()+"'";
					
					java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
					
				java.sql.Statement stm = cnn.createStatement();
				java.sql.Statement stm2 = cnn.createStatement();
				
				ResultSet rs =  stm.executeQuery("Select * from producto"
						+ " where id_producto = '"+txtid.getText()+"'");
				
				if(rs.next()==true) {
					int opt = JOptionPane.showConfirmDialog(null,"¿Estás seguro que quieres eliminar"
							+ " este producto?","Selecciona si o no", JOptionPane.YES_NO_OPTION);
					if(opt == 0) {
						stm2.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "El producto fue eliminado"
								+ " exitosamente");
						
						txtid.setText(null);
					}
				}else {
					lblconf.setText("Este producto no existe");
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
		contentPane.add(btndelete);
		
		lblconf = new JLabel("");
		lblconf.setBounds(21, 72, 215, 24);
		contentPane.add(lblconf);
	}

}
