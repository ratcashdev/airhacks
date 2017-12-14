
package com.airhacks;

import java.util.Date;
import javax.validation.constraints.Future;

/**
 *
 * @author airhacks.com
 */
public class Development {

    @Future
    private Date date;

    public Development() {
        this.date = new Date();
    }


}
