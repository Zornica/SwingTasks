package multyserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by Zornitsa Petkova on 6/8/15.
 */
public class ClientThread extends Thread {
  private Socket socket;
  private int br;
  public String s;

  public ClientThread(Socket socket,int br){
    this.socket = socket;
    this.br = br;
  }

  public void run(){
    try{
      PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      out.flush();
      s="YOU ARE CLIENT " + br + " ! ";
      out.write(s);
      out.close();
      this.interrupt();
      System.out.println(s);
    }catch(IOException ioe){
      System.out.println(ioe.getMessage());
    }
  }
}
