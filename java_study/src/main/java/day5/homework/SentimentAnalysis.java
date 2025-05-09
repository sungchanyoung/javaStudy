package day5.homework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SentimentAnalysis implements TextAnalysisStrategy {
//감정 분석 클래스  부정 긍정 클래스
    private String[] positive = {
        "희망", "성장", "행복", "사랑", "믿음",
        "감사", "용기", "열정", "미소", "긍정"
    };

    private String[] negative = {
            "절망", "불안", "실패", "분노",
            "두려움", "외로움", "좌절", "슬픔",
            "미움", "불신"
    };

    @Override
    public void analyzeText(String text) {
        if (text == null || text.isEmpty()){
            throw new IllegalArgumentException("text is null or empty");
        }

        text = text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", " ");
        String[] words = text.split("\\s+");

        HashSet<String> positiveSet = new HashSet<>(Arrays.asList(positive));
        HashSet<String> negativeSet = new HashSet<>(Arrays.asList(negative));

        HashMap<String, Integer> positiveMap = new HashMap<>();
        HashMap<String, Integer> negativeMap = new HashMap<>();

        for (String word : words){
            if (positiveSet.contains(word)){
                positiveMap.put(word, positiveMap.getOrDefault(word, 0) + 1);
            } else if (negativeSet.contains(word)) {
                negativeMap.put(word, negativeMap.getOrDefault(word, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : positiveMap.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : negativeMap.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        int positiveCount = positiveMap.values().stream().mapToInt(Integer::intValue).sum();
        int negativeCount = negativeMap.values().stream().mapToInt(Integer::intValue).sum();

        if (positiveCount > negativeCount){
            System.out.println("긍정글 입니다");
        }else {
            System.out.println("부정글 입니다");
        }
    }
}
