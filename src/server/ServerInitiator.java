package server;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerInitiator {
	// Main server frame
	private JFrame frame = new JFrame();
	// JDesktopPane represents the main container that will contain all
	// connected clients's screens
	private JDesktopPane desktop = new JDesktopPane();

	public static void main(String[] args) {
		String port = JOptionPane.showInputDialog("Please enter listening port");
		new ServerInitiator(Integer.parseInt(port));
	}

	public ServerInitiator(int port) {
		try {
			ServerSocket sc = new ServerSocket(port);

			// show server GUI
			GUI();

			while (true) {
				Socket client = sc.accept();
				System.out.println("New client connected to the server");

				// Per each client create a clienthandler
				new ClientHandler(client, desktop);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void GUI() {
		frame.add(desktop, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(3);
		// show the frame in a maximized state
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
}
