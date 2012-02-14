package com.lake.socket;

import java.io.IOException;
import java.net.Socket;

import javax.net.SocketFactory;

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */


public class MyClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket s = SocketFactory.getDefault().createSocket("localhost", MyServer.PORT);
        System.out.println(s.isConnected() + " " + s.isBound() + " " + s.isClosed() + " " +  s.isInputShutdown() + " " +  s.isOutputShutdown());
        Thread.sleep(10000);
        System.out.println(s.isConnected() + " " + s.isBound() + " " + s.isClosed() + " " +  s.isInputShutdown() + " " +  s.isOutputShutdown());
        
        s.setTcpNoDelay(true);
    }
}
