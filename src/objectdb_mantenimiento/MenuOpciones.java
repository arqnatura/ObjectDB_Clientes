package objectdb_mantenimiento;

import static com.objectdb.o.B64.e;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.TypedQuery;


public class MenuOpciones {
   
    private Scanner teclado;
    private BD baseDatos;


    public MenuOpciones ()
    {
       
        this.teclado=new Scanner(System.in);
        this.baseDatos= new BD();
      
    }
    

    private void limpiaTeclado()
    {
        this.teclado.nextLine();
    }
    
    public void hacer() 
    {
        int opcion;
        
        if (!this.baseDatos.open()) 
            {
                System.out.println ("Error "+this.baseDatos.mensajeError);
                return;
            }
        
        while (true)  //eternamente!!
        {
            visualizaMenu();
            opcion=leeOpcion();
            switch (opcion)
            {
              case  0:  this.baseDatos.close();
                        return;
                     // acabamos el menu
              case  1: this.listar();
                       break;
              case  2: this.borrar();
                       break;
              case  3: this.insertar();
                       break;
              case  4: this.modificar();
                       break;
              case  5: this.insertar10();
                       break;
              case  6: this.listaErrores();
              //         break; 
            }
            
        }
    }

    private void visualizaMenu() {
        System.out.println ("======= OPCIONES BD. CLIENTES ===============================\n"+
                            "1 ....  Listar\n"+
                            "2 ....  Borrar\n"+
                            "3 ....  Insertar nuevo Cliente\n" +
                            "4 ....  Modificar\n" +
                            "5 ....  Insertar 10 nuevos Clientes\n"  +
                            "6 ....  Listar errores\n"  +
                            "0 ....  Salir\n" +
                            "==============================================================\n"
                            );       
    }

    private int leeOpcion() {
       int op;
     
       do 
       {  
         System.out.print ("Opcion > ");
         op=this.teclado.nextInt();
       
       } while ( (op <0) || (op > 6) );
       this.limpiaTeclado(); // quitamos el <enter> que queda en el buffer de teclado después leer el id
       return op;
    }

//Listado  de la tabla.

    public  void listar()  {
    
      TypedQuery<Cliente> query =   this.baseDatos.comandoBD.createQuery("SELECT c FROM Cliente c", Cliente.class);       
      List<Cliente> results = query.getResultList();
       
       for (Cliente c : results)   c.visualiza();
       
    }

    public  void listaErrores()  {
    
      TypedQuery<InformeDeErrores> query =   this.baseDatos.comandoBD.createQuery("SELECT d FROM InformeDeErrores d", InformeDeErrores.class);       
      List<InformeDeErrores> results = query.getResultList();
       
       for (InformeDeErrores d : results)   d.getTexto();
             
       System.out.println (" Los errores son: "+ results);
    }
    
    
// Insertar los datos de un cliente nuevo (id, nombre y domicilio) en base de datos

    private void insertar()  {
       
       System.out.print ("\nId : ");
       int id=this.teclado.nextInt();
       
       this.limpiaTeclado(); // quitamos el <enter> que queda en el buffer de teclado después leer el id
       
       System.out.print ("Nombre: ");
       String nombre= this.teclado.nextLine();      
       
       System.out.print ("Domicilio: ");
       String domicilio= this.teclado.nextLine();
       
       // dos lineas de código 
       Cliente c= new Cliente( id, nombre, domicilio);      
       
       try
         { 
            this.baseDatos.comandoBD.getTransaction().begin(); // Iniciamos transaction
            
                this.baseDatos.comandoBD.persist(c); // grabamos
                
            this.baseDatos.comandoBD.getTransaction().commit(); // fin trascation , OK!              
      
         }
       
       catch ( Exception e)  
          {
           System.out.println("Error grabando\n"+e.getMessage());
          
           insertarError();
         }
    }
    
    
//    public boolean carga()
//    {  
//      
//       Cliente GrabarError;
//       boolean grabaCorrectamente=true;
//       
//       MenuOpciones c= new MenuOpciones();  
//       
//           GrabarError=c.insertar();
//           if (!MenuOpciones.insert(GrabarError)) 
//           {
//               grabaCorrectamente=false;
//               grabaError();
//           }                                     
//
//        return grabaCorrectamente;        
//    }
//    
        
    public void insertarError()  {
       
       InformeDeErrores c= new InformeDeErrores();      
    //  System.out.println ( c.getGrabado()+" "+c.getTexto());
       try
         { 
            this.baseDatos.comandoBD.getTransaction().begin(); // Iniciamos transaction
            
                this.baseDatos.comandoBD.persist(c); // grabamos
                
            this.baseDatos.comandoBD.getTransaction().commit(); // fin trascation , OK!              
      
         }
 
       catch ( Exception d)  
          {

           
           System.out.println("Error grabando Error\n"+d.getMessage());
           
                 d.getMessage();
    //            d.getGrabado();
    //             d.getTexto();
       
         }
      
       
    }
        
//    private void grabaError()
//    {
//        BD bd= new BD();
//        InformeDeErrores error= new InformeDeErrores();
//       // bd.clear(); <--- IMPORTANTE . ahora añadido cuando falla a la clase BaseDatos !!!
//        error.setTexto( bd.mensajeError);
//
//        if (!bd.insertarError(error)) System.out.println ("Error grabando en LOG: "+bd.mensajeError);
//        
//    }
    

    
//    public void listaErrores() // listamos errores
//{
//    
//      TypedQuery<Cliente> query =   this.baseDatos.comandoBD.createQuery("SELECT c FROM InformeDeErrores c", InformeDeErrores.class);       
//      List<Cliente> results = query.getResultList();
//      
//               System.out.println ("\n\n\t\t\t  ***  HAN HABIDO ERRORES AL GRABAR \n\n ");
//               
//       for (InformeDeErrores e : listaErrores ());
//      {
//              System.out.println ( e.getGrabado()+" "+e.getTexto());
//           }
//    
//    
//    //List<InformeDeErrores> listaErrores= 
//    //           (List<InformeDeErrores>) bd.executeQuery("from InformeDeErrores");
//
//    } 
    
    
    
    
    
    
    
    
// Insertar los datos de 10 clientes nuevos (id, nombre y domicilio) en base de datos
    
    private void insertar10()  
    {
       
       int i;
       for (i=1; i <= 10; i++)
       {
        
       System.out.print ("\nId : ");
       int id=this.teclado.nextInt();
       
       this.limpiaTeclado(); // quitamos el <enter> que queda en el buffer de teclado después leer el id
       
       System.out.print ("Nombre: ");
       String nombre= this.teclado.nextLine();      
       
       System.out.print ("Domicilio: ");
       String domicilio= this.teclado.nextLine();
       
       Cliente c= new Cliente( id, nombre, domicilio);      
       
       try
         { 
            this.baseDatos.comandoBD.getTransaction().begin(); // Iniciamos transaction
            
                this.baseDatos.comandoBD.persist(c); // grabamos
                
            this.baseDatos.comandoBD.getTransaction().commit(); // fin trascation , OK!              
      
         }
       
       catch ( Exception e)  
          {
           System.out.println("Error grabando\n"+e.getMessage());
          
         }
    }
    
}
    
// Borrado por Id del cliente

    private void borrar() {
        
        System.out.print("\nEntra 'id' del cliente a borrar : ");
        int id= this.teclado.nextInt();
               
        
        Cliente c = new Cliente( id); // creamo sun objeto cliente con solo el ID
        
        c=this.baseDatos.comandoBD.find(Cliente.class, c); // buscamos primero el cliente!!
        
        if ( c == null)
        {
            System.out.println ("No existe");
            return;
        }
        
       this.baseDatos.comandoBD.getTransaction().begin(); // Iniciamos transaction
       
       this.baseDatos.comandoBD.remove(c); // borramos !!!************************** SOLO PODEMOS BORRAR OBJETOS PREVIAMENTE BUSCADOS.!!!
            
       this.baseDatos.comandoBD.getTransaction().commit(); // fin trascation , OK!
                        
    }

// Modificado por Id del cliente

    private void modificar()  {
        System.out.print("\nEntra 'id' del cliente a modificar : ");
        int id= this.teclado.nextInt();
        this.limpiaTeclado(); // quitamos el <enter> que queda en el buffer de teclado después leer el id
        
        Cliente c=new Cliente(id);
        
        c=this.baseDatos.comandoBD.find(Cliente.class, c); // buscamos primero el cliente!!
        
        if ( c == null)
        {
            System.out.println ("No existe");
            return;
        }
        
       c.visualiza(); // vemos el objeto= los datos del cliente. 
       
        
       this.baseDatos.comandoBD.getTransaction().begin(); // Iniciamos transaction
       
          // le cambiamos el nombre  ******************* ESO HACE QUE YA SE MODIFIQUE EN LA BASE DE DATOS
                                                                //   cuando hagamos commit;
            System.out.println ("Entra nuevo nombre : ");
            c.nombre=this.teclado.nextLine();
            
       this.baseDatos.comandoBD.getTransaction().commit(); // fin trascation , OK!
  
    }

   
}
