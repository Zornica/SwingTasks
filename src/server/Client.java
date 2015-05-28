package server;

import sun.management.resources.agent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Zornitsa Petkova on 5/28/15.
 */
public class Client extends JFrame implements ActionListener{
  PanelClient panel;
  public Client(){
    setSize(200, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Client");
    panel = new PanelClient();

    add(panel);
    setVisible(true);

 panel.text.addActionListener(this);

  }
  public void actionPerformed(ActionEvent e){
    try{
      Socket connection = new Socket("localhost",1099);
      panel.text.setText("The client connects with server!\n");
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      panel.text.setText(panel.text.getText()+"The client reads from server:\n");
      System.out.println(in.readLine());
      in.close();
      connection.close();
      panel.text.setText(panel.text.getText()+"The connection with server is closed!\n");
    }catch (IOException ex){
      System.out.println(ex.getMessage());
    }
  }
  public static void main(String[] args) throws IOException{
Client client = new Client();
  }
}
