/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.data;

/**
 *
 * @author ely
 */
public class FilePathObject {
    private String name;
    private String path;

    public FilePathObject() {
    }

    public FilePathObject(String name, String path) {
        this.name = name;
        this.path = path;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
