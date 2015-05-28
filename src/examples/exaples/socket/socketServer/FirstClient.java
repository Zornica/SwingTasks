package examples.exaples.socket.socketServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 5/26/15.
 */
public class FirstClient {
  public static void main(String[] args) throws IOException{
    String server = null;
    InetAddress addr =  InetAddress.getByName(server);
    System.out.println("addr = " + addr);
    Socket socket = new Socket(addr, FirstSever.PORT);
    try {
      System.out.println("socket = " + socket);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedReader sin =  new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
      for(int i = 0; i <10 ; i ++) {
        System.out.print("input a line [empty for finish] " + i + ":");
        String s = sin.readLine();
        if(s.length()==0) break;
        out.println(i + ": " +s);
        String str = in.readLine();
        System.out.println(str);
      }
      out.println("END");
    }
    finally {
      System.out.println("closing...");
      socket.close();
    }
  }
}
