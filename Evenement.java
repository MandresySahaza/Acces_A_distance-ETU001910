package client;

import java.io.*;
import java.net.Socket;
import java.awt.Robot;

public class Evenement extends Thread {
    
    Socket client;
    BufferedReader reader;
    Robot robot;
    boolean test;
   
    
    public Evenement(Socket client, Robot robot) {
        this.client = client;
        this.robot = robot;
        this.test = true;
        start();
    }
    
    @Override
    public void run() {
        super.run();
        try {
            this.reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            while(test == true){
                String com=reader.readLine();
                System.out.println(com+"  ,com");
            switch(com){
                case "1":
                    this.robot.keyPress(Integer.valueOf(reader.readLine()));
                    break;
                case "2":
                    this.robot.keyRelease(Integer.valueOf(reader.readLine()));
                    break;
                case "3":
                    this.robot.mouseMove((Integer.valueOf(reader.readLine())),Integer.valueOf(reader.readLine()));
                    break;
                case "4":
                    this.robot.mousePress(Integer.valueOf(reader.readLine()));
                    break;
                case "5":
                    this.robot.mouseRelease(Integer.valueOf(reader.readLine()));
                    break;
                default:
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
}
