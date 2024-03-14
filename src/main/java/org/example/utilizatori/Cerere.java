package org.example.utilizatori;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cerere {
    /* subclasa de tip enum folosita pentru retinerea tipurilor de cereri */
    public enum TipCerere {
    inlocuire_buletin,
    inregistrare_venit_salarial,
    inlocuire_carnet_de_sofer,
    inlocuire_carnet_de_elev,
    creare_act_constitutiv,
    reinnoire_autorizatie,
    inregistrare_cupoane_de_pensie;

    public static TipCerere tipCerere(String text) {
        switch(text) {
            case "inlocuire buletin":
                return inlocuire_buletin;
            case "inregistrare venit salarial":
                return inregistrare_venit_salarial;
            case "inlocuire carnet de sofer":
                return inlocuire_carnet_de_sofer;
            case "inlocuire carnet de elev":
                return inlocuire_carnet_de_elev;
            case "creare act constitutiv":
                return creare_act_constitutiv;
            case "reinnoire autorizatie":
                return reinnoire_autorizatie;
            case "inregistrare cupoane de pensie":
                return inregistrare_cupoane_de_pensie;
            default:
                return null;
        }
      }
    }

    private String continutText;
    private String data;
    private int prioritate;

    public Cerere(String continutText, int prioritate, String data) {
        this.continutText = continutText;
        this.data = data;
        this.prioritate = prioritate;
    }

    public String getContinutText() {
        return continutText;
    }

    public String getData() {
        return data;
    }

    public int getPrioritate() {
        return prioritate;
    }
}
