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
  public String message;
  private int port;
  private ServerSocket server = null;
  public int count =1;
  private MessageListener messageListener;

  public FakeServer(MessageListener messageListener,String message,int port){
    this.messageListener=messageListener;
    this.message = message;
    this.port=port;
  }

  public void start(){
    Socket client=null;
    try{
      server = new ServerSocket(port);
      while(true){
        client=server.accept();
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
        out.println(messageListener.newMessage(message + count));
        count ++;
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
