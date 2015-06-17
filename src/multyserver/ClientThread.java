package multyserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 6/17/15.
 */
public class ClientThread extends Thread {
  private List<Socket> list;
  private Socket client;
  private ServerMessage serverMessage;
  private ClientMessage clientMessage;
  private int count;
  private PrintWriter writer;

  public ClientThread(List<Socket> list,Socket client,ServerMessage serverMessage,ClientMessage clientMessage,int count){
    this.list = list;
    this.client = client;
    this.serverMessage = serverMessage;
    this.clientMessage = clientMessage;
    this.count = count;
  }

  public void run(){
    try{
      writer = new PrintWriter(client.getOutputStream());
      writer.println(serverMessage.connectClient(count));
      new Thread(new Runnable() {
        @Override
        public void run() {
          for(Socket clientList : list){
            try{
              writer = new PrintWriter(clientList.getOutputStream());
              writer.println(clientMessage.sendToAll(count));
            }catch (IOException ioe){
              ioe.getStackTrace();
            }
          }
          list.add(client);
        }
      }).start();
    }catch (IOException ioe){
      ioe.getStackTrace();
    }
  }
}
