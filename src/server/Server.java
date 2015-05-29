package server;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class Server extends JFrame implements ActionListener {
  PanelServer panel;
  Socket connection;

  public Server() throws IOException {
    setSize(200, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Server");
    panel = new PanelServer();

    add(panel);
    setVisible(true);

    ServerSocket server = new ServerSocket(1099);
    connection = server.accept();
    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
    out.flush();
    out.write("Hello!" + new Date());
    out.close();
    connection.close();

    panel.button.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {

      System.exit(0);

 }

  public static void main(String[] args) throws IOException {
    Server server = new Server();
  }
}
