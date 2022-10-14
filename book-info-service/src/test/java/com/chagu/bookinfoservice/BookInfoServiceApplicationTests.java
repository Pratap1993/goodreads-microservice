package com.chagu.bookinfoservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class BookInfoServiceApplicationTests {

    @Test
    void contextLoads() {
        Integer[] arr = new Integer[]{1, 2, 3};
        System.out.println(Arrays.toString(arr));
    }

}
