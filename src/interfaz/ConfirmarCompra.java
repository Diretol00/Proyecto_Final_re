package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import metodos.EnviarCorreo;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ConfirmarCompra extends JFrame implements EnviarCorreo {

	private JPanel contentPane;
	public JTextField txtcorreo,txtarjeta,txtexp,txtcvv;
	public String s_nombre, s_precio, s_marca, s_modelo;
	public JLabel lblusern;
	private JScrollPane scrollPane;
	public JTable table;
	public String bMsg;
	public static int cont1 = 0;
	public String b_nombre,b_precio,b_modelo,b_marca;
	
	public DefaultTableModel modelo = new DefaultTableModel() {
		 public boolean isCellEditable(int row, int column) {
		       return false;
		    }
	};
	
	Comprar comp = new Comprar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmarCompra frame = new ConfirmarCompra();
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
	public ConfirmarCompra() {
		setTitle("Confirmar Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informaci\u00F3n personal para compra", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel2.setBounds(32, 226, 352, 169);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblcorreo = new JLabel("Correo electr\u00F3nico");
		lblcorreo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblcorreo.setBounds(10, 21, 114, 26);
		panel2.add(lblcorreo);
		
		JLabel lbltarjeta = new JLabel("N\u00FAmero de tarjeta");
		lbltarjeta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbltarjeta.setBounds(10, 57, 114, 26);
		panel2.add(lbltarjeta);
		
		JLabel lblexpi = new JLabel("Expira");
		lblexpi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblexpi.setBounds(10, 93, 65, 26);
		panel2.add(lblexpi);
		
		JLabel lblcvv = new JLabel("CVV");
		lblcvv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblcvv.setBounds(10, 129, 43, 26);
		panel2.add(lblcvv);
		
		txtcorreo = new JTextField();
		txtcorreo.setBounds(134, 22, 193, 26);
		panel2.add(txtcorreo);
		txtcorreo.setColumns(10);
		
		txtarjeta = new JTextField();
		txtarjeta.setColumns(10);
		txtarjeta.setBounds(134, 61, 193, 26);
		panel2.add(txtarjeta);
		
		txtexp = new JTextField();
		txtexp.setColumns(10);
		txtexp.setBounds(197, 97, 130, 26);
		panel2.add(txtexp);
		
		txtcvv = new JTextField();
		txtcvv.setColumns(10);
		txtcvv.setBounds(197, 133, 130, 26);
		panel2.add(txtcvv);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 10, 352, 205);
		contentPane.add(scrollPane);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		
		
		
		JButton btncomprar = new JButton("Comprar");
		btncomprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cont1 = table.getRowCount();
				System.out.println(cont1);
				LlenarFactura();
				enviar(txtcorreo.getText(), bMsg);
				txtcorreo.setText("");
				txtarjeta.setText("");
				txtexp.setText("");
				txtcvv.setText("");

				ConfirmarCompra.this.dispose();
				
			}
		});
		btncomprar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btncomprar.setBounds(32, 422, 164, 36);
		contentPane.add(btncomprar);
		
		JButton btncancel = new JButton("Cancelar");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btncancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btncancel.setBounds(206, 422, 164, 36);
		contentPane.add(btncancel);
		
		lblusern = new JLabel("");
		lblusern.setVisible(false);
		lblusern.setBounds(311, 10, 73, 23);
		contentPane.add(lblusern);
		
		
		
		
		
	}
	
void LlenarTabla(){
		modelo.setRowCount(0);
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


public void BorrarCarrito() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost/proyectofinal";
		String usr = "root";
		String password = "";
		java.sql.Connection con = DriverManager.getConnection(url, usr, password);
		Statement stm = con.createStatement();
		
		stm.executeUpdate("Delete from carrito");
		
		con.close();
		
	}catch(ClassNotFoundException ex) {
	}catch (SQLException f) {
		f.printStackTrace();
	}
 }



public void LlenarFactura() {
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost/proyectofinal";
		String usr = "root";
		String password = "";
		java.sql.Connection con = DriverManager.getConnection(url, usr, password);
		java.sql.Statement stm = con.createStatement();
		
		
        	if(cont1 > 0) {
        	for(int i = 0; i < cont1; i++) {
        	stm.executeUpdate("INSERT INTO factura(nombre_producto,precio,marca_producto,modelo_producto,vendedor,comprador)"
					+ " values('"+table.getValueAt(i, 0)+"', '"+table.getValueAt(i, 1)+"', '"+table.getValueAt(i, 2)+"',"
							+ " '"+table.getValueAt(i, 3)+"', '"+lblusern.getText()+"', '"+txtcorreo.getText()+"')");
        	
        	}
	}

		con.close();
		
	}catch(ClassNotFoundException ex) {
	}catch (SQLException f) {
		f.printStackTrace();
	}

	
}
}
