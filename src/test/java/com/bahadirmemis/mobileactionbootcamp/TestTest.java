package com.bahadirmemis.mobileactionbootcamp;

import com.bahadirmemis.mobileactionbootcamp.acc.dto.AccAccountDto;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

/**
 * for learning how test annotations work
 *
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class TestTest {

    private static AccAccountDto accAccountDto;

    @BeforeAll
    public static void setup(){
        System.out.println("before all");
    }

    private static void initAccAccountDto() {
        accAccountDto = AccAccountDto.builder()
                .cusCustomerId(1L)
                .ibanNo("TR123456789012345678901234")
                .currentBalance(BigDecimal.ZERO)
                .build();
    }

    @BeforeEach
    public void beforeEach(){
        initAccAccountDto();
        System.out.println("before each");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("After Each");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("After All");
    }

    @Test
    public void test1(){
        System.out.println("test1");
        accAccountDto.setIbanNo("0");
        System.out.println(accAccountDto.getIbanNo());
    }

    @Test
    public void test2(){
        System.out.println("test2");
        System.out.println(accAccountDto.getIbanNo());
    }

    @Test
    public void test3(){

        int number1 = 5;
        int number2 = 15;

        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> sum(number1, number2));

        Assertions.assertEquals("AAAA", runtimeException.getMessage());

//        int sum = sum(number1, number2);

//        Assertions.assertEquals(20, sum);
    }

    private int sum(int i1, int i2){

        System.out.println("AAA");

        throw new RuntimeException("AAAA");

//        return i1+i2;
    }
}
