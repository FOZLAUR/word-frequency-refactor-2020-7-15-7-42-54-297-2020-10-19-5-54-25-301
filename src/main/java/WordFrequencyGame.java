import java.util.*;

public class WordFrequencyGame {
    public String getResult(String sentence){

        if (sentence.split("\\s+").length==1) {
            return sentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                List<String> unfilteredWords = getWords(sentence);

                List<WordInfo> distinctWords = getDistinctWordInfos(unfilteredWords);

                distinctWords.sort((w1, w2) -> w2.getQuantity() - w1.getQuantity());

                String conjoinedString = getConjoinedString(distinctWords);
                return conjoinedString;
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> getDistinctWordInfos(List<String> unfilteredWords) {
        List<WordInfo> distinctWordInfoList = new ArrayList<>();
        HashSet<String> distinctWords = new HashSet<>(unfilteredWords);
        for (String word : distinctWords){
            WordInfo input = new WordInfo(word,
                    Collections.frequency(unfilteredWords, word) );
            distinctWordInfoList.add(input);
        }
        return distinctWordInfoList;
    }

    private String getConjoinedString(List<WordInfo> words) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : words) {
            String wordInfoLine = wordInfo.getWord() + " " +wordInfo.getQuantity();
            joiner.add(wordInfoLine);
        }
        return joiner.toString();
    }

    private List<String> getWords(String sentence) {
        return Arrays.asList(sentence.split("\\s+"));
    }
}
