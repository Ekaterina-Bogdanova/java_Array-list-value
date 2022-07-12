package threads;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<int[]> arrayList = new ArrayListGenerator()
                .createRandomArrayList(3, 2, 10, 1, 10);

        ArrayListHandler handler = new ArrayListHandler(arrayList);
        handler.displayArrayList();

        System.out.printf("Total result = %d\n", handler.getSumOfArraysValues());
    }

}
