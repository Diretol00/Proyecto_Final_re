package interfaz;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Factura extends JFrame {

	private JPanel contentPane;
	public DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] {"ID", "Nombre", "Precio", "Marca", "Modelo", "Vendedor","Comprador"});
	private JTable table;
	JTextField txtid, txtnom, txtprecio, txtmarca, txtmodelo, txtvendedor, txtcomprador;
	JLabel lblfiltro;
	
	public String b_id;
	public String b_nombre;
	public String b_precio;
	public String b_marca;
	public String b_modelo;
	public String b_vendedor;
	public String b_comprador;
	public int filaseleccionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Factura frame = new Factura();
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
	public Factura() {
		Actualizar();
		
		setTitle("Facturas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 487, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 303, 467, 142);
		panel.add(scrollPane);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 10, 28, 39);
		panel.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(10, 61, 58, 39);
		panel.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecio.setBounds(10, 110, 48, 39);
		panel.add(lblPrecio);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMarca.setBounds(10, 159, 48, 39);
		panel.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModelo.setBounds(10, 208, 48, 39);
		panel.add(lblModelo);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVendedor.setBounds(236, 10, 69, 39);
		panel.add(lblVendedor);
		
		JLabel lblComprador = new JLabel("Comprador");
		lblComprador.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblComprador.setBounds(236, 61, 82, 39);
		panel.add(lblComprador);
		
		txtid = new JTextField();
		txtid.setBounds(78, 17, 137, 27);
		panel.add(txtid);
		txtid.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(78, 68, 137, 27);
		panel.add(txtnom);
		
		txtprecio = new JTextField();
		txtprecio.setColumns(10);
		txtprecio.setBounds(78, 121, 137, 27);
		panel.add(txtprecio);
		
		txtmarca = new JTextField();
		txtmarca.setColumns(10);
		txtmarca.setBounds(78, 170, 137, 27);
		panel.add(txtmarca);
		
		txtmodelo = new JTextField();
		txtmodelo.setColumns(10);
		txtmodelo.setBounds(78, 219, 137, 27);
		panel.add(txtmodelo);
		
		txtvendedor = new JTextField();
		txtvendedor.setColumns(10);
		txtvendedor.setBounds(315, 17, 162, 27);
		panel.add(txtvendedor);
		
		txtcomprador = new JTextField();
		txtcomprador.setColumns(10);
		txtcomprador.setBounds(315, 72, 162, 27);
		panel.add(txtcomprador);
		
		JButton filtrovendedor = new JButton("Filtrar por vendedor\r\n");
		filtrovendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FiltrarVendedor();
			}
		});
		filtrovendedor.setFont(new Font("Tahoma", Font.BOLD, 10));
		filtrovendedor.setBounds(263, 114, 173, 32);
		panel.add(filtrovendedor);
		
		JButton btneliminarf = new JButton("Eliminar filtro");
		btneliminarf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				lblfiltro.setText("");
			}
		});
		btneliminarf.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminarf.setBounds(263, 156, 173, 32);
		panel.add(btneliminarf);
		
		JButton btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				lblfiltro.setText("");
			}
		});
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBounds(156, 455, 173, 32);
		panel.add(btnactualizar);
		
		lblfiltro = new JLabel("");
		lblfiltro.setBounds(10, 271, 252, 22);
		panel.add(lblfiltro);
		
		JButton btnllenar = new JButton("Obtener datos de factura");
		btnllenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LlenarCampos();
			}
		});
		btnllenar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnllenar.setBounds(263, 197, 173, 32);
		panel.add(btnllenar);
		
		JButton btneliminarc = new JButton("Vaciar campos");
		btneliminarc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtid.setText("");
				txtnom.setText("");
				txtprecio.setText("");
				txtmarca.setText("");
				txtmodelo.setText("");
				txtvendedor.setText("");
				txtcomprador.setText("");
			}
		});
		btneliminarc.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminarc.setBounds(263, 239, 173, 32);
		panel.add(btneliminarc);
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
		
			ResultSet rs =  stm.executeQuery("select * from factura");
		
			while(rs.next()==true) {
				String id = rs.getString("id_factura");
				String nombre = rs.getString("nombre_producto");
				String precio = rs.getString("precio");
				String marca = rs.getString("marca_producto");
				String modeloT = rs.getString("modelo_producto");
				String vendedor = rs.getString("vendedor");
				String comprador = rs.getString("comprador");
				
				
				modelo.addRow(new Object[] {id, nombre, "US$"+precio, marca, modeloT, vendedor,comprador});
			}
				
				cnn.close();
			}
				catch(ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch(SQLException i) {
					i.printStackTrace();
			}
	}
	
	
	
	
	public void FiltrarVendedor() {
		
		modelo.setRowCount(0);
		String marcaFiltro = JOptionPane.showInputDialog("Inserte nombre del vendedor");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
			java.sql.Statement stm = cnn.createStatement();
		
			ResultSet rs =  stm.executeQuery("select * from factura where vendedor = '"+marcaFiltro+"'");
		
			while(rs.next()==true) {
				String id = rs.getString("id_factura");
				String nombre = rs.getString("nombre_producto");
				String precio = rs.getString("precio");
				String marca = rs.getString("marca_producto");
				String modeloT = rs.getString("modelo_producto");
				String vendedor = rs.getString("vendedor");
				String comprador = rs.getString("comprador");
				
				
				modelo.addRow(new Object[] {id, nombre, "US$"+precio, marca, modeloT, vendedor,comprador});
			}
				lblfiltro.setText("Filtrado por " + marcaFiltro);
				cnn.close();
			}
				catch(ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch(SQLException i) {
					i.printStackTrace();
			}
	}
	
	
	
	public void LlenarCampos() {
		try {
			filaseleccionada = table.getSelectedRow();
			
			if(filaseleccionada == -1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna factura");
			}else {
				b_id = (String) table.getValueAt(filaseleccionada, 0);
				b_nombre = (String) table.getValueAt(filaseleccionada, 1);
				b_precio = (String) table.getValueAt(filaseleccionada, 2);
				b_marca = (String) table.getValueAt(filaseleccionada, 3);
				b_modelo = (String) table.getValueAt(filaseleccionada, 4);
				b_vendedor = (String) table.getValueAt(filaseleccionada, 5);
				b_comprador = (String) table.getValueAt(filaseleccionada, 6);
				
				txtid.setText(b_id);
				txtnom.setText(b_nombre);
				txtprecio.setText(b_precio);
				txtmarca.setText(b_marca);
				txtmodelo.setText(b_modelo);
				txtvendedor.setText(b_vendedor);
				txtcomprador.setText(b_comprador);
				


				
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
