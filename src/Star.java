import java.awt.Color;
import java.awt.Graphics2D;

public class Star {
    private Vector2D position;
    private Vector2D velocity;
    private double mass;
    private Color color;
    private int size;

    // Constructor
    public Star(Vector2D position, Vector2D velocity, double mass, Color color, int size) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.color = color;
        this.size = size;
    }

    // Method to update the star's position based on its velocity
    public void update(double dt) {
        position = position.addScaled(velocity, dt);
    }

    // Method to draw the star on the canvas
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) position.getX() - size / 2, (int) position.getY() - size / 2, size, size);
    }

    // Getters and setters
    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
