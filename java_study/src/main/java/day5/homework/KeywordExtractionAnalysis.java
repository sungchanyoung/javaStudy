package day5.homework;

public class KeywordExtractionAnalysis  implements TextAnalysisStrategy {
    //핵심 키워드 추출
    private String[] keywords;


    @Override
    public String analyzeText(String text) {
        if (text == null && text.isEmpty()){
            return "";
        }
        text =text.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ");
        String[] words = text.split("\\s+");

    }
}
