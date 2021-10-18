package client;

import java.awt.Robot;
import java.net.Socket;
import java.util.Scanner;

/*
 * used to recieve server commands then execute them at the client
 */
public class ServerDelegate extends Thread {
	Socket socket = null;
	Robot robot = null;
	boolean continureLoop = true;

	public ServerDelegate(Socket socket, Robot robot) {
		this.socket = socket;
		this.robot = robot;
		start();
	}

	@Override
	public void run() {
		Scanner scanner = null;
		try {
			// prepare scanner object
			System.out.println("Waiting InputStream");
			scanner = new Scanner(socket.getInputStream());

			while (continureLoop) {
				// recieve commands and respond accordingly
				System.out.println("Waiting for command");
				int command = scanner.nextInt();
				System.out.println("New command: " + command);
				switch (command) {
				case -1:
					robot.mousePress(scanner.nextInt());
					break;
				case -2:
					robot.mouseRelease(scanner.nextInt());
					break;
				case -3:
					robot.keyPress(scanner.nextInt());
					break;
				case -4:
					robot.keyRelease(scanner.nextInt());
					break;
				case -5:
					robot.mouseMove(scanner.nextInt(), scanner.nextInt());
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
