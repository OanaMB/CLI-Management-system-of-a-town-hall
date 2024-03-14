package org.example.utilizatori;

public class CerereUtilizator {

    /* clasa pentru retinerea cererii impreuna cu utilizatorul pe care a facut-o */
    private String utilizator;
    private Cerere cerere;

    public CerereUtilizator(String utilizator, Cerere cerere) {
        this.utilizator = utilizator;
        this.cerere = cerere;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public Cerere getCerere() {
        return cerere;
    }

}
