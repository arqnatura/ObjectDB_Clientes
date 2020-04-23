package objectdb_mantenimiento;

import java.util.Date;
import java.util.Set;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class InformeDeErrores {
    
    @Id @GeneratedValue int id;

     private String texto;
     private Date grabado;
     @ManyToOne Cliente clienteError;

    public InformeDeErrores() {
    }

    public InformeDeErrores(String texto, Date grabado) {
       this.texto = texto;
       this.grabado = grabado;
    }
   
    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public Date getGrabado() {
        return this.grabado;
    }
    
    public void setGrabado(Date grabado) {
        this.grabado = grabado;
    }


}
