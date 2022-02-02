package streams;

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
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello streams");
        Sorter sorter = new Sorter();
        long averageRunTime = sorter.runSorting(100, 100, 5000);
        System.out.print("Average run time in ns ");
        System.out.println(averageRunTime);
    }
}
/*
   100 - 1.116 ms avg - 50k runs - 100 numbers per element
    1k - 94.37 ms avg - 5k runs - 1k numbers per element
   10k - 8.44 s avg - 50 runs - 10k numbers per element
  100k - 1 m 25 s avg - 10 runs - 10k numbers per element
    1m - 1 m 26.596 s avg - 10 runs - 1k numbers per element
*/