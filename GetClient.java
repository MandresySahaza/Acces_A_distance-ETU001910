package server;

import java.awt.*;
import javax.swing.JPanel;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GetClient extends Thread {
    
    Socket client;
    boolean test;
    JPanel panel;
   
    public GetClient (Socket client,JPanel panel) {
        this.client = client;
        this.test = true;
        this.panel = panel;
        start();
    }
    @Override
    public void run() {
        
        super.run();
        DataInputStream dataInputStream = null;
        
        try {
            dataInputStream = new DataInputStream(this.client.getInputStream());
        } catch (Exception e) {
           e.printStackTrace();
        }

        while(test == true){
           try {
                byte[] sizetotal = new byte[4];
                dataInputStream.readFully(sizetotal);

                int taille = ByteBuffer.wrap(sizetotal).asIntBuffer().get();
                byte [] bytefinal = new byte[taille];
                int nowInRead = 0;
                int addtein = 0;


                while(nowInRead < taille && (addtein = dataInputStream.read(bytefinal, nowInRead, taille-nowInRead)) > 0){
                    nowInRead+=addtein;
                }


                Image image = ImageIO.read(new ByteArrayInputStream(bytefinal));
                Graphics2D graphics2d = (Graphics2D)this.panel.getGraphics();
                graphics2d.drawImage(image,0,0,this.panel.getWidth(),this.panel.getHeight(),this.panel);
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
