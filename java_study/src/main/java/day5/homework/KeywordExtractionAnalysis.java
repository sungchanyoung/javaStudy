package day5.homework;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class KeywordExtractionAnalysis  implements TextAnalysisStrategy {

    //핵심 키워드 추출
    private String[] keywords = {
            "나는", "입니다", "그렇게", "안녕하세요",
            "야구", "선수", "스포츠", "야구장"
    };
    Set<String> result = new HashSet<>();

    @Override
    public void analyzeText(String text) {
        if (text == null || text.isEmpty()){
            throw new IllegalArgumentException("text is null or empty");
        }

        text = text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", " ");
        String[] words = text.split("\\s+");
        Set<String> wordSet = new HashSet<>(Arrays.asList(keywords));

        for (String word : words){ //대소문자 구분
            wordSet.add(word.toLowerCase());
        }

        for (String word : wordSet){
            if (wordSet.contains(word)){
                result.add(word);
            }
        }
        System.out.println(result);
    }
}
