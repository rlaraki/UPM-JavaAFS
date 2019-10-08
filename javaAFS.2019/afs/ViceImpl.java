// Implementación de la interfaz de servidor que define los métodos remotos
// para iniciar la carga y descarga de ficheros
package afs;
import java.rmi.*;
import java.io.*;
import java.rmi.server.*;

public class ViceImpl extends UnicastRemoteObject implements Vice {
    public ViceImpl() throws RemoteException  {
    }
    public ViceReader download(String fileName, String mode /* añada los parámetros que requiera */)
          throws RemoteException, FileNotFoundException{
    	ViceReader res = new ViceReaderImpl(fileName, mode);
       return res;
    
    }
    public ViceWriter upload(String fileName, String mode /* añada los parámetros que requiera */)
          throws RemoteException , FileNotFoundException {
        ViceWriter res = new ViceWriterImpl(fileName, mode);
        return res;
    }
}
