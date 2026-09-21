package main.java.abstraction.class_problems;

public interface Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class MediaFile {
    private final String fileId;
    private static int fileCounter = 1000;

    protected MediaFile() {
        fileCounter++;
        this.fileId = "MF-" + fileCounter;
    }

    public abstract String getFormatInfo();

    public String getFileId() {
        return fileId;
    }
}

class AudioFile extends MediaFile implements Playable {
    private final String title;

    public AudioFile(String title) {
        super();

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.title = title;
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {
        if (fromSecond < 0) {
            throw new IllegalArgumentException();
        }

        return "Playing audio: " + title + " from " + fromSecond + "s";
    }

    @Override
    public String pause() {
        return "Paused audio: " + title;
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Podcast implements Playable {
    private final String showName;
    private final int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        if (showName == null || showName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (episodeNumber <= 0) {
            throw new IllegalArgumentException();
        }

        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " + episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        if (fromSecond < 0) {
            throw new IllegalArgumentException();
        }

        return "Streaming episode " + episodeNumber + " of " + showName
                + " from " + fromSecond + "s";
    }

    @Override
    public String pause() {
        return "Paused episode " + episodeNumber + " of " + showName;
    }
}

class MediaLauncher {
    public static void launchAll(Playable[] items) {
        if (items == null) {
            return;
        }

        for (Playable item : items) {
            if (item != null) {
                System.out.println(item.play());
            }
        }
    }
}
