package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.event.ChangeListener;

import ObjectClasses.Aircraft;
import ObjectClasses.Airline;
import ObjectClasses.Airport;
import ObjectClasses.Flight;
import ObjectClasses.Seat;

import javax.swing.event.ChangeEvent;

public class Login extends JFrame {

	private JPanel loginPanel;
	private JTextField emailInput;
	private JPasswordField passwordInput;

	/**
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		//conexiune bd
				ConnectDB project = new ConnectDB();
				project.createConnection();
				
				//preluare date
				java.sql.Statement statement = ConnectDB.connection.createStatement(); 
				ResultSet rs = statement.executeQuery("SELECT * FROM AIRLINE"); 
				
				//creare liste
						ArrayList<Seat> seats = new ArrayList<>();
						ArrayList<Airline> airlines = new ArrayList<>();
						ArrayList<Aircraft> aircrafts = new ArrayList<>();
						ArrayList<Airport> airports = new ArrayList<>();
						ArrayList<Flight> flights = new ArrayList<>();
				
				//initializare lista airline
				while(rs.next()) { 
					int id = rs.getInt("airlineid");
					String name = rs.getString("airlinename"); 
					
					Airline airline = new Airline(id,name);
					airlines.add(airline);
				}
				
				//initializare lista seats
				rs = statement.executeQuery("SELECT * FROM SEAT"); 
				
				while(rs.next()) { 
					int id = rs.getInt("seatid");
					String type = rs.getString("seattype");
					Boolean isAvailable;
					if(rs.getInt("isavailable") == 1)
						isAvailable = true;
					else
						isAvailable = false;
					int aircraftid = rs.getInt("aircraftid");
					
					Seat seat = new Seat(id,type,isAvailable,aircraftid);
					seats.add(seat);
				}
				
				//initializare lista aircraft
				rs = statement.executeQuery("SELECT * FROM AIRCRAFT"); 
				
				while(rs.next()) { 
					int id = rs.getInt("aircraftid");
					String manufacturer = rs.getString("manufacturer");
					String aircraftmodel = rs.getString("aircraftmodel");
					int totalseats = rs.getInt("totalseats");
					int emptyseats = rs.getInt("emptyseats");
					
					ArrayList<Seat> aircraftseats = new ArrayList<>();
					for(Seat i : seats) {
						if(i.getAirid() == id)
							aircraftseats.add(i);
					}
					
					Aircraft aircraft = new Aircraft(id,manufacturer,aircraftmodel,totalseats,emptyseats, aircraftseats);
					aircrafts.add(aircraft);
				}
				
				//initializare lista airport
				rs = statement.executeQuery("SELECT * FROM AIRPORT"); 
				
				while(rs.next()) { 
					int id = rs.getInt("airportid");
					String name = rs.getString("airportname");
					String country = rs.getString("country");
					String city = rs.getString("city");
					
					Airport airport = new Airport(id,name,country,city);
					airports.add(airport);
				}
				
				//initializare lista flight
				rs = statement.executeQuery("SELECT * FROM FLIGHT"); 
				
				while(rs.next()) { 
					int id = rs.getInt("id");
					int price = rs.getInt("price");
					String departure = rs.getString("departure");
					String arrival = rs.getString("arrival");
					int departureid = rs.getInt("departureairportid");
					int arrivalid = rs.getInt("arrivalairportid");
					int aircraftid = rs.getInt("aircraftid");
					int airlineid = rs.getInt("airlineid");
					
					Airport depairport = null;
					Airport arrairport = null;
					Aircraft aircraft = null;
					Airline airline = null;
					
					for(Airport i : airports) {
						if(i.getId() == departureid)
							depairport = i;
						if(i.getId() == arrivalid)
						    arrairport = i;
					}
					
					for(Aircraft i : aircrafts)
						if(i.getAircraftid() == aircraftid)
							aircraft = i;
					
					for(Airline i : airlines)
						if(i.getId() == airlineid)
							airline = i;
					
					Flight flight = new Flight(id,price,departure,arrival,depairport,arrairport,aircraft,airline);
					flights.add(flight);
				}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(flights);
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
	public Login(ArrayList<Flight> flights) throws SQLException {
		
		//conexiune bd
		ConnectDB project = new ConnectDB();
		project.createConnection();
		
		setTitle("Travel Assistant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 389);
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(176, 196, 222));
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel title = new JLabel("Travel Assistant");
		title.setFont(new Font("SansSerif", Font.BOLD, 24));
		title.setBounds(143, 50, 215, 25);
		loginPanel.add(title);
		
		JLabel emailLabel = new JLabel("E-Mail:");
		emailLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		emailLabel.setBounds(58, 96, 78, 14);
		loginPanel.add(emailLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		passwordLabel.setBounds(58, 163, 91, 14);
		loginPanel.add(passwordLabel);
		
		emailInput = new JTextField();
		emailInput.setFont(new Font("SansSerif", Font.PLAIN, 14));
		emailInput.setBounds(58, 121, 376, 31);
		loginPanel.add(emailInput);
		emailInput.setColumns(10);
		
		passwordInput = new JPasswordField();
		passwordInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordInput.setBounds(58, 188, 376, 32);
		loginPanel.add(passwordInput);
		
		JToggleButton adminToggle = new JToggleButton("Admin");
		adminToggle.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			}
		});
		adminToggle.setBackground(new Color(192, 192, 192));
		adminToggle.setBounds(10, 316, 91, 23);
		loginPanel.add(adminToggle);

		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String email = emailInput.getText();
				String password = passwordInput.getText();
				adminMainPage frame1;
				mainPage frame2;
				java.sql.Statement stmt;
				
				if(adminToggle.isSelected()) {
					try {
						stmt = ConnectDB.connection.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM ADMINS");
						
						while(rs.next()) {
							if(email.equals(rs.getString("email")) && password.equals(rs.getString("password"))) {
							    frame1 = new adminMainPage(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), rs.getString("phone"), rs.getString("country"), true, flights);
								frame1.setVisible(true);
								SwingUtilities.getWindowAncestor(loginButton).setVisible(false);
							}
							
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				else {
					try {
						stmt = ConnectDB.connection.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
						
						while(rs.next()) {
							if(email.equals(rs.getString("email")) && password.equals(rs.getString("password"))) {
							    frame2 = new mainPage(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), rs.getString("phone"), rs.getString("country"), false, flights);
								frame2.setVisible(true);
								SwingUtilities.getWindowAncestor(loginButton).setVisible(false);
							}
							
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		loginButton.setBounds(79, 248, 131, 31);
		loginPanel.add(loginButton);
		
		JButton accountButton = new JButton("Create Account");
		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {	
				try {
					createAccount accountFrame = new createAccount(flights);
					accountFrame.setVisible(true);
					SwingUtilities.getWindowAncestor(accountButton).setVisible(false);
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		});
		accountButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		accountButton.setBackground(Color.LIGHT_GRAY);
		accountButton.setBounds(268, 249, 131, 31);
		loginPanel.add(accountButton);
		
	}
}
