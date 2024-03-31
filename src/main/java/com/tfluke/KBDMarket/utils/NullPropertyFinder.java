package com.tfluke.KBDMarket.utils;

import java.util.HashSet;
import java.util.Set;
//import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

//class to return the null properties
public class NullPropertyFinder {
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
//        never let id get modified
        emptyNames.add("id");
        String[] result = new String[emptyNames.size()+1];
        return emptyNames.toArray(result);
    }

}