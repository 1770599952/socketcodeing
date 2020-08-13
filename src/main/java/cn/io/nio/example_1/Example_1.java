package cn.io.nio.example_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Example_1 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("D:\\green.png");
        FileChannel inputChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("D:\\copy.png");
        FileChannel outputChannel = outputStream.getChannel();

        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (-1 != inputChannel.read(byteBuffer)) {
                byteBuffer.flip();
                int outLength = 0;
                while ((outLength = outputChannel.write(byteBuffer)) != 0) {
                    System.out.println("输出字节数:" + outLength);
                }
                byteBuffer.clear();
            }
            outputChannel.force(true);
        } finally {
            outputChannel.close();
            outputStream.close();
            outputChannel.close();
            inputChannel.close();
        }

    }
}
