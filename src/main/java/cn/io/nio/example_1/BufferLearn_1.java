package cn.io.nio.example_1;

import java.nio.Buffer;
import java.nio.IntBuffer;

public class BufferLearn_1 {
    public static void main(String[] args) {
        // 分配20 * 4字节的空间
        IntBuffer buffer = IntBuffer.allocate(20);
        System.out.println("init:");
        printStatus(buffer);

        for (int i = 0; i < 5; i++) {
            buffer.put(i);
        }
        System.out.println("after put:");
        printStatus(buffer);


        // 写模式切换为读模式
        buffer.flip();
        printStatus(buffer);

    }

    private static void printStatus(Buffer buffer) {
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
    }

}
