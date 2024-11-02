import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;

//class DrawVolume extends JPanel{
//    private int volume;
//}

class PaintUi extends JPanel implements MouseMotionListener, MouseListener{
    Point point1;
    Point point2;
    Line2D currentLine;
    private final ArrayList<Line2D> lines = new ArrayList<>();

    public PaintUi(){
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.5f));

        for(Line2D line: lines){
            g2d.draw(line);
        }

        if(point1 != null && point2 != null){
            g2d.draw(currentLine);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        point1 = e.getPoint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        point2 = e.getPoint();
        currentLine = new Line2D.Double(point1, point2);
        lines.add(currentLine);
        point1 = e.getPoint();
        point2 = null;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(currentLine != null){
            lines.add(currentLine);
            currentLine = null;
            point1 = null;
            point2 = null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }

}

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Line drawer");
            frame.setSize(500, 500);
            PaintUi paintUi = new PaintUi();
            frame.add(paintUi);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}