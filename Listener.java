package listener;

import javax.swing.JPanel;
import java.awt.event.*;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;


public class Listener implements  MouseListener, KeyListener , MouseMotionListener{
    
    PrintWriter writer;
    Socket client;
    JPanel panel;
    double widths;
    double heights;
    
    public Listener(Socket client, JPanel panel, double w, double h) {
        this.client = client;
        this.panel=panel;
        this.widths=w;
        this.heights=h;
        try {
            writer=new PrintWriter(client.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.panel.addKeyListener(this);
        this.panel.addMouseListener(this);
        this.panel.addMouseMotionListener(this);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        this.panel.requestFocus();
        this.panel.setFocusable(true);
        System.out.print("keyPressed:"+e.getKeyCode());
        writer.println("1");
        writer.println(e.getKeyCode());
        writer.flush();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.panel.requestFocus();
        this.panel.setFocusable(true);
        System.out.print("keyReleased:"+e.getKeyCode());
        writer.println("2");
        writer.println(e.getKeyCode());
        writer.flush();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.panel.requestFocus();
        this.panel.setFocusable(true);
        System.out.print("x");
        writer.println("3");
        double er=this.widths/this.panel.getWidth();
        double ez=this.heights/this.panel.getHeight();
        int eee=(int)(e.getX()*er);
        String ee=(eee)+"";

        int ddd=(int)(e.getY()*ez);
        String dd=(ddd)+"";
        writer.println(Integer.valueOf(ee));
        writer.println(Integer.valueOf(dd));
        writer.flush();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.panel.requestFocus();
        this.panel.setFocusable(true);
        System.out.print("mousePressed,"+e.getButton()+"    ");
        writer.println("4");
        if(e.getButton()==3){
            writer.println(4);
        }
        else{
            writer.println(16);
        }
        writer.flush();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.panel.requestFocus();
        this.panel.setFocusable(true);
        System.out.print("mouseReleased,"+e.getButton()+"    ");
        writer.println("5");
        if(e.getButton()==3){
            writer.println(4);
        }
        else{
            writer.println(16);
        }
        writer.flush();
    }
    
}