package com.assesment;

import com.assesment.model.Message;

import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

public class Producer {
    private final BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void produce() {
        IntStream.range(1, 11).forEach(i -> {
            Message message = new Message(i, "Message " + i);
            try {
                queue.put(message);
                System.out.println("Produced: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Producer interrupted.");
            }
        });
    }
}