public class Vector2D {
    private double x;
    private double y;

    // Constructor
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Method to calculate the squared length of the vector
    public double lengthSquared() {
        return x * x + y * y;
    }

    // Method to calculate the length of the vector
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    // Method to clone the vector
    public Vector2D clone() {
        return new Vector2D(x, y);
    }

    // Method to negate the vector
    public void negate() {
        x = -x;
        y = -y;
    }

    // Method to convert the vector to a unit vector
    public Vector2D unit() {
        double length = length();
        if (length > 0) {
            return new Vector2D(x / length, y / length);
        } else {
            return new Vector2D(0, 0);
        }
    }

    // Method to normalize the vector (modifies the vector itself)
    public void normalize() {
        double length = length();
        if (length > 0) {
            x /= length;
            y /= length;
        }
    }

    // Method to add another vector to this vector
    public Vector2D add(Vector2D vec) {
        return new Vector2D(x + vec.x, y + vec.y);
    }

    // Method to subtract another vector from this vector
    public Vector2D subtract(Vector2D vec) {
        return new Vector2D(x - vec.x, y - vec.y);
    }

    // Method to multiply the vector by a scalar
    public Vector2D multiply(double k) {
        return new Vector2D(k * x, k * y);
    }

    // Method to add a scaled vector to this vector
    public Vector2D addScaled(Vector2D vec, double k) {
        return new Vector2D(x + k * vec.x, y + k * vec.y);
    }

    // Method to scale the vector by a scalar (modifies the vector itself)
    public void scaleBy(double k) {
        x *= k;
        y *= k;
    }

    // Method to calculate the dot product with another vector
    public double dotProduct(Vector2D vec) {
        return x * vec.x + y * vec.y;
    }

    // Method to calculate the projection of this vector onto another vector
    public double projection(Vector2D vec) {
        double length = length();
        double lengthVec = vec.length();
        if (length == 0 || lengthVec == 0) {
            return 0;
        } else {
            return (x * vec.x + y * vec.y) / lengthVec;
        }
    }

    // Method to project this vector onto another vector
    public Vector2D project(Vector2D vec) {
        return vec.para(projection(vec));
    }

    // Method to calculate the parallel component of the vector
    public Vector2D para(double u) {
        return unit().multiply(u);
    }

    // Method to calculate the perpendicular component of the vector
    public Vector2D perp(double u) {
        return new Vector2D(-y, x).unit().multiply(u);
    }

    // Getters and setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" + "x=" + x + ", y=" + y + '}';
    }
}
