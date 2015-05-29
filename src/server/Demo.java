package server;

import java.io.IOException;

/**
 * Created by Zornitsa Petkova on 5/29/15.
 */
public class Demo {
  public static void main(String[] args)throws IOException{
    ServerStart server = new ServerStart();
server.start();
    ClientStart client = new ClientStart();
client.start();
  }
}
