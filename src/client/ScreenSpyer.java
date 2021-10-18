package client;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;

public class ScreenSpyer extends Thread {
	Socket socket = null;
	Robot robot = null; // used to capture screen
	Rectangle rectangle = null; // used to represent screen dimensions
	boolean continueLoop = true; // used to exit the program

	public ScreenSpyer(Socket socket, Robot robot, Rectangle rect) {
		this.socket = socket;
		this.robot = robot;
		rectangle = rect;
		start();
	}

	@Override
	public void run() {
		ObjectOutputStream oos = null; // used to write an object to the stream
		try {
			// prepare objectoutputstream
			oos = new ObjectOutputStream(socket.getOutputStream());
			/*
			 * send screen size to the server in order to calculate correct mouse location
			 * on the server's panel
			 */
			oos.writeObject(rectangle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		while (continueLoop) {
			// Capture screen
			BufferedImage image = robot.createScreenCapture(rectangle);
			/*
			 * I have to wrap bufferedImage with ImageIcon because bufferedImage class does
			 * not implement serializable interface
			 */
			ImageIcon imageIcon = new ImageIcon(image);

			// send captured screen to the server
			try {
				System.out.println("before sending image");
				oos.writeObject(imageIcon);
				oos.reset();// clear objectoutputstream cache
				System.out.println("New screenshot sent");
			} catch (Exception e) {
				// TODO: handle exception
			}
			// wait for 100ms to reduce network traffic
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
