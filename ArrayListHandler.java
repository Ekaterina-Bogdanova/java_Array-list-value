package threads;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ArrayListHandler{
    private List<int[]> arrayList;

    public ArrayListHandler(List<int[]> arrayList) {
        this.arrayList = arrayList;
    }

    private long sumArrayValues(int[] array) {
        AtomicLong sum = new AtomicLong(0);
        for (int item : array) {
            sum.addAndGet(item);
//            System.out.printf("Work thread %s\n", Thread.currentThread().getName());
        }
        return sum.intValue();
    }

    public long getSumOfArraysValues() {
        AtomicLong totalResult = new AtomicLong(0);
        ExecutorService service = Executors.newCachedThreadPool();

        for (int [] array : arrayList) {
            Future<Long> result = service.submit(() -> sumArrayValues(array));

            try {
                totalResult.addAndGet(result.get());
//                System.out.println(totalResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();

        return totalResult.get();
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

}
