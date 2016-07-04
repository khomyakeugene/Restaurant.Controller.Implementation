package com.company.restaurant.controllers.proto;

import com.company.util.DataIntegrityException;

/**
 * Created by Yevhen on 04.07.2016.
 */
public class Controller {
    protected void errorMessage(String message) {
        throw new DataIntegrityException(message);
    }
}
