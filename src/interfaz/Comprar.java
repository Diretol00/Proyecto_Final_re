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
	JTable table;
	JPanel panel1;
	
	DefaultTableModel modelo = new DefaultTableModel() {
		 public boolean isCellEditable(int row, int column) {
		       return false;
		    }
	};
	private JLabel lblnom;
	private JLabel lblprecio;
	private JLabel lblmarca;
	private JLabel lblmodel;
	private JLabel lblexis;
	JTextField txtnom, txtprecio, txtmarca, txtmodel, txtexis;
	
	public int filaseleccionada;
	private JButton btncomprar;
	private JButton btnlimpiar;


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
		scrollPane.setBounds(20, 229, 483, 178);
		panel1.add(scrollPane);
		
		table = new JTable(modelo);
		
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		modelo.addColumn("Existencias");
		
		scrollPane.setViewportView(table);
		
		lblnom = new JLabel("Nombre");
		lblnom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnom.setBounds(20, 23, 67, 27);
		panel1.add(lblnom);
		
		lblprecio = new JLabel("Precio");
		lblprecio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblprecio.setBounds(20, 56, 67, 27);
		panel1.add(lblprecio);
		
		lblmarca = new JLabel("Marca");
		lblmarca.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblmarca.setBounds(20, 93, 67, 27);
		panel1.add(lblmarca);
		
		lblmodel = new JLabel("Modelo");
		lblmodel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblmodel.setBounds(20, 130, 67, 27);
		panel1.add(lblmodel);
		
		lblexis = new JLabel("Existencias");
		lblexis.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblexis.setBounds(20, 167, 84, 27);
		panel1.add(lblexis);
		
		txtnom = new JTextField();
		txtnom.setEditable(false);
		txtnom.setBounds(97, 24, 137, 27);
		panel1.add(txtnom);
		txtnom.setColumns(10);
		
		txtprecio = new JTextField();
		txtprecio.setEditable(false);
		txtprecio.setColumns(10);
		txtprecio.setBounds(97, 61, 137, 27);
		panel1.add(txtprecio);
		
		txtmarca = new JTextField();
		txtmarca.setEditable(false);
		txtmarca.setColumns(10);
		txtmarca.setBounds(97, 98, 137, 27);
		panel1.add(txtmarca);
		
		txtmodel = new JTextField();
		txtmodel.setEditable(false);
		txtmodel.setColumns(10);
		txtmodel.setBounds(97, 135, 137, 27);
		panel1.add(txtmodel);
		
		txtexis = new JTextField();
		txtexis.setEditable(false);
		txtexis.setColumns(10);
		txtexis.setBounds(97, 172, 137, 27);
		panel1.add(txtexis);
		
		JButton btnmostrar = new JButton("Mostrar");
		btnmostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionarDatos();
			}
		});
		btnmostrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnmostrar.setBounds(189, 418, 137, 35);
		panel1.add(btnmostrar);
		
		btncomprar = new JButton("Comprar");
		btncomprar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btncomprar.setBounds(42, 418, 137, 35);
		panel1.add(btncomprar);
		
		btnlimpiar = new JButton("Limpiar");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			txtnom.setText("");
			txtprecio.setText("");
			txtmarca.setText("");
			txtmodel.setText("");
			txtexis.setText("");
			}
		});
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnlimpiar.setBounds(334, 417, 137, 35);
		panel1.add(btnlimpiar);
		
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
	
	
	void SeleccionarDatos() {
		try{
            //Guardamos en un entero la fila seleccionada.
            filaseleccionada = table.getSelectedRow();
            if (filaseleccionada == -1){
                JOptionPane.showMessageDialog(panel1, "No se ha seleccionado ningun producto");
            } else {

                //String ayuda = tabla.getValueAt(filaseleccionada, num_columna).toString()); 
                String nom = (String)table.getValueAt(filaseleccionada, 0);
                String precio = (String)table.getValueAt(filaseleccionada, 1);
                String marca = (String)table.getValueAt(filaseleccionada, 2);
                String modelo = (String)table.getValueAt(filaseleccionada, 3);
                String exis = (String)table.getValueAt(filaseleccionada, 4);
                
                txtnom.setText(nom);
                txtprecio.setText(precio);
                txtmarca.setText(marca);
                txtmodel.setText(modelo);
                txtexis.setText(exis);
            }
        }catch (Exception ex){
        }
	}
}
