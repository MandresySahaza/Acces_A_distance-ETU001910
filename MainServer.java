package server;

import java.net.ServerSocket;
import java.io.*;
import java.util.Vector;
import java.net.Socket;

public class MainServer{

    public MainServer(){}

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(1910);
        Socket client = serverSocket.accept();
        ReScreen rsScreen = new ReScreen(client);
    }
}