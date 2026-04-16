public class Day6StringAnalyser {
    public static void main(String[] args) {
        StringAnalyzer analyzer = new StringAnalyzer("Hello World Java");
        System.out.println("Words  : " + analyzer.getWordCount());
        System.out.println("Vowels : " + analyzer.getVowelCount());
    }
}

class StringAnalyzer {
    private String text;

    public StringAnalyzer(String text) {
        this.text = text != null ? text.trim() : "";
    }

    public int getWordCount() {
        if (text.isEmpty()) return 0;
        return text.split("\\s+").length;
    }

    public int getVowelCount() {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : text.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}