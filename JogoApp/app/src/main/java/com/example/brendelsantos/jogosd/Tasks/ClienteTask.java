package com.example.brendelsantos.jogosd.Tasks;

/**
 * Created by Brendel Santos on 10/05/2016.
 */

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

import com.example.brendelsantos.jogosd.Model.Jogador;

public class ClienteTask extends AsyncTask<Void, Void, String> {

    private String enderecoDestino;
    private int portaDestino;
    private String mensagemEnviada;
    private String resposta;
    private OutputStream os;
    private OutputStreamWriter osw;
    BufferedWriter bw;
    private String mensagemRetorno;

    public ClienteTask(String addr, int port, String mensagemEnviada){
        this.enderecoDestino = addr;
        this.portaDestino = port;
        this.mensagemEnviada = mensagemEnviada;
        this.resposta = "";
    }

    public ClienteTask(String addr, int port, String mensagemEnviada, String mensagemRetorno){
        this.enderecoDestino = addr;
        this.portaDestino = port;
        this.mensagemEnviada = mensagemEnviada;
        this.resposta = "";
        this.mensagemRetorno = mensagemRetorno;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(enderecoDestino, portaDestino);
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            String mensagemEnviada = this.mensagemEnviada + "\n";
            bw.write(mensagemEnviada);
            bw.flush();

            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream(4096);
            byte[] buffer = new byte[4096];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1){

                byteArrayOutputStream.write(buffer, 0, bytesRead);
                resposta += byteArrayOutputStream.toString("utf-8");
            }


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resposta = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resposta = "IOException: " + e.toString();
        }finally{
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return resposta;
    }

    @Override
    protected void onPostExecute(String result) {
        mensagemRetorno = resposta;
        super.onPostExecute(result);
    }

}