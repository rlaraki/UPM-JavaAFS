// Interfaz de servidor que define los métodos remotos
// para completar la carga de un fichero
package afs;
import java.io.IOException;
import java.rmi.*;


public interface ViceWriter extends Remote {
    public void write(byte [] b) throws RemoteException, IOException;
    public void close() throws RemoteException, IOException;
    public void setLength(long length) throws IOException;
    /* añada los métodos remotos que requiera */
}       

