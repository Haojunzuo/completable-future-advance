package cn.wolfcode._03_parallelstream_completablefuture;

import cn.wolfcode.utils.CommonUtils;

public class MyTask {
    private int duration;

    public MyTask(int duration) {
        this.duration = duration;
    }

    // 模拟耗时的长任务
    public int doWork() {
        CommonUtils.printThreadLog("doWork");
        CommonUtils.sleepSecond(duration);
        return duration;
    }
}
