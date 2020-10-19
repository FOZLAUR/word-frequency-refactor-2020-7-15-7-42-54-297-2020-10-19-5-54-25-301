import java.util.*;

public class WordFrequencyGame {
    public String getResult(String sentence){


        if (sentence.split("\\s+").length==1) {
            return sentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                List<String> unfilteredWords = getWords(sentence);

                //get the map for the next step of sizing the same word
                //Map<String, List<WordInfo>> wordMap = getListMap(words);

                //words = getUniqueWordInfos(wordMap);
                List<WordInfo> distinctWords = getUniqueWordInfos(unfilteredWords);

                distinctWords.sort((w1, w2) -> w2.getQuantity() - w1.getQuantity());

                String conjoinedString = getConjoinedString(distinctWords);
                return conjoinedString;
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> getUniqueWordInfos(List<String> unfilteredWords) {
        List<WordInfo> distinctWordInfoList = new ArrayList<>();
        HashSet<String> distinctWords = new HashSet<>(unfilteredWords);
        for (String word : distinctWords){
            WordInfo input = new WordInfo(word,
                    Collections.frequency(unfilteredWords, word) );
            distinctWordInfoList.add(input);
        }
        return distinctWordInfoList;
    }

//    private List<WordInfo> getUniqueWordInfos(Map<String, List<WordInfo>> wordMap) {
//        List<WordInfo> list = new ArrayList<>();
//        for (Map.Entry<String, List<WordInfo>> entry : wordMap.entrySet()){
//            WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
//            list.add(input);
//        }
//        return list;
//    }

    private String getConjoinedString(List<WordInfo> words) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : words) {
            String wordInfoLine = wordInfo.getWord() + " " +wordInfo.getQuantity();
            joiner.add(wordInfoLine);
        }
        return joiner.toString();
    }

//    private List<WordInfo> getWords(String sentence) {
//        String[] wordsArray = sentence.split("\\s+");
//
//        List<WordInfo> words = new ArrayList<>();
//        for (String word : wordsArray) {
//            WordInfo wordInfo = new WordInfo(word, 1);
//            words.add(wordInfo);
//        }
//        return words;
//    }

    private List<String> getWords(String sentence) {
        return Arrays.asList(sentence.split("\\s+"));
    }

    private Map<String,List<WordInfo>> getListMap(List<WordInfo> words) {
        Map<String, List<WordInfo>> wordMap = new HashMap<>();
        for (WordInfo input :  words){
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
