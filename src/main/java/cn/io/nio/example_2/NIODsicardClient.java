package cn.io.nio.example_2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIODsicardClient {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        SocketChannel socketChannel = SocketChannel.open(address);
        while (!socketChannel.finishConnect()) {
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("Hello NIO".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.shutdownOutput();
        socketChannel.close();
    }
}
