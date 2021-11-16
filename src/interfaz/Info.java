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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Info extends JFrame {

	private JPanel contentPane;
	public Image profile_img = new ImageIcon(this.getClass().getResource("/1.png")).getImage();
	public CambiarContrasenha cambiarContrasenha = new CambiarContrasenha();
	public JLabel lblNewLabel_2_1;
	
	
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
		setTitle("Informacion personal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 341, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(profile_img));
		lblNewLabel.setBounds(82, 0, 181, 163);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Hola,");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(123, 134, 89, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_2_1 = new JLabel("/");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(160, 134, 89, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("Cambiar contrase\u00F1a");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarContrasenha.lblNewLabel_1.setText(lblNewLabel_2_1.getText());
				cambiarContrasenha.setVisible(true);
			}
		});
		btnNewButton.setBounds(70, 158, 181, 23);
		contentPane.add(btnNewButton);
	}
}
