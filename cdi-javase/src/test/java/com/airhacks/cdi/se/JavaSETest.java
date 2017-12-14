/*
 */
package com.airhacks.cdi.se;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class JavaSETest {

    @Test
    public void bootstrap() {
        SeContainerInitializer containerInit = SeContainerInitializer.newInstance();
        try (SeContainer container = containerInit.initialize()) {
            Messenger messenger = container.select(Messenger.class).get();
            String message = messenger.getMessage();
            System.out.println("message = " + message);
            assertNotNull(message);
        }
    }

}
