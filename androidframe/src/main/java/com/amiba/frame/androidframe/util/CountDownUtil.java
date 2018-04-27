package com.amiba.frame.androidframe.util;


import android.os.Handler;
import android.os.Message;

/**
 * com.android.base.androidframework.common.util
 * Created by wudl on 16/5/23.
 */
public class CountDownUtil {

    private final static String TAG = "CountDownUtil";

    private final static int DOING_COUNT = 0;
    private final static int INTERRUPT_COUNT = 1;
    private final static int AFTER_COUNT = 2;
    private final static int THREAD_TIME = 1000;

    private CountDownListener listener;
    private Handler handler;
    private Thread thread;
    private boolean isRun; //倒计时是否正在进行
    private boolean isStop = false;//是否停止倒计时

    public CountDownUtil() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DOING_COUNT:
                        listener.doDuringCount(msg.arg1);
                        break;
                    case INTERRUPT_COUNT:
                        listener.doInterruptCount(msg.arg1);
                        break;
                    case AFTER_COUNT:
                        listener.doAfterCount();
                        break;
                }
            }
        };
    }

    public CountDownUtil(CountDownListener listener) {
        this();
        this.listener = listener;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setIsRun(boolean isRun) {
        this.isRun = isRun;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setIsStop(boolean isStop) {
        this.isStop = isStop;
    }

    public void startCount(final int countSecond) {
        thread = new Thread(() -> {
            int i = countSecond;
            while (i > 0 && isRun && !isStop) {
                Message msg = new Message();
                msg.what = DOING_COUNT;
                msg.arg1 = i;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(THREAD_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
            if (i <= 0) {
                isStop = true;
            }
            if (isRun && isStop) {
                Message msg = new Message();
                msg.what = AFTER_COUNT;
                handler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = INTERRUPT_COUNT;
                msg.arg1 = countSecond;
                handler.sendMessage(msg);
            }
        });
        thread.start();
    }

    public void stopCount() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            thread = null;
        }
    }

    public interface CountDownListener {
        void doDuringCount(int second);

        void doAfterCount();

        void doInterruptCount(int countSecond);
    }
}
