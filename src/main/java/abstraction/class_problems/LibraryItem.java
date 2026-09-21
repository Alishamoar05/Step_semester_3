package main.java.abstraction.class_problems;

public abstract class LibraryItem {
    private final int itemId;
    private static int itemCounter = 0;

    public LibraryItem() {
        itemCounter++;
        itemId = itemCounter;
    }

    public abstract int getLoanPeriodDays();

    public int getItemId() {
        return itemId;
    }
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem implements Renewable, Reservable {
    private final String title;

    public Textbook(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return "\"" + title + " renewed\"";
    }

    @Override
    public String reserve() {
        return "\"" + title + " reserved\"";
    }
}

class Magazine extends LibraryItem implements Renewable {
    private final String title;

    public Magazine(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return "\"" + title + " renewed\"";
    }
}

class DigitalPass implements Renewable {
    private final String resourceName;

    public DigitalPass(String resourceName) {
        if (resourceName == null || resourceName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return "\"" + resourceName + " renewed\"";
    }
}

class LibraryManager {
    public static void processCheckout(LibraryItem[] items) {
        if (items == null) {
            return;
        }

        for (LibraryItem item : items) {
            if (item != null) {
                System.out.println(item.getLoanPeriodDays());
            }
        }
    }

    public static String reserveIfSupported(Object object) {
        if (object instanceof Reservable) {
            Reservable reservable = (Reservable) object;
            return reservable.reserve();
        }

        return "Reservation not supported";
    }
}
