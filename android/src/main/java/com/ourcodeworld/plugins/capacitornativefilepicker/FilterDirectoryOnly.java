// FilterDirectoryOnly.java
package com.ourcodeworld.plugins.capacitornativefilepicker;

import android.util.Log;

import java.util.ArrayList;
import me.rosuh.filepicker.bean.FileItemBeanImpl;
import me.rosuh.filepicker.config.AbstractFileFilter;

public class FilterDirectoryOnly extends AbstractFileFilter{
    // Override the doFilter method
    @Override
    public ArrayList<FileItemBeanImpl> doFilter(ArrayList<FileItemBeanImpl> listData){
        // In this logic, we will return a new list that will contain only the items that we desire
        ArrayList<FileItemBeanImpl> newList = new ArrayList<FileItemBeanImpl>();

        // Iterate over every element of the list
        for (FileItemBeanImpl fileItem: listData) {
            // If the element is a directory, include it
            if(fileItem.isDir()){
                newList.add(fileItem);
            }
        }

        return newList;
    }
}