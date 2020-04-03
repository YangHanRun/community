package life.mawen.community;

import life.mawen.community.provider.AliCloudProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private AliCloudProvider aliCloudProvider;

    @Test
    void contextLoads() {
    }

}
