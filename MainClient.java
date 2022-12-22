package client;

import java.util.Vector;
import javax.swing.JOptionPane;
import java.net.Socket;
import java.io.*;
import java.awt.*;

public class MainClient {

    public MainClient(){}
    public static void main(String[] args) throws Exception {
        try {
            String host = null;
            host = JOptionPane.showInputDialog("entre le numero de l'host");
            System.out.println(host);
            Socket client = new Socket(host, 1910);
            ScreenClient screenClient = new ScreenClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
                                                                                                                 
    }
}
