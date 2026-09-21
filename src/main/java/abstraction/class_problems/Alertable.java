package main.java.abstraction.class_problems;

public interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor implements Alertable {
    private final String zoneName;

    public SecuritySensor(String zoneName) {
        if (zoneName == null || zoneName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }

    @Override
    public String sendAlert(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return "[" + zoneName + "] " + message;
    }
}

class MotionSensor extends SecuritySensor implements Alertable {
    public MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message);
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private final String secondZoneName;

    public DualZoneMotionSensor(String zoneName, String secondZoneName) {
        super(zoneName);

        if (secondZoneName == null || secondZoneName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message)
                + " [also covering " + secondZoneName + "]";
    }
}

class SmokeDetector implements Alertable {
    private final String detectorId;

    public SmokeDetector(String detectorId) {
        if (detectorId == null || detectorId.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.detectorId = detectorId;
    }

    @Override
    public String sendAlert(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return "[" + detectorId + "] " + message;
    }
}

class AlertNetwork {
    public static void broadcastAlert(Alertable[] devices, String message) {
        if (devices == null) {
            return;
        }

        for (Alertable device : devices) {
            if (device != null) {
                System.out.println(device.sendAlert(message));
            }
        }
    }

    public static String getZoneIfMotionSensor(Alertable device) {
        if (device instanceof MotionSensor) {
            MotionSensor sensor = (MotionSensor) device;
            return sensor.getZoneName();
        }

        return "Not a motion sensor";
    }
}
