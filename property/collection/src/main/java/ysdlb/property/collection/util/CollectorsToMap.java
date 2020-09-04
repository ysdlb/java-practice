package ysdlb.property.collection.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;



public class CollectorsToMap {
    static class Answer {
        private int id;

        private Boolean answer;

        Answer() {
        }

        Answer(int id, Boolean answer) {
            this.id = id;
            this.answer = answer;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Boolean getAnswer() {
            return answer;
        }

        public void setAnswer(Boolean answer) {
            this.answer = answer;
        }
    }
    public static void main(String[] args) {
        List<Answer> answerList = new ArrayList<>();

        answerList.add(new Answer(1, true));
        answerList.add(new Answer(2, true));
        answerList.add(new Answer(3, null));

//        Map<Integer, Boolean> answerMap =
//                answerList
//                        .stream()
//                        .collect(Collectors.toMap(Answer::getId, Answer::getAnswer));

        Map<Integer, Boolean> collect = answerList.stream()
                .collect(HashMap::new, (m, v)->m.put(v.getId(), v.getAnswer()), HashMap::putAll);
        collect.forEach((x, y) -> System.out.println(x  + " :" + y));

        Map<Integer, Boolean> collect_1 = answerList.stream()
                .collect(HashMap::new, (m, v)->m.put(v.getId(), v.getAnswer()), (m, n) -> m.putAll(n));
        collect_1.forEach((x, y) -> System.out.println(x  + " :" + y));

        System.out.println("==================");
        Map<Integer, Boolean> collect_2 = answerList.stream()
                .collect(HashMap::new, (m, v)->m.put(v.getId(), v.getAnswer()), (m, n) -> {m.put(111, true); n.put(222, false);});
        collect_2.forEach((x, y) -> System.out.println(x  + " :" + y));
    }
}
