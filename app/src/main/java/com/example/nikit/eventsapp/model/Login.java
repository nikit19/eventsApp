package com.example.nikit.eventsapp.model;

/**
 * Created by nikit on 11/5/18.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    public String email;
    public String password;
}
