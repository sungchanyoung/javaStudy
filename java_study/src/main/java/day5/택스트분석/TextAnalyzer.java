package day5.택스트분석;

import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {
    private TextAnalysisStrategy analysisStrategy;
    private Map<String, TextAnalysisStrategy> registeredStrategies;

    public TextAnalyzer() {
        this.registeredStrategies = new HashMap<>();
    }

    public TextAnalyzer(TextAnalysisStrategy analysisStrategy){
        this();
        this.analysisStrategy = analysisStrategy;
    }

    public void registerStrategy(String name, TextAnalysisStrategy strategy){
        registeredStrategies.put(name, strategy);
    }

    public boolean selectStrategy(String name){
        TextAnalysisStrategy strategy = registeredStrategies.get(name);
        if (strategy != null){
            this.analysisStrategy = strategy;
            return true;
        }
        return false;
    }

    public AnalysisResult analyze(String text){
        if (analysisStrategy == null){
            throw new IllegalStateException("분석기가 선택되지 않았습니다");
        }
        return analysisStrategy.analyze(text);
    }

    public AnalysisResult analyzeWith(String text, String strategyName){
        TextAnalysisStrategy strategy = registeredStrategies.get(strategyName);
        if (strategy == null){
            throw new IllegalArgumentException("지원하지 않는 분석기 입니다.");
        }
        return strategy.analyze(text);
    }

    public Map<String, AnalysisResult> analyzeWithAll(String text){
        Map<String, AnalysisResult> results = new HashMap<>();
        for (Map.Entry<String, TextAnalysisStrategy> entry : registeredStrategies.entrySet()){
           String name = entry.getKey();
           TextAnalysisStrategy strategy = entry.getValue();

           results.put(name, strategy.analyze(text));
        }
        return results;
    }

    public String getCurrentStrategyType() {
        if (analysisStrategy == null) {
            return "설정 되지 않음";
        } else if (analysisStrategy instanceof WordFrequencyAnalysis) {
            return "단어 빈도 분석";
        } else if (analysisStrategy instanceof SentimentAnalysis) {
            return "감정 분석";
        } else if (analysisStrategy instanceof KeywordExtractionAnalysis) {
            return "키워드 추출 분석";
        } else {
            return "기타 분석 전략";
        }
    }


}
