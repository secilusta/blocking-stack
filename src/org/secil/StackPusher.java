package org.secil;

import java.util.Stack;
import java.util.concurrent.Semaphore;

public class StackPusher<V> implements Runnable {

    Stack<V> stack;
    V name;
    Semaphore semaphore;

    public StackPusher(Stack<V> stack, V name, Semaphore semaphore) {
        this.stack = stack;
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("pushed: " + name);
        stack.push(name);
        semaphore.release();
    }
}
