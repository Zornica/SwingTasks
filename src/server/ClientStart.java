package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 5/29/15.
 */
public class ClientStart {
  String s;

  public void start() throws IOException {
    Socket connection = new Socket("localhost", 1099);
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    s = in.readLine();
    System.out.println(s);
    in.close();
    connection.close();
  }

  public static void main(String[] args)throws IOException{
    ClientStart client = new ClientStart();
    client.start();
  }
}
