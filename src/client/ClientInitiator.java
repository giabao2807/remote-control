package client;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientInitiator {
	Socket socket = null;

	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("Please enter server IP");
		String port = JOptionPane.showInputDialog("Please enter server port");
		new ClientInitiator(ip, Integer.parseInt(port));
	}

	public ClientInitiator(String ip, int port) {
		Robot robot = null; // use to capture the screen
		Rectangle rectangle = null; // use to represent screen dimensions

		try {
			System.out.println("Connecting to server.......");
			socket = new Socket(ip, port);
			System.out.println("Connection Established.");

			// Get default screen device
			GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

			// Get screen dimensions
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			rectangle = new Rectangle();

			// Prepare Robot object
			robot = new Robot(gDev);

			GUI();
			// ScreenSpyer sends screenshots of the client screen
			new ScreenSpyer(socket, robot, rectangle);

			// Serverdelegate recieves server commands and execute them
			new ServerDelegate(socket, robot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void GUI() {
		JFrame frame = new JFrame("Remote Admin");
		JButton button = new JButton("Terminate");

		frame.setBounds(100, 100, 150, 150);
		frame.setDefaultCloseOperation(3);
		frame.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
