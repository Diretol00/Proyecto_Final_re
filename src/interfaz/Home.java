package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	public Image background = new ImageIcon(this.getClass().getResource("/bg.png")).getImage();
	public JLabel lblNewLabel_2_1;
	public Info info = new Info();
	public Carrito carrito = new Carrito();
	public String userName;
	public Comprar comprar = new Comprar();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 485);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btncarrito = new JButton("Carrito");
		btncarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carrito.setVisible(true);
				carrito.setLocationRelativeTo(null);
				carrito.modelo.setRowCount(0);
				carrito.LlenarTabla();

				/*carrito.modelo.addRow(new Object[] {comprar.b_nombre, comprar.b_precio, comprar.b_marca, comprar.b_modelo});
				
				carrito.l_nombre = comprar.b_nombre;
				carrito.l_precio = comprar.b_precio;
				carrito.l_marca = comprar.b_marca;
				carrito.l_modelo = comprar.b_modelo;*/
			}
		});
		btncarrito.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncarrito.setBounds(364, 34, 85, 31);
		contentPane.add(btncarrito);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 24, 459, 426);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(background));
		lblNewLabel.setBounds(0, -10, 459, 426);
		panel.add(lblNewLabel);
		
		JButton btncomprar = new JButton("Comprar");
		btncomprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprar.setVisible(true);
				comprar.setLocationRelativeTo(null);
				
				
				userName = lblNewLabel_2_1.getText();
				comprar.lblusername.setText(userName);
			}
		});
		btncomprar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncomprar.setBounds(180, 317, 98, 30);
		panel.add(btncomprar);
		
		JButton btncsesion = new JButton("Cerrar Sesi\u00F3n");
		btncsesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opt = JOptionPane.showConfirmDialog(contentPane,"Al cerrar sesión saldrás"
						+ " de la aplicación,  ¿Estás seguro que quieres salir de "
						+ "la aplicación?","Salir de la app", JOptionPane.YES_NO_OPTION);
				
				if(opt == 0) {
					System.exit(0);
				}
			}
		});
		btncsesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncsesion.setBounds(156, 360, 145, 30);
		panel.add(btncsesion);
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenido:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 10, 89, 13);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_2_1 = new JLabel("/");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(94, 11, 89, 13);
		panel.add(lblNewLabel_2_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 459, 24);
		contentPane.add(menuBar);
		
		JMenu minfo = new JMenu("Informaci\u00F3n");
		minfo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(minfo);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cliente");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.lblNewLabel_2_1.setText(lblNewLabel_2_1.getText());
				
				info.setVisible(true);
				info.setLocationRelativeTo(null);
			}
		});
		minfo.add(mntmNewMenuItem_2);
	}
	
	
	

}
