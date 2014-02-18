package com.epsi.VignPerzMal.database;

import com.epsi.VignPerzMal.models.*; 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
 
public class MySQLiteHelper extends SQLiteOpenHelper {
 
	private static final String TABLE_STORES = "table_stores";
	private static final String COL_ID = "ID";
	private static final String COL_CODEMAG = "CODEMAG";
	private static final String COL_LIBELLE = "libelle";
	private static final String COL_ADRESSE = "adresse";
	private static final String COL_CODE_POSTAL = "codepostal";
	private static final String COL_VILLE = "ville";
	private static final String COL_TELEPHONE = "telephone";
	private static final String COL_HORAIRES = "horaires";
	private static final String COL_FAX = "fax";
	private static final String COL_LATITUDE = "latitude";
	private static final String COL_LONGITUDE = "longitude";
	
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "eleves.db";
 
	private SQLiteDatabase bdd;
 
	private MySQLiteHelper maBaseSQLite;
 
	private static final String CREATE_BDD = "CREATE TABLE " 
	+ TABLE_STORES + " ("
	+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ COL_CODEMAG + " TEXT NOT NULL, "
	+ COL_ADRESSE + " TEXT NOT NULL," 
	+ COL_CODE_POSTAL + " TEXT NOT NULL, "
	+ COL_ADRESSE + " TEXT NOT NULL,"
	+ COL_ADRESSE + " TEXT NOT NULL,"
	+ COL_ADRESSE + " TEXT NOT NULL," +
	");";
 
	public MySQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + TABLE_STORES + ";");
		onCreate(db);
	}
	
	
	public void StoresBDD(Context context){
		//On créer la BDD et sa table
		maBaseSQLite = new MySQLiteHelper(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertStore(Store store){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_CODEMAG, store.getCodeMag());
		values.put(COL_ADRESSE, store.getAddress());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(TABLE_STORES, null, values);
	}
 
	public int updateStore(int codeMag, Store store){
		//La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(COL_CODEMAG, store.getCodeMag());
		values.put(COL_ADRESSE, store.getAddress());
		return bdd.update(TABLE_STORES, values, COL_ID + " = " + codeMag, null);
	}
 
	public int removeLivreWithID(int id){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(TABLE_STORES, COL_ID + " = " +id, null);
	}
 
	public Store getStoreWithTitre(String titre){
		//Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
		Cursor c = bdd.query(TABLE_STORES, new String[] {COL_ID, COL_CODEMAG, COL_ADRESSE}, COL_ID + " LIKE \"" + titre +"\"", null, null, null, null);
		return cursorToStore(c);
	}
 
	//Cette méthode permet de convertir un cursor en un livre
	private Store cursorToStore(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un livre
		Store store = new Store();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		//store.setId(c.getString(1));
		store.setAddress(c.getString(2));
		store.setName(c.getString(3));
		//On ferme le cursor
		c.close();
 
		//On retourne le livre
		return store;
	}
 
}