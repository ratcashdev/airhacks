
package com.airhacks.cdi.se;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
@ApplicationScoped
public class Messenger {

    @Inject
    MessageProvider provider;

    public String getMessage() {
        return this.provider.getMessage();
    }

}
