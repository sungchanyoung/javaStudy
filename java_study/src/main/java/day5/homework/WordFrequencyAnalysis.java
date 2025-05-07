package day5.homework;

public class WordFrequencyAnalysis implements TextAnalysisStrategy {

    private int count = 0;


    @Override
    public void analyzeText(String text) {

        if (text == null || text.isEmpty()){
            throw new IllegalArgumentException("text is null or empty");
        }


    }
}
