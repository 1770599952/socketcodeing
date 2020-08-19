package cn.io.nio.example_2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIODiscardServer {

    private static ServerSocketChannel serverSocketChannel;
    private static Selector selector;

    public static void init() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
        serverSocketChannel.configureBlocking(false);
        System.out.println("server started, listening on :" + serverSocketChannel.getLocalAddress());

        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private static void selectEvent() throws IOException {
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    socketChannel.configureBlocking(false);

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    byteBuffer.clear();
                    int readLength = -1;
                    while ((readLength = socketChannel.read(byteBuffer)) != 0) {
                        byteBuffer.flip();
                        String receiveStr = new String(byteBuffer.array(), 0, readLength);
                        System.out.println(receiveStr);
                        byteBuffer.clear();
                        byteBuffer.put(receiveStr.getBytes());
                        socketChannel.write(byteBuffer);
                    }
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        selectEvent();
    }
}
