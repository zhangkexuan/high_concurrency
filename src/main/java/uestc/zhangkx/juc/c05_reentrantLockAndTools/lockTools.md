# countDownLatch

倒计时器，这个工具通常是用来控制线程等待，它可以让某一个线程等待到倒计时器结束，再开始执行

使用`new CountDownLatch(Integer)`就可创建一个倒计时器

它的使用最核心的是 `countDownLatch.await()`和`countDownLatch.countDown()`两者的对比使用

## 场景一：发令枪

```java
    CountDownLatch countDownLatch = new CountDownLatch(1);
    for (int i = 0; i < 5; i++) {
        new Thread(() -> {
            try {
                //准备完毕……运动员都阻塞在这，等待号令
                countDownLatch.await();
                String parter = "【" + Thread.currentThread().getName() + "】";
                System.out.println(parter + "开始执行……");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    Thread.sleep(2000);// 裁判准备发令
    countDownLatch.countDown();// 发令枪：执行发令
```

用await去阻塞所有的线程，再用一个countdown去解除阻塞，实现发令枪

## 场景二：join

代码案例里就使用的这个
```java
    Thread[] threads = new Thread[100];
    CountDownLatch latch = new CountDownLatch(threads.length);

    for(int i=0; i<threads.length; i++) {
        threads[i] = new Thread(()->{
            int result = 0;
            for(int j=0; j<10000; j++) result += j;
            latch.countDown();
        });
    }

    /*
    这里的for，导致每一个threads也是有先后顺序的，并不是完美的并发
     */
    for (int i = 0; i < threads.length; i++) {
        threads[i].start();
    }

    try {
        latch.await();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println("end latch");
```

不一样的地方是 先countdown，再await。这就是一种线程执行完成后的汇总。

# CyclicBarrier

循环栅栏，收集满后再统一执行。关键的方法是`new CyclicBarrier(Integer,new Runnable)`收集量达到Integer后执行Runnable方法，并且不断循环。

```java
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人"));
        
        for(int i=0; i<100; i++) {

                new Thread(()->{
                    try {
                        barrier.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }).start();
            
        }
    }
```

## 场景

复杂操作中：数据库、网络、文件中可以收集齐数据后再统一执行。

# phaser

相当于cyclicbarrier中每个阶段非循环，而是各自定义。

# semaphore

信号量，允许多少得到。`new Semaphore(2, true);`两个线程同时运行。`s.acquire();`求得一个信号（信号量-1）。`s.release();`释放一个信号（信号量+1）。

## 场景

- 限流

- 车道和收费站