package day5.homework;

import java.util.*;

public class WordFrequencyAnalysis implements TextAnalysisStrategy {
    //단어 빈도 체크
    private String[] keywords ={
            "용기", "사랑", "나", "고마워", "처음", "배움",
            "하루", "는", "배려", "카카오톡", "학교", "거짓말"
    };


    @Override
    public void analyzeText(String text) {

        if (text == null || text.isEmpty()){
            throw new IllegalArgumentException("text is null or empty");
        }

        text = text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", " ");
        String[] words = text.split("\\s+");

        Map<String, Integer> wordMap = new HashMap<>();


        for (String word : words){
            for (String keyword : keywords){
                if (word.equals(keyword)){
                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }
}
