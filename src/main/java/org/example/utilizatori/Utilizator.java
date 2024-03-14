package org.example.utilizatori;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utilizator {
    public abstract String scriereCerere(Cerere.TipCerere tip);
    public abstract Cerere creeazaCerere(Cerere.TipCerere tip, int prioritate, String data, PrintWriter printWriter) throws IOException;
    public abstract void retrageCerere(Date data);
    public abstract void vizualizareCereri();
    public abstract void afisareCererisolutionate();
    public abstract void afisareCereriInAsteptare(PrintWriter printWriter) throws IOException;

    /* parseaza string to date */
    public Date getDate(String data_string) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        Date data = null;
        try {
            data = formatter.parse(data_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

}
