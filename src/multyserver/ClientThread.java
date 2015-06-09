package multyserver;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by Zornitsa Petkova on 6/8/15.
 */
public class ClientThread extends Thread {
  private Socket socket;
  private int br;
  public String s;
  public PrintWriter out;

  public ClientThread(Socket socket,int br){
    this.socket = socket;
    this.br = br;
  }

  public void run(){
    try{
      out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      out.flush();
      s="YOU ARE CLIENT " + br + " ! ";
      /*out.println("Hello");*/
      out.println(s);
      out.close();
      this.interrupt();
      System.out.println(s);
    }catch(IOException ioe){
      System.out.println(ioe.getMessage());
    }
  }
}
