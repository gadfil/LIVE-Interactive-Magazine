package com.aod.clubapp.api.model.errors;

import android.os.Parcel;

import java.util.Arrays;

/**
 * Created by gadfil on 27.12.2014.
 */
public class ResponseError {
    private String[] errors;

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }



    @Override
    public String toString() {
        String error =Arrays.toString(errors);
        return error.substring(1, error.length() - 1);
    }


}
