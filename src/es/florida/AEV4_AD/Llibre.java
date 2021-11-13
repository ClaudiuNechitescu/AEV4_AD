package es.florida.AEV4_AD;


public class Llibre {
	private Integer identificador;
    private String titol;
    private String autor;
    private Integer anyNaixement;
    private Integer anyPublicacio;
    private String editorial;
    private Integer nombrePagines;

    public Llibre() {};
    
    public Llibre(String tit,String aut,int anyN,int anyP,String edi,int nom){
        this.titol=tit;
        this.autor=aut;
        this.anyNaixement=anyN;
        this.anyPublicacio=anyP;
        this.editorial=edi;
        this.nombrePagines=nom;
    }

    /**
     * Mètode que mostra el títol, l'autor i l'any de publicació d'un llibre
     * */
    public void mostrarInfo() {
    	System.out.println("Titol: " + titol);
    	System.out.println("Autor: " + autor);
    	System.out.println("Any de publicació: " + anyPublicacio);
    }
    
	public Integer getId(){
        return identificador;
    }
    
    public String getTitol(){
        return titol;
    }

    public String getAutor(){
        return autor;
    }
    
    public Integer getAnyNaixement() {
    	return anyNaixement;
    }

    public Integer getAnyPublicacio(){
        return anyPublicacio;
    }

    public String getEditorial(){
        return editorial;
    }

    public int getNombrePagines(){
        return nombrePagines;
    }

    public void setId(Integer id){
        identificador=id;
    }
    
    public void setTitol(String tit){
        titol = tit;
    }

    public void setAutor(String aut){
        autor=aut;
    }

    public void setAnyNaixement(Integer any) {
    	anyNaixement = any;
    }
    
    public void setAnyPublicacio(Integer any){
        anyPublicacio=any;
    }

    public void setEditorial(String edi){
        editorial=edi;
    }

    public void setNombrePagines(Integer nom){
        nombrePagines=nom;
    }

}
