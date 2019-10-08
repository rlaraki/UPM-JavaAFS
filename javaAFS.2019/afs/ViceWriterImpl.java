// Implementación de la interfaz de servidor que define los métodos remotos
// para completar la carga de un fichero
package afs;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.rmi.*;
import java.rmi.server.*;

public class ViceWriterImpl extends UnicastRemoteObject implements ViceWriter {
    private static final String AFSDir = "AFSDir/";
    RandomAccessFile fichero;
    String file;

    public ViceWriterImpl(String fileName , String mode/* añada los parámetros que requiera */)
		    throws RemoteException, FileNotFoundException {
                fichero = new RandomAccessFile(AFSDir+fileName, mode);
                file = fileName;
    }
    public void write(byte [] b) throws RemoteException, IOException{
        fichero.write(b);
    }
    public void close() throws RemoteException, IOException {
        fichero.close();
    }

    public void setLength(long length) throws IOException{
        fichero.setLength(length);
    }
}       

