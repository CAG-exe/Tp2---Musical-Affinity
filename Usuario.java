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

    public String comparacionTotalString (Usuario u){
        String mensaje= "Esta es la comparacion total de los usuarios " + this.getNombre() + " y " 
        + u.getNombre() + ": \n" + "Datos generales de " + this.getNombre() + " :" + "\n"
         + this.toString() + "\n" + "Datos generales de " + u.getNombre() + ": \n" + u.toString() + 
        }

    public int compararNivelTango(Usuario u){
        if(this.gustosPuntuales(this.nivelTango(), u.nivelTango()) == 0){
            return 0;
        }
        else{
            return gustosPuntuales(this.nivelTango(), u.nivelTango());
        }

    }

    public int compararNivelFolclore(Usuario u){
        if(this.gustosPuntuales(this.nivelFolclore(), u.nivelFolclore()) == 0){
            return 0;
        }
        else{
            return gustosPuntuales(this.nivelFolclore(), u.nivelFolclore());
        }

    }

    public int compararNivelRock(Usuario u){ 
        if(this.gustosPuntuales(this.nivelRock(), u.nivelRock()) == 0){
            return 0;
        }
        else{
            return gustosPuntuales(this.nivelRock(), u.nivelRock());
        }

    }

    public int compararNivelUrbano(Usuario u){
        if(this.gustosPuntuales(this.nivelUrbano(), u.nivelUrbano()) == 0){
            return 0;
        }
        else{
            return gustosPuntuales(this.nivelUrbano(), u.nivelUrbano());
        }

    }

    private int gustosPuntuales(int gustoUno, int gustoDos) {
        return (Math.abs(gustoUno - gustoDos));
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
