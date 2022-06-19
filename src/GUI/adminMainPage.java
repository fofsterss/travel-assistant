package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import ObjectClasses.Flight;

public class adminMainPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminMainPage frame = new adminMainPage(null,null,null,null,null,null,true, null);
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
	public adminMainPage(String fName, String lName, String mail, String pass, String phoneNumber, String cntry, Boolean isAdmin, ArrayList<Flight> flights) {
		setTitle("Travel Assistant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBackground(new Color(253, 245, 230));
		profilePanel.setBounds(0, 81, 295, 360);
		contentPane.add(profilePanel);
		profilePanel.setLayout(null);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		firstNameLabel.setBounds(26, 58, 107, 21);
		profilePanel.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lastNameLabel.setBounds(26, 115, 107, 21);
		profilePanel.add(lastNameLabel);
		
		JLabel emailLabel = new JLabel("E-Mail:");
		emailLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		emailLabel.setBounds(26, 172, 107, 21);
		profilePanel.add(emailLabel);
		
		JLabel phoneLabel = new JLabel("Phone:");
		phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		phoneLabel.setBounds(26, 229, 107, 21);
		profilePanel.add(phoneLabel);
		
		JLabel countryLabel = new JLabel("Country:");
		countryLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		countryLabel.setBounds(26, 286, 107, 21);
		profilePanel.add(countryLabel);
		
		JLabel firstName = new JLabel(fName);
		firstName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		firstName.setBounds(62, 83, 198, 21);
		profilePanel.add(firstName);
		
		JLabel lastName = new JLabel(lName);
		lastName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lastName.setBounds(62, 140, 198, 21);
		profilePanel.add(lastName);
		
		JLabel email = new JLabel(mail);
		email.setFont(new Font("SansSerif", Font.PLAIN, 12));
		email.setBounds(62, 197, 198, 21);
		profilePanel.add(email);
		
		JLabel phone = new JLabel(phoneNumber);
		phone.setFont(new Font("SansSerif", Font.PLAIN, 12));
		phone.setBounds(62, 254, 198, 21);
		profilePanel.add(phone);
		
		JLabel country = new JLabel(cntry);
		country.setFont(new Font("SansSerif", Font.PLAIN, 12));
		country.setBounds(62, 311, 198, 21);
		profilePanel.add(country);
		
		JLabel profileDataLabel = new JLabel("Profile Data:");
		profileDataLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		profileDataLabel.setBounds(95, 22, 107, 21);
		profilePanel.add(profileDataLabel);
		
		if(isAdmin == true) {
			JLabel lblNewLabel = new JLabel("Admin");
			lblNewLabel.setForeground(new Color(255, 0, 0));
			lblNewLabel.setBackground(new Color(255, 0, 0));
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
			lblNewLabel.setBounds(10, 335, 58, 14);
			profilePanel.add(lblNewLabel);
		}
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(250, 235, 215));
		titlePanel.setBounds(0, 0, 295, 84);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Travel Assistant");
		titleLabel.setBounds(50, 24, 184, 32);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		titlePanel.add(titleLabel);
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login loginFrame = new Login(flights);
					loginFrame.setVisible(true);
					SwingUtilities.getWindowAncestor(logoutButton).setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		logoutButton.setBackground(Color.LIGHT_GRAY);
		logoutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		logoutButton.setBounds(605, 11, 89, 23);
		contentPane.add(logoutButton);
		
		JButton createAdminAccount = new JButton("Create account");
		createAdminAccount.setBackground(Color.LIGHT_GRAY);
		createAdminAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					adminCreateAccount adminFrame = new adminCreateAccount(fName,lName,mail,pass,phoneNumber,cntry,isAdmin,flights);
					adminFrame.setVisible(true);
					SwingUtilities.getWindowAncestor(createAdminAccount).setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		createAdminAccount.setFont(new Font("SansSerif", Font.BOLD, 14));
		createAdminAccount.setBounds(426, 256, 148, 48);
		contentPane.add(createAdminAccount);
		
		JButton checkUsers = new JButton("View Users");
		checkUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				checkUsersPage usersFrame = new checkUsersPage(fName,lName,mail,pass,phoneNumber,cntry,isAdmin,flights);
				usersFrame.setVisible(true);
				SwingUtilities.getWindowAncestor(checkUsers).setVisible(false);
			}
		});
		checkUsers.setBackground(Color.LIGHT_GRAY);
		checkUsers.setFont(new Font("SansSerif", Font.BOLD, 14));
		checkUsers.setBounds(426, 147, 148, 48);
		contentPane.add(checkUsers);
	}
}
