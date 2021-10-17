package server;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ClientHandler extends Thread{
	private JDesktopPane desktop = null;
	private Socket cSocket= null;
	private JInternalFrame interFrame = new JInternalFrame();
	
	private JPanel cPanel = new JPanel();
	
	public ClientHandler(Socket cSocket,JDesktopPane desktop) {
		this.cSocket=cSocket;
		this.desktop=desktop;
		start();
	}
	public void GUI() {
		interFrame.setLayout(new BorderLayout());
		interFrame.getContentPane().add(cPanel,BorderLayout.CENTER);
		interFrame.setSize(100,100);
		desktop.add(interFrame);
		try {
			//Initially show the internal frame maximized
			interFrame.setMaximum(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//this allows to handle keylistener event
		cPanel.setFocusable(true);
		interFrame.setVisible(true);
	}
	@Override
	public void run() {
		//used to present client screen size
		Rectangle clientScreenDim = null;
		//used to read screenshots and clients screen dimension
		ObjectInputStream ois = null;
		GUI();
		try {
			//Read client screen dimension
			ois = new ObjectInputStream(cSocket.getInputStream());
			clientScreenDim=(Rectangle) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//start recieveing screenshots
		new ClientScreenReciever(ois,cPanel);
		
		//start sending envents to the client
		new ClientCommandsSender(cSocket,cPanel,clientScreenDim);
	}
}
