
package ec.edu.espe.view;

import ec.edu.espe.Controller.ProjectController;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dennis Paucar
 */
public class UpdateStatus extends javax.swing.JFrame {
    private ProjectController projectController = new ProjectController();
    private Project project;
            
    /**
     * Creates new form UpdateStatus
     */
    public UpdateStatus() {
        initComponents();
        loadProjectsIntoTable();
        
    }
    
     private void loadProjectsIntoTable() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        lblModificationDate.setText(formattedDate);
            
        try {
          
          List<Project> projects = projectController.findAllProjects();

         
          DefaultTableModel model = (DefaultTableModel) tblStatus.getModel();
          model.setRowCount(0); 

          for (Project project : projects) {
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              String startDateFormatted = (project.getStartDate() != null) ? sdf.format(project.getStartDate()) : "";

              model.addRow(new Object[]{
                  project.getProjectId(),
                  project.getProjectTitle(),
                  startDateFormatted,   
                  project.getOperationalStatus().toString()   
              });
          }

      } catch (Exception e) {
          JOptionPane.showMessageDialog(this, "Error al cargar los datos de los proyectos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStatus = new javax.swing.JTable();
        lblProjectTitle = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblProjectId = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblProjectName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblStartDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbStatusOptions = new javax.swing.JComboBox<>();
        lblProjectStatus = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblModificationDate = new javax.swing.JLabel();
        btnChangeStatus = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Cambiar Estatus de Proyecto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(266, 266, 266))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tblStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo de Proyecto", "Nombre", "Fecha de creación", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStatusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStatus);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setText("Código de Proyecto:");

        lblProjectId.setText("jLabel3");

        jLabel4.setText("Nombre del Proyecto:");

        lblProjectName.setText("jLabel5");

        jLabel6.setText("Fecha de creación:");

        lblStartDate.setText("jLabel7");

        jLabel8.setText("Estatus actual:");

        cmbStatusOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En progreso", "Pausado", "Cerrado", " " }));

        lblProjectStatus.setText("jLabel9");

        jLabel10.setText("Cambiar Estatus:");

        jLabel11.setText("Fecha de modificación:");

        lblModificationDate.setText("jLabel7");

        btnChangeStatus.setText("Cambiar");
        btnChangeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeStatusActionPerformed(evt);
            }
        });

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lblProjectTitleLayout = new javax.swing.GroupLayout(lblProjectTitle);
        lblProjectTitle.setLayout(lblProjectTitleLayout);
        lblProjectTitleLayout.setHorizontalGroup(
            lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblProjectTitleLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(25, 25, 25)
                .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProjectStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(lblProjectTitleLayout.createSequentialGroup()
                            .addComponent(cmbStatusOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addGap(37, 37, 37)
                            .addComponent(btnChangeStatus))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lblProjectTitleLayout.createSequentialGroup()
                            .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblProjectName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                .addComponent(lblProjectId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblModificationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        lblProjectTitleLayout.setVerticalGroup(
            lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblProjectTitleLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblProjectId)
                    .addComponent(jLabel6)
                    .addComponent(lblStartDate))
                .addGap(18, 18, 18)
                .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblProjectName)
                        .addComponent(jLabel11)
                        .addComponent(lblModificationDate))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lblProjectStatus))
                .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblProjectTitleLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStatusOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addContainerGap(37, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblProjectTitleLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(lblProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChangeStatus)
                            .addComponent(jButton1))
                        .addGap(16, 16, 16))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProjectTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProjectTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeStatusActionPerformed
        int selectedRow = tblStatus.getSelectedRow();

        if (selectedRow != -1) {
            String projectId = (String) tblStatus.getValueAt(selectedRow, 0); // Obtener el ID del proyecto
            String selectedStatus = (String) cmbStatusOptions.getSelectedItem(); // Estado seleccionado en el comboBox

            if (selectedStatus != null) {
                ProjectStatus newStatus = ProjectStatus.fromString(selectedStatus);

                try {
                    List<Project> projects = projectController.findAllProjects();
                    Project selectedProject = null;

                    for (Project project : projects) {
                        if (project.getProjectId().equals(projectId)) {
                            selectedProject = project;
                            break;
                        }
                    }

                    if (selectedProject != null) {
                        ProjectStatus oldStatus = selectedProject.getOperationalStatus();

                        if (!oldStatus.equals(newStatus)) {
                            selectedProject.setOperationalStatus(newStatus); 
                            projectController.saveProject(selectedProject); 

                            JOptionPane.showMessageDialog(this, "Estado del proyecto actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                            loadProjectsIntoTable(); 
                        } else {
                            JOptionPane.showMessageDialog(this, "El estado seleccionado es igual al actual. No se realizaron cambios.", "Sin cambios", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró el proyecto con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el estado del proyecto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un estado válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proyecto en la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnChangeStatusActionPerformed

    private void tblStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStatusMouseClicked
         int selectedRow = tblStatus.getSelectedRow();
         
        if (selectedRow != -1) {

            String projectId = (String) tblStatus.getValueAt(selectedRow, 0); 
            String projectTitle = (String) tblStatus.getValueAt(selectedRow, 1);
            String startDate = (String) tblStatus.getValueAt(selectedRow, 2);
            String projectStatus = (String) tblStatus.getValueAt(selectedRow, 3);

            lblProjectId.setText(projectId);  
            lblProjectName.setText(projectTitle); 
            lblStartDate.setText(startDate);
            lblProjectStatus.setText(projectStatus);
            
            
        }
    }//GEN-LAST:event_tblStatusMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        
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
            java.util.logging.Logger.getLogger(UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeStatus;
    private javax.swing.JComboBox<String> cmbStatusOptions;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblModificationDate;
    private javax.swing.JLabel lblProjectId;
    private javax.swing.JLabel lblProjectName;
    private javax.swing.JLabel lblProjectStatus;
    private javax.swing.JPanel lblProjectTitle;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JTable tblStatus;
    // End of variables declaration//GEN-END:variables
}
