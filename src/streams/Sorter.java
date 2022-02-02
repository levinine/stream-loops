package streams;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Sorter {
    public long sort(Element[] elements) {
        Long[] sums = Arrays.stream(elements)
            .map(e -> {
                int[] numbers = Arrays.stream(e.getNumbers()).sorted().toArray();
                e.setNumbers(numbers);
                return e;
            }).map(e -> new ElemWithAvg(e, 
                Arrays.stream(e.getNumbers()).sum() / e.getNumbers().length
            )).map(e -> {
                int[] numbers = Arrays.stream(e.element().getNumbers()).distinct().toArray();
                e.element().setNumbers(numbers);
                return e;
            }).map(e -> {
                int[] numbers = Arrays.stream(e.element().getNumbers()).filter(i -> i < e.average()).toArray();
                e.element().setNumbers(numbers);
                return e;
            }).map(e -> {
                return Arrays.stream(e.element().getNumbers()).asLongStream().sum();
            }).toArray(Long[]::new);

        return Arrays.stream(sums).mapToLong(e -> e.longValue()).sum() / sums.length;
    }

    public Element[] makeElements(int volume, int numbersVolume) {
        Element[] elements = new Element[volume];
        for (int i = 0; i < volume; i++) {
            int[] numbers = new int[numbersVolume];
            for (int j = 0; j < numbersVolume; j++) {
                numbers[j] = ThreadLocalRandom.current().nextInt(10000);
            }
            elements[i] = new Element(numbers);
        }
        return elements;
    }

    public long runSorting(int volume, int numbersVolume, int runs) {
        long start, end;
        long[] runTimes = new long[runs];
        for (int i = 0; i < runs; i++) {
            System.out.printf("Run %d\n", i +1 );
            Element[] elements = this.makeElements(volume, numbersVolume);
            start = System.nanoTime();
            this.sort(elements);
            end = System.nanoTime();
            runTimes[i] = end - start;
            System.out.printf("Run time %d\n", runTimes[i]);
        }
        long sum = 0;
        for (int i = 0; i < runs; i++) {
            sum += runTimes[i];
        }
        return sum / runs;
    }
}