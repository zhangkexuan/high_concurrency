# java高并发
## 线程

线程的概念

线程的启动

线程常用的方法

## Synchronized

### Synchronized(object)

- synchronized是用来锁对象的

    >sync(Object)->锁该对象
                         
    >sync(this)->锁调用对象

    >sync [METHOD]->锁调用方法
    
    >sync static->锁class文件

- synchronized和非同步方法同步进行的

- synchronized是可重入的（解决子类继承父类时同一个锁问题）

- Object是不能用String常量 integer long

    >所有用到该常量的都是同一个常量，很容易冲突
                              
    >Integer和Long的话，只要变动一下值，就变成新的对象了

### JVM对synchronize的优化

jvm中synchronize是重量级锁（几度优化 效率已经很高了），有一整套锁优化方案：

- 偏向锁

    >markword 记录这个线程ID （偏向锁）

- 自旋锁

    >如果线程争用：升级为 自旋锁 

    >10次以后，升级为重量级锁 - OS

**如何选择用什么锁？**

- 执行时间短（加锁代码），线程数少，用自旋

- 执行时间长，线程数多，用系统锁
