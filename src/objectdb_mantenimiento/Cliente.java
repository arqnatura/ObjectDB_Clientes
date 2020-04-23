
package objectdb_mantenimiento;

import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity  // IMPORTANTE PARA PODER GRABAR ..
public class Cliente implements java.io.Serializable{
    

    @Id public int id;
    
      public String nombre;
      public String domicilio;
      
//@ElementCollection  // indicamos que es una coleccion dentro de mi objeto.  ************
//                      // Además la clase InformeDeErrores debe tener @Embeddable  ************
//         Set<InformeDeErrores> errores; // errores encontrados...
    
          
    public Cliente() {}  
    
      public Cliente ( int xId, String xNombre , String xDomicilio)
               
      {
          this.id=xId;
          this.nombre=xNombre;
          this.domicilio=xDomicilio;
      }
      

    
    Cliente(int xid) {
        this.id= xid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void visualiza()
    {
        System.out.println(" Id: "+this.id+" \t\t\t  Nombre :" +this.nombre+" \t\t\t  Domicilio :" +this.domicilio);
    }
}
