//package chess;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//
//public class GUI extends JPanel implements MouseListener, MouseMotionListener, Runnable
//{
//    public static final int WIDTH = 600, HEIGHT = 600;
//    public static int mouseX, mouseY;
//    private Thread thread;
//    private final int FPS = 30;
//    private long targetTime = 1000/FPS;
//    private boolean isRunning = false;
//    private Graphics2D g2d;
//
//    public GUI()
//    {
//        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setFocusable(true);
//        addMouseListener(this);
//        addMouseMotionListener(this);
//
//        start();
//    }
//
//    private void start()
//    {
//        isRunning = true;
//        thread = new Thread(this, "game loop");
//        thread.start();
//
//    }
//
//    private void stop()
//    {
//        isRunning = false;
//
//        try
//        {
//            thread.join();
//        }
//        catch(InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void run()
//    {
//        long start, elapsed, wait;
//        while (isRunning)
//        {
//            start = System.currentTimeMillis();
//
//            update();
//            repaint();
//
//
//            elapsed = System.currentTimeMillis() - start;
//            wait = targetTime - elapsed;
//            if (wait < 5) {wait = 5;}
//
//            try
//            {
//                thread.sleep(wait);
//            }
//            catch(InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//
//        }
//        stop();
//    }
//
//    public void update()
//    {
//        System.out.println("Updating");
//    }
//
//    public void paintComponent(Graphics graphic)
//    {
//        g2d = (Graphics2D) graphic;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//
//        g2d.dispose();
//    }
//
//    public static void main(String[] args)
//    {
//        JFrame frame = new JFrame("Final Project: Chess");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setLayout(new BorderLayout());
//        frame.add(new GUI(), BorderLayout.CENTER);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//    public void mouseClicked(MouseEvent e)
//    {
//
//    }
//
//    public void mouseDragged(MouseEvent e)
//    {
//        setMousePosition(e);
//    }
//
//    public void mouseMoved(MouseEvent e)
//    {
//        setMousePosition(e);
//    }
//
//    private void setMousePosition(MouseEvent e)
//    {
//        mouseX = e.getX();
//        mouseY = e.getY();
//
//    }
//
//    //unused methods
//
//    public void mousePressed(MouseEvent e) {}
//
//    public void mouseReleased(MouseEvent e) {}
//
//    public void mouseEntered(MouseEvent e) {}
//
//    public void mouseExited(MouseEvent e) {}
//}
