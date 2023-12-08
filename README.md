# Galaxy Simulation
Galaxy Simulation is a Java-based application that visualizes the dynamic interactions and movements of stars within a galaxy. This project aims to provide an educational tool for understanding astrophysical phenomena, highlighting the effects of gravitational forces, and the influence of dark matter.

# Features
- Simulation of a galaxy with numerous stars.
- Detailed modeling of gravitational interactions between stars and a galactic nucleus.
- Representation of dark matter and its impact on galactic dynamics.
- Customizable parameters for different simulation scenarios.

# Architecture and Key Classes
- **Main**: Initializes and runs the Galaxy Simulation.
- **Vector2D**: Represents two-dimensional vectors, fundamental for physics calculations.
- **Star**: Models individual stars, including properties like position, velocity, and mass.
- **Forces**: Provides static methods for various force calculations, crucial for simulating physical interactions.
- **GalaxySimulation**: The main JFrame window, orchestrating the simulation's visualization and updates.
- **Galaxy**: Represents the entire galaxy, managing stars, the nucleus, and dark matter distribution.
