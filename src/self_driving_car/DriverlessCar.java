package self_driving_car;


import java.util.Random;
import java.util.Scanner;

public class DriverlessCar {

    private boolean isRunning;
    private int speed;
    private int direction; // 0 = straight, -1 = left, 1 = right
    private final Random random;
    private final Scanner scanner;

    public DriverlessCar() {
        this.isRunning = false;
        this.speed = 0;
        this.direction = 0;
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        isRunning = true;
        System.out.println("Car started.");
    }

    public void stop() {
        isRunning = false;
        speed = 0;
        System.out.println("Car stopped.");
    }

    public void accelerate(int increase) {
        if (isRunning) {
            speed += increase;
            System.out.println("Accelerated to " + speed + " km/h.");
        } else {
            System.out.println("Start the car first.");
        }
    }

    public void brake(int decrease) {
        if (isRunning) {
            speed = Math.max(0, speed - decrease);
            System.out.println("Decelerated to " + speed + " km/h.");
        } else {
            System.out.println("Start the car first.");
        }
    }

    public void steer(int direction) {
        if (isRunning) {
            this.direction = direction;
            String steerDirection = (direction == 0) ? "straight" : (direction == -1) ? "left" : "right";
            System.out.println("Steering " + steerDirection + ".");
        } else {
            System.out.println("Start the car first.");
        }
    }

    public void detectObstacle() {
        boolean obstacleDetected = random.nextBoolean(); // Simulate obstacle detection
        if (obstacleDetected) {
            System.out.println("Obstacle detected! Slowing down.");
            brake(10);
        } else {
            System.out.println("No obstacles detected.");
        }
    }

    public void changeLane() {
        if (isRunning) {
            System.out.println("Changing lane...");
            steer(random.nextBoolean() ? -1 : 1); // Randomly change lane
        } else {
            System.out.println("Start the car first.");
        }
    }

    public void navigateTo(String destination) {
        if (isRunning) {
            System.out.println("Navigating to " + destination + "...");
            // Simulated navigation logic could go here
        } else {
            System.out.println("Start the car first.");
        }
    }

    public void park() {
        if (isRunning) {
            stop();
            System.out.println("Car parked.");
        } else {
            System.out.println("Car is already stopped.");
        }
    }

    public void displayStatus() {
        System.out.println("Car status:");
        System.out.println("Running: " + isRunning);
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Direction: " + (direction == 0 ? "Straight" : (direction == -1 ? "Left" : "Right")));
    }

    public void userInput() {
        String command;
        do {
            System.out.println("\nEnter command (start, stop, accelerate, brake, steer, detect, change, navigate, park, status, exit):");
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "start":
                    start();
                    break;
                case "stop":
                    stop();
                    break;
                case "accelerate":
                    System.out.print("Enter speed increase: ");
                    int increase = Integer.parseInt(scanner.nextLine());
                    accelerate(increase);
                    break;
                case "brake":
                    System.out.print("Enter speed decrease: ");
                    int decrease = Integer.parseInt(scanner.nextLine());
                    brake(decrease);
                    break;
                case "steer":
                    System.out.print("Enter direction (-1 = left, 0 = straight, 1 = right): ");
                    int direction = Integer.parseInt(scanner.nextLine());
                    steer(direction);
                    break;
                case "detect":
                    detectObstacle();
                    break;
                case "change":
                    changeLane();
                    break;
                case "navigate":
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();
                    navigateTo(destination);
                    break;
                case "park":
                    park();
                    break;
                case "status":
                    displayStatus();
                    break;
                case "exit":
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (!command.equalsIgnoreCase("exit"));
        scanner.close();
    }

    public static void main(String[] args) {
        DriverlessCar car = new DriverlessCar();
        car.userInput();
    }
}

