import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ImageFileManager {
    private final List<OFImage> images = new ArrayList<>();
    private int currentIndex = -1;

    private static final String[] IMAGE_EXT = {"jpg","jpeg","png","gif","bmp"};

    public ImageFileManager(File directory) {
        loadFromDirectory(directory);
    }

    private boolean isImageFile(File f) {
        String name = f.getName().toLowerCase(Locale.ROOT);
        for (String ext : IMAGE_EXT) {
            if (name.endsWith("." + ext)) return true;
        }
        return false;
    }

    private void loadFromDirectory(File dir) {
        images.clear();
        currentIndex = -1;
        if (dir == null || !dir.isDirectory()) return;
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) {
            if (f.isFile() && isImageFile(f)) {
                images.add(new OFImage(f));
            }
        }
        if (!images.isEmpty()) currentIndex = 0;
    }

    public boolean hasImages() {
        return !images.isEmpty();
    }

    public OFImage getCurrent() {
        if (!hasImages() || currentIndex < 0) return null;
        return images.get(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int size() {
        return images.size();
    }

    public void next() {
        if (!hasImages()) return;
        currentIndex = (currentIndex + 1) % images.size();
    }

    public void previous() {
        if (!hasImages()) return;
        currentIndex = (currentIndex - 1 + images.size()) % images.size();
    }

    public void goTo(int index) {
        if (!hasImages()) return;
        if (index >= 0 && index < images.size()) currentIndex = index;
    }
}
