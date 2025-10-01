package Visual;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class prueba extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba frame = new prueba();
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
	public prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(445, 411, 40, 22);
		contentPane.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2","3","4","5"}));
		
		JLabel lblNewLabel = new JLabel("Grupos:");
		lblNewLabel.setBounds(394, 413, 64, 18);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 181, 149, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccionar usuarios");
		lblNewLabel_1.setBounds(10, 156, 129, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(169, 180, 105, 22);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registar usuarios");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(10, 399, 149, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Generar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grupos ventanaGrupos = new grupos();
		        ventanaGrupos.mostrarVentana();
			}
		});
		btnNewButton_2.setBounds(495, 399, 129, 46);
		contentPane.add(btnNewButton_2);
		setLocationRelativeTo(null);
		
		
		JLabel LogoLabel = new JLabel();
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));

		// Escalar la imagen al tamaño deseado 
		Image scaledImage = originalIcon.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		LogoLabel.setIcon(scaledIcon);
		LogoLabel.setBounds(118, 11, 400, 100);
		getContentPane().add(LogoLabel);

	}
}
