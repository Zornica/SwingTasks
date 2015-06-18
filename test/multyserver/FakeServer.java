package multyserver;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 6/18/15.
 */
public class FakeServer {
  private String message;
  private int port;
  private ServerSocket server = null;

  public FakeServer(String message,int port){
    this.message = message;
    this.port=port;
  }

  public void start(){
    Socket client=null;
    try{
      server = new ServerSocket(port);
      while(true){
        client=server.accept();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.println(message);
      }
    }catch (IOException ioe){
      ioe.getStackTrace();
    }finally{
      try{
        if(client != null){
          client.close();
        }
        stop();
      }catch (IOException ioe){
        ioe.getStackTrace();
      }
    }
  }

  public void stop(){
    try{
      if(server !=null){
        server.close();
      }
    }catch (IOException ioe){
      ioe.getStackTrace();
    }
  }
}
