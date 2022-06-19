package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ObjectClasses.Flight;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class mainPage extends JFrame {

	private JPanel contentPane;
	private JTextField departurePlace;
	private JTextField arrivalPlace;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPage frame = new mainPage(null,null,null,null,null,null,true, null);
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
	public mainPage(String fName, String lName, String mail, String pass, String phoneNumber, String cntry, Boolean isAdmin, ArrayList<Flight> flights) {
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
		
		departurePlace = new JTextField();
		departurePlace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departurePlace.setBounds(357, 170, 295, 35);
		contentPane.add(departurePlace);
		departurePlace.setColumns(10);
		
		
		JLabel selectDepartureLabel = new JLabel("Select company");
		selectDepartureLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		selectDepartureLabel.setBounds(357, 144, 172, 21);
		contentPane.add(selectDepartureLabel);
		
		JButton btnNewButton = new JButton("Search flight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Flight> availableFlights = new ArrayList<>();
				
				for(Flight i : flights) {
					if(i.getDepartureAirport().getCity().equals(departurePlace.getText()) && i.getArrivalAirport().getCity().equals(arrivalPlace.getText()) && i.getAircraft().getEmptyseats() > 0) {
						availableFlights.add(new Flight(i.getId(), i.getPrice(), i.getDeparture(), i.getArrival(), i.getDepartureAirport(), i.getArrivalAirport(), i.getAircraft(),i.getAirline()));
						
					}
				}
				flightsPage flightFrame = new flightsPage(fName, lName, mail, pass, phoneNumber, cntry, isAdmin, availableFlights);
				flightFrame.setVisible(true);
				SwingUtilities.getWindowAncestor(btnNewButton).setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnNewButton.setBounds(423, 319, 150, 52);
		contentPane.add(btnNewButton);
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login loginFrame = new Login(flights);
					loginFrame.setVisible(true);
					SwingUtilities.getWindowAncestor(logoutButton).setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		logoutButton.setBackground(Color.LIGHT_GRAY);
		logoutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		logoutButton.setBounds(605, 11, 89, 23);
		contentPane.add(logoutButton);
	}
}
