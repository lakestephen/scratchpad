package com.concurrentperformance.socket;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class MyServer {
    public static final int PORT = 1245;

	static Executor executor =  Executors.newSingleThreadExecutor();

	public static int count = 100 * 1000;

	public static void main(String[] args) throws IOException, InterruptedException {
        new MyServer().run();
    }

	private void run() throws IOException {
		ServerSocket ss = ServerSocketFactory.getDefault().createServerSocket(PORT);
	    for (;;) {
            Socket s = ss.accept();
		    s.setTcpNoDelay(true);
		    SocketReadHandler handler = new SocketReadHandler(s);
		    executor.execute(handler);
	    }

    }

	class SocketReadHandler implements Runnable {
		private final Socket socket;

		SocketReadHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				socket.setTcpNoDelay(true);
				System.out.println("New Socket");
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				Hessian2Input hi = new Hessian2Input(is);
				Hessian2Output ho = new Hessian2Output(os);
				long overallStart = System.nanoTime();
				for(int i=0;i< count;i++){
					ho.writeLong(i);
					ho.flush();
					long start = hi.readLong();
				}
				long overallEnd = System.nanoTime();
				System.out.println("Overall[" + (count *2)+ "]:" + ((overallEnd-overallStart)/(1000 * 1000)) + "MS");

				os.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class SocketWriteHandler implements Runnable {
		private final Socket socket;

		SocketWriteHandler (Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				socket.setTcpNoDelay(true);
				System.out.println("New Socket");
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				Hessian2Input hi = new Hessian2Input(is);
				Hessian2Output ho = new Hessian2Output(os);
				long overallStart = System.nanoTime();
				for(int i=0;i< count;i++){
					ho.writeLong(i);
					ho.flush();
					long start = hi.readLong();
				}
				long overallEnd = System.nanoTime();
				System.out.println("Overall[" + (count *2)+ "]:" + ((overallEnd-overallStart)/(1000 * 1000)) + "MS");

				os.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

