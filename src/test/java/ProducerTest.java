import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.assesment.Producer;
import com.assesment.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ProducerTest {

    private BlockingQueue<Message> queue;
    private Producer producer;

    @Mock
    private BlockingQueue<Message> mockQueue;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        queue = new LinkedBlockingQueue<>();
        producer = new Producer(queue);
    }

    @Test
    void testProducerAddsMessagesToQueue() {
        producer.produce();
        assertEquals(10, queue.size(), "Queue should contain 10 messages after production");
    }

    @Test
    void testProducerHandlesInterruptedException() throws InterruptedException {
        doThrow(new InterruptedException()).when(mockQueue).put(any(Message.class));
        Producer mockProducer = new Producer(mockQueue);
        assertDoesNotThrow(mockProducer::produce, "Producer should handle InterruptedException gracefully");
    }
}
