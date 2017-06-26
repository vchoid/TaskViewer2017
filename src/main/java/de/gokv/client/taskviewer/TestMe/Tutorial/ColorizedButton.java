package de.gokv.client.taskviewer.TestMe.Tutorial;

/*
 * ColorizedButton.java
 */
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
/**
 * @author  Leif Samuelsson  
 * Swing Engineer, Sun Microsystems  
 * Santa Clara, CA, USA  
 * [url]http://forum.java.sun.com/thread.jspa?threadID=430926&start=15&tstart=0[/url]
 */

public class ColorizedButton extends JButton {
    private static ColorizeImageFilter imageFilter = new ColorizeImageFilter();
    
    private Color color;
    
    public ColorizedButton(String title, Color color) {
        super(title);
        this.color = color;
    }
    
    public void paintComponent(Graphics g) {
        boolean opaque = isOpaque();
        if (opaque) {
            // Fill background
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
        // Let the UI paint to offscreen image
        setOpaque(false);
        Image img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = img.getGraphics();
        g2.setFont(g.getFont());
        super.paintComponent(g2);
        
        // Colorize!
        img = imageFilter.colorize(this, img, color);
        
        // Paint offscreen image to button
        g.drawImage(img, 0, 0, null);
        setOpaque(opaque);
    }
    
    private static class ColorizeImageFilter extends RGBImageFilter {
        double cr, cg, cb;
        int bgRGB, fgRGB;
        
        public ColorizeImageFilter() {
            canFilterIndexColorModel = true;
        }
        
        public Image colorize(JComponent comp, Image fromImage, Color c) {
            cr = c.getRed()   / 255.0;
            cg = c.getGreen() / 255.0;
            cb = c.getBlue()  / 255.0;
            bgRGB = comp.getBackground().getRGB();
            fgRGB = comp.getForeground().getRGB();
            ImageProducer producer = new FilteredImageSource(fromImage.getSource(), this);
            return new ImageIcon(comp.createImage(producer)).getImage();
        }
        
        public int filterRGB(int x, int y, int rgb) {
            int alpha = rgb & 0xff000000;
            
            if (rgb == bgRGB || rgb == fgRGB || alpha < 0x80000000) {
                return rgb;
            }
            
            // Assume all rgb values are shades of gray
            double grayLevel = 2 * (rgb & 0xff) / 255.0;
            double r, g, b;
            
            if (grayLevel <= 1.0) {
                r = cr * grayLevel;
                g = cg * grayLevel;
                b = cb * grayLevel;
            } else {
                grayLevel -= 1.0;
                r = cr + (1.0 - cr) * grayLevel;
                g = cg + (1.0 - cg) * grayLevel;
                b = cb + (1.0 - cb) * grayLevel;
            }
            
            return (alpha +
                    (((int)(r * 255)) << 16) +
                    (((int)(g * 255)) << 8) +
                    (int)(b * 255));
        }
    }
    
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(new ColorizedButton("Red",   Color.red));
        cp.add(new ColorizedButton("Green", Color.green));
        cp.add(new ColorizedButton("Blue",  Color.blue));
        frame.setBounds(100, 100, 200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}