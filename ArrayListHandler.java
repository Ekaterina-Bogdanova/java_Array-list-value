package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ArrayListHandler{
    private List<int[]> arrayList;

    public ArrayListHandler(List<int[]> arrayList) {
        this.arrayList = arrayList;
    }

    public long getSumOfArraysValues() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<Long>> tasks = new ArrayList<>();
        long totalResult = 0;

        for (int [] array : arrayList) {
            tasks.add(new ArrayHandler(array));
        }
        List<Future<Long>> futures = executorService.invokeAll(tasks);

        executorService.shutdown();

        for (Future<Long> future : futures) {
            totalResult = Long.sum(totalResult, future.get());
        }

        return totalResult;
    }

    public void displayArrayList() {
        int a = 0;
        for (int[] array : arrayList) {
            System.out.printf("Array #%d\n", ++a);

            for (int item : array) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }

    class ArrayHandler implements Callable<Long> {
        private int [] array;

        ArrayHandler(int [] array ){
            this.array = array;
        }

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int item : array) {
                sum = Long.sum(sum, item);
                System.out.printf("Work thread %s\n", Thread.currentThread().getName());
            }
            return sum;
        }
    }

}
