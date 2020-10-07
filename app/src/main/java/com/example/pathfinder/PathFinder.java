package com.example.pathfinder;

import android.os.Environment;


import java.io.File;


public class PathFinder {
    public String getPath(String fileName) throws NoExternalStorageFoundException {
        String path = Environment.getExternalStorageState();
        //deprecated
        //use documents provider
        //DocumentsProvider documentsProvider = createDocumentsProvider();
        if(!isExternalStorageAvailable()) throw new NoExternalStorageFoundException();
        File foundFile = searchForFile(Environment.getDataDirectory(),fileName);
        if(foundFile == null) return null;
        return foundFile.getPath();
    }
    private boolean isExternalStorageAvailable(){
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
    }

    private File searchForFile(File dir, String fileName){
        File[] childFiles = dir.listFiles();
        for(File childFile : childFiles){
            if(childFile.isDirectory()){
                File found = searchForFile(childFile, fileName);
                if(found != null) return found;
            } else{
                if(fileName.equals(childFile.getName())){
                    return childFile;
                }
            }
        }
        return null;
    }

}
