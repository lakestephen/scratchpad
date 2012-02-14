package com.lake.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class SingleThreadWebServer {
	
  public void runServer() throws IOException {
      ServerSocket socket = new ServerSocket(80);
      while (true) {
          Socket connection = socket.accept();
          handleRequest(connection);
      }
  }

	private static void handleRequest(Socket connection) {
		// TODO Auto-generated method stub
		
	}
}


