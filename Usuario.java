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

    public String toString(){
        return infoUsuario(this);
    }

    public String toString(Usuario u){
        return infoUsuario(u);
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public boolean esIgual(Usuario u){
        return usuarioEquals(u);
    }
    
    public String igualesToString (Usuario u) {
        return sonIgualesToString(u);
    }
    
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
    
    private String infoUsuario (Usuario u){
        return "Nombre de usuario: " + u.nombre
        + "Nivel de gusto de tango: " + u.tango
        + " Nivel de gusto de folclore: " + u.folclore
        + " Nivel de gusto de rock: " + u.rock
        + " Nivel de gusto de urbano: " + u.urbano;

    }
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
}
