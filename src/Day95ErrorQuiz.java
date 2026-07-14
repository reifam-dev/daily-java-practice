import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

class ContentBlock {
    private String blockType;
    private String mediaType;
    private String data;

    public ContentBlock(String blockType, String mediaType, String data) {
        blockType = blockType;
        this.mediaType = mediaType;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ContentBlock{type='" + blockType + "', mediaType='" + mediaType + "'}";
    }
}

public class Day95ErrorQuiz {
    private Map<String, String> mediaTypes = new HashMap<>();

    public Day95ErrorQuiz() {
        mediaTypes.put(".png", "image/png");
        mediaTypes.put(".jpg", "image/jpeg");
        mediaTypes.put(".pdf", "application/pdf");
    }

    private String encodeFile(Path path) throws Exception {
        byte[] bytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(bytes)
    }

    public ContentBlock buildContentBlock(Path path) throws Exception {
        String fileName = path.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        String mediaType = mediaTypes.get(extension);
        String blockType = mediaType.startsWith("image") ? "document" : "image";
        String data = encodeFile(path);
        return new ContentBlock(blockType, mediaType, data);
    }

    public static void main(String[] args) throws Exception {
        Day95ErrorQuiz analyser = new Day95ErrorQuiz();
        Path report = Path.of("riverside_jv_valuation.pdf");
        ContentBlock block = analyser.buildContentBlock(report);
        System.out.println(block);
    }
}