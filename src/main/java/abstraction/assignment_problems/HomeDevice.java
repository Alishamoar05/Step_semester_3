package main.java.abstraction.assignment_problems;

public abstract class HomeDevice {
    private final String serialNumber;
    private static int serialCounter = 1000;

    protected HomeDevice() {
        serialCounter++;
        serialNumber = "HD-" + serialCounter;
    }

    public abstract double getConsumptionWatts();

    public String getSerialNumber() {
        return serialNumber;
    }
}

interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

class WashingMachine extends HomeDevice implements RemoteControllable, EnergyTrackable {
    private final double consumptionWatts;

    public WashingMachine(double consumptionWatts) {
        super();

        if (consumptionWatts <= 0) {
            throw new IllegalArgumentException();
        }

        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }

    @Override
    public String connect(String appId) {
        if (appId == null || appId.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return "Washing machine " + getSerialNumber()
                + " started a wash cycle"
                + " | " + getSerialNumber()
                + " connected to " + appId;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {
    private final double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        super();

        if (consumptionWatts <= 0) {
            throw new IllegalArgumentException();
        }

        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {
    private final String appName;

    public MobileApp(String appName) {
        if (appName == null || appName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        if (appId == null || appId.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return appName + " connected to " + appId;
    }
}

class HomeControlPanel {
    public static void connectAll(RemoteControllable[] items, String appId) {
        if (items == null) {
            return;
        }

        for (RemoteControllable item : items) {
            if (item != null) {
                System.out.println(item.connect(appId));
            }
        }
    }

    public static double getConsumptionIfTrackable(HomeDevice device) {
        if (device instanceof EnergyTrackable) {
            EnergyTrackable trackable = (EnergyTrackable) device;
            return trackable.getConsumptionWatts();
        }

        return 0.0;
    }
}
