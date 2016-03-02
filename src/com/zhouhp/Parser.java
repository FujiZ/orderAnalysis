package com.zhouhp;


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
                lineBuffer=bufferedReader.readLine();
            }
            catch (InvalidEntryException|IOException e){
                System.out.println(e.getMessage());
                valid=false;
            }
        }
        return entry;
    }

    private BufferedReader bufferedReader;
    private String lineBuffer;

}
