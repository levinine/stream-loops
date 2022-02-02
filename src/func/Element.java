package func;

public class Element {
    private int[] numbers;

    public Element(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return this.numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public static Element fromArray(int[] numbers) {
        int[] res = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            res[i] = numbers[i];
        }
        return new Element(res);
    }
}