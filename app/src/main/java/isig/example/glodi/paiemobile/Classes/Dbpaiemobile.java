package isig.example.glodi.paiemobile.Classes;

import java.sql.Date;

public class Dbpaiemobile {

    public static final int SYNC_STATUS_OK=0;
    public  static final int SYNC_STATUS_FAILED=1;
    public static  final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="paiemobiledb";

    //POUR LA TABLE CLIENT
    public  static  final String TABLE_NAME_CLIENT="tClient";
    public  static  final String CODE_CLIENT="code_client";
    public  static  final String NOM_CLIENT="nom_client";
    public  static  final String ADRESSE_CLIENT="adresse_client";
    public  static  final String CONTACT_CLIENT="contact_client";
    public  static  final String DATE_SAVE_CLIENT="date_save_client";
    public  static  final String NOM_USER_CLIENT="nom_user_client";
    public  static  final String PASSWORD_CLIENT="password_client";
    public  static  final String IMAGE_CLIENT="image_client";
    public  static  final String COMPTE_CLIENT="compte_client";
    public  static  final String SYNC_STATUT_CLIENT="sync_statut_client";

    //POUR LES AGENTS
    public  static  final String TABLE_NAME_AGENT="tAgent";
    public  static  final String CODE_AGENT="code_agent";
    public  static  final String NOM_AGENT="nom_agent";
    public  static  final String ADRESSE_AGENT="adresse_agent";
    public  static  final String CONTACT_AGENT="contact_agent";
    public  static  final String DATE_SAVE_AGENT="date_save_agent";
    public  static  final String NOM_USER_AGENT="nom_user_agent";
    public  static  final String PASSWORD_AGENT="password_agent";
    public  static  final String NIVEAU_AGENT="niveau_agent";
    public  static  final String SYNC_STATUT_AGENT="status_sync_agent";

    //POUR LE PAIEMENT CLIENT
    public  static  final String TABLE_NAME_PAIEMENTBUSCLIENT="tPaiementbusclient";
    public  static  final String CODE_PAIE="code_paie";
    public  static  final String REFERENCE_CLIENT="reference_client";
    public  static  final String MONTANT_PAIE="montant_paie";
    public  static  final String DATE_PAIE="date_paie";
    public  static  final String SYNC_STATUT_PAIECLIENT="status_sync_paieclient";

    //POUR LE CHARGEMENT COMPTE CLIENT
    public  static  final String TABLE_NAME_CHARGECOMPTECLIENT="tChargementCompteclient";
    public  static  final String CODE_CHARGECLIENT="code_charge";
    public  static  final String REFERENCE_CLIENT_COMPTE="reference_client";
    public  static  final String MONTANT_CHARGE_CLIENT="montant_charge";
    public  static  final String DATE_CHARGE_CLIENT="date_charge";
    public  static  final String SYNC_STATUT_CHARGECLIENT="status_sync_chargeclient";

    //POUR CHARGEMENT COMPTE BUS
    public  static  final String TABLE_NAME_CHARGECOMPTEBUS="tChargecomptebus";
    public  static  final String CODE_CHARGE="code_charge";
    public  static  final String REFERENCE_BUS="reference_bus";
    public  static  final String MONTANT_CHARGE="montant_charge";
    public  static  final String DATE_CHARGE="date_charge";
    public  static  final String SYNC_STATUT_CHARGE="status_sync_charge";

    //POUR LES PRIX DE BUS
    public  static  final String TABLE_NAME_PARAMETREPRIX="tParametreprixbus";
    public  static  final String CODE_PARAMPRIX="code_paramprix";
    public  static  final String MONTANT_PRIX="montant_prix";
    public  static  final String SYNC_STATUT_PRIX="status_sync_prix";

    //POUR LES BUS
    public  static  final String TABLE_NAME_BUS="tBus";
    public  static  final String CODE_BUS="code_bus";
    public  static  final String NUMERO_PLAQUE_BUS="numeroplaque_bus";
    public  static  final String MARQUE_BUS="marque_bus";
    public  static  final String NOM_PROPRIETAIRE="nom_proprietaire";
    public  static  final String ADRESSE_PROPRIETAIRE="adresse_proprietaire";
    public  static  final String CONTACT_PROPRIETAIRE="contact_proprietaire";
    public  static  final String NOMUSER_PROPRIETAIRE="nomuser_proprietaire";
    public  static  final String PASSWORD_PROPRIETAIRE="password_proprietaire";
    public  static  final String NOM_CHAUFFEUR="nom_chauffeur";
    public  static  final String ADRESSE_CHAUFFEUR="adresse_chauffeur";
    public  static  final String CONTACT_CHAUFFEUR="contact_chauffeur";
    public  static  final String NOMUSER_CHAUFFEUR="nomuser_chauffeur";
    public  static  final String PASSWORD_CHAUFFEUR="password_chauffeur";
    public  static  final String COMPTE_BUS="compte_bus";
    public  static  final String SYNC_STATUT_BUS="status_sync_bus";
    public  static  final String DATE_SAVE_BUS="date_save_bus";

    //Pour le server

    //public static  final String SERVER_URL="http://127.0.0.1:8887/syncdemo/syncinfo.php";

    public static  final String SERVER_URL="http://192.168.137.1:8087//syncpaiemobile/syncagent.php";
    public static  final String SERVER_URL_CLIENT="http://192.168.137.1:8087//syncpaiemobile/syncclient1.php";
    public static  final String SERVER_URL_BUS="http://192.168.137.1:8087//syncpaiemobile/syncbus.php";
    public static  final String SERVER_URL_PAIEBUS="http://192.168.137.1:8087//syncpaiemobile/syncpaiementbus.php";
    public static  final String SERVER_URL_CHARGE_COMPTE_BUS="http://192.168.137.1:8087//syncpaiemobile/syncchargebus.php";
    public static  final String SERVER_URL_CHARGE_COMPTE_CLIENT="http://192.168.137.1:8087//syncpaiemobile/syncchargecompteclient.php";
    public static  final String SERVER_URL_PRIX="http://192.168.137.1:8087//syncpaiemobile/syncparametreprix.php";

    public  static  final String UI_UPDATE_BROADCAST="isig.example.glodi.paiemobile.uiupdatebroadcast";



}
