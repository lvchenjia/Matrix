package com.thyme;

public class TestSync implements Runnable{
    private int num = 10;
    private boolean flag = true;
    private int cnt=0;
    @Override
    public void run() {
        while (flag){
            buy();
        }
        System.out.println(Thread.currentThread().getName()+"total"+cnt);
    }

    public synchronized void buy(){
        if(num<=0){
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread().getName()+num--);cnt++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestSync testSync = new TestSync();


        //new Thread(testSync, "A").start();
        new Thread(testSync, "B").start();
        new Thread(testSync, "C").start();
        Thread a = new Thread(testSync, "A");
        a.setPriority(10);
        a.start();

    }
}
