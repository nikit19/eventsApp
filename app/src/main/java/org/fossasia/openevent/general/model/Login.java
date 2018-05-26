package org.fossasia.openevent.general.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by nikit on 11/5/18.
 */
@Data
@AllArgsConstructor
public class Login {
    private String email;
    private String password;
}