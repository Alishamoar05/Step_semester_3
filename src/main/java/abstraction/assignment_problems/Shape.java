package main.java.abstraction.assignment_problems;

public abstract class Shape {
    private final String shapeId;
    private static int shapeCounter = 0;

    protected Shape() {
        shapeCounter++;
        shapeId = "SH-" + shapeCounter;
    }

    public abstract double calculateArea();

    public double scale(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException();
        }

        return factor;
    }

    public double scale(double xFactor, double yFactor) {
        if (xFactor <= 0 || yFactor <= 0) {
            throw new IllegalArgumentException();
        }

        return xFactor * yFactor;
    }

    public String getShapeId() {
        return shapeId;
    }

    public static void printArea(Shape shape) {
        if (shape == null) {
            return;
        }

        System.out.println(shape.calculateArea());
    }
}

class CircleShape extends Shape {
    private final double radius;

    public CircleShape(double radius) {
        super();

        if (radius <= 0) {
            throw new IllegalArgumentException();
        }

        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double scale(double factor) {
        super.scale(factor);
        return new CircleShape(radius * factor).calculateArea();
    }
}

class SquareShape extends Shape {
    private final double side;

    public SquareShape(double side) {
        super();

        if (side <= 0) {
            throw new IllegalArgumentException();
        }

        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double scale(double factor) {
        super.scale(factor);
        return new SquareShape(side * factor).calculateArea();
    }
}
