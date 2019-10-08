package afs;

import java.rmi.*; 

public class Venus {
    private String host;
    private int port;
    private int blocksize;
    private Vice ref;
  

    public Venus() throws Exception{
        this.host = System.getenv().get("REGISTRY_HOST");
        this.port = Integer.parseInt(System.getenv().get("REGISTRY_PORT"));
        this.blocksize = Integer.parseInt(System.getenv().get("BLOCKSIZE"));
        this.ref = (Vice)Naming.lookup("rmi://"+ this.host + ":" + this.port +"/AFS");
    }


public String getHost() {
    return this.host;
}

public int getPort() {
    return this.port;
}

public int getBlockSize() {
    return this.blocksize;
}

public Vice getRef(){
    return this.ref;
}


}
















/*public class Venus {
    public Venus() throws Exception{
        this.dir =(Vice)Naming.lookup("rmi://"+ this.getHost() + ":" + this.getPort() +"/AFS");
    }
    
    private static String host;
    private static int port;
    private static int blocksize;
    public Vice dir; 
    
    public String getHost() {
    	return host;
    }
    
    public int getPort() {
    	return port;
    }
    
    public int getBlockSize() {
    	return blocksize;
    }

   /* public Vice getDir() throws Exception{
        dir =(Vice)Naming.lookup("rmi://"+ this.getHost() + ":" + this.getPort() +"/AFS");
        return dir;
    }
    
    
    
    static public void main (String args[]) {
        if (args.length<2) {
            System.err.println("Uso: ClienteEco hostregistro numPuertoRegistro ...");
            return;
        }

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {

            
            host = System.getenv("REGISTRY_HOST");
            port = Integer.parseInt(System.getenv("REGISTRY_PORT"));
            blocksize = Integer.parseInt(System.getenv("BLOCKSIZE"));
            
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteEco:");
            e.printStackTrace();
        }
    }
*/

    
    
    
    
    
    	  




