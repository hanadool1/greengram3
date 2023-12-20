package com.green.greengram3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exam1 {

    @Test
    @DisplayName("테스트1")
    public void test1() {
        System.out.println("test1");
        int sum = 2 + 2;
        Assertions.assertEquals(4,sum);
    }

    @Test
    public void test2() {
        System.out.println("test2");
        int multi = 2 * 3;
        Assertions.assertEquals(6,multi);
    }

    @Test
    public void test3() {
        Assertions.assertEquals(4,MyUtils.sum(2,2));
        Assertions.assertEquals(3,MyUtils.sum(1,2));
    }

    @Test
    public void test4() {
        MyUtils mu = new MyUtils();
        Assertions.assertEquals(2,mu.multi(1,2));
        Assertions.assertEquals(35,mu.multi(7,5));
        Assertions.assertEquals(30,mu.multi(3,10));
    }
}
