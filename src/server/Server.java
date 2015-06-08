package server;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class Server extends JFrame implements ActionListener {
  PanelServer panel;
  ServerStart server;


  public Server() throws IOException {
    setSize(200, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Server");
    panel = new PanelServer();
    server = new ServerStart();

    add(panel);
    setVisible(true);

    server.start();

    panel.button.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
try{
server.connection.close();
    System.exit(0);
}catch (IOException ex){

}

  }

  public static void main(String[] args) throws IOException {
    Server server = new Server();
  }
}
