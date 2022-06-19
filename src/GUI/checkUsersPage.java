package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import ObjectClasses.Flight;

public class checkUsersPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkUsersPage frame = new checkUsersPage(null,null,null,null,null,null,true,null);
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
	public checkUsersPage(String fName, String lName, String mail, String pass, String phoneNumber, String cntry, Boolean isAdmin, ArrayList<Flight> flights) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 480);
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
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminMainPage adminFrame = new adminMainPage(fName,lName,mail,pass,phoneNumber,cntry,isAdmin,flights);
				adminFrame.setVisible(true);
				SwingUtilities.getWindowAncestor(backButton).setVisible(false);
			}
		});
		backButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		backButton.setBounds(196, 326, 89, 23);
		profilePanel.add(backButton);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(250, 235, 215));
		titlePanel.setBounds(0, 0, 295, 84);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Travel Assistant");
		titleLabel.setBounds(50, 24, 184, 32);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		titlePanel.add(titleLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(294, 0, 738, 441);
		contentPane.add(scrollPane);
		
		JPanel scrollContentPanel = new JPanel();
		scrollContentPanel.setBackground(new Color(176, 196, 222));
		scrollPane.setViewportView(scrollContentPanel);
		scrollContentPanel.setLayout(new GridLayout(0, 1, 0, 20));
		
		if(isAdmin == true) {
			JLabel lblNewLabel = new JLabel("Admin");
			lblNewLabel.setForeground(new Color(255, 0, 0));
			lblNewLabel.setBackground(new Color(255, 0, 0));
			lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
			lblNewLabel.setBounds(10, 335, 58, 14);
			profilePanel.add(lblNewLabel);
		}
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		
		ConnectDB project = new ConnectDB();
		try {
			project.createConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		java.sql.Statement stmt;
		try {
			stmt = ConnectDB.connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
			
			int i = 0;
			while(rs.next()) {
				
				labels.add(new JLabel());
				labels.get(i).setOpaque(false);
				labels.get(i).setFont(new Font("SansSerif", Font.BOLD, 14));
				labels.get(i).setText("<html>&ensp;E-Mail: " + rs.getString("email") 
				+ "<br>" + "&ensp;Password: " + rs.getString("password")
				+ "<br>" + "&ensp;Name: " + rs.getString("firstname") + " " + rs.getString("lastname")
				+ "<br>" + "&ensp;Phone: " + rs.getString("phone")
				+ "<br>" + "&ensp;Country : " + rs.getString("country") + "<br></html>");
				scrollContentPanel.add(labels.get(i));
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
}
