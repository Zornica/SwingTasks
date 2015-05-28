package examples.exaples.socket.socketServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 5/27/15.
 */
public class Client {
  public static void main(String[] args)throws IOException,ClassNotFoundException{
    ObjectToTransfer a;
    Socket connection = new Socket("localhost",1099);
    ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
    a=(ObjectToTransfer)input.readObject();
    System.out.println(a);
  }
}
