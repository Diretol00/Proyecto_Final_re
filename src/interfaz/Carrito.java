package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import metodos.Conectar;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Carrito extends JFrame {

	private JPanel contentPane;
	public Random rnd = new Random();
	public DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] {"Nombre", "Precio", "Marca", "Modelo"});
	public ConfirmarCompra ce = new ConfirmarCompra();
	public JTable table;
	public String l_nombre;
	public String l_precio;
	public String l_marca;
	public String l_modelo;
	public String l_existencias;
	public JLabel lblNewLabel_1;
	
	Conectar conec = new Conectar();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Carrito frame = new Carrito();
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
	public Carrito() {
		
		setTitle("Carrito");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JButton btnNewButton = new JButton("Confirmar compra");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ce.setVisible(true);
				//ce.table.setModel(modelo);
				ce.LlenarTabla();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/proyectofinal";
					String us = "root";
					String pw = "";
					
					java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
					
					java.sql.Statement stm = cnn.createStatement();
				
					ResultSet rs =  stm.executeQuery("Select * from carrito");

					while(rs.next()==true) {
						String nombre = rs.getString("nombre_producto");
		            	int precio = rs.getInt("precio_producto");
		            	String marca = rs.getString("marca_producto");
		            	String model = rs.getString("modelo_producto");
		            	
		            	ce.bMsg = "------ Detalles ------\nProducto: " + nombre + "\nPrecio: "
								+ ""+ precio + "\nMarca:" + marca + "\nModelo: "+ model + "\nID de Compra: " + rnd.nextInt(9999) + 
								"\n----------------------" + "\nGracias por confiar en Mobile Paradise";
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
		btnNewButton.setBounds(10, 264, 165, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Carrito");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 414, 30);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 414, 201);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(170, 21, 254, 14);
		contentPane.add(lblNewLabel_1);
	}
	
	
	void LlenarTabla(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
			java.sql.Statement stm = cnn.createStatement();
		
			ResultSet rs =  stm.executeQuery("Select * from carrito");

			while(rs.next()==true) {
				String nombre = rs.getString("nombre_producto");
            	int precio = rs.getInt("precio_producto");
            	String marca = rs.getString("marca_producto");
            	String model = rs.getString("modelo_producto");
            	
            	modelo.addRow(new Object[] {nombre,precio,marca,model});
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

