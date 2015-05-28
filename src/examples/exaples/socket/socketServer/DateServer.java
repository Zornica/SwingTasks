package examples.exaples.socket.socketServer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Zornitsa Petkova on 5/26/15.
 */
public class DateServer {
  public static void main(String[] args)throws IOException{
    ServerSocket serverSocket = new ServerSocket(2002);
    while(true){
      Socket socket = serverSocket.accept();
      OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
      out.write(new Date() +"n");
      out.close();
      socket.close();
    }
  }
}
