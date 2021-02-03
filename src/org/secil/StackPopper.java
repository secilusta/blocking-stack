package org.secil;

import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class StackPopper<T> implements Callable<T> {

    Stack<T> stack;
    Semaphore semaphore;

    public StackPopper(Stack<T> stack,Semaphore semaphore) {
        this.stack = stack;
        this.semaphore = semaphore;
    }

    @Override
    public T call() throws Exception {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        T val = stack.pop();
        System.out.println("popped: " + val);
        semaphore.release();
        return val;
    }
}
