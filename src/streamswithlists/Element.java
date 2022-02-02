package streamswithlists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Element {
    private List<Integer> numbers;

    public Element(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Element fromArray(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        Collections.copy(numbers, result);
        return new Element(result);
    }
}