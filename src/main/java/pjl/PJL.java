package pjl;

/*import com.lipstikLF.LipstikLookAndFeel;
import com.lipstikLF.theme.DefaultTheme;
import com.lipstikLF.theme.KlearlooksTheme;
import com.lipstikLF.theme.LightGrayTheme;*/
import org.swixml.SwingEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PJL extends WindowAdapter implements ActionListener {

    private SwingEngine swix;
    public JPanel pnl_Center;
    public JToolBar tb;
    public JMenu mu_file;
    public static JFrame frame;

    public static List<String> lookAndFeelsDisplay = new ArrayList<>();
    public static List<String> lookAndFeelsRealNames = new ArrayList<>();
    public static List<String> tinyTemes = new ArrayList<>();
    //public static List<String> lipstikTemes = new ArrayList<>();
    //public static Process pcs = null;
    //public static RunProg_Thread execCmd;

    public Action execAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            String bufer = ((JMenuItem) e.getSource()).getText();
            new Thread(() -> {
                execProg(bufer);
            }).start();
        }
    };

    private PJL() {
        try {
            swix = new SwingEngine(this);
            File xmlres = new File("menu.config");
            //swix.render("xml/actions.xml");
            swix.render(xmlres);
            //swix.setActionListener(pnl_North, this);
            swix.setActionListener(pnl_Center, this);
            //mi_exit.addActionListener(this);
            tb.setBorder(javax.swing.BorderFactory.createTitledBorder("Tool Bar"));
            //this.swix.getRootComponent().setLocation(150,150);
            //this.swix.getRootComponent().setSize(640,110);  
            ImageIcon icone = new ImageIcon(getClass().getResource("/img/SubFrameIcon.png"));
            frame.setIconImage(icone.getImage());
            //frame.setTitle("Portable Java Launcher,   version from 13-09-16.");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.setLocation(200, 250);
            //frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            //frame.setSize(700,110);
            setLF();
            swix.getRootComponent().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("AC_EXIT".equals(command)) {
            this.windowClosing(null);
        } else if ("AC_ABOUT".equals(command)) {
            About();
        } else if ("AC_HELP".equals(command)) {
            help();
        } else if ("AC_SKIN".equals(command)) {
            changeLF();
        } else if ("AC_SYS".equals(command)) {
            SysInfo si = new SysInfo(frame, true);
            si.setVisible(true);
        } else if ("AC_SAVE".equals(command)) {
            changeLF();
        } else {
            System.out.println("Click");
        }
    }

    public void windowClosing(WindowEvent e) {
       int r = JOptionPane.showConfirmDialog(frame, "Really Quit ?", "Quit ?", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.out.println("Good Bye!");
            super.windowClosing(e);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        Actions.InstallLF();
        SwingEngine.DEBUG_MODE = true;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PJL();
            }
        });
    }

////////////////////////////////////////////////////////////////////////////////////
    public void setLF() {
        if (Actions.currentLAF.contains("tinylookandfeel")) {
            de.muntjak.tinylookandfeel.Theme.loadTheme(new File(Actions.currentTheme));
        }
        /*if (Actions.currentLAF.contains("lipstik")) {
            switch (Actions.currentTheme) {
                case "Default":    LipstikLookAndFeel.setMyCurrentTheme(new DefaultTheme()); break;
                case "Klearlooks": LipstikLookAndFeel.setMyCurrentTheme(new KlearlooksTheme()); break;
                case "LightGray":  LipstikLookAndFeel.setMyCurrentTheme(new LightGrayTheme()); break;
                default: LipstikLookAndFeel.setMyCurrentTheme(new DefaultTheme()); 
            }
        }  */
        try {
            UIManager.setLookAndFeel(Actions.currentLAF);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PJL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PJL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PJL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PJL.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(this.swix.getRootComponent());
        //this.swix.getRootComponent().pack();        
    }

    public void changeLF() {
        Actions.currentTheme = "";
        //String changeLook = (String) JOptionPane.showInputDialog(this.swix.getRootComponent(), "Choose Look and Feel Here:", "Select Look and Feel", JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/img/color_swatch.png")), lookAndFeelsDisplay.toArray(), null);
        String changeLook = "de.muntjak.tinylookandfeel.TinyLookAndFeel";
        if (changeLook.contains("tinylookandfeel")) {
            Actions.currentTheme = (String) JOptionPane.showInputDialog(this.swix.getRootComponent(), "Set TinyLF Theme:", "Select TinyLF Theme", JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/img/color_swatch.png")), tinyTemes.toArray(), null);
        }
        /*if (changeLook.contains("lipstik")) {
            Actions.currentTheme=(String) JOptionPane.showInputDialog(this.swix.getRootComponent(), "Set LipstikLF Theme:", "Select LipstikLF Theme", JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/img/color_swatch.png")), lipstikTemes.toArray(), null);
        } */
        if (changeLook != null) {
            for (int a = 0; a < lookAndFeelsDisplay.size(); a++) {
                if (changeLook.equals(lookAndFeelsDisplay.get(a))) {
                    Actions.currentLAF = lookAndFeelsRealNames.get(a);
                    setLF();
                    break;
                }
            }
        }
    }

    public void About() {
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/img/pjl_73x73.jpg"));
        JOptionPane.showMessageDialog(this.swix.getRootComponent(),
                "PJL - Free Cross-Platform\n"
                + "Portable Java Launcher for\n"
                + "executable files = jar, exe, etc.\n"
                + "Same as Win Portable Start Menu\n"
                + "and other launcher programs.\n"
                + "Useful thing for USB and others.\n"
                + "Roman Koldaev,\n"
                + "Saratov city, Russia\n"
                + "harp07@mail.ru", "About", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public void help() {
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/img/24x24/info-book-green.png"));
        JOptionPane.showMessageDialog(this.swix.getRootComponent(),
                "Simply edit file \"menu.config\"\n"
                + "bitween labels:\n"
                + "\"START CUSTOM USER MENU\"\n"
                + "and \"END CUSTOM USER MENU\"",
                "Help", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static void execProg(String progpath) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Thread(() -> {
                    try {
                        //String [] StrArray={};
                        //StrArray=System.getenv().toString().split(",");
                        //StrArray[0]=StrArray[0].substring(1);
                        //StrArray[StrArray.length-1]=StrArray[StrArray.length-1].substring(0,StrArray[StrArray.length-1].length()-1);                    
                        File prog = new File(progpath);
                        System.out.println(prog.getName());
                        File progdir = new File(prog.getParent());
                        System.out.println(prog.getParent());
                        if (progpath.toLowerCase().endsWith(".sh")) {
                            Runtime.getRuntime().exec("./" + prog.getName(), null, progdir);
                        } else if (progpath.toLowerCase().endsWith(".jar")) {
                            Runtime.getRuntime().exec("java -jar " + prog.getName(), null, progdir);
                            //Runtime.getRuntime().exec("java -jar "+progpath);
                            //new EngineIO("java -jar "+progpath);
                        } else {
                            Runtime.getRuntime().exec(progpath, null, progdir);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PJL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //});
            }
        });
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Thread(() -> {
                    File prog = new File(progpath);
                    System.out.println(prog.getName());
                    File progdir = new File(prog.getParent());
                    System.out.println(prog.getParent());
                    ProcessBuilder pb = new ProcessBuilder();
                    pb.directory(progdir);
                    if (progpath.toLowerCase().endsWith(".sh")) {
                        pb.command("./" + prog.getName());
                    } else if (progpath.toLowerCase().endsWith(".jar")) {
                        pb.command("java -jar " + prog.getName());
                        //Runtime.getRuntime().exec("java -jar "+progpath);
                        //new EngineIO("java -jar "+progpath);
                    } else {
                        pb.command(progpath);
                    }
                    try {
                        System.out.println(System.getProperty("user.dir"));
                        Process pcs = pb.start();
                    } catch (IOException ex) {
                        Logger.getLogger(PJL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            }
        });*/
    }
}
