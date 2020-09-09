package uestc.zhangkx.juc.c04_atomicAndUnsafe;

//import sun.misc.*;

import sun.misc.Unsafe;

public class T04_HelloUnsafe {
    static class M {
        private M() {}

        int i =0;
    }

   public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}


