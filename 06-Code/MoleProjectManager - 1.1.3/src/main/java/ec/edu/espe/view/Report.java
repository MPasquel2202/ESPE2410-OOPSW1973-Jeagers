
package ec.edu.espe.view;

import ec.edu.espe.Controller.ProjectController;
import ec.edu.espe.Controller.ReportController;
import ec.edu.espe.model.ProjectReport;
import ec.edu.espe.model.QuoteChangeLog;
import ec.edu.espe.model.StatusChangeLog;
import ec.edu.espe.model.Support;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Dennis Paucar
 */
public class Report extends javax.swing.JFrame {
    private ProjectController projectController = new ProjectController();
    /**
     * Creates new form StatusChangeLogs
     */
    public Report() {
        initComponents();
        loadProjectIDs();
    }
    
    private void loadProjectIDs() {
        List<String> projectIds = projectController.findAllProjectIds();

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        for (String id : projectIds) {
            comboBoxModel.addElement(id);
        }

        cmbProjectId.setModel(comboBoxModel);
    }
    

private String formatReport(ProjectReport report) {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy"); 
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    sb.append("📌 REPORTE DEL PROYECTO\n");
    sb.append("──────────────────────────────\n");
    sb.append("🆔 ID Proyecto: ").append(report.getProject().getProjectId()).append("\n");
    sb.append("📌 Título: ").append(report.getProject().getProjectTitle()).append("\n");
    sb.append("📄 Descripción: ").append(report.getProject().getProjectDescription()).append("\n");
    sb.append("📅 Inicio: ").append(formatDate(report.getProject().getStartDate(), dateFormat)).append("\n");
    sb.append("📅 Cierre: ").append(formatDate(report.getProject().getClosingDate(), dateFormat)).append("\n");
    sb.append("💰 Cotización Inicial: $").append(report.getProject().getStartquote()).append("\n");
    sb.append("⚡ Estado Operacional: ").append(report.getProject().getOperationalStatus()).append("\n");
    sb.append("📜 Estado de Cotización: ").append(report.getProject().getQuoteStatus()).append("\n");
    sb.append("🧾 Facturado: ").append(report.getProject().isInvoiced() ? "Sí" : "No").append("\n");
    sb.append("💵 Pagado: ").append(report.getProject().isPaid() ? "Sí" : "No").append("\n");
    sb.append("🌍 Público: ").append(report.getProject().isPublic() ? "Sí" : "No").append("\n");

    sb.append("\n📜 HISTORIAL DE COTIZACIONES\n");
    sb.append("──────────────────────────────\n");
    for (QuoteChangeLog log : report.getQuoteChangeLogs()) {
        sb.append("💰 Antes: $").append(log.getOldQuote()).append("\n");
        sb.append("💰 Ahora: $").append(log.getNewQuote()).append("\n");
        sb.append("📅 Fecha: ").append(formatDate(log.getChangeDate(), dateFormat)).append("\n");
        sb.append("⏰ Hora: ").append(formatDate(log.getChangeDate(), timeFormat)).append("\n");
        sb.append("──────────────────────────────\n");
    }

    sb.append("\n📜 HISTORIAL DE ESTADOS\n");
    sb.append("──────────────────────────────\n");
    for (StatusChangeLog log : report.getStatusChangeLogs()) {
        sb.append("🔄 Estado Anterior: ").append(log.getOldStatus()).append("\n");
        sb.append("✅ Estado Nuevo: ").append(log.getNewStatus()).append("\n");
        sb.append("📅 Fecha: ").append(formatDate(log.getChangeDate(), dateFormat)).append("\n");
        sb.append("⏰ Hora: ").append(formatDate(log.getChangeDate(), timeFormat)).append("\n");
        sb.append("──────────────────────────────\n");
    }

    sb.append("\n🔧 SOPORTES ASOCIADOS\n");
    sb.append("──────────────────────────────\n");
    for (Support support : report.getSupports()) {
        sb.append("🆔 ID Soporte: ").append(support.getSupportId()).append("\n");
        sb.append("🛠️ Detalles: ").append(support.getSupportDetails()).append("\n");
        sb.append("📅 Inicio: ").append(formatDate(support.getStartDate(), dateFormat)).append("\n");
        sb.append("📅 Fin: ").append(formatDate(support.getEndDate(), dateFormat)).append("\n");
        sb.append("🔄 Estado: ").append(support.getSupportStatus()).append("\n");
        sb.append("──────────────────────────────\n");
    }

    return sb.toString();
}

private String formatDate(Date date, SimpleDateFormat format) {
    return (date != null) ? format.format(date) : "N/A";
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbProjectId = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReportArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Reportes ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jLabel1)
                .addContainerGap(312, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(37, 37, 37))
        );

        jLabel2.setText("Seleccionar Proyecto:");

        cmbProjectId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        cmbProjectId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProjectIdActionPerformed(evt);
            }
        });

        jButton1.setText("Generar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtReportArea.setColumns(20);
        txtReportArea.setRows(5);
        jScrollPane1.setViewportView(txtReportArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(cmbProjectId, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(54, 54, 54))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbProjectId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("Regresar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton2)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProjectIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProjectIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProjectIdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String selectedProjectId = (String) cmbProjectId.getSelectedItem();
        System.out.println("Proyecto seleccionado: " + selectedProjectId); 

        if (selectedProjectId == null || selectedProjectId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un ID de proyecto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ReportController reportController = new ReportController();
        ProjectReport report = reportController.generateReport(selectedProjectId);

        if (report != null) {
            System.out.println("Reporte generado: " + report); 
            JOptionPane.showMessageDialog(this, "Reporte generado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            if (txtReportArea != null) {
                txtReportArea.setText(formatReport(report));

            } else {
                System.out.println(report.toString()); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo generar el reporte. Verifique el ID del proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbProjectId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtReportArea;
    // End of variables declaration//GEN-END:variables
}
