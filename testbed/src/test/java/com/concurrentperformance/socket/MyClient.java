package com.concurrentperformance.socket;

import com.caucho.hessian.io.Hessian2Input;

import javax.net.SocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class MyClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        new MyClient().run();
    }

	private void run() throws IOException, InterruptedException {
        Socket socket = SocketFactory.getDefault().createSocket("localhost", MyServer.PORT);
//        socket.setTcpNoDelay(true);
		InputStream is = socket.getInputStream();
//		OutputStream os = socket.getOutputStream();
		Hessian2Input hi = new Hessian2Input(is);
//		Hessian2Output ho = new Hessian2Output(os);
		for (;;) {
			long l = hi.readLong();
			System.out.println("Read" + l);
			Thread.sleep(50);
//			ho.writeLong(l);
//			ho.flush();
		}
		//is.close();
	//	s.close();
    }
}
