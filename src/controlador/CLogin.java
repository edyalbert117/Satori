package controlador;

import controlador.bd.Transacciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import vista.VLogin;
import vista.VMenu;

/**
 *
 * @author adrian
 */
public class CLogin implements ActionListener {

    VLogin login;
    Usuario usuario;
    Seguridad seguridad;
    Transacciones transacciones;
    
    public CLogin(VLogin login) {        
        this.seguridad = new Seguridad();
        this.transacciones = new Transacciones();
        
        this.login = login;
        this.login.btnIniciarSesion.addActionListener(this);        
    }
    
    public void iniciarLogin() {        
        this.login.setLocationRelativeTo(null);
        this.login.setTitle("Login");
        this.login.setResizable(false);
               
        this.login.pack();  //redimensión de contenedores
        this.login.setVisible(true);        
    } //close iniciarLogin
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == login.btnIniciarSesion) {
            this.usuario = new Usuario();

            //Obtenemos los datos que han ingresado en el VLogin
            String nombreUsuario = this.login.txtUsuario.getText();
            char[] contrasenaChars = this.login.txtContrasena.getPassword();            
            String contrasena = this.seguridad.getHashMD5(new String(contrasenaChars));            
            this.usuario.setNombreUsuario(nombreUsuario);
            this.usuario.setContrasena(contrasena);            
            
            //validamos la sesión
            if (this.transacciones.validarSesion(usuario)) {
              
                VMenu menu = new VMenu();
                CMenu cmenu = new CMenu(menu);
                cmenu.iniciarMenu();  
                this.login.dispose();
                                                
            } 
            
        }//close if
        
    } //close actionPerformed           
    
} //close class CLogin

