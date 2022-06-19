package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ObjectClasses.Flight;

import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class flightsPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					flightsPage frame = new flightsPage(null,null,null,null,null,null,true,null);
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
	public flightsPage(String fName, String lName, String mail, String pass, String phoneNumber, String cntry, Boolean isAdmin, ArrayList<Flight> availableFlights) {
		setTitle("Travel Assistant");
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
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		
		for(int i = 0;i < availableFlights.size(); i++) {
			labels.add(new JLabel());
			buttons.add(new JButton());
			
			labels.get(i).setOpaque(false);
			labels.get(i).setFont(new Font("SansSerif", Font.BOLD, 14));
			labels.get(i).setText("<html>&ensp;" + availableFlights.get(i).getDepartureAirport().getName() + "(" + availableFlights.get(i).getDepartureAirport().getCity() + ")" 
			+ " --> " + availableFlights.get(i).getArrivalAirport().getName() + "(" + availableFlights.get(i).getArrivalAirport().getCity() + ") - " + availableFlights.get(i).getPrice() + "$" 
			+ "<br>" + "&ensp;Departs at: " + availableFlights.get(i).getDeparture() + " - " + "Arrives at: " + availableFlights.get(i).getDeparture() 
			+ "<br>" + "&ensp;Handled by: " + availableFlights.get(i).getAirline().getName()
			+ "<br>" + "&ensp;Plane used: " + availableFlights.get(i).getAircraft().getManufacturer() + " " + availableFlights.get(i).getAircraft().getAircraftmodel() + "<br><br></html>");
			scrollContentPanel.add(labels.get(i));
			
			buttons.get(i).setBackground(new Color(192, 192, 192));
			buttons.get(i).setFont(new Font("SansSerif", Font.BOLD, 14));
			buttons.get(i).setText("Book Flight");
			Flight f = availableFlights.get(i);
			JButton b = buttons.get(i);
			buttons.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					try {
						paymentPage paymentFrame = new paymentPage(fName, lName, mail, pass, phoneNumber, cntry, isAdmin, availableFlights, f);
						paymentFrame.setVisible(true);
						SwingUtilities.getWindowAncestor(profilePanel).setVisible(false);
						SwingUtilities.getWindowAncestor(profileDataLabel).setVisible(false);
						SwingUtilities.getWindowAncestor(profileDataLabel).setVisible(false);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			scrollContentPanel.add(buttons.get(i));
		}
	
	}
}
