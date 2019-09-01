package cn.io.nio;

import sun.misc.Cleaner;

import java.net.Socket;
import java.util.Arrays;

public class BIOSocketClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8888);
        socket.getOutputStream().write("Hello World".getBytes());
        socket.close();
    }
}
