package com.zhouhp;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.*;
import java.util.Iterator;

/**
 * Created by fuji on 15-12-16.
 */
public class Parser {

    public Parser(String fileName){
        File file=new File(fileName);
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            lineBuffer=bufferedReader.readLine();
            OrderEntry.setDataFormat(lineBuffer);
            lineBuffer=bufferedReader.readLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Iterator<OrderEntry> getEntryIterator(){
        return new EntryIterator();
    }

    private class EntryIterator implements Iterator<OrderEntry>{

        public boolean hasNext(){
            return lineBuffer!=null&&!lineBuffer.isEmpty();
        }

        public OrderEntry next(){
            return nextEntry();
        }

    }

    private OrderEntry nextEntry(){
        //有可能发生数据域无效的情况,当catch异常时，要放弃该条，读取下一条记录
        boolean valid=false;
        OrderEntry entry = null;

        while (!valid){
            valid=true;
            try {
                entry=OrderEntry.parseEntry(lineBuffer);
            }
            catch (InvalidEntryException e){
                valid=false;
            }
            try {
                lineBuffer=bufferedReader.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return entry;
    }

    private BufferedReader bufferedReader;
    private String lineBuffer;

}
