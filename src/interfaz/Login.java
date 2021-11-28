package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtuser;
	private JLabel lbluser;
	private JLabel lblpass;
	private JButton btningresar;
	private JButton btncancelar;
	public Admin admin = new Admin();
	public Home home = new Home();
	public AgregarVendedor agregarCliente = new AgregarVendedor();
	public AgregarAdmin agregarAdministrador = new AgregarAdmin();
	public String userName;
	private JPasswordField txtpass;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Iniciar Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 476);
		setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Registrar");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Vendedor");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCliente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Administrador");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarAdministrador.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(135, 206, 235));
		panel1.setBounds(0, 0, 475, 439);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lbllogin = new JLabel("Login");
		lbllogin.setForeground(new Color(47, 79, 79));
		lbllogin.setFont(new Font("Tahoma", Font.BOLD, 44));
		lbllogin.setBounds(167, 10, 129, 62);
		panel1.add(lbllogin);
		
		txtuser = new JTextField();
		txtuser.setBounds(137, 132, 202, 32);
		panel1.add(txtuser);
		txtuser.setColumns(10);
		
		lbluser = new JLabel("Usuario");
		lbluser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbluser.setBounds(200, 95, 74, 32);
		panel1.add(lbluser);
		
		lblpass = new JLabel("Contrase\u00F1a");
		lblpass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblpass.setBounds(188, 191, 103, 32);
		panel1.add(lblpass);
		
		btningresar = new JButton("Ingresar");
		btningresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar();
			}
		});
		btningresar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btningresar.setBounds(84, 322, 114, 46);
		panel1.add(btningresar);
		
		btncancelar = new JButton("Cancelar");
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btncancelar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btncancelar.setBounds(269, 322, 114, 46);
		panel1.add(btncancelar);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(137, 252, 202, 32);
		panel1.add(txtpass);
	}
	
	
	
	public void Ingresar() {
		boolean validAdmins = false;
		boolean validUsers = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			java.sql.Statement stm = cnn.createStatement();
			java.sql.Statement stm_users = cnn.createStatement();
			ResultSet rs_admins =  stm.executeQuery("select * from usuario where nombre_usuario='"+txtuser.getText()+"' and contrasenha='"+txtpass.getText()+"' and tipo_usuario='admin'");
			ResultSet rs_users =  stm_users.executeQuery("select * from usuario where nombre_usuario='"+txtuser.getText()+"' and contrasenha='"+txtpass.getText()+"' and tipo_usuario='vendedor'");
			
			if(rs_admins.next()) {
				validAdmins = true;
				if(validAdmins == true) {
					admin.setVisible(true);
					admin.setLocationRelativeTo(null);
				}
			}else {
				if(rs_users.next()) {
					validUsers = true;
					if(validUsers == true) {
						home.setVisible(true);
						home.setLocationRelativeTo(null);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Usuario no existe!");
				}
			}
			userName = txtuser.getText();
			home.lblNewLabel_2_1.setText(userName);
			admin.lblNewLabel_2_1.setText(userName);
			txtuser.setText("");
			txtpass.setText("");
			cnn.close();
		}
			catch(ClassNotFoundException x) {
				x.printStackTrace();
			}catch(SQLException f) {
				f.printStackTrace();
			}
	}
}