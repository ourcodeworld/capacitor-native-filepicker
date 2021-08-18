// FilterByExtension.java
package com.ourcodeworld.plugins.capacitornativefilepicker;

import java.util.ArrayList;
import java.util.List;
import me.rosuh.filepicker.bean.FileItemBeanImpl;
import me.rosuh.filepicker.config.AbstractFileFilter;

public class FilterByExtension extends AbstractFileFilter{
    public List<String> allowedExtensions;

    public FilterByExtension(List<String> _extensions){
        this.allowedExtensions = _extensions;
    }

    // Override the doFilter method
    @Override
    public ArrayList<FileItemBeanImpl> doFilter(ArrayList<FileItemBeanImpl> listData){
        // If there are no filters, return the same list
        if(this.allowedExtensions.size() <= 0) return listData;

        // In this logic, we will return a new list that will contain only the items that we desire
        ArrayList<FileItemBeanImpl> newList = new ArrayList<>();

        // Iterate over every element of the list
        for (FileItemBeanImpl fileItem: listData) {
            // Otherwise add the directory to the list
            if(fileItem.isDir()){
                newList.add(fileItem);

            // If the item is a file, filter it by its extension
            }else{
                String extension = this.getFileExtension(fileItem.getFileName());

                // If the extension of the file is allowed, add it to the list
                if(this.allowedExtensions.contains(extension)){
                    newList.add(fileItem);
                }
            }
        }

        return newList;
    }

    public String getFileExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }
}