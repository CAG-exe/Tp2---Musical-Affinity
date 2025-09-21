import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombre;

    private int tango, folclore, rock, urbano;

    public Usuario(String nombre, int t, int f, int r, int u) {
        this.nombre = nombre;
        this.tango = t;
        this.folclore = f;
        this.rock = r;
        this.urbano = u;

    }
// Metodos publicos, estos son los metodos que se ven fuera de la clase:

    //Devuelve la informacion del usuario
    public String toString(){
        return infoUsuario(this);
    }

    //devuelve la informacion de un usuario puntual
    public String toString(Usuario u){
        return infoUsuario(u);
    }
    //Devuelve el nombre del usuario
    public String getNombre(){
        return this.nombre;
    }
    //Compara un usuario con otro
    public boolean esIgual(Usuario u){
        return usuarioEquals(u);
    }

    //Compara si dos usuarios no son totalmente iguales y marca si existe una diferencia puntual
    public String igualesToString (Usuario u) {
        return sonIgualesToString(u);
    }
    
    // Estos metodos permiten hacer la comparacion general directamente para observacion del usuario,
    //para corroborar que los datos hayan sido correctamente leidos

        public String comparacionTotalUsuario(Usuario u){
            return this.comparacionTotal(u);
        }
    
        public String comparacionTotalUsuarios(List<Usuario> usuarios){
            return this.comparacionTotalString(usuarios);
        }

    //A partir de esta zona empiezan los metodos que devuelven valores enteros, ideales para hacer los distintos calculos
    
    public int nivelTango() {
        return gustos("tango");
    }

    public int nivelFolclore() {
        return gustos("folclore");
    }

    public int nivelRock() {
        return gustos("rock");
    }

    public int nivelUrbano() {
        return gustos("urbano");
    }
    
    public int compararNivelTango(Usuario u){
        return gustosPuntuales(this.nivelTango(), u.nivelTango());
    }
    
    public int compararNivelFolclore(Usuario u){
        return gustosPuntuales(this.nivelFolclore(), u.nivelFolclore());
    }
    
    public int compararNivelRock(Usuario u){ 
        return gustosPuntuales(this.nivelRock(), u.nivelRock());
    }
    
    public int compararNivelUrbano(Usuario u){
        return gustosPuntuales(this.nivelUrbano(), u.nivelUrbano());
    }

    //Aca comienzan los metodos privados, necesarios para la ejecucion de los distintos metodos publicos
    //aqui se encuentra toda la logica de la clase usuario:
    
    //Obtiene el valor absoluto de la resta de los dos niveles, y te devuelve la diferencia
    private int gustosPuntuales(int gustoUno, int gustoDos) {
        return (Math.abs(gustoUno - gustoDos));
    }
    
    //Compara los gustos con un string puntual y te devuelve los valores de cada nivel de gusto
    private int gustos(String gusto){
        if (gusto == "tango"){
            return this.tango;
        }
        
        if (gusto == "folclore"){
            return this.folclore;
        }
        
        if (gusto == "rock"){
            return this.rock;
        }
        
        if (gusto == "urbano"){
            return this.urbano;
        }
        
        return 0;
    }
    
    //Necesario para el ToString, devuelve toda la informacion de un solo usuario

    private String infoUsuario (Usuario u){
        return "Nombre de usuario: " + u.nombre
        + "Nivel de gusto de tango: " + u.tango
        + " Nivel de gusto de folclore: " + u.folclore
        + " Nivel de gusto de rock: " + u.rock
        + " Nivel de gusto de urbano: " + u.urbano;

    }

    //Metodos equals que es utilizado en las comparaciones de las distintas logicas

    private boolean usuarioEquals (Usuario u){
        if ( (this.tango==u.tango) 
        & (this.folclore==u.folclore) 
        & (this.rock==u.rock) 
        & (this.urbano==u.urbano)){
            return true;
        }
        else{
            return false;
        }
    }
    
    private String sonIgualesToString (Usuario u){
        if(this.esIgual(u) & this.nombre == u.nombre){
            return this.nombre + " y " + u.nombre + " Son el mismo usuario";
        }
        else{
            if (this.esIgual(u)){
                return this.nombre + " y " + u.nombre + " son usuarios con los mismos gustos";
            }
            else{
                return this.nombre + " y " + u.nombre + " Son usuarios con gustos distintos";
            } 
        }
    }
    
    //Compara un usuario con otro y te muestra las diferencias que hay entre ellos.

    private String comparacionTotal(Usuario u) {
        // Lista con los usuarios
        Usuario[] usuarios = { this, u };
    
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Esta es la comparación total de los usuarios:\n");
    
        // Datos de cada usuario
        for (Usuario usuario : usuarios) {
            mensaje.append("Datos generales de ").append(usuario.getNombre()).append(":\n");
            mensaje.append(usuario.toString()).append("\n");
        }
    
        // Aca se comparan las diferencias entre los distintos usuarios
        mensaje.append("\nDiferencias encontradas:\n");
    
        if (this.compararNivelTango(u) != 0) {
            mensaje.append(" - Nivel de tango distinto: ")
                   .append(this.nivelTango())
                   .append(" vs ")
                   .append(u.nivelTango())
                   .append("\n");
        }
        if (this.compararNivelFolclore(u) != 0) {
            mensaje.append(" - Nivel de Folclore distinto: ")
                   .append(this.nivelFolclore())
                   .append(" vs ")
                   .append(u.nivelFolclore())
                   .append("\n");
        }
    
        if (this.compararNivelRock(u) != 0) {
            mensaje.append(" - Nivel de Rock: ")
                   .append(this.nivelRock())
                   .append(" vs ")
                   .append(u.nivelRock())
                   .append("\n");
        }
    
        if (this.compararNivelUrbano(u)!= 0) {
            mensaje.append(" - Nivel de Urbano distinto: ")
                   .append(this.nivelUrbano())
                   .append(" vs ")
                   .append(u.nivelUrbano())
                   .append("\n");
        }
    
        return mensaje.toString();
    }

    //compara al usuario actual con una lista de usuarios recibida por parametro y muestra todas las diferencias
    //utiliza como caso base al usuario en el que se le aplica el metodo

    private String comparacionTotalString(List<Usuario> usuarios) {
    // Incluimos siempre al usuario actual al inicio
    List<Usuario> todos = new ArrayList<>();
    todos.add(this);
    todos.addAll(usuarios);

    StringBuilder mensaje = new StringBuilder();
    mensaje.append("Esta es la comparación total de los usuarios:\n");

    // Datos de cada usuario
    for (Usuario usuario : todos) {
        mensaje.append("Datos generales de ").append(usuario.getNombre()).append(":\n");
        mensaje.append(usuario.toString()).append("\n");
    }

    // Comparación de diferencias: this (mi usuario actual) vs cada usuario
    mensaje.append("\nDiferencias encontradas:\n");

    for (Usuario usuario : usuarios) {
        mensaje.append("Comparando ").append(this.getNombre())
               .append(" con ").append(usuario.getNombre()).append(":\n");

                if (this.compararNivelTango(usuario) != 0) {
            mensaje.append(" - Nivel de tango distinto: ")
                   .append(this.nivelTango())
                   .append(" vs ")
                   .append(usuario.nivelTango())
                   .append("\n");
        }
        if (this.compararNivelFolclore(usuario) != 0) {
            mensaje.append(" - Nivel de Folclore distinto: ")
                   .append(this.nivelFolclore())
                   .append(" vs ")
                   .append(usuario.nivelFolclore())
                   .append("\n");
        }
    
        if (this.compararNivelRock(usuario) != 0) {
            mensaje.append(" - Nivel de Rock: ")
                   .append(this.nivelRock())
                   .append(" vs ")
                   .append(usuario.nivelRock())
                   .append("\n");
        }
    
        if (this.compararNivelUrbano(usuario)!= 0) {
            mensaje.append(" - Nivel de Urbano distinto: ")
                   .append(this.nivelUrbano())
                   .append(" vs ")
                   .append(usuario.nivelUrbano())
                   .append("\n");
        }

        mensaje.append("\n");
    }

    return mensaje.toString();
}
}
