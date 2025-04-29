import day1.userDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.lang.Long.sum;

public class PerformanceTest {
    @Test
    void compareNullPerformance(){
        userDto oldStyle = new userDto();
        long startTimeOld = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            try{
                oldStyle.setUserName(null);
            }catch (Exception e){

            }
        }
        long endTimeOld = System.nanoTime();

        userDto newStyle = new userDto();
        long startTimeNew = System.nanoTime();

        for (int i = 0; i < 1000000; i++) {
            try{
                newStyle.setUserName(null);
            }catch (Exception e){

            }
        }
        long endTimeNew = System.nanoTime();

        System.out.println("oldStyle = " + (endTimeOld - startTimeOld)/1000000 + "ms");
        System.out.println("newStyle = " + (endTimeNew - startTimeNew)/1000000 + "ms");
    }
    @Test
    void codeQuality(){
        int old = calculateComplexity(userDto.class);
        int newDto = calculateComplexity(userDto.class);

    }
    private int calculateComplexity(Class<?> clazz){
        return Arrays.stream(clazz.getMethods())
                .mapToInt(method -> {
                    int complexity = 1;
                    complexity += method.getParameterCount();
                    complexity += method.getExceptionTypes().length;
                    return complexity;
                })
                .sum();
    }
}
