/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Daren
 */
public class Registro extends java.awt.Frame {

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        registro = new javax.swing.JButton();
        correo = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        apellido1 = new javax.swing.JTextField();
        password1 = new javax.swing.JPasswordField();
        password2 = new javax.swing.JPasswordField();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(800, 600));

        registro.setText("Registrar");
        jLayeredPane1.add(registro);
        registro.setBounds(350, 440, 110, 30);

        correo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        correo.setText("Correo Electronico");
        jLayeredPane1.add(correo);
        correo.setBounds(130, 170, 550, 40);

        nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre.setText("Nombre");
        jLayeredPane1.add(nombre);
        nombre.setBounds(130, 80, 250, 40);

        apellido1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellido1.setText("apellido");
        jLayeredPane1.add(apellido1);
        apellido1.setBounds(430, 80, 250, 40);

        password1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password1.setText("ALGO");
        password1.setToolTipText("Repita su Contraseña");
        jLayeredPane1.add(password1);
        password1.setBounds(430, 260, 260, 40);

        password2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password2.setText("ALGO");
        password2.setToolTipText("Contraseña");
        jLayeredPane1.add(password2);
        password2.setBounds(130, 260, 260, 40);

        add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField apellido1;
    public javax.swing.JTextField correo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLayeredPane jLayeredPane1;
    public javax.swing.JTextField nombre;
    public javax.swing.JPasswordField password1;
    public javax.swing.JPasswordField password2;
    public javax.swing.JButton registro;
    // End of variables declaration//GEN-END:variables
}
