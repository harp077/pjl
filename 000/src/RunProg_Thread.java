package pjl;

import static pjl.PJL.pcs;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class RunProg_Thread implements Runnable {
    
    private String msg;
    private Thread go;
    
    public RunProg_Thread (String s) 
        {
            msg = s;
            go = new Thread(this);
            go.start();
        }
    
    @Override
    public void run () {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    File prog=new File(msg);               System.out.println(prog.getName());
                    File progdir=new File(prog.getParent());    System.out.println(prog.getParent());
                    if (msg.toLowerCase().endsWith(".sh")) {
                        pcs=Runtime.getRuntime().exec("./"+prog.getName(),null,progdir);
                        //return;
                    }
                    else if (msg.toLowerCase().endsWith(".jar")) {
                        pcs=Runtime.getRuntime().exec("java -jar "+prog.getName(),null,progdir);
                        //Runtime.getRuntime().exec("java -jar "+progpath);
                        //return;
                    }
                    else 
                        pcs=Runtime.getRuntime().exec(msg,null,progdir);                    
                    //pcs=Runtime.getRuntime().exec(msg);
                } catch (IOException ex) {
                    //Logger.getLogger(Bejapis.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException iae) {}
            }
        }); 
    }
    
    public void stop ()  {  go = null;  }
    
}
