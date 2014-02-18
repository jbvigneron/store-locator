package com.epsi.VignPerzMal.models;

public class StoreModel {
	
	 private String codeMag;
	 private String libelle;
	 private String adresse;
	 private double codePostal;
	 private String ville;
	 private String telephone;
	 private String horaires;
	 private String fax;
	 private String latitude;
	 private String longitude;
	 
	 
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the codeMag
	 */
	public String getCodeMag() {
		return codeMag;
	}
	/**
	 * @param codeMag the codeMag to set
	 */
	public void setCodeMag(String codeMag) {
		this.codeMag = codeMag;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the codePostal
	 */
	public double getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(double codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the horaires
	 */
	public String getHoraires() {
		return horaires;
	}
	/**
	 * @param horaires the horaires to set
	 */
	public void setHoraires(String horaires) {
		this.horaires = horaires;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return adresse;
	  }
	 
	 
}
