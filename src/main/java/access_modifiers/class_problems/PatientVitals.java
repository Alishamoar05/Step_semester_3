package main.java.access_modifiers.class_problems;

public class PatientVitals {

    private double[] readings;

    public PatientVitals(double[] initialReadings) {
        this.readings = new double[0];

        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading <= 0 || reading > 45) {
            return;
        }

        double[] updatedReadings = new double[readings.length + 1];

        for (int i = 0; i < readings.length; i++) {
            updatedReadings[i] = readings[i];
        }

        updatedReadings[readings.length] = reading;
        readings = updatedReadings;
    }

    public double getAverage() {
        if (readings.length == 0) {
            return 0.0;
        }

        double sum = 0;

        for (double reading : readings) {
            sum += reading;
        }

        return sum / readings.length;
    }

    public double[] getAllReadings() {
        double[] copy = new double[readings.length];

        for (int i = 0; i < readings.length; i++) {
            copy[i] = readings[i];
        }

        return copy;
    }

    public static void main(String[] args) {
        PatientVitals vitals = new PatientVitals(
                new double[]{-36.5, 36.5, -2, 37.1}
        );

        double[] readings = vitals.getAllReadings();

        System.out.print("[");
        for (int i = 0; i < readings.length; i++) {
            System.out.print(readings[i]);

            if (i < readings.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        double[] copy = vitals.getAllReadings();
        copy[0] = 999;

        System.out.println(vitals.getAllReadings()[0]);
    }
}
