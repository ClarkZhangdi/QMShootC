package com.clark.qmshootc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        new Thread("hahaha").start();
        Map<Thread, StackTraceElement[]> allStackTraces =
                Thread.getAllStackTraces();
        Set<Map.Entry<Thread, StackTraceElement[]>> entries = allStackTraces.entrySet();
        for (Map.Entry<Thread, StackTraceElement[]> entry :
                entries) {
            Thread key = entry.getKey();
            String name = key.getName();
            System.out.println(name);
            if (name.equals("hahaha")){
                System.out.println("===============");
            }
        }
    }
}
