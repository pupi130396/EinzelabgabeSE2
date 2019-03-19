package com.example.einzelabgabese2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient extends Thread{

    static String sentence;
    static String  modifiedSentence;


    TCPClient(String sentence){
        this.sentence = sentence;
    }

    public void run() {

        try {

            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(sentence + '\n');

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER:" + modifiedSentence);

            clientSocket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }
}
