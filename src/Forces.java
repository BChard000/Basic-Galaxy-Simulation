public class Forces {
    public static Vector2D zeroForce() {
        return new Vector2D(0, 0);
    }

    // Static method for constant gravity force
    public static Vector2D constantGravity(double m, double g) {
        return new Vector2D(0, m * g);
    }

    // Static method for gravitational force
    public static Vector2D gravity(double G, double m1, double m2, Vector2D r) {
        return r.multiply(-G * m1 * m2 / (Math.pow(r.length(), 3)));
    }

    // Static method for modified gravitational force with softening factor 'eps'
    public static Vector2D gravityModified(double G, double m1, double m2, Vector2D r, double eps) {
        return r.multiply(-G * m1 * m2 / (Math.pow(r.lengthSquared() + eps * eps, 1.5)));
    }

    // Static method for electric force
    public static Vector2D electric(double k, double q1, double q2, Vector2D r) {
        return r.multiply(k * q1 * q2 / (Math.pow(r.length(), 3)));
    }

    // Static method for force in a uniform electric field
    public static Vector2D forceField(double q, Vector2D E) {
        return E.multiply(q);
    }

    // Static method for Lorentz force
    public static Vector2D lorentz(double q, Vector2D E, double B, Vector2D vel) {
        return E.multiply(q).add(vel.perp(q * B * vel.length()));
    }

    // Static method for central force
    public static Vector2D central(double k, double n, Vector2D r) {
        return r.multiply(k * Math.pow(r.length(), n - 1));
    }

    // Static method for linear drag force
    public static Vector2D linearDrag(double k, Vector2D vel) {
        double velMag = vel.length();
        if (velMag > 0) {
            return vel.multiply(-k);
        } else {
            return new Vector2D(0, 0);
        }
    }

    // Static method for quadratic drag force
    public static Vector2D drag(double k, Vector2D vel) {
        double velMag = vel.length();
        if (velMag > 0) {
            return vel.multiply(-k * velMag);
        } else {
            return new Vector2D(0, 0);
        }
    }

    // Static method for lift force
    public static Vector2D lift(double k, Vector2D vel) {
        double velMag = vel.length();
        if (velMag > 0) {
            return vel.perp(k * velMag);
        } else {
            return new Vector2D(0, 0);
        }
    }

    // Static method for upthrust force
    public static Vector2D upthrust(double rho, double V, double g) {
        return new Vector2D(0, -rho * V * g);
    }

    // Static method for vortex force
    public static Vector2D vortex(double k, double r0, Vector2D r) {
        double rMag = r.length();
        if (rMag > 0) {
            if (rMag > r0) {
                return r.multiply(-k * Math.pow(r0 / rMag, 4));
            } else {
                return r.multiply(k);
            }
        } else {
            return new Vector2D(0, 0);
        }
    }

    // Static method for spring force
    public static Vector2D spring(double k, Vector2D r) {
        return r.multiply(-k);
    }

    // Static method for damping force
    public static Vector2D damping(double c, Vector2D vel) {
        double velMag = vel.length();
        if (velMag > 0) {
            return vel.multiply(-c);
        } else {
            return new Vector2D(0, 0);
        }
    }

    // Static method for adding multiple forces
    public static Vector2D add(Vector2D[] arr) {
        Vector2D forceSum = new Vector2D(0, 0);
        for (Vector2D force : arr) {
            forceSum = forceSum.add(force);
        }
        return forceSum;
    }
}
