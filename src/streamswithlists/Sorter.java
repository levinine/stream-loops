package streamswithlists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Sorter {
    public long sort(List<Element> elements) {
        var sumList = elements.stream()
            .map(e -> {
                e.getNumbers().sort((n1, n2) -> n1.compareTo(n2));
                return e;
            }).map(e -> new ElemWithAvg(e, 
                e.getNumbers().stream().mapToInt(n -> n.intValue()).sum() / e.getNumbers().size())
            ).map(e -> {
                var list = e.element().getNumbers().stream().distinct().toList();
                e.element().setNumbers(list);
                return e;
            }).map(e -> {
                var list = e.element().getNumbers().stream().filter(n -> n < e.average()).toList();
                e.element().setNumbers(list);
                return e;
            }).map(e -> e.element().getNumbers().stream().mapToLong(n -> n.longValue()).sum())
            .toList();

        return sumList.stream().mapToLong(s -> s.longValue()).sum() / sumList.size();
    }

    public List<Element> makeElements(int volume, int numbersVolume) {
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < volume; i++) {
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < numbersVolume; j++) {
                numbers.add(ThreadLocalRandom.current().nextInt(10000));
            }
            elements.add(new Element(numbers));
        }
        return elements;
    }

    public long runSorting(int volume, int numbersVolume, int runs) {
        long start, end;
        long[] runTimes = new long[runs];
        for (int i = 0; i < runs; i++) {
            System.out.printf("Run %d\n", i +1 );
            var elements = this.makeElements(volume, numbersVolume);
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