package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Session extends Thread{

	private String id;
	private BufferedWriter bw;
	private BufferedReader bf;
	private Socket socket;
	private IObserver observer;
	
	public Session(Socket socket, IObserver observer, String id) {
		this.socket = socket;
		this.observer = observer;
		this.id = id;
	}
	
	@Override
	public void run() {
		try {
			//To receive
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bf = new BufferedReader(isr);
			
			//To transmit
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
			
			receiveMessage();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void receiveMessage() {
		new Thread(
				() -> {
					while(true) {
						try {
							String msg = bf.readLine(); //Stops program until there is a message
							
							observer.notifyMessage(this, msg);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();	
	}
	
	public void sendMessage(String message) {
		new Thread(()->{
			//Write message
			try {
				bw.write(message + "\n");
				bw.flush();
				System.out.println("enviando mensaje");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		
	}

	public String getID() {
		return this.id;
	}
}
