package cn.wolfcode._02_get_join;

import cn.wolfcode.utils.CommonUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GetOrJoinDemo {
    public static void main(String[] args) {
        // get or join
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "hello";
        });

        String ret = future.join();
        CommonUtils.printThreadLog("ret = " + ret);

        // get()
        /*try {
            String ret = future.get();
            CommonUtils.printThreadLog("ret = " + ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }
}
