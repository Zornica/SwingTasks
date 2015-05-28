package examples.exaples.socket.socketServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 5/26/15.
 */
public class FirstSever {
  public static final int PORT=1029;

  public static void main(String[] args)

  throws IOException{
    ServerSocket s = new ServerSocket(PORT);
    System.out.println("Started: " + s);
    try {
      Socket socket = s.accept();
      try {
        System.out.println("Connection accepted: "+ socket);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        while (true) {
          String str = in.readLine();
          if (str.equals("END")) break;
          System.out.println("Echoing: " + str);
          out.println(str);
        }
      } finally {
        System.out.println("closing...");
        socket.close();
      }
    } finally {
      s.close();
    }
}
}
