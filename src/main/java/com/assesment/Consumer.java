package com.assesment;

import com.assesment.model.Message;

import java.util.concurrent.BlockingQueue;

public class Consumer {
    private final BlockingQueue<Message> queue;
    private static int successCount = 0;
    private static int errorCount = 0;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void consume() {
        while (!queue.isEmpty()) {
            try {
                Message message = queue.take();
                System.out.println("Consumed: " + message);
                successCount++;
            } catch (InterruptedException e) {
                errorCount++;
                Thread.currentThread().interrupt();
                System.err.println("Consumer interrupted.");
            }
        }
    }

    public static void printStats() {
        System.out.println("Total messages processed successfully: " + successCount);
        System.out.println("Total errors encountered: " + errorCount);
    }
}