package day5.택스트분석;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentimentAnalysis implements TextAnalysisStrategy{

    private final List<String> positiveWords = Arrays.asList(
            "좋다", "훌륭", "행복", "기쁜", "즐거운", "사랑", "감사",
            "최고", "멋지다", "환상적", "긍정", "아름", "성공", "칭찬",
            "happy", "good", "excellent", "great", "love", "wonderful", "beautiful",
            "amazing", "awesome", "thank", "positive", "success", "perfect"
    );

    private final List<String> negativeWords = Arrays.asList(
            "나쁘다", "슬프다", "화나다", "실망", "불만", "싫다", "걱정", "신남",
            "최악", "불행", "실패", "고통", "미워하다", "괴롭다", "부족",
            "bad", "sad", "angry", "terrible", "hate", "disappointed", "negative",
            "worse", "worst", "failure", "poor", "sorry", "problem", "difficult"
    );

    @Override
    public AnalysisResult analyze(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("분석할 텍스트가 없습니다.");
        }

        AnalysisResult result = new AnalysisResult(text,"감정 분석");
        String processedText = text.toLowerCase()
                .replaceAll("\\p{Punct}"," ")
                .replaceAll("\\s+", " ")
                .trim();

        String[] words = processedText.split("\\s+");

        int positiveCount = 0;
        int negativeCount = 0;

        Map<String, String> foundSentimentWords = new HashMap<>();
        for (String word : words) {
            if (positiveWords.contains(word)) {
                positiveCount++;
                foundSentimentWords.put(word, "positive");
            } else if (negativeWords.contains(word)) {
                negativeCount++;
                foundSentimentWords.put(word, "negative");
            }
        }
        double totalWords = words.length;
        double positiveScore = totalWords > 0 ? positiveCount / totalWords : 0;
        double negativeScore = totalWords > 0 ? negativeCount / totalWords : 0;
        double sentimentScore = positiveScore - negativeScore;

        String sentimentState;
        if (sentimentScore > 0.1) {
            sentimentState = "긍정적";
        } else if (sentimentScore > 0.1) {
            sentimentState = "부정적";
        }else {
            sentimentState = "중립적";
        }

        result.addResultData("총 단어 수", words.length);
        result.addResultData("긍정 단어 수", positiveCount);
        result.addResultData("부정 단어 수", negativeCount);
        result.addResultData("감정 점수", String.format("%.2f", sentimentScore));
        result.addResultData("감정 상태", sentimentState);
        result.addResultData("감정 단어 목록", foundSentimentWords);

        return result;
    }
}
