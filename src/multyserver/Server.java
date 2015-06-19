package multyserver;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class Server {
  public int count = 1;
  private ServerMessage serverMessage;
  private MessageListener messageListener;
  private int port;
  private ServerSocket server;
  private ArrayList<Socket> list = new ArrayList<Socket>();
  private PrintWriter out;


  public Server(ServerMessage serverMessage, MessageListener messageListener, int port) {
    this.serverMessage = serverMessage;
    this.messageListener = messageListener;
    this.port = port;
  }

  public void start() {
    Socket client = null;
    try{
    server = new ServerSocket(port);
      while(true){
        client = server.accept();
        out=new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.println("amamamamama");
        out.println(serverMessage.connect(count));
        sendToAll(list, count);
        list.add(client);
        messageListener.newMessage(serverMessage.connectClient(count));
        count++;
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    } finally {
      if (client != null) {
        try {
          client.close();
        } catch (IOException ioe) {
          ioe.getStackTrace();
        }
      }
    }
  }

  public void sendToAll(List<Socket> list,int count){
    for(Socket l:list){
      out.write(serverMessage.sendToAll(count));
    }
  }
  public void stop() {
    try {
      if (server != null) {
        server.close();
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }
}
