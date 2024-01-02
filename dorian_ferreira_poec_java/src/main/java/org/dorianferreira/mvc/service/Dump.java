package org.dorianferreira.mvc.service;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Dump {

    private static String getPackagesProject() {
        String packages = Dump.class.getPackageName();
        String[] myPackage = packages.split("\\.");
        return myPackage[0] + "." + myPackage[1] + "." + myPackage[2];
    }
    public static void json(String json) {
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.toString(4)); // Print it with specified indentation
    }

    public static void dump(Object o) {
        JSONObject jsonObject = new JSONObject(o);
        String str = o.getClass().getSimpleName() + " " + jsonObject.toString(4);
        System.out.println(str);
    }
}
