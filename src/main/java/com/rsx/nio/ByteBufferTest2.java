package com.rsx.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest2 {
    public static void main(String[] args) throws IOException {
        //FileInputStream inputStream = new FileInputStream("C:\\Users\\RenShixu\\Desktop\\test.txt");
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\RenShixu\\Desktop\\test.txt","rw");
        FileChannel fc = file.getChannel();
        MappedByteBuffer mb = fc.map(FileChannel.MapMode.READ_WRITE,0,5);
        mb.put(0,(byte)'a');
        mb.put(3,(byte)'c');

        file.close();
        fc.close();
    }
}
