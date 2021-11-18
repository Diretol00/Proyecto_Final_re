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
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import metodos.EnviarCorreo;

import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Vector;
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
		
		JButton btncomprar = new JButton("Comprar");
		btncomprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviar(txtcorreo.getText(), bMsg);
			}
		});
		btncomprar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btncomprar.setBounds(32, 422, 164, 36);
		contentPane.add(btncomprar);
		
		JButton btncancel = new JButton("Cancelar");
		btncancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btncancel.setBounds(206, 422, 164, 36);
		contentPane.add(btncancel);
		
		lblusern = new JLabel("");
		lblusern.setBounds(311, 10, 73, 23);
		contentPane.add(lblusern);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 10, 352, 205);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}
}
