package com.permanent_liufoxmail.pl.background.Thread;

import android.util.Log;
import android.util.TimeUtils;

import com.permanent_liufoxmail.pl.background.ClientManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.permanent_liufoxmail.pl.background.ClientManager.sendMessageAll;

/**
 * Created by 10142 on 2018/2/17.
 */

public class ServerThread implements Runnable
{
    private int port = 10010;
    private boolean isExit = false;
    private ServerSocket serverSocket = null;

    public ServerThread()
    {
        try
        {
            serverSocket = new ServerSocket(port);
            System.out.println("The server has been started succeed!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Failed to start the server!!!!!!");
        }
    }

    @Override
    public void run()
    {
        try
        {
            while (! isExit)
            {
                System.out.println("Waiting for the connection of mobile...");
                final Socket socket = serverSocket.accept();
                final String address = socket.getRemoteSocketAddress().toString();
                System.out.println("Connection built Succeed!");

                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            synchronized (this)
                            {
                                ClientManager.clientList.put(address, socket);
                            }
                            InputStream inputStream = socket.getInputStream();
                            byte[] buffer = new byte[1024];
                            int len;

                            while ((len = inputStream.read(buffer)) != -1)
                            {
                                String text = new String(buffer, 0, len);
                                System.out.println("The message is " + text);
                                sendMessageAll(text);
                            }
                        }
                        catch (Exception e)
                        {
                            Log.d("Exception", "ServerThread");
                        }
                        finally
                        {
                            synchronized (this)
                            {
                                System.out.println("Connection closed!");
                            }
                        }
                    }
                }).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        isExit = true;

        if (serverSocket != null)
        {
            try
            {
                serverSocket.close();
                System.out.println("Server has been closed!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}