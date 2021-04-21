package pjl;

import static pjl.PJL.lookAndFeelsDisplay;
import static pjl.PJL.lookAndFeelsRealNames;

public class Actions {
    
    public static String currentLAF="de.muntjak.tinylookandfeel.TinyLookAndFeel";
    public static String currentTheme="lib/themes/Default.theme";
    
    public static void MyInstLF(String lf) {
        //UIManager.installLookAndFeel(lf,lf); 
        lookAndFeelsDisplay.add(lf);
        lookAndFeelsRealNames.add(lf);        
    }    
    
    public static void InstallLF () {
        MyInstLF("javax.swing.plaf.metal.MetalLookAndFeel");
        MyInstLF("de.muntjak.tinylookandfeel.TinyLookAndFeel");      
    }    
    
}
