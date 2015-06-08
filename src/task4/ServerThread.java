package task4;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 6/5/15.
 */
public class ServerThread extends Thread {
  private Socket socket = null;
  private Server server = null;
  private int ID=-1;
  private DataInputStream streamIn=null;

  public ServerThread(Server server, Socket socket){
    this.server = server;
    this.socket = socket;
    ID = socket.getPort();
  }

  public void run(){
    System.out.println("Server Thread "+ID + " running.");
    while(true){
      try{
        System.out.println(streamIn.readUTF());
      }catch(IOException ioe){
        ioe.getMessage();
      }
    }
  }

  public void open() throws IOException{
    streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }
  public void close() throws IOException{
    if(socket !=null) socket.close();
    if(streamIn != null)streamIn.close();
  }
}
