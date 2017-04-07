/*
 * 使用线程类，可以实现多个Client同时访问Server端
 * */

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
	public static void main(String[] args) {
		new ChatServer().start();  //如果将start方法中代码全部放到main方法中，会报错。因为main方法时一个静态方法，不能在其内部new一个非静态的内部类
	}
	
	public void start() {
		boolean started = false;
		ServerSocket ss = null;
		Socket s = null;
		int NUM = 0;  //记录线程个数
		try {
			ss = new ServerSocket(9989);
			started = true; // 有客户端连接到端口9989时，started置为true
			while (started) {
				s = ss.accept();				
				NUM ++;   
				System.out.println("Client NO." + NUM + " Connect!");
				Client cRunner = new Client(s);
				Thread cThread = new Thread(cRunner,"线程"+ Integer.toString(NUM)); //线程名称=num
				cThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				ss.close();  //关闭整个ServerScoket
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	
	//内部类
	class Client implements Runnable {  //定义线程类，并作为Server的内部类
		Socket s = null;
		DataInputStream dis = null;
		boolean cConnected = false;
		
		public Client(Socket s) {  //构造方法用来拿到当前的Socket s
			this.s = s;
//			dis = new DataInputStream(s.getInputStream()); // 输入流
//			cConnected = true;
		}
		
		@Override
		public void run() {
			try {
				dis = new DataInputStream(s.getInputStream()); // 输入流，可以将这步放到构造方法中
				cConnected = true;				
				while(cConnected){  //通过循环可以接收到客户端发过来的多次信息。没有这个，那么服务器端只能接受一次客户端发送的信息
					String str = dis.readUTF();
					System.out.println(str); // 打印Client端发送的信息
				}				
			} catch (EOFException eof) { // 客户端关闭会引起的EOFException
				System.out.println("Client端连接失败！");
			} catch (Exception e1) { // 捕获到任何异常都进行处理
				e1.printStackTrace();
			} finally {
				try {
					if (dis != null)
						dis.close(); // dis为null,说明dis还没有初始化，所以没有必要close
					if (s != null)
						s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}