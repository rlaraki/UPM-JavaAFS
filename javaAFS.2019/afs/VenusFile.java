package afs;

import java.rmi.*; 

import java.io.*; 


public class VenusFile {
    public static final String cacheDir = "Cache/";
    public RandomAccessFile fichero;
    public String mode;
    public ViceReader reader;
    public ViceWriter writer;
    public Venus venus;
    public String fileName;
    public Vice ref;
    public long new_tam;
    public boolean writing;

    public VenusFile(Venus venus, String fileName, String mode) throws RemoteException, IOException, FileNotFoundException {
    	File af = new File(cacheDir+fileName);
        
            this.venus=venus;
            this.mode=mode;
            this.fileName=fileName;
            this.writing= false;
            try {
            if(mode.equals("rw")){
            if(!af.exists()){
                fichero = new RandomAccessFile(cacheDir+fileName, mode);
               // System.out.println("despues RAF");
                ref = venus.getRef();
                reader = ref.download(fileName, mode);
                byte [] arr;
                //lectura bloque a bloque del servidor y escritura en el cache mediante fichero.write(arr)
                    while( (arr = reader.read(venus.getBlockSize())) != null){
                        fichero.write(arr);
                    }
                    fichero.seek(0);
                    reader.close();
            }

            else { // si en el cache "lo recuperamos" en fichero
                fichero = new RandomAccessFile(cacheDir+fileName, mode);
            }
            this.new_tam = fichero.length();
            } else {//produce una FileNotFoundException si no existe en cache
                fichero = new RandomAccessFile(cacheDir+fileName, mode);}
    // le recuperamos aqui 
} catch(FileNotFoundException e){
            byte [] arr;
            ref = venus.getRef();
            reader = ref.download(fileName, mode);
            //modo "rw" para que no produzca una nueva exception
            fichero = new RandomAccessFile(cacheDir+fileName, "rw");
            //lectura bloque a bloque del servidor y escritura en el cache mediante fichero.write(arr)
            while( (arr = reader.read(venus.getBlockSize())) != null){
                fichero.write(arr);
            }
            fichero.close();
            // lo reponemos en modo "r" porque es el modo que era al principio.
            fichero = new RandomAccessFile(cacheDir+fileName, "r");
            reader.close();
}
    }

    public int read(byte[] b) throws RemoteException, IOException {
        return fichero.read(b);
    }
    public void write(byte[] b) throws RemoteException, IOException {
         writing = true;
         fichero.write(b);
    }
    public void seek(long p) throws RemoteException, IOException {
         fichero.seek(p);
    }
    public void setLength(long l) throws RemoteException, IOException {
         fichero.setLength(l);
    }
    public void close() throws RemoteException, IOException {
        long new_tam_1= 0;
        if (mode.equals("rw")){
        if (this.writing){
           fichero.seek(0);
            byte[] new_arr = new byte[this.venus.getBlockSize()];
            ref = this.venus.getRef();
            writer = ref.upload(this.fileName, mode);
            int num;
            //Escritura bloque a bloque
            while((num= this.fichero.read(new_arr))>0){
            if(num < this.venus.getBlockSize()){
                byte[] res = new byte[num];
            int i= 0;
            while(i<num){
                res[i]= new_arr[i];
                ++i;
            }
            writer.write(res);
            } else writer.write(new_arr);
        }
        //Actualizar tamaño si modificado
        if((new_tam_1 = this.fichero.length())!= this.new_tam){
            writer.setLength(new_tam_1);
        }
        writer.close();
    }
    // si no hemos escrito en el fichero (porque rw puede crear un fichero sin escribir en ello)
     else if( (new_tam_1=fichero.length())!= this.new_tam){
        ref = this.venus.getRef();
        writer = ref.upload(this.fileName, mode);
        writer.setLength(new_tam_1);
        writer.close();
    }
}

        fichero.close();
    }
}