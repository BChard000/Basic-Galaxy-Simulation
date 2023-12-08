import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Galaxy {
    private List<Star> stars;   // List to store all the stars in the galaxy
    private Star nucleus;   // The central star or nucleus of the galaxy
    private final double G; // Gravitational constant
    private final Vector2D center;  // The center point of the galaxy
    private final int numStars;
    private final double massNucleus;
    private final int radiusNucleus;
    private final double maxRadius; // Maximum radius of the galaxy
    private final double massStar;  // Mass of each star
    private final double veloMag;   // Magnitude of velocity for stars
    private final double constDark; // Constant related to dark matter
    private final int rmax; // Maximum radius for mass calculations
    private final double A = 1; // A constant used in mass calculations
    private final double alpha = 0.5;   // Alpha constant used in mass calculations
    private List<Double> massStars; // List to store mass distribution of stars
    private List<Double> massDark;  // List to store mass distribution of dark matter

    // Constructor to initialize the galaxy with specified parameters
    public Galaxy(Vector2D center, int numStars, double massNucleus, int radiusNucleus, double maxRadius, double massStar, double veloMag, double G, double constDark, int rmax) {
        this.center = center;
        this.numStars = numStars;
        this.massNucleus = massNucleus;
        this.radiusNucleus = radiusNucleus;
        this.maxRadius = maxRadius;
        this.massStar = massStar;
        this.veloMag = veloMag;
        this.G = G;
        this.constDark = constDark;
        this.rmax = rmax;
        initializeGalaxy();
        calculateMassDistribution();
    }

    // Initializes the galaxy by creating stars and placing them in the galaxy
    private void initializeGalaxy() {
        stars = new ArrayList<>();
        nucleus = new Star(center, new Vector2D(0, 0), massNucleus, Color.GRAY, radiusNucleus);

        for (int i = 0; i < numStars; i++) {
            double radius = radiusNucleus + (maxRadius - radiusNucleus) * Math.random();
            double angle = 2 * Math.PI * Math.random();
            Vector2D position = new Vector2D(center.getX() + radius * Math.cos(angle), center.getY() + radius * Math.sin(angle));
            Vector2D velocity = new Vector2D(veloMag * Math.sin(angle), -veloMag * Math.cos(angle));
            stars.add(new Star(position, velocity, massStar, Color.YELLOW, 2));
        }
    }

    // Calculates the mass distribution of stars and dark matter in the galaxy
    private void calculateMassDistribution() {
        massStars = new ArrayList<>(rmax);
        massDark = new ArrayList<>(rmax);

        for (int i = 0; i < rmax; i++) {
            double starMass = calculateCumulativeStarMass(i);
            double darkMass = constDark * Math.pow(i, alpha) * Math.exp(-A * i);
            massStars.add(starMass);
            massDark.add(darkMass);
        }
    }

    // Calculates the cumulative mass of stars within a given radius
    private double calculateCumulativeStarMass(int radius) {
        double totalMass = 0;
        for (Star star : stars) {
            if (star.getPosition().subtract(center).length() <= radius) {
                totalMass += star.getMass();
            }
        }
        return totalMass;
    }

    // Updates the state of each star in the galaxy based on gravitational forces
    public void update(double dt) {
        for (Star star : stars) {
            double radius = star.getPosition().subtract(center).length();
            double massWithinRadius = calcMass(radius); // Calculate the mass within the radius

            Vector2D force = calculateGravitationalForce(star, massWithinRadius);
            Vector2D acceleration = force.multiply(1 / star.getMass());
            star.setVelocity(star.getVelocity().addScaled(acceleration, dt));
            star.update(dt);
        }
    }

    // Calculates the gravitational force exerted on a star
    private Vector2D calculateGravitationalForce(Star star, double massWithinRadius) {
        Vector2D dist = star.getPosition().subtract(center);
        if (dist.length() < radiusNucleus) {
            return new Vector2D(0, 0);
        } else {
            return Forces.gravity(G, massNucleus + massWithinRadius, star.getMass(), dist);
        }
    }

    // Calculates the total mass (stars + dark matter) within a certain radius
    private double calcMass(double radius) {
        double starMass = 0;
        double darkMatterMass = 0;

        if (radius < rmax) {
            starMass = massStars.get((int) radius);
            darkMatterMass = massDark.get((int) radius);
        }

        return starMass + darkMatterMass;
    }

    // Method to draw the galaxy on a given Graphics2D context
    public void draw(Graphics2D g) {
        nucleus.draw(g);
        for (Star star : stars) {
            star.draw(g);
        }
    }
}

