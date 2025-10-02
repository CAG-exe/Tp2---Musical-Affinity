package Visual;

public class NuevoUsuarioDatos {
	private final String nombre;
    private final Integer tango;
    private final Integer folklore;
    private final Integer rock;
    private final Integer urbano;

    private NuevoUsuarioDatos(Builder b) {
        this.nombre = b.nombre;
        this.tango = b.tango;
        this.folklore = b.folclore;
        this.rock = b.rock;
        this.urbano = b.urbano;
    }

    public String getNombre() { return nombre; }
    public Integer getTango() { return tango; }
    public Integer getFolclore() { return folklore; }
    public Integer getRock() { return rock; }
    public Integer getUrbano() { return urbano; }

    public static Builder builder() { return new Builder(); }


	
	public static final class Builder {
        private String nombre;
        private Integer tango;
        private Integer folclore;
        private Integer rock;
        private Integer urbano;


        private Builder() {}

        public Builder nombre(String nombre) {
            this.nombre = nombre != null ? nombre.trim() : null;
            return this;
        }

        public Builder tango(Integer tango) {
            this.tango = tango;
            return this;
        }

        public Builder folclore(Integer folklore) {
            this.folclore = folklore;
            return this;
        }

        public Builder rock(Integer rock) {
            this.rock = rock;
            return this;
        }
        
        public Builder urbano(Integer urbano) {
            this.urbano = urbano;
            return this;
        }
        public NuevoUsuarioDatos build() {
            return new NuevoUsuarioDatos(this);
        }
    }
}

