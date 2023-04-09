package com.github.codergate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoderGateApplicationTests {

    @Test
    public void applicationContextTest() {
        CoderGateApplication.main(new String[] {});
        assertNotNull(CoderGateApplication.class);
    }

}

