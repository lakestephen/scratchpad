package com.lake.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ThreadPerTaskWebServer {
  
  public void runServer() throws IOException {
      ServerSocket socket = new ServerSocket(80);
      while (true) {
          final  Socket connection = socket.accept();
          Runnable task = new Runnable() {
                  public void run() {
                      handleRequest(connection);
                  }
              };
          new Thread(task).start();
      }
  }
  
	private static void handleRequest(Socket connection) {
		// TODO Auto-generated method stub
		
	}
}

