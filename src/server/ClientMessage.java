package server;

/**
 * Created by Zornitsa Petkova on 6/16/15.
 */
public interface ClientMessage {
  String connect();

  String read();

  String print();

  String closeClient();
}
