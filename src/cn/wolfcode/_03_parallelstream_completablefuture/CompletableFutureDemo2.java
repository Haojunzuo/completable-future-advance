package cn.wolfcode._03_parallelstream_completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        // CompletableFuture 在流式操作中的优势
        // 需求：创建10MyTask耗时的任务，统计它们执行完的总耗时
        // 方案三：使用CompletableFuture

        // step 1: 创建10个MyTask对象，每个任务持续1s，存入List集合
        IntStream intStream = IntStream.range(0, 10);
        List<MyTask> tasks = intStream.mapToObj(item -> {
            return new MyTask(1);
        }).collect(Collectors.toList());

        // 准备线程池
        int N_CPU = Runtime.getRuntime().availableProcessors(); // 8
        // 设置线程池中线程的数量至少为10
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(),N_CPU * 2));

        // step 2: 根据MyTask对象构建10个异步任务
        List<CompletableFuture<Integer>> futures = tasks.stream().map(myTask -> {
            return CompletableFuture.supplyAsync(() -> {
                return myTask.doWork();
            },executor);
        }).collect(Collectors.toList());

        // step 3: 执行异步任务，执行完成后，获取异步任务的结果，存入List集合中，统计总耗时
        long start = System.currentTimeMillis();
        List<Integer> results = futures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("processed %d tasks cost %.2f second",tasks.size(),costTime);

        // 关闭线程池
        executor.shutdown();

        /**
         * 总结：
         * CompletableFuture可以控制更多的线程数量，而ParallelStream不能
         */
    }
}
