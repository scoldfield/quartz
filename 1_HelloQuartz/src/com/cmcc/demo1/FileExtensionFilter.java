package com.cmcc.demo1;

import java.io.File;
import java.io.FileFilter;

/*
 * java.io.FileFilter是一个文件过滤器接口，可以通过实现该接口来完成不同的文件过滤需求
 */
public class FileExtensionFilter implements FileFilter {

    private String extension;
    public FileExtensionFilter(String extension) {
        super();
        this.extension = extension;
    }

    @Override
    public boolean accept(File file) {
        //Lowcase the filename for easier comparion
        String lCaseFilename = file.getName().toLowerCase();
        return (file.isFile() && (lCaseFilename.indexOf(extension) > 0))?true:false;
    }

}
