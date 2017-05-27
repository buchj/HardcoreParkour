/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t0ast.parkour.visualization;

import com.t0ast.parkour.EvolutionApplication;

/**
 *
 * @author T0astBread
 */
public class MainFrame extends javax.swing.JFrame
{
    private EvolutionApplication evo;

    /**
     * Creates new form MainFrame
     */
    public MainFrame()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        btnDoGenerations = new javax.swing.JButton();
        spnGenerations = new javax.swing.JSpinner();
        sldZoom = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        visualizationPanel1 = new com.t0ast.parkour.visualization.VisualizationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                formWindowOpened(evt);
            }
        });

        btnDoGenerations.setText("Do Generations");
        btnDoGenerations.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoGenerationsActionPerformed(evt);
            }
        });

        spnGenerations.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 100));

        sldZoom.setMaximum(2000);
        sldZoom.setMinimum(100);
        sldZoom.setValue(200);
        sldZoom.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                sldZoomStateChanged(evt);
            }
        });
        sldZoom.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                sldZoomMouseReleased(evt);
            }
        });

        jLabel2.setText("Zoom");

        javax.swing.GroupLayout visualizationPanel1Layout = new javax.swing.GroupLayout(visualizationPanel1);
        visualizationPanel1.setLayout(visualizationPanel1Layout);
        visualizationPanel1Layout.setHorizontalGroup(
            visualizationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        visualizationPanel1Layout.setVerticalGroup(
            visualizationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(visualizationPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDoGenerations)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnGenerations, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sldZoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDoGenerations)
                        .addComponent(spnGenerations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sldZoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        this.evo = new EvolutionApplication(this.visualizationPanel1);
    }//GEN-LAST:event_formWindowOpened

    private void btnDoGenerationsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoGenerationsActionPerformed
    {//GEN-HEADEREND:event_btnDoGenerationsActionPerformed
        this.btnDoGenerations.setEnabled(false);
        new Thread(() ->
        {
            this.evo.doGenerations((int) this.spnGenerations.getValue());
            this.btnDoGenerations.setEnabled(true);
        }).start();
    }//GEN-LAST:event_btnDoGenerationsActionPerformed

    private void sldZoomStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_sldZoomStateChanged
    {//GEN-HEADEREND:event_sldZoomStateChanged
        this.visualizationPanel1.setZoom(this.sldZoom.getValue()/100f);
    }//GEN-LAST:event_sldZoomStateChanged

    private void sldZoomMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_sldZoomMouseReleased
    {//GEN-HEADEREND:event_sldZoomMouseReleased
        this.jScrollPane1.revalidate();
    }//GEN-LAST:event_sldZoomMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch(ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch(InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch(IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch(javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoGenerations;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider sldZoom;
    private javax.swing.JSpinner spnGenerations;
    private com.t0ast.parkour.visualization.VisualizationPanel visualizationPanel1;
    // End of variables declaration//GEN-END:variables
}
