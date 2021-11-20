package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Inventario extends JFrame {

	private JPanel contentPane;
	public DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Nombre", "Precio", "Marca", "Modelo", "Existencias"});
	public JLabel lblfiltro;
	public AgregarProducto agregarProducto = new AgregarProducto();
	public ModificarProducto modificar = new ModificarProducto();
	public EliminarProducto eliminar = new EliminarProducto();
	private JTable table;
	JButton filtroMarca;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventario frame = new Inventario();
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
	public Inventario() {
		
		Actualizar();
		
		
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 546, 496);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 410, 39);
		contentPane.add(lblNewLabel);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(10, 46, 512, 415);
		contentPane.add(panel1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 199, 492, 159);
		panel1.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(modelo);
		scrollPane_1.setViewportView(table);
		
		filtroMarca = new JButton("Filtrar por marca\r\n");
		filtroMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FiltrarMarca();
				
			}
		});
		filtroMarca.setFont(new Font("Tahoma", Font.BOLD, 10));
		filtroMarca.setBounds(10, 10, 173, 32);
		panel1.add(filtroMarca);
		
		JButton filtroPrecio = new JButton("Filtrar por precio\r\n");
		filtroPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FiltrarPrecio();
			}
		});
		filtroPrecio.setFont(new Font("Tahoma", Font.BOLD, 10));
		filtroPrecio.setBounds(10, 64, 173, 32);
		panel1.add(filtroPrecio);
		
		JButton btneliminarf = new JButton("Eliminar filtro");
		btneliminarf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				lblfiltro.setText("");
			}
		});
		btneliminarf.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminarf.setBounds(10, 120, 173, 32);
		panel1.add(btneliminarf);
		
		JButton btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				lblfiltro.setText("");
			}
		});
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBounds(160, 368, 173, 32);
		panel1.add(btnactualizar);
		
		lblfiltro = new JLabel("");
		lblfiltro.setBounds(10, 162, 276, 32);
		panel1.add(lblfiltro);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.setBounds(247, 10, 255, 142);
		panel1.add(panel2);
		
		JButton btnagregar = new JButton("Agregar producto");
		btnagregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarProducto.setVisible(true);
			}
		});
		btnagregar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnagregar.setBounds(42, 10, 173, 32);
		panel2.add(btnagregar);
		
		JButton btnmodificar = new JButton("Modificar producto");
		btnmodificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar.setVisible(true);
			}
		});
		btnmodificar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnmodificar.setBounds(42, 52, 173, 32);
		panel2.add(btnmodificar);
		
		JButton btneliminar = new JButton("Eliminar producto");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar.setVisible(true);
			}
		});
		btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminar.setBounds(42, 94, 173, 32);
		panel2.add(btneliminar);
	}
	
	
	public void FiltrarMarca() {
		
		modelo.setRowCount(0);
		String marcaFiltro = JOptionPane.showInputDialog("Inserte marca");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
			java.sql.Statement stm = cnn.createStatement();
		
			ResultSet rs =  stm.executeQuery("select * from producto where marca='"+marcaFiltro+"'");
		
			while(rs.next()==true) {
				String id = rs.getString("id_producto");
				String nombre = rs.getString("nombre");
				String precio = rs.getString("precio");
				String marca = rs.getString("marca");
				String modeloT = rs.getString("modelo");
				String existencias = rs.getString("existencias");
				
				modelo.addRow(new Object[] {id, nombre, "US$"+precio, marca, modeloT, existencias});
			}
				lblfiltro.setText("Filtrando por marca: " + marcaFiltro);
				lblfiltro.setVisible(true);
				cnn.close();
			}
				catch(ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch(SQLException i) {
					i.printStackTrace();
			}
	}
	
	
	public void FiltrarPrecio() {
		modelo.setRowCount(0);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
			java.sql.Statement stm = cnn.createStatement();
		
			ResultSet rs =  stm.executeQuery("select * from producto order by precio ASC");
		
			while(rs.next()==true) {
				String id = rs.getString("id_producto");
				String nombre = rs.getString("nombre");
				String precio = rs.getString("precio");
				String marca = rs.getString("marca");
				String modeloT = rs.getString("modelo");
				String existencias = rs.getString("existencias");
				
				modelo.addRow(new Object[] {id, nombre, "US$"+precio, marca, modeloT, existencias});
			}
				lblfiltro.setText("Filtrando por precio: Mas barato a mas caro");
				lblfiltro.setVisible(true);
				cnn.close();
			}
				catch(ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch(SQLException i) {
					i.printStackTrace();
			}
	}
	
	
	
	public void Actualizar() {
		modelo.setRowCount(0);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
			java.sql.Statement stm = cnn.createStatement();
		
			ResultSet rs =  stm.executeQuery("select * from producto");
		
			while(rs.next()==true) {
				String id = rs.getString("id_producto");
				String nombre = rs.getString("nombre");
				String precio = rs.getString("precio");
				String marca = rs.getString("marca");
				String modeloT = rs.getString("modelo");
				String existencias = rs.getString("existencias");
				
				
				modelo.addRow(new Object[] {id, nombre, "US$"+precio, marca, modeloT, existencias});
			}
				
				cnn.close();
			}
				catch(ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch(SQLException i) {
					i.printStackTrace();
			}
	}
}
