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
                    System.out.println("11");
                    this.robot.keyPress(Integer.valueOf(reader.readLine()));
                    break;
                case "2":
                    System.out.println("22");
                    this.robot.keyRelease(Integer.valueOf(reader.readLine()));
                    break;
                case "3":
                    System.out.println("33");
                    this.robot.mouseMove((Integer.valueOf(reader.readLine())),Integer.valueOf(reader.readLine()));
                    break;
                case "4":
                    System.out.println("44");
                    this.robot.mousePress(Integer.valueOf(reader.readLine()));
                    break;
                case "5":
                    System.out.println("55");
                    this.robot.mouseRelease(Integer.valueOf(reader.readLine()));
                    break;
                default:
                System.out.println("don't here!!!!!!!!!!!!!");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
}
