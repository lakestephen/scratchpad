/*
 * Copyright (c) PCMS Group plc 2010. All Rights Reserved.
 * This source code is copyright of PCMS Group plc. The information
 * contained herein is proprietary and confidential to PCMS Group plc.
 * This proprietary and confidential information, either in whole or in
 * part, shall not be used for any purpose unless permitted by the terms
 * of a valid license agreement.
 */
package com.pcmsgroup.powerscheduler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.junit.Test;

/**
 * Test set that will exercise the Power Scheduler socket comms. 
 * Refer to ControlServiceAPI.xsd for the protocol of the comms. 
 * 
 * @author Stephen Lake
 * @version 1.0 
 */
public class PowerScheduleServiceTest {

	private static final String HOST_NAME = "localhost";
	private static final int PORT = 2000;

	/**
	 * Test submit a trading hours API call.
	 */
	@Test
	public void submitTradingHours() {
		StringBuilder b = new StringBuilder();
		
		b.append(getHead());

		b.append("<setTradingHours>\r\n");
		b.append("\t<sunday from=\"10:00\" to=\"23:00\"/>\r\n");
		b.append("\t<monday from=\"02:23\" to=\"00:00\"/>\r\n");
		b.append("\t<tuesday from=\"16:50\" to=\"01:00\"/>\r\n");
		b.append("\t<wednesday from=\"09:00\" to=\"10:00\"/>\r\n");
		b.append("\t<thursday from=\"00:00\" to=\"00:00\"/>\r\n");
		b.append("\t<friday from=\"00:00\" to=\"00:00\"/>\r\n");
		b.append("\t<saturday from=\"00:00\" to=\"00:00\"/>\r\n");
		b.append("</setTradingHours>\r\n");
		
		b.append(getTail());
		
		sendRequest(b);
		
	}

	/**
	 * Test submit a deny sleep API call.
	 */
	@Test
	public void submitDenySleep() {
		StringBuilder b = new StringBuilder();
		
		b.append(getHead());
		b.append("<denySleep identifier=\"USER_LOGGED_ON\" validityPeriod=\"55\"/>\r\n");
		b.append(getTail());

		sendRequest(b);
	}

	/**
	 * Test submit an identification API call.
	 */
	@Test
	public void submitIdentification() {
		StringBuilder b = new StringBuilder();
		
		b.append(getHead());
		b.append("<identification storeId=\"4\" storeMaxId=\"64\" tillId=\"104\" tillMaxIdInStore=\"164\"/>\r\n");
		b.append(getTail());
		
		sendRequest(b);
	}
	
	private CharSequence getHead() {
		StringBuilder b = new StringBuilder();
		b.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		b.append("<controlServiceAPI xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		b.append("xsi:noNamespaceSchemaLocation=\"ControlServiceAPI.xsd\" version=\"1.0\">\r\n");
		return b;
	}

	private CharSequence getTail() {
		return "</controlServiceAPI>\r\n";
	}
	
	private void sendRequest(CharSequence request) {
		
		// crude open, write, closing of the socket 
		final String requestContents = request.toString();

		InetAddress address = null;
		Socket socket = null;
		OutputStream os = null;
		
		try {
			address = InetAddress.getByName(HOST_NAME);
			System.out.println("Opening connection to " + address + " on port " + PORT);
			
			socket = new Socket(address, PORT);
			socket.setSoTimeout(0);
			os = socket.getOutputStream();

			System.out.println("Sending: " + requestContents);
			os.write(requestContents.getBytes(), 0, requestContents.getBytes().length);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			
			try {
				if (os != null) {
					os.flush();
				}
			} catch (IOException e) {
				System.out.println("Error flushing output stream: " + e);
			}

			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing output stream: " + e);
			}
			
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing socket: " + e);
			}
			
		}
		
		System.out.println("Finished:");

	}

}
