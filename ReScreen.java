package server;


import java.awt.*;
import java.io.DataInputStream;
import java.net.Socket;
import javax.swing.*;
import listener.*;

public class ReScreen extends Thread{
    
    double width;
    JPanel panel;
    Socket client;
    double height; 
 
    public ReScreen(Socket client) {
        this.client = client;
        start();
    }
    
    public void Interface(){
        JFrame frame = new JFrame("Screen");
        this.panel = new JPanel();
        frame.add(this.panel);
        frame.setVisible(true);
    }

    
    @Override
    public void run() {
        super.run();
        Interface();
        try {
            
            DataInputStream data = new DataInputStream(client.getInputStream());
            this.width = data.readDouble();
            this.height = data.readDouble();
            System.out.println(this.width+"  "+this.height);
            GetClient getClient = new GetClient(this.client,this.panel);
        
        } catch (Exception e) {
            e.printStackTrace();
        } 

        Listener listener = new Listener(this.client,this.panel,this.width,this.height);
    }
}
