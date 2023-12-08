import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalaxySimulation extends JFrame {
    private final GalaxyPanel panel;
    private final Galaxy galaxy;

    public GalaxySimulation() {
        setTitle("Galaxy Simulation");
        setSize(900, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Initialize the galaxy
        Vector2D center = new Vector2D(getWidth() / 2.0, getHeight() / 2.0);
        galaxy = new Galaxy(center, 10000, 100000, 20, 300, 10, 20, 3, 1000, 500);

        panel = new GalaxyPanel();
        add(panel);

        // Set up a timer to refresh the panel at regular intervals
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                galaxy.update(0.02); // Update the galaxy state
                panel.repaint();     // Redraw the galaxy
            }
        });
        timer.start();
    }

    private class GalaxyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            setBackground(Color.BLACK);

            galaxy.draw((Graphics2D) g);
        }
    }
}
