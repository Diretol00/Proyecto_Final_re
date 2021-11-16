package interfaz;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import metodos.AccionesTablas;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class VerUsuario extends JFrame implements AccionesTablas{

	private JPanel contentPane;
	private JTable table;

	
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnafadmin;
	private JButton btnfcliente;
	private JButton btneliminarf;
	private JButton btnactualizar;
	JLabel lblfiltro;
	private JPanel panel2;
	private JButton btnagregar;
	private JButton btnmodificar;
	private JButton btneliminar;
	public AgregarUsuario agregar = new AgregarUsuario();
	public ModificarUsuario modificar = new ModificarUsuario();
	public EliminarUsuario eliminar = new EliminarUsuario();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerUsuario frame = new VerUsuario();
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
	public VerUsuario() {
		setTitle("Usuarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 546, 462);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 10, 512, 415);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 199, 492, 159);
		panel1.add(scrollPane);
		
		table = new JTable(modelo);
		modelo.addColumn("ID_Usuario");
		modelo.addColumn("Nombre_Usuario");
		modelo.addColumn("Contraseña");
		modelo.addColumn("Tipo_Usuario");
		
		LlenarTabla();
		
		scrollPane.setViewportView(table);
		
		btnafadmin = new JButton("Filtrar por administrador");
		btnafadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarTablaAdmin();
				lblfiltro.setText("Usuario filtrado por administridor");
			}
		});
		btnafadmin.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnafadmin.setBounds(10, 10, 173, 32);
		panel1.add(btnafadmin);
		
		btnfcliente = new JButton("Filtrar por cliente");
		btnfcliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarTablaCliente();
				lblfiltro.setText("Usuario filtrado por cliente");
			}
		});
		btnfcliente.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnfcliente.setBounds(10, 64, 173, 32);
		panel1.add(btnfcliente);
		
		btneliminarf = new JButton("Eliminar filtro");
		btneliminarf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				lblfiltro.setText("");
			}
		});
		btneliminarf.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminarf.setBounds(10, 120, 173, 32);
		panel1.add(btneliminarf);
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblfiltro.setText("");
				Actualizar();
			}
		});
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBounds(160, 368, 173, 32);
		panel1.add(btnactualizar);
		
		lblfiltro = new JLabel("");
		lblfiltro.setBounds(10, 162, 276, 32);
		panel1.add(lblfiltro);
		
		panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.setBounds(247, 10, 255, 142);
		panel1.add(panel2);
		panel2.setLayout(null);
		
		btnagregar = new JButton("Agregar usuario");
		btnagregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar.setVisible(true);
				agregar.setLocationRelativeTo(null);
			}
		});
		btnagregar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnagregar.setBounds(42, 10, 173, 32);
		panel2.add(btnagregar);
		
		btnmodificar = new JButton("Modificar usuario");
		btnmodificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar.setVisible(true);
				modificar.setLocationRelativeTo(null);
			}
		});
		btnmodificar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnmodificar.setBounds(42, 52, 173, 32);
		panel2.add(btnmodificar);
		
		btneliminar = new JButton("Eliminar usuario");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar.setVisible(true);
				eliminar.setLocationRelativeTo(null);
			}
		});
		btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminar.setBounds(42, 94, 173, 32);
		panel2.add(btneliminar);
		
	}

	@Override
	public void LlenarTabla() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
			java.sql.Statement stm = cnn.createStatement();
		
			ResultSet rs =  stm.executeQuery("Select * from usuario");
		
		
		
			while(rs.next()==true) {
				String id = rs.getString("id_usuario");
				String nom = rs.getString("nombre_Usuario");
				String pass = rs.getString("contrasenha");
				String tipou = rs.getString("tipo_usuario");
	
				modelo.addRow(new Object[] {id,nom,pass,tipou});
		}
			
			cnn.close();
		}
			catch(ClassNotFoundException e1) {
				e1.printStackTrace();
			}catch(SQLException i) {
				i.printStackTrace();
			}
		
	}


	@Override
	public void ActualizarTablaAdmin() {
		// TODO Auto-generated method stub
		modelo.setRowCount(0);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
		java.sql.Statement stm = cnn.createStatement();
		
		ResultSet rs =  stm.executeQuery("Select * from usuario where tipo_usuario = 'admin'");
		
		
		
		while(rs.next()== true) {
			String id = rs.getString("id_usuario");
			String nom = rs.getString("nombre_Usuario");
			String pass = rs.getString("contrasenha");
			String tipou = rs.getString("tipo_usuario");
	
			modelo.addRow(new Object[] {id,nom,pass,tipou});
		}
			
			cnn.close();
		}
			catch(ClassNotFoundException e1) {
				e1.printStackTrace();
			}catch(SQLException i) {
				i.printStackTrace();
			}
	}

	@Override
	public void ActualizarTablaCliente() {
		// TODO Auto-generated method stub
		modelo.setRowCount(0);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
		java.sql.Statement stm = cnn.createStatement();
		
		ResultSet rs =  stm.executeQuery("Select * from usuario where tipo_usuario = 'cliente'");
		
		
		
		while(rs.next()==true) {
			String id = rs.getString("id_usuario");
			String nom = rs.getString("nombre_Usuario");
			String pass = rs.getString("contrasenha");
			String tipou = rs.getString("tipo_usuario");
	
			modelo.addRow(new Object[] {id,nom,pass,tipou});
		}
			
			cnn.close();
		}
			catch(ClassNotFoundException e1) {
				e1.printStackTrace();
			}catch(SQLException i) {
				i.printStackTrace();
			}
		
	}

	@Override
	public void Actualizar() {
		// TODO Auto-generated method stub
		modelo.setRowCount(0);
		LlenarTabla();
	}
}
