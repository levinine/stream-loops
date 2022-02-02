package func;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/*
  Make 100, 1k, 10k, 100k, 1m elements with 100, 1k, 10k, 100k, 1m randomly generated numbers respectevly.
  What to do:
  * Sort numbers
  * Calculate average
  * Keep only unique numbers
  * Filter out lower than average
  * Change element to sum of numbers (long)
  * Get average of summs
*/
public class Sorter {
    public long sort(Element[] elements) {
        Element element;
        int[] numbers;
        int average;
        long finalSum = 0L;
        for (int i = 0; i < elements.length; i++) {
            element = elements[i];
            numbers = element.getNumbers();
            Arrays.sort(numbers);
            average = avgInt(numbers);
            numbers = keepUnique(numbers);
            numbers = filterOutLowerThanAvg(numbers, average);
            finalSum += sum(numbers);
        }
        return finalSum / elements.length;
    }

    private int avgInt(int[] numbers) {
        Long sum = sum(numbers);
        sum /= numbers.length;
        return sum.intValue();
    }

    private int[] keepUnique(int[] numbers) {
        if (numbers.length == 0) {
            return new int[0];
        }
        if (numbers.length == 1) {
            return numbers;
        }
        int newSize = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] != numbers[i + 1]) {
                newSize++;
            }
        }
        if (numbers[numbers.length - 2] != numbers[numbers.length - 1]) {
            newSize++;
        }
        int[] result = new int[newSize];
        int j = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] != numbers[i + 1]) {
                result[j] = numbers[i];
                j++;
            }
        }
        if (numbers[numbers.length - 2] != numbers[numbers.length - 1]) {
            result[j] = numbers[numbers.length - 1];
        }
        return result;
    }

    private int[] filterOutLowerThanAvg(int[] numbers, int average) {
        int newSize = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] >= average) {
                newSize++;
            }
        }
        int[] result = new int[newSize];
        int j = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] >= average) {
                result[j] = numbers[i];
                j++;
            }
        }
        return result;
    }

    private long sum(int[] numbers) {
        long sum = 0L;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
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