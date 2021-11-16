package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	
	public Info info = new Info();

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
		btncarrito.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncarrito.setBounds(364, 34, 85, 31);
		contentPane.add(btncarrito);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 24, 459, 426);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, -10, 459, 426);
		panel.add(lblNewLabel);
		
		JButton btncomprar = new JButton("Comprar");
		btncomprar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncomprar.setBounds(180, 317, 98, 30);
		panel.add(btncomprar);
		
		JButton btncsesion = new JButton("Cerrar Sesi\u00F3n");
		btncsesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btncsesion.setBounds(156, 360, 145, 30);
		panel.add(btncsesion);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 459, 24);
		contentPane.add(menuBar);
		
		JMenu minfo = new JMenu("Informaci\u00F3n");
		minfo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(minfo);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cliente");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.setVisible(true);
				info.setLocationRelativeTo(null);
			}
		});
		minfo.add(mntmNewMenuItem_2);
	}

}
