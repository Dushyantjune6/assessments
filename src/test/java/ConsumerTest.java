import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.assesment.Consumer;
import com.assesment.Producer;
import com.assesment.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConsumerTest {

    private BlockingQueue<Message> queue;
    private Consumer consumer;

    @Mock
    private BlockingQueue<Message> mockQueue;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        queue = new LinkedBlockingQueue<>();
        consumer = new Consumer(queue);
    }

    @Test
    void testConsumerProcessesMessagesSuccessfully() {
        Producer producer = new Producer(queue);
        producer.produce();
        consumer.consume();
        assertTrue(queue.isEmpty(), "Queue should be empty after all messages are consumed");
    }

    @Test
    void testConsumerHandlesInterruptedException() throws InterruptedException {
        doThrow(new InterruptedException()).when(mockQueue).take();
        Consumer mockConsumer = new Consumer(mockQueue);
        assertDoesNotThrow(mockConsumer::consume, "Consumer should handle InterruptedException gracefully");
    }
}
