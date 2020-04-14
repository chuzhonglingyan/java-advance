package com.yuntian.limit;

import com.google.common.util.concurrent.RateLimiter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yuntian
 * @date 2020/3/14 0014 22:12
 * @description
 *
 * 系统以恒定的速率产生令牌，然后将令牌放入令牌桶中
 * 令牌桶有一个容量，当令牌桶满了的时候，再向其中放入的令牌就会被丢弃
 * 每次一个请求过来，需要从令牌桶中获取一个令牌，假设有令牌，那么提供服务；假设没有令牌，那么拒绝服务
 *
 * 可以控制单位时间内请求通过数量,通常是  n个/s
 *
 * 假设一秒钟均匀放置10个令牌,容量最大10个
 *
 * 1.假设第一秒钟内来了100个请求;则只有10个请求通过,90个请求被拒;放置10个令牌,10个令牌消耗掉。
 * 2.假设第二秒钟内来了100个请求;则只有10个请求通过,90个请求被拒;放置10个令牌,10个令牌消耗掉。
 *
 * 1.假设第一秒钟内来了5个请求;则5个请求都通过;放置10个令牌,5个令牌消耗掉。
 * 2.假设第二秒钟内来了5个请求;则5个请求都通过;放置10个令牌,被拒绝放入5个,5个令牌消耗掉。
 *
 * 平均请求速率 rV    平均令牌速率 mV    桶里最大令牌M  系统能承载的qps
 *
 *  rv>mV则单位时间多余请求被拒绝
 *
 *  rv<mV则请求都通过
 *
 * 令牌桶算法：必须读写分离的情况下，限制写的速率或者小米手机饥饿营销的场景 只卖1分种抢购1000
 *
 *  缓存、限流、降级、熔断
 * https://www.cnblogs.com/xrq730/p/11025029.html
 * https://cloud.tencent.com/developer/article/1408819
 */
public class SmoothBursty {


    public static void main(String[] args) {
        //因此使用令牌桶算法特别注意设置桶中令牌的上限
        //每秒最多过五个请求
        RateLimiter limiter = RateLimiter.create(5);
        //模拟20个并发请求
        while (true){
            for (int i = 0; i < new Random().nextInt(5)+1; i++) {
                new Thread(new RequestTask(limiter, 1 + i)).start();
            }
        }
    }


    @Data
    @AllArgsConstructor
    private static class RequestTask implements Runnable {

        private RateLimiter limiter;
        private int num;

        @Override
        public void run() {
            // 超时机制
            boolean flag=limiter.tryAcquire(5, TimeUnit.SECONDS);
            if (flag){
                System.out.println("用户编号:" + num + ",获取了令牌,时刻:" + LocalDateTime.now());
            }
        }
    }
}
