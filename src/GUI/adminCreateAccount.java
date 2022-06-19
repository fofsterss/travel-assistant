package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import ObjectClasses.Flight;

public class adminCreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JTextField phone;
	private JTextField country;
	private JPasswordField password;
	private JPasswordField confirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminCreateAccount frame = new adminCreateAccount(null,null,null,null,null,null,true, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public adminCreateAccount(String fName, String lName, String mail, String pass, String phoneNumber, String cntry, Boolean isAdmin, ArrayList<Flight> flights) throws SQLException {
		ConnectDB project = new ConnectDB();
		project.createConnection();
		
		setTitle("Travel Assistant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 619);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create an admin account");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setBounds(91, 21, 285, 57);
		contentPane.add(lblNewLabel);
		
		firstName = new JTextField();
		firstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstName.setBounds(43, 100, 393, 30);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lastName.setColumns(10);
		lastName.setBounds(43, 161, 393, 30);
		contentPane.add(lastName);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		email.setColumns(10);
		email.setBounds(43, 223, 393, 30);
		contentPane.add(email);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password.setBounds(43, 285, 393, 30);
		contentPane.add(password);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		confirmPassword.setBounds(43, 347, 393, 30);
		contentPane.add(confirmPassword);
		
		phone = new JTextField();
		phone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phone.setColumns(10);
		phone.setBounds(43, 408, 393, 30);
		contentPane.add(phone);
		
		country = new JTextField();
		country.setFont(new Font("Tahoma", Font.PLAIN, 14));
		country.setColumns(10);
		country.setBounds(43, 471, 393, 30);
		contentPane.add(country);
		
		JLabel firstNameLabel = new JLabel("First name:");
		firstNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		firstNameLabel.setBounds(43, 79, 91, 21);
		contentPane.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last name:");
		lastNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lastNameLabel.setBounds(43, 141, 91, 21);
		contentPane.add(lastNameLabel);
		
		JLabel emailLabel = new JLabel("E-Mail:");
		emailLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		emailLabel.setBounds(43, 202, 91, 21);
		contentPane.add(emailLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		passwordLabel.setBounds(43, 264, 91, 21);
		contentPane.add(passwordLabel);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm password:");
		confirmPasswordLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		confirmPasswordLabel.setBounds(43, 326, 147, 21);
		contentPane.add(confirmPasswordLabel);
		
		JLabel phoneLabel = new JLabel("Phone number:");
		phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		phoneLabel.setBounds(43, 388, 119, 21);
		contentPane.add(phoneLabel);
		
		JLabel countryLabel = new JLabel("Country:");
		countryLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		countryLabel.setBounds(43, 449, 119, 21);
		contentPane.add(countryLabel);
		
		JButton createAccountButton = new JButton("Create account");
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = firstName.getText();
				String lname = lastName.getText();
				String mail = email.getText();
				String pass = password.getText();
				String confirm = confirmPassword.getText();
				String phoneNumber = phone.getText();
				String cntry = country.getText();
				
				if(pass.equals(confirm) && mail.contains("@")) {
					try {
						java.sql.Statement stmt = ConnectDB.connection.createStatement();
						String dbop = "INSERT INTO ADMINS VALUES(" + '"' + mail + '"' + "," + '"' + pass + '"' + "," + '"' + fname + '"' + "," + '"' + lname + '"' + "," + '"' + phoneNumber + '"' + "," + '"' + cntry + '"'+ ");";
						stmt.execute(dbop);
						stmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				adminMainPage adminFrame = null;
				adminFrame = new adminMainPage(fName,lName,mail,pass,phoneNumber,cntry,isAdmin,flights);
				adminFrame.setVisible(true);
				SwingUtilities.getWindowAncestor(createAccountButton).setVisible(false);
				
			}
		});
		createAccountButton.setBackground(new Color(192, 192, 192));
		createAccountButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		createAccountButton.setBounds(71, 512, 157, 40);
		contentPane.add(createAccountButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminMainPage adminFrame = null;
				adminFrame = new adminMainPage(fName,lName,mail,pass,phoneNumber,cntry,isAdmin,flights);
				adminFrame.setVisible(true);
				SwingUtilities.getWindowAncestor(createAccountButton).setVisible(false);
			}
		});
		backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setBounds(239, 512, 157, 40);
		contentPane.add(backButton);
	}

}
