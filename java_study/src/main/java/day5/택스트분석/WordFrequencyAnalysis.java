package day5.택스트분석;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WordFrequencyAnalysis implements TextAnalysisStrategy{

    @Override
    public AnalysisResult analyze(String text) {
        if (text == null || text.isEmpty()){
            throw new IllegalArgumentException("분석할 텍스트가 없습니다");
        }

        AnalysisResult result = new AnalysisResult(text,"단어 횟수 분석");

        Map<String,Integer> wordFrequency = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(text.toLowerCase(),"\\t\\n\\r\\f.,;:!?'\\\"()[]{}");

        int totalCount = 0;

        while (tokenizer.hasMoreTokens()){
            String word = tokenizer.nextToken();
            if (word.matches("^[0-9\\\\p{Punct}]+$")){
                continue;
            }
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            totalCount++;
        }

        //가장 비번한 단어 찾기
        String mostFrequentWord = "";
        int highestFrequency = 0;
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()){
            if (entry.getValue() > highestFrequency){
                mostFrequentWord = entry.getKey();
                highestFrequency = entry.getValue();
            }
        }
        // 결과 저장
        result.addResultData("총 단어 수", totalCount);
        result.addResultData("고유 단어 수", wordFrequency.size());
        result.addResultData("가장 빈번한 단어", mostFrequentWord);
        result.addResultData("가장 빈번한 단어의 출현 횟수", highestFrequency);
        result.addResultData("단어 빈도", wordFrequency);

        return result;

    }
}
