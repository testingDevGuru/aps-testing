package com.aps.testing.helper;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class CurrIdentityHelper {
	/**
	 * establishes connection to the cloud server 
	 * and drops the file in a sub-directory
	 * @param user
	 * @param host
	 * @param privateKey
	 * @param fileName
	 * @return
	 */
	public String runShell(String user, String host, String privateKey,String fileName){
		String outcome ="";
		try {
			/**
			 * adding the attributes to jsch to establish connection
			 */
			JSch jsch = new JSch();
			int port = 22;
			

			jsch.addIdentity(privateKey);
			JSch.setConfig("StrictHostKeyChecking", "no");

			Session session = jsch.getSession(user, host, port);

			session.connect();

			/**
			 * creates an sftp session to transfer 
			 * the file to the root dir
			 */
			Channel channel1 = session.openChannel("sftp");
			channel1.setInputStream(System.in);
			channel1.setOutputStream(System.out);
			channel1.connect();

			ChannelSftp c = (ChannelSftp) channel1;

			c.put(fileName, ".");
			c.exit();
			channel1.disconnect();
			
			/**
			 * creates an ssh session to run 
			 * shell commands on the remote server
			 */
			Channel channel = session.openChannel("shell");
			System.out.println("shell channel connected....");
			OutputStream ops = channel.getOutputStream();
			PrintStream ps = new PrintStream(ops, true);
			String command0 = "sudo chmod -R a+w data";
			String command1 = "mv "+fileName+" data";
			channel.connect();
			ps.println(command0 + " && " + command1);
			InputStream in = channel.getInputStream();
			byte[] tmp = new byte[1024];

			while(true){
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						channel.disconnect();
					System.out.print(new String(tmp, 0, i));
				}

				if (channel.isClosed()) {
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ee) {
			}
			outcome ="File Transfered";
			channel.disconnect();
			session.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outcome;
	}

	public String runExec(String user, String host, String privateKey, String fileName) {
		String message = "";

		try {
			/**
			 * adding the attributes to jsch to establish connection
			 */
			JSch jsch = new JSch();
			int port = 22;

			jsch.addIdentity(privateKey);
			JSch.setConfig("StrictHostKeyChecking", "no");

			Session session = jsch.getSession(user, host, port);
			session.connect();

			/**
			 * creates an sftp session to transfer the file to the root dir
			 */
			Channel channel1 = session.openChannel("sftp");
			channel1.setInputStream(System.in);
			channel1.setOutputStream(System.out);
			channel1.connect();

			ChannelSftp c = (ChannelSftp) channel1;

			c.put(fileName, ".");
			c.exit();
			channel1.disconnect();

			String command = "mv " + fileName + " data2";
			
			/**
			 * creates an ssh session to run 
			 * shell commands on the remote server
			 */

			Channel channel = session.openChannel("exec");

			((ChannelExec) channel).setPty(true);

			((ChannelExec) channel).setCommand("sudo -S -p '' " + command);

			InputStream in = channel.getInputStream();
			OutputStream out = channel.getOutputStream();
			PrintStream ps = new PrintStream(out, true);
			((ChannelExec) channel).setErrStream(System.err);
			ps.println("sudo -S -p '' " + command);

			channel.connect();
			
			/**
			 * interacts with the remote server console
			 */

			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));

				}
				if (channel.isClosed()) {
					message = "exit-status: " + channel.getExitStatus();
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
				System.out.println("te");
			}

			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;
	}


}
