package com.lake.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
public class MyServer {
    public static final int PORT = 12345;
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = ServerSocketFactory.getDefault().createServerSocket(PORT);
        Socket s = ss.accept();
        Thread.sleep(5000);
        ss.close();
        s.getOutputStream().close();
        s.getInputStream().close();
        s.close();
    }
}

