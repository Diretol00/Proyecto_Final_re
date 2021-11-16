package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Info extends JFrame {

	private JPanel contentPane;
	public Image profile_img = new ImageIcon(this.getClass().getResource("/1.png")).getImage();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
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
	public Info() {
		setType(Type.UTILITY);
		setTitle("Informacion personal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 341, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(profile_img));
		lblNewLabel.setBounds(82, 0, 181, 163);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: Ludwig\r\n");
		lblNewLabel_1.setBounds(95, 180, 181, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido: Cabrera\r\n");
		lblNewLabel_2.setBounds(95, 212, 181, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Matricula: 2021-0215");
		lblNewLabel_3.setBounds(95, 244, 181, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Carrera: Desarrollo de Software\r\n");
		lblNewLabel_4.setBounds(95, 276, 181, 21);
		contentPane.add(lblNewLabel_4);
	}

}
