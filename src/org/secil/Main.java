package org.secil;

import java.util.Stack;
import java.util.concurrent.*;

public class Main {

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Stack<String> stack = new Stack<>();
        StackPusher stackPusher = new StackPusher(stack,"hi1", semaphore);
        StackPusher stackPusher2 = new StackPusher(stack,"hi2", semaphore);
        StackPusher stackPusher3 = new StackPusher(stack,"hi3", semaphore);
        StackPusher stackPusher4 = new StackPusher(stack,"hi4", semaphore);

        Thread t1 = new Thread(stackPusher);
        Thread t2 = new Thread(stackPusher2);
        Thread t3 = new Thread(stackPusher3);
        Thread t4 = new Thread(stackPusher4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        Future<String> stackPopper = executor.submit(new StackPopper(stack, semaphore));
        Future<String> stackPopper2 = executor.submit(new StackPopper(stack, semaphore));
        Future<String> stackPopper3 = executor.submit(new StackPopper(stack, semaphore));
        Future<String> stackPopper4 = executor.submit(new StackPopper(stack, semaphore));

        String result = stackPopper.get();
        result = stackPopper2.get();
        result = stackPopper3.get();
        result = stackPopper4.get();

        executor.shutdown();
    }
}
