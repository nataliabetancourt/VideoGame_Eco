package view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPLauncher extends Thread{

	private static TCPLauncher tcp;
	private ServerSocket server;
	private IObserver observer;
	private ArrayList<Session> sessions;
	
	private TCPLauncher() {
		this.sessions = new ArrayList<>();
	}

	public static TCPLauncher getInstance() {
		if(tcp == null) {
			tcp = new TCPLauncher();
			tcp.start();
		}
		return tcp;	
	}
	
	@Override
	public void run() {
		initCom();
	}
	
	private void initCom(){
		try {
			
			System.out.println("Iniciando servidor");
			server = new ServerSocket(9000);
			
			while(sessions.size() < 2) {
				System.out.println("Esperando clientes");
				Socket socket = server.accept(); 
				
				Session session = new Session(socket, observer, "player" + sessions.size()+1);
				sessions.add(session);
				session.start();
				System.out.println("Cliente conectado");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public IObserver getObserver() {
		return observer;
	}

	public void setObserver(IObserver observer) {
		this.observer = observer;
	}
	
	public ArrayList<Session> getSessions() {
		return sessions;
	}



}
