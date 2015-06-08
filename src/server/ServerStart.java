package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Zornitsa Petkova on 5/29/15.
 */
public class ServerStart {
  Socket connection;

  public void start() throws IOException {
    ServerSocket server = new ServerSocket(1099);
    connection = server.accept();
    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
    out.flush();
    out.write("Hello!" + new Date());
    out.close();
  }

  public static void main(String[] args)throws IOException{
    ServerStart server = new ServerStart();
    server.start();
  }
}
