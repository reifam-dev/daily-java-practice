import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

final class ContentBlock {
    private final String blockType;
    private final String mediaType;
    private final String data;

    public ContentBlock(String blockType, String mediaType, String data) {
        this.blockType = blockType;
        this.mediaType = mediaType;
        this.data = data;
    }

    public String getBlockType() {
        return this.blockType;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public String getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "ContentBlock{type='" + this.blockType + "', mediaType='" + this.mediaType + "'}";
    }
}

final class UnsupportedFileTypeException extends RuntimeException {
    public UnsupportedFileTypeException(String extension) {
        super("No media type for " + extension);
    }
}

/**
 * Builds Anthropic-style multimodal content blocks (image or document)
 * from a file on disk, base64-encoding the contents.
 */
public class Day95DocumentVisionAnalyser {
    private final Map<String, String> mediaTypes;

    public Day95DocumentVisionAnalyser() {
        this.mediaTypes = new HashMap<>();
        this.mediaTypes.put(".png", "image/png");
        this.mediaTypes.put(".jpg", "image/jpeg");
        this.mediaTypes.put(".jpeg", "image/jpeg");
        this.mediaTypes.put(".pdf", "application/pdf");
    }

    private String encodeFile(Path path) throws IOException {
        byte[] bytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public ContentBlock buildContentBlock(Path path) throws IOException {
        String fileName = path.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        String mediaType = this.mediaTypes.get(extension);
        if (mediaType == null) {
            throw new UnsupportedFileTypeException(extension);
        }
        String blockType = mediaType.startsWith("image") ? "image" : "document";
        String data = encodeFile(path);
        return new ContentBlock(blockType, mediaType, data);
    }

    @Override
    public String toString() {
        return "Day95DocumentVisionAnalyser{supportedTypes=" + this.mediaTypes.keySet() + '}';
    }

    public static void main(String[] args) throws IOException {
        Day95DocumentVisionAnalyser analyser = new Day95DocumentVisionAnalyser();
        Path report = Path.of("riverside_jv_valuation.pdf");
        ContentBlock block = analyser.buildContentBlock(report);
        System.out.println(block);
    }
}