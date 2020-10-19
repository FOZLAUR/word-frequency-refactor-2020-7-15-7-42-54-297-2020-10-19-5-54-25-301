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
                for (String s : wordsArray) {
                    WordInfo input = new WordInfo(s, 1);
                    words.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map =getListMap(words);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
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
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getWord())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            }

            else {
                map.get(input.getWord()).add(input);
            }
        }


        return map;
    }


}
