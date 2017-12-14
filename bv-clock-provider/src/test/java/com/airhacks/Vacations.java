
package com.airhacks;

import java.util.Date;
import javax.validation.constraints.PastOrPresent;

/**
 *
 * @author airhacks.com
 */
public class Vacations {

    @PastOrPresent
    private Date date;

    public Vacations() {
        this.date = new Date();
    }

}
