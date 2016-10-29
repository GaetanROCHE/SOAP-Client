package metier;


public class Pays    {
	
	private String nomPays;
	private String nomCapitale;
	private float nbhabitants;
	

	public String getNomPays() {
		return nomPays;
	}


	
	public String getNomCapitale() {
		return nomCapitale;
	}


	
	public float getNbhabitants() {
		return nbhabitants;
	}


	public Pays(String nom_pays, String nom_capitale, float nbhabitants) {
		super();
		this.nomPays = nom_pays;
		this.nomCapitale = nom_capitale;
		this.nbhabitants = nbhabitants;
	}

	public Pays() {
		super();
		
	}

	
}
