package org.example.utilizatori;
import org.example.UtilizatorCerereNepermisa;
import org.example.birou.Birou;

import java.util.*;
import java.io.*;

public class Angajat extends Utilizator {
    private String nume;
    private String compania;

    private Queue<Cerere> cereriAngajat;

    public Angajat(String nume, String compania) {
        this.nume = nume;
        this.compania = compania;
        this.cereriAngajat = new PriorityQueue<>(new Comparator<Cerere> (){
            @Override
            public int compare(Cerere cerere1, Cerere cerere2) {

                if(getDate(cerere1.getData()).before(getDate(cerere2.getData()))) {
                    return -1;
                } else if (getDate(cerere1.getData()).after(getDate(cerere2.getData()))) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    public String getNume() {
        return nume;
    }

    @Override
    public String scriereCerere(Cerere.TipCerere tip) {
        String tipul = tip.toString().replace("_", " ");
            return "Subsemnatul " + this.nume + ", angajat la compania " + this.compania + ", va rog sa-mi aprobati urmatoarea solicitare: " + tipul;
    }

    /* creare cerere de anumit tip folosind un switch */
    @Override
    public Cerere creeazaCerere(Cerere.TipCerere tip, int prioritate, String data, PrintWriter printWriter) throws IOException {
        String continutText = null;
        String tipul = tip.toString().replace("_", " ");

        try {
        switch(tip) {
            case inlocuire_buletin: {
                continutText = scriereCerere(tip);
                break;
            }
            case inlocuire_carnet_de_sofer: {
                continutText = scriereCerere(tip);
                break;
            }
            case inregistrare_venit_salarial: {
                continutText = scriereCerere(tip);
                break;
            }
            default: {
                throw new UtilizatorCerereNepermisa("Utilizatorul de tip angajat nu poate inainta o cerere de tip " + tipul);
            }
        }
        } catch (Exception e) {
            // scrie exceptia in fisier
            printWriter.append(e.getMessage());
            printWriter.append("\n");
            return null;
        }

        // creeaza obiectul cerere si adauga-l in coada de cereri
        Cerere cerere = new Cerere(continutText, prioritate, data);
        this.cereriAngajat.add(cerere);
        return cerere;

    }
    /* folosind un iterator, folosim metoda remove pentru a elimina cererea facuta la data anumita */
    @Override
    public void retrageCerere(Date data) {
        Iterator<Cerere> iterator = cereriAngajat.iterator();

        while (iterator.hasNext()) {
            Cerere valoareCurenta = iterator.next();
            if (getDate(valoareCurenta.getData()).equals(data)) {
                iterator.remove();
                return;
            }
        }

    }

    @Override
    public void vizualizareCereri() {

    }

    @Override
    public void afisareCererisolutionate() {

    }
    /* se realizeaza un shallow copy si se extre elemente din coada copie pentru care apoi sa fie afisate */
    @Override
    public void afisareCereriInAsteptare(PrintWriter printWriter) throws IOException {
        printWriter.write(this.getNume() + " - cereri in asteptare:\n");
        Queue<Cerere> cereriAngajatCopie = new PriorityQueue<>(cereriAngajat);
        while(!cereriAngajatCopie.isEmpty()) {
            Cerere cerere = cereriAngajatCopie.poll();
            printWriter.append(cerere.getData() + " - " + cerere.getContinutText() + "\n");
        }

    }

}