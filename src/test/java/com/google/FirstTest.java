package com.google;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    public void firstTest(){
        System.out.println("firstTest");
        Assertions.assertTrue(1==1, "Один не равно один");
        Assertions.assertTrue(1==2, "Один не равно один");
        Assertions.assertTrue(1==1, "Один не равно один");
    }
}
