package func;

public class RunTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello func");
        Sorter sorter = new Sorter();
        long averageRunTime = sorter.runSorting(100, 100, 5000);
        System.out.print("Average run time in ns ");
        System.out.println(averageRunTime);
    }
}
/*
   100 - 292.924 us avg - 50k runs - 100 numbers per element
    1k - 34.87 ms avg - 5k runs - 1k numbers per element
   10k - 4.525 s avg - 50 runs - 10k numbers per element
  100k - 43.44 s avg - 10 runs - 10k numbers per element
    1m - 33.63 s avg - 10 runs - 1k numbers per element
*/