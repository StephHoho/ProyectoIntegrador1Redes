package laweafome.proyectointegrador1redes;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;

/**
 * Created by ASUS on 3/17/2017.
 */

public class Comunicacion extends Observable implements Runnable {

    private InetAddress ipDestino;
    private int puertoDestino;
    private int miPuerto;
    private DatagramSocket miBuzon;
    public String mensaje;
    private static Comunicacion ref;

    public Comunicacion(){
        try {
            ipDestino= InetAddress.getByName("192.168.0.7");
            //miPuerto= 5001;

            puertoDestino= 5000;
            //  miBuzon= new DatagramSocket();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run() {
        while (true){
            try{
                setChanged();
                notifyObservers();
                clearChanged();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void enviar(final String data) {
        new Thread(new Runnable() {
            public void run() {
                // while (true) {
                try {
                    miBuzon= new DatagramSocket();
                    byte[] bytes = data.getBytes();
                    DatagramPacket enviarP = new DatagramPacket(bytes, bytes.length, ipDestino, puertoDestino);
                    miBuzon.send(enviarP);
                    Log.d("enviar","mensaje enviado");
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // }
            }
        }).start();
    }

    public String recibir() {
        try {
            byte[] paquete = new byte[1000];
            DatagramPacket packet = new DatagramPacket(paquete, paquete.length);
            miBuzon.receive(packet);
            Log.d("recibir","mensaje recibido");
            //System.out.println("mensaje recibido");
            mensaje = new String(packet.getData(), 0, packet.getLength());

            return mensaje;

        } catch (Exception e) {
        }
        return null;
    }

    public byte[] serializar(Object mensaje) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bytes);
        os.writeObject(mensaje);
        os.close();
        return bytes.toByteArray();

    }

    public Object deserializar(byte[] bytes) throws Exception {
        ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(byteArray);
        Object aux = is.readObject();
        is.close();
        return aux;

    }

    public static Comunicacion getInstance(){
        if(ref==null){
            ref= new Comunicacion();
            new Thread(ref).start();
        }
        return ref;
    }
}
