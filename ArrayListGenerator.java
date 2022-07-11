package threads;

import java.util.ArrayList;
import java.util.List;

public class ArrayListGenerator {

    public List<int[]> createRandomArrayList(int listSize, int minArraySize, int maxArraySize, int minArrayValue, int maxArrayValue) {
        List<int[]> arrayList = new ArrayList<>();
        int counter = 0;

        while (counter < listSize) {
            int ramdomArraySize = (int)(Math.random() * (maxArraySize - minArraySize + 1) + minArraySize);
            int[] array = new int[ramdomArraySize];

            for (int i = 0; i < ramdomArraySize; i++) {
                array[i] = (int)(Math.random() * (maxArrayValue - minArrayValue + 1) + minArrayValue);
            }
            arrayList.add(array);
            counter++;
        }
        return arrayList;
    }

}
