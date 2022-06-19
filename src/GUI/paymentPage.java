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

public class paymentPage extends JFrame {

	private JPanel contentPane;
	private JTextField cardCodeField;
	private JTextField cardOwnerField;
	private JTextField expDateField;
	private JPasswordField cvvField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paymentPage frame = new paymentPage(null,null,null,null,null,null,true,null, null);
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
	public paymentPage(String fName, String lName, String mail, String pass, String phoneNumber, String cntry, Boolean isAdmin, ArrayList<Flight> availableFlights, Flight f) throws SQLException {
		ConnectDB project = new ConnectDB();
		project.createConnection();
		
		setTitle("Travel Assistant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 451);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Payment details");
		title.setFont(new Font("SansSerif", Font.BOLD, 24));
		title.setBounds(145, 21, 266, 57);
		contentPane.add(title);
		
		cardCodeField = new JTextField();
		cardCodeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cardCodeField.setBounds(43, 100, 393, 30);
		contentPane.add(cardCodeField);
		cardCodeField.setColumns(10);
		
		cardOwnerField = new JTextField();
		cardOwnerField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cardOwnerField.setColumns(10);
		cardOwnerField.setBounds(43, 161, 393, 30);
		contentPane.add(cardOwnerField);
		
		expDateField = new JTextField();
		expDateField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expDateField.setColumns(10);
		expDateField.setBounds(43, 223, 393, 30);
		contentPane.add(expDateField);
		
		cvvField = new JPasswordField();
		cvvField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cvvField.setBounds(43, 285, 393, 30);
		contentPane.add(cvvField);
		
		JLabel cardCode = new JLabel("Card code:");
		cardCode.setFont(new Font("SansSerif", Font.BOLD, 16));
		cardCode.setBounds(43, 79, 91, 21);
		contentPane.add(cardCode);
		
		JLabel ownerName = new JLabel("Card owner:");
		ownerName.setFont(new Font("SansSerif", Font.BOLD, 16));
		ownerName.setBounds(43, 141, 105, 21);
		contentPane.add(ownerName);
		
		JLabel expDate = new JLabel("Expiration date:");
		expDate.setFont(new Font("SansSerif", Font.BOLD, 16));
		expDate.setBounds(43, 202, 135, 21);
		contentPane.add(expDate);
		
		JLabel cvvCode = new JLabel("CVV:");
		cvvCode.setFont(new Font("SansSerif", Font.BOLD, 16));
		cvvCode.setBounds(43, 264, 91, 21);
		contentPane.add(cvvCode);
		
		JButton payButton = new JButton("Pay");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConnectDB project = new ConnectDB();
				try {
					project.createConnection();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					java.sql.Statement stmt = ConnectDB.connection.createStatement();
					String dbop = "INSERT INTO PASSENGER VALUES(" + '"' + mail + '"' + "," + '"' + fName + '"' + "," + '"' + lName + '"' + "," + f.getId() + "," + '"' + f.getAircraft().getSeats().get(1).getType() + '"' + ");";
					stmt.execute(dbop);
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					java.sql.Statement stmt = ConnectDB.connection.createStatement();
					String dbop = "INSERT INTO PAYMENT VALUES(" + '"' + cardCodeField.getText() + '"' + "," + '"' + cardOwnerField.getText() + '"' + "," + '"' + expDateField.getText() + '"' + "," + '"' + cvvField.getText() + '"' + ");";
					stmt.execute(dbop);
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				mainPage mainFrame = null;
				mainFrame = new mainPage(fName, lName, mail, pass, phoneNumber, cntry, isAdmin, availableFlights);
				mainFrame.setVisible(true);
				SwingUtilities.getWindowAncestor(payButton).setVisible(false);
				
			}
		});
		payButton.setBackground(new Color(192, 192, 192));
		payButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		payButton.setBounds(72, 344, 157, 40);
		contentPane.add(payButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flightsPage flightsFrame = null;
				flightsFrame = new flightsPage(fName, lName, mail, pass, phoneNumber, cntry, isAdmin, availableFlights);
				flightsFrame.setVisible(true);
				SwingUtilities.getWindowAncestor(backButton).setVisible(false);
			}
		});
		backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		backButton.setBackground(Color.LIGHT_GRAY);
		backButton.setBounds(239, 344, 157, 40);
		contentPane.add(backButton);
	}

}
