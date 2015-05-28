package examples.exaples.socket.socketServer;

import java.io.Serializable;

/**
 * Created by Zornitsa Petkova on 5/26/15.
 */
public class ObjectToTransfer implements Serializable {
  public int a;
  public int b;
  public ObjectToTransfer(int a, int b){
    this.a = a;
    this.b = b;
  }
}
