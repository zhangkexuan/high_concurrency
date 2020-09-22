# ReentrantLock

## ReentrantLock对比synchronized的优势

- 可以用来替换 synchronized 但是synchronized是jvm层面，而Lock是api层面

- trylock

- lock.unlock()

- lockInterruptibly

- 可以设置 fair

- Lock.newCondition() 可以精确唤醒（synchronized要么随机要么全部唤醒）