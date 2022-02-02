package streamswithlists;

public class RunTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello streams with lists");
        Sorter sorter = new Sorter();
        long averageRunTime = sorter.runSorting(100000, 1000, 10);
        System.out.print("Average run time in ns ");
        System.out.println(averageRunTime);
    }
}
/*
   100 - 1.287 ms avg - 50k runs - 100 numbers per element
    1k - 137.85 ms avg - 5k runs - 1k numbers per element
   10k - 18.2 s avg - 50 runs - 10k numbers per element
  100k - OOM - 10 runs - 10k numbers per element
    1m - Not even gonna bother - 10 runs - 1k numbers per element
*/