import com.github.bearboy.retry.SpringBootRetryDemo;
import com.github.bearboy.retry.service.RemoteRpcServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootRetryDemo.class)
class RetryServiceTest {

    @Autowired
    private RemoteRpcServer retryService;

    @Test
    void testService1() throws RuntimeException {
        retryService.getAccountByRpc("retry");
    }
}