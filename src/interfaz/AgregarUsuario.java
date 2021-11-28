package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AgregarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtnom;
	private JPasswordField txtpass;
	private JPasswordField txtrepass;
	JPanel panel1;
	JComboBox cb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarUsuario frame = new AgregarUsuario();
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
	public AgregarUsuario() {
		setTitle("Agregar Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 290);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 492, 253);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblnom = new JLabel("Nombre");
		lblnom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblnom.setBounds(10, 10, 83, 26);
		panel1.add(lblnom);
		
		JLabel lblpass = new JLabel("Contrase\u00F1a");
		lblpass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblpass.setBounds(10, 58, 83, 26);
		panel1.add(lblpass);
		
		JLabel lblrepass = new JLabel("Repetir contrase\u00F1a");
		lblrepass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblrepass.setBounds(10, 106, 133, 26);
		panel1.add(lblrepass);
		
		cb = new JComboBox();
		cb.setModel(new DefaultComboBoxModel(new String[] {"admin", "vendedor"}));
		cb.setBounds(10, 153, 133, 26);
		panel1.add(cb);
		
		txtnom = new JTextField();
		txtnom.setBounds(155, 13, 173, 24);
		panel1.add(txtnom);
		txtnom.setColumns(10);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(155, 61, 173, 24);
		panel1.add(txtpass);
		
		txtrepass = new JPasswordField();
		txtrepass.setBounds(155, 109, 173, 24);
		panel1.add(txtrepass);
		
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
		chbox.setBounds(346, 59, 140, 26);
		panel1.add(chbox);
		
		JButton btnadd = new JButton("Agregar");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarUsuario();
			}
		});
		btnadd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnadd.setBounds(168, 199, 133, 31);
		panel1.add(btnadd);
	}
	
	
	
	
	public void AgregarUsuario() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String usr = "root";
			String password = "";
			java.sql.Connection con = DriverManager.getConnection(url, usr, password);
			Statement stm = con.createStatement();
			
			if(txtpass.getText().trim().equals(txtrepass.getText().trim())) {
				stm.executeUpdate("INSERT INTO usuario(nombre_usuario, contrasenha, tipo_usuario)"
						+ " values('"+txtnom.getText()+"', '"+txtpass.getText()+"', "
								+ "'"+cb.getSelectedItem()+"')");
				JOptionPane.showMessageDialog(panel1, "Este usuario ha sido agregado correctamente");
				
				txtnom.setText(null);
				txtpass.setText(null);
				txtrepass.setText(null);
				
			}else {
				JOptionPane.showMessageDialog(panel1, "Las contraseñas no coinciden");
			}
			
			con.close();
			
		}catch(ClassNotFoundException ex) {
		}catch (SQLException f) {
			f.printStackTrace();
		}
	}
}
