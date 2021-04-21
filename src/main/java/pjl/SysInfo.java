
package pjl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

public class SysInfo extends javax.swing.JDialog {
    
    private String [] StrArray;
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private int returnStatus = RET_CANCEL;    

    public SysInfo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(PJL.frame);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        infoTA.setEditable(false);
        infoTA.append("==============================================\n");
        infoTA.append("System Environment:\n");
        infoTA.append("==============================================\n------------------------------------\n");
        StrArray=System.getenv().toString().split(",");
        StrArray[0]=StrArray[0].substring(1);
        StrArray[StrArray.length-1]=StrArray[StrArray.length-1].substring(0,StrArray[StrArray.length-1].length()-1);
        for (int i=0; i<StrArray.length; i++)
            infoTA.append(StrArray[i]+"\n------------------------------------\n");
        infoTA.append("==============================================\n");
        infoTA.append("System Properties:\n");
        infoTA.append("==============================================\n------------------------------------\n");
        StrArray=System.getProperties().toString().split(",");
        StrArray[0]=StrArray[0].substring(1);
        StrArray[StrArray.length-1]=StrArray[StrArray.length-1].substring(0,StrArray[StrArray.length-1].length()-1);
        for (int i=0; i<StrArray.length; i++)
            infoTA.append(StrArray[i]+"\n------------------------------------\n");
        infoTA.append("Number of CPUs   = "+Runtime.getRuntime().availableProcessors()+"\n");
        infoTA.append("JVM Memory free  = "+Runtime.getRuntime().freeMemory()/1000000+" Mb\n");
        infoTA.append("JVM Memory max   = "+Runtime.getRuntime().maxMemory()/1000000+" Mb\n");
        infoTA.append("JVM Memory total = "+Runtime.getRuntime().totalMemory()/1000000+" Mb\n");        
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });        
    }
    
    public int getReturnStatus() {
        return returnStatus;
    }    
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        infoTA = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("System Info");

        infoTA.setEditable(false);
        infoTA.setColumns(20);
        infoTA.setRows(5);
        jScrollPane1.setViewportView(infoTA);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea infoTA;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
