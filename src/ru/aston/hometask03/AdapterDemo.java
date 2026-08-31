package ru.aston.hometask03;

// Основной интерфейс
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Несовместимый интерфейс
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Реализация несовместимого интерфейса 1
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Воспроизведение VLC-файла: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // этот плеер не умеет играть mp4
    }
}

// Реализация несовместимого интерфейса 2
class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // этот плеер не умеет играть vlc
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Воспроизведение MP4-файла: " + fileName);
    }
}

// Адаптер
class MediaAdapter implements MediaPlayer {
    private final AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter(String audioType) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            advancedPlayer = new VlcPlayer();
        } else if ("mp4".equalsIgnoreCase(audioType)) {
            advancedPlayer = new Mp4Player();
        } else {
            throw new IllegalArgumentException("Формат не поддерживается адаптером: " + audioType);
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            advancedPlayer.playVlc(fileName);
        } else if ("mp4".equalsIgnoreCase(audioType)) {
            advancedPlayer.playMp4(fileName);
        }
    }
}

// Клиентский класс
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) {
            // основной формат через проигрыватель (адаптер не нужен)
            System.out.println("Воспроизведение MP3-файла: " + fileName);
        } else if ("vlc".equalsIgnoreCase(audioType) || "mp4".equalsIgnoreCase(audioType)) {
            // остальные форматы через адаптер
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Формат " + audioType + " не поддерживается");
        }
    }
}

public class AdapterDemo {

    public static void run() {
        AudioPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");
        player.play("mp4", "video.mp4");
        player.play("vlc", "movie.vlc");
        player.play("avi", "clip.avi"); // неподдерживаемый формат
    }

    public static void main(String[] args) {
        run();
    }
}
