// Implementación de la interfaz de servidor que define los métodos remotos
// para completar la descarga de un fichero
package afs;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;

public class ViceReaderImpl extends UnicastRemoteObject implements ViceReader {
    private static final String AFSDir = "AFSDir/";
    RandomAccessFile fichero;
    String file;
    
    public ViceReaderImpl(String fileName, String mode /* añada los parámetros que requiera */)
		    throws RemoteException, FileNotFoundException {
            
            fichero = new RandomAccessFile(AFSDir+fileName, mode);
            file = fileName;
    }
   
    public byte[] read(int tam) throws RemoteException, IOException {
        byte [] arr = new byte[tam];
        int num = fichero.read(arr);
        if(num < 0){
            return null;
        }
        else if(num < tam){
            byte[] res = new byte[num];
            int i= 0;
            while(i<num){
                res[i]= arr[i];
                ++i;
            }
            return res;
        }
        return arr;
    }
    public void close() throws RemoteException, IOException{
        fichero.close();
    }
}       


