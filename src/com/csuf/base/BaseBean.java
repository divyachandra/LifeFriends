package com.csuf.base;

import java.io.Serializable;

/**
 * Created by Divya on 26-09-2015.
 */
public class BaseBean implements Serializable {
    private Object pk;

    public Object getPk() {
        return pk;
    }

    public void setPk(Object pk) {
        this.pk = pk;
    }

    public Object createPK(Object value) {
        return value;
    }
}
