package server;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class ClientCommandsSender implements KeyListener, MouseListener, MouseMotionListener {
	private Socket cSocket = null;
	private JPanel cPanel = null;
	private PrintWriter writer = null;
	private Rectangle clientScreenDim = null;

	public ClientCommandsSender(Socket s, JPanel p, Rectangle r) {
		cSocket = s;
		cPanel = p;
		clientScreenDim = r;

		// Associate event listener to the panel
		cPanel.addKeyListener(this);
		cPanel.addMouseListener(this);
		cPanel.addMouseMotionListener(this);
		try {
			// Prepare printwriter which will be used to send commands
			// to client
			writer = new PrintWriter(cSocket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// not implemented yet
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		double xScale = clientScreenDim.getWidth() / cPanel.getWidth();
		System.out.println("xScale: " + xScale);
		double yScale = clientScreenDim.getHeight() / cPanel.getHeight();
		System.out.println("yScale: " + yScale);
		System.out.println("Mouse Moved");
		writer.println(EnumCommands.MOVE_MOUSE.getAbbrev());
		writer.println(((int) (e.getX() * xScale)));
		writer.println(((int) (e.getY() * yScale)));
		writer.flush();
	}

	// this is not implemented
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed");
		writer.println(EnumCommands.PRESS_MOUSE.getAbbrev());
		int button = e.getButton();
		int xButton = 16;
		if (button == 3) {
			xButton = 4;
		}
		writer.println(xButton);
		writer.flush();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse Released");
		writer.println(EnumCommands.RELEASE_MOUSE.getAbbrev());
		int button = e.getButton();
		int xButton = 16;
		if (button == 3) {
			xButton = 4;
		}
		writer.println(xButton);
		writer.flush();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed");
		writer.println(EnumCommands.PRESS_KEY.getAbbrev());
		writer.println(e.getKeyCode());
		writer.flush();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Mouse Released");
		writer.println(EnumCommands.RELEASE_KEY.getAbbrev());
		writer.println(e.getKeyCode());
		writer.flush();
	}
}
