import java.io.File;

public class OFImage {
    private final File file;
    private final String name;

    public OFImage(File file) {
        this.file = file;
        this.name = file.getName();
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }
}
