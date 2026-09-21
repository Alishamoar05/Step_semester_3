package main.java.abstraction.assignment_problems;

public interface Exportable {
    String exportData();

    static int getTotalExports() {
        return ExportCounter.totalExports;
    }

    class ExportCounter {
        private static int totalExports = 0;

        private ExportCounter() {
        }

        static void increment() {
            totalExports++;
        }
    }
}

class ReportGenerator implements Exportable {
    private final String reportName;

    public ReportGenerator(String reportName) {
        if (reportName == null || reportName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        Exportable.ExportCounter.increment();
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private final String username;

    public UserProfile(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.username = username;
    }

    @Override
    public String exportData() {
        Exportable.ExportCounter.increment();
        return "Exported profile: " + username;
    }
}

class ExportManager {
    public static void exportAll(Exportable[] items) {
        if (items == null) {
            return;
        }

        for (Exportable item : items) {
            if (item != null) {
                System.out.println(item.exportData());
            }
        }
    }
}
