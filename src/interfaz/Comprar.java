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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Comprar extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	public JPanel panel1;
	public DefaultTableModel modelo = new DefaultTableModel() {
		 public boolean isCellEditable(int row, int column) {
		       return false;
		    }
	};

	public String b_nombre;
	public String b_precio;
	public String b_marca;
	public String b_modelo;
	public String b_existencias;
	public static int filaseleccionada;
	private JButton btncomprar;
	private JButton btnlimpiar;
	public String nombre, precio, marca, modelox,userName;
	public JLabel lblusername;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comprar frame = new Comprar();
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
	public Comprar() {
		setTitle("Comprar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 532);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(10, 10, 527, 475);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 23, 483, 384);
		panel1.add(scrollPane);
		
		table = new JTable(modelo);
		
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		modelo.addColumn("Existencias");
		
		scrollPane.setViewportView(table);
		
		btncomprar = new JButton("Agregar al carrito");
		btncomprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					filaseleccionada = table.getSelectedRow();
					
					if(filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto");
					}else {
						b_nombre = (String) table.getValueAt(filaseleccionada, 0);
						b_precio = (String) table.getValueAt(filaseleccionada, 1);
						b_marca = (String) table.getValueAt(filaseleccionada, 2);
						b_modelo = (String) table.getValueAt(filaseleccionada, 3);
						b_existencias = (String) table.getValueAt(filaseleccionada, 4);
						
						System.out.println(b_nombre);
						JOptionPane.showMessageDialog(null, "Agregado al carrito!");
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btncomprar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btncomprar.setBounds(42, 418, 216, 35);
		panel1.add(btncomprar);
		
		btnlimpiar = new JButton("Volver a comprar");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comprar.this.dispose();
			}
		});
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnlimpiar.setBounds(268, 417, 203, 35);
		panel1.add(btnlimpiar);
		
		lblusername = new JLabel("");
		lblusername.setBounds(404, 23, 113, 18);
		panel1.add(lblusername);
		
		LlenarTabla();
		

	}
	
	void LlenarTabla() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/proyectofinal";
			String us = "root";
			String pw = "";
			
			java.sql.Connection cnn = DriverManager.getConnection(url,us,pw);
			
			java.sql.Statement stm = cnn.createStatement();
		
			ResultSet rs =  stm.executeQuery("Select * from producto");

			while(rs.next()==true) {
				String nom = rs.getString("nombre");
				String precio = rs.getString("precio");
				String marca = rs.getString("marca");
				String model = rs.getString("modelo");
				String exis = rs.getString("existencias");
	
				modelo.addRow(new Object[] {nom,"US$"+precio,marca,model,exis});
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
