package objectdb_mantenimiento;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


// Capa de base de datos

public class BD {
    private EntityManagerFactory conexionBD;
    public  EntityManager comandoBD;

    public String mensajeError="";
    public boolean error;
    
    
    public boolean hayError=false;

    
    public BD()
            {
          
            }
    
    public boolean open()       // abrir la conexión con la base de datos..
    {
        
         try {
                                // Creamos objeto de la base de datos..
              conexionBD = Persistence.createEntityManagerFactory("BASE_DATOS/cliente.odb"); // usando una base de datos local y en esta carpeta                                                       
                                                      
                this.comandoBD= this.conexionBD.createEntityManager();
        } catch (Exception ex) {
           
            this.error=true;
            this.mensajeError=ex.getMessage();
            return false;                           // falló
        }
       return true;                                 // todo ok            
    }
    

    
    
    
   public boolean close() // cerrar la conexión con la base de datos.
   {
        try 
           {
                this.mensajeError="";
                this.error=false;          
               
                this.comandoBD.close();
                this.conexionBD.close(); // !! IMPORTANTE PARA QUE SE CIERRE LA CONEXION A LA BASE DE DATOS
            
           }
        
       catch (Exception ex) 
           {
           
                this.error=true;
                this.mensajeError=ex.getMessage();
                return false; // falló
           }
       return true;               // todo ok    
   }
    
   
 
 
 
}