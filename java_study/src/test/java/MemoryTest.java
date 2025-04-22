import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MemoryTest {

    @Test
    void compareMemory(){
        Runtime runtime = Runtime.getRuntime();

        long usedMemory = runtime.totalMemory() - runtime.freeMemory();

        List<userDto> oldStyleObjects = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            oldStyleObjects.add(new userDto());
        }

        long usedMemory2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("usedMemory = " + (usedMemory2 - usedMemory));

        oldStyleObjects = null;
        System.gc();

        usedMemory2 = runtime.totalMemory() - runtime.freeMemory();
        List<userDto> newStyleObjects = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            newStyleObjects.add(new userDto());
        }
        System.out.println("usedMemory2 = " + (usedMemory2 - usedMemory));
    }
}
