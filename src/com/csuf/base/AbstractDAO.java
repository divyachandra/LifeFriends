package com.csuf.base;

import com.csuf.exceptions.DbException;

import java.util.Date;
import java.util.Map;

/**
 * Created by Divya on 27-09-2015.
 */
public class AbstractDAO {
    protected static final String TABLE_KEY = "__TABLE__";

    protected static void putString(Map<String, Object> data, String key, String value, boolean trim) {
        if (trim)
            value = value.trim();
        data.put(key, value);
    }

    protected static void putInt(Map<String, Object> data, String key, int value) {
        data.put(key, new Integer(value));
    }

    protected static void putLong(Map<String, Object> data, String key, long value) {
        data.put(key, new Long(value));
    }

    protected static void putShort(Map<String, Object> data, String key, short value) {
        data.put(key, new Short(value));
    }

    protected static void putBoolean(Map<String, Object> data, String key, boolean value) {
        data.put(key, new Integer(value ? 1 : 0));
    }

    protected static void putDate(Map<String, Object> data, String key, Date value) {
        data.put(key, value);
    }

    protected static void putBytes(Map<String, Object> data, String key, byte[] value) {
        data.put(key, value);
    }

    protected static void putObject(Map<String, Object> data, String key, Object value) {
        data.put(key, value);
    }

    protected static void putIntNull(Map<String, Object> data, String key, int value) {
        if (value == 0)
            data.put(key, null);
        else
            putInt(data, key, value);
    }

    protected static Object getObject(Map<String, Object> data, String key) {
        key = key.toUpperCase();
        Object value = data.get(key);
        if (value == null && !data.containsKey(key))
            throw new DbException("Column name '" + key + "' not found (in TABLE= '" + data.get(TABLE_KEY)
                    + "').");
        return value;
    }

    protected static String getString(Map<String, Object> data, String key) {
        final Object value = getObject(data, key);
        if (value != null)
            return value.toString().trim();
        return null;
    }

    protected static int getInt(Map<String, Object> data, String key) {
        final Object value = getObject(data, key);
        if (value instanceof Number)
            return ((Number) value).intValue();
        else if (value instanceof Boolean)
            return (Boolean) value ? 1 : 0;
        try {
            return Integer.parseInt((String) value);
        } catch (Exception e) {
            // Ignore exception, handle below
        }
        return 0;
    }


    protected static boolean getBool(Map<String, Object> data, String key) {
        final Object value = getObject(data, key);
        if (value  instanceof Boolean)
            return ((Boolean) value ).booleanValue();
        if (value  instanceof Number)
            return ((Number) value ).intValue() != 0;
        String s = String.valueOf(value);
        if (s != null)
            s = s.toUpperCase().trim();
        if (s != null && s.length() != 0) {
            if (s.length() == 1) {
                char ch = s.charAt(0);
                if (ch == 'Y')
                    return true;
                if (ch == 'T')
                    return true;
                if (ch == '1')
                    return true;
                return false;
            }
            if (s.equals("TRUE"))
                return true;
            if (s.equals("YES"))
                return true;
            if (s.equals("ON"))
                return true;
            return false;
        }
        return false;
    }

    protected static Date getDate(Map<String, Object> data, String key) {
        Object stmp = getObject(data, key);
        Date date = stmp == null ? null : (Date) stmp;
        return date;
    }
}