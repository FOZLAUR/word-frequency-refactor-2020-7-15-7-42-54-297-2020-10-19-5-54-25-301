import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String sentence){


        if (sentence.split("\\s+").length==1) {
            return sentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] wordsArray = sentence.split("\\s+");

                List<WordInfo> words = new ArrayList<>();
                for (String word : wordsArray) {
                    WordInfo input = new WordInfo(word, 1);
                    words.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> wordMap = getListMap(words);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : wordMap.entrySet()){
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                words = list;

                words.sort((w1, w2) -> w2.getQuantity() - w1.getQuantity());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : words) {
                    String s = w.getWord() + " " +w.getQuantity();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> wordMap = new HashMap<>();
        for (WordInfo input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordMap.containsKey(input.getWord())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                wordMap.put(input.getWord(), arr);
            }

            else {
                wordMap.get(input.getWord()).add(input);
            }
        }


        return wordMap;
    }


}
