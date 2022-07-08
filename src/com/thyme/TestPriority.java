package com.thyme;

public class TestPriority{
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority());
        MyPriority myPriority = new MyPriority();
        new Thread(myPriority).start();
    }
}

class MyPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority());
    }
}
