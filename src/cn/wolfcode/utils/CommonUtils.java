package cn.wolfcode.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务辅助工具类
 * @author kallen
 */
public class CommonUtils {

    // 读取指定路径的文件
    public static String readFile(String pathToFile) {
        try {
            return Files.readString(Paths.get(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // 休眠指定的毫秒数
    public static void sleepMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 休眠指定的秒数
    public static void sleepSecond(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTime() {
        LocalTime now = LocalTime.now();
        return now.format(DateTimeFormatter.ofPattern("[HH:mm:ss.SSS]"));
    }

    // 打印输出带线程信息的日志
    public static void printThreadLog(String message) {
        // 当前时间 | 线程id | 线程名 | 日志信息
        String result = new StringJoiner(" | ")
                .add(getCurrentTime())
                .add(String.format("%2d", Thread.currentThread().getId()))
                .add(Thread.currentThread().getName())
                .add(message)
                .toString();
        System.out.println(result);
    }

}
