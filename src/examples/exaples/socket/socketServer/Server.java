package examples.exaples.socket.socketServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 5/27/15.
 */
public class Server {
  public static void main(String[] args)throws IOException{
    while(true){
      ObjectToTransfer testObject = new ObjectToTransfer(5,8);
      ServerSocket server = new ServerSocket(1099);
      Socket client = server.accept();
      ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
      output.flush();
      output.writeObject(testObject);
      output.close();
      client.close();
    }
  }
}
