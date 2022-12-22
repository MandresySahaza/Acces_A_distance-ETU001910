package client;

import java.awt.*;
import java.nio.ByteBuffer;
import java.io.*;
import java.util.Vector;
import java.net.Socket;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


public class ScreenClient extends Thread{
    
    double width;
    double height;
    boolean test;
    Socket client;
    Robot robot;
    Dimension dimension;
    
    
    public ScreenClient(Socket client) throws Exception {
        
        GraphicsEnvironment grEnvironnement = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grDevevise = grEnvironnement.getDefaultScreenDevice();
        this.client = client;
        this.robot = new Robot(grDevevise);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.dimension = dimension;
        this.width = dimension.getWidth();
        this.height = dimension.getHeight();
        this.test = true;
            start();
    
    }
    
    @Override
    public void run() {
        super.run();
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(this.client.getOutputStream());
            dataOutputStream.writeDouble(this.width);
            dataOutputStream.writeDouble(this.height);
            while(test == true){
                BufferedImage img = this.robot.createScreenCapture(new Rectangle(this.dimension));
                try {
                    ByteArrayOutputStream outs = new ByteArrayOutputStream();
                    ImageIO.write(img, "png", outs);
                    byte[] len = ByteBuffer.allocate(4).putInt(outs.size()).array();
                    dataOutputStream.write(len);
                    dataOutputStream.write(outs.toByteArray());
                    dataOutputStream.flush();
                    Evenement evenement = new Evenement(this.client,this.robot); 
                } catch (Exception e) {
                    e.printStackTrace();
                }  
                  
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
