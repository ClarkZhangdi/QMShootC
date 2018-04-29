package com.clark.qmshootc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    enum A {
        haha(0), hehe(1);

        public int getI() {
            return i;
        }

        private int i;

        A(int i) {
            this.i = i;
        }
    }

    @Test
    public void addition_isCorrect() {
        System.out.println(A.haha.getI());
        System.out.println(A.hehe.getI());
    }
}