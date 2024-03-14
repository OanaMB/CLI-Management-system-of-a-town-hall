package org.example;
import org.example.birou.Birou;
import org.example.utilizatori.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class ManagementPrimarie {
    private Birou<Persoana> birouPersoana;
    private Birou<Angajat> birouAngajat;
    private Birou<Elev> birouElev;
    private Birou<EntitateJuridica> birouEntitateJuridica;
    private Birou<Pensionar> birouPensionar;
    private Map<String, Utilizator> map;
    public ManagementPrimarie() {
        map = new HashMap<>();
        birouPersoana = new Birou<>();
        birouAngajat = new Birou<>();
        birouElev = new Birou<>();
        birouEntitateJuridica = new Birou<>();
        birouPensionar = new Birou<>();
    }

    /* in main sunt realizate urmatoarele actiuni: se deschide fisierul de intrare si de iesire,
    se apeleaza functia de citire a fisierului de intrare si se initializeaza un printWriter pentru cel de iesire */
    public static void main(String[] args) throws IOException, ParseException {

        File fileInput = new File("src/main/resources/input/" + args[0]);
        File fileOutput = new File("src/main/resources/output/" + args[0]);
        PrintWriter printWriter = new PrintWriter(fileOutput);
        ManagementPrimarie managementPrimarie = new ManagementPrimarie();
        FunctiiFisiere.citesteFisier(fileInput,printWriter,managementPrimarie);
        printWriter.close();
    }

    /* in functie de primul argument(argumentul[0]), se decide ce comanda urmeaza sa se desfasoare */
    public void functionalitati(String[] arguments, ManagementPrimarie managementPrimarie, PrintWriter printWriter) throws IOException, ParseException {
        switch (arguments[0]) {
            case "adauga_utilizator":
                this.adaugaUtilizator(arguments, managementPrimarie);
                break;
            case "cerere_noua":
                this.cerereNoua(arguments, managementPrimarie, printWriter);
                break;
            case "retrage_cerere":
                this.retrageCerere(arguments, managementPrimarie);
                break;
            case "rezolva_cerere":
              // rezolvaCerere(arguments, managementPrimarie);
                break;
            case "afiseaza_cereri_in_asteptare":
                this.afiseazaCereriInAsteptare(arguments, managementPrimarie, printWriter);
                break;
            case "afiseaza_cereri_finalizate":
              //  afiseazaCereriRezolvate(arguments, managementPrimarie);
                break;
            case "afiseaza_cereri":
                this.afiseazaCereriBirou(arguments, managementPrimarie, printWriter);
                break;
            default:
                System.out.println("Comanda invalida");
        }
    }

    /* in functie tipul de utilizator se afiseaza toate cererile in astepate de la un birou */
    public void afiseazaCereriBirou(String[] arguments, ManagementPrimarie managementPrimarie, PrintWriter printWriter) throws IOException {
        switch(arguments[1]) {
            case "persoana":
                managementPrimarie.birouPersoana.afiseazaCereri(arguments[1],printWriter);
                break;
            case "angajat":
                managementPrimarie.birouAngajat.afiseazaCereri(arguments[1],printWriter);
                break;
            case "elev":
                managementPrimarie.birouElev.afiseazaCereri(arguments[1],printWriter);
                break;
            case "entitate_juridica":
                managementPrimarie.birouEntitateJuridica.afiseazaCereri(arguments[1],printWriter);
                break;
            case "pensionar":
                managementPrimarie.birouPensionar.afiseazaCereri(arguments[1],printWriter);
                break;
            default:
                System.out.println("Comanda invalida");
        }
    }

    /* din argumente extragem persoana care vrea sa retraga o cerere si data la care ea a fost facuta
     si, in functie de tip de utilizator este, se apeleaza functia retrageCerere */
    public void retrageCerere(String[] arguments, ManagementPrimarie managementPrimarie) {
        Utilizator utilizator = managementPrimarie.map.get(arguments[1]);
        utilizator.retrageCerere(utilizator.getDate(arguments[2]));
        if (utilizator instanceof Persoana) {
            birouPersoana.stergeCereriInAsteptare(arguments[1],utilizator.getDate(arguments[2]));
        } else if (utilizator instanceof Angajat) {
            birouAngajat.stergeCereriInAsteptare(arguments[1],utilizator.getDate(arguments[2]));
        } else if (utilizator instanceof Elev) {
            birouElev.stergeCereriInAsteptare(arguments[1],utilizator.getDate(arguments[2]));
        } else if (utilizator instanceof EntitateJuridica) {
            birouEntitateJuridica.stergeCereriInAsteptare(arguments[1],utilizator.getDate(arguments[2]));
        } else if (utilizator instanceof Pensionar) {
            birouPensionar.stergeCereriInAsteptare(arguments[1],utilizator.getDate(arguments[2]));
        }
    }

    /* se afiseaza cererile in asteptare ale unui utilizator */
    public void afiseazaCereriInAsteptare(String[] arguments, ManagementPrimarie managementPrimarie, PrintWriter printWriter) {
        Utilizator utilizator = managementPrimarie.map.get(arguments[1]);
        if (utilizator == null) {
            System.out.println("Utilizatorul nu exista");
        } else {
            try {
                utilizator.afisareCereriInAsteptare(printWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /* se creeaza o variabila de tip enum TipCerere pentru a se cunoaste tipul cererii, apoi se creeaza un obiect de tip Cerere,
    care este adaugat la coada utilizatorului cu functia creeazaCerere si apoi, in functie de ce instanta este utilizatorul,
    se adauga si la coada biroului*/
    public void cerereNoua(String[] arguments, ManagementPrimarie managementPrimarie, PrintWriter printWriter) {
        String nume = arguments[1];
        String tip = arguments[2];
        String data = arguments[3];
        int prioritate = Integer.parseInt(arguments[4]);

        Cerere.TipCerere tipCerereUtilizator = Cerere.TipCerere.tipCerere(tip);

        if (managementPrimarie.map.containsKey(nume)) {
            Utilizator utilizator = managementPrimarie.map.get(nume);
            try{
                Cerere cererea = utilizator.creeazaCerere(tipCerereUtilizator, prioritate, data, printWriter);
                if(cererea == null) {
                    return;
                }
                CerereUtilizator cerereUtilizator = new CerereUtilizator(nume, cererea);
                if (utilizator instanceof Persoana) {
                    birouPersoana.adaugaCerereInAsteptare(cerereUtilizator);
                } else if (utilizator instanceof Angajat) {
                    birouAngajat.adaugaCerereInAsteptare(cerereUtilizator);
                } else if (utilizator instanceof Elev) {
                    birouElev.adaugaCerereInAsteptare(cerereUtilizator);
                } else if (utilizator instanceof EntitateJuridica) {
                    birouEntitateJuridica.adaugaCerereInAsteptare(cerereUtilizator);
                } else if (utilizator instanceof Pensionar) {
                    birouPensionar.adaugaCerereInAsteptare(cerereUtilizator);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Utilizatorul nu exista");
        }

    }

    /* in functie de tipul de utilizator, se adauga un utilizator in hashmap-ul de utilizatori */
    private void adaugaUtilizator(String[] arguments, ManagementPrimarie managementPrimarie) {
        String tipAngajat = arguments[1];
        String nume = arguments[2];
        switch(tipAngajat) {
            case "persoana":
                Persoana persoana= new Persoana(nume);
                managementPrimarie.map.put(persoana.getNume(), persoana);
                break;
            case "angajat":
                Angajat angajat = new Angajat(nume, arguments[3]);
                managementPrimarie.map.put(angajat.getNume(), angajat);
                break;
            case "elev":
                Elev elev = new Elev(nume, arguments[3]);
                managementPrimarie.map.put(elev.getNume(), elev);
                break;
            case "pensionar":
                Pensionar pensionar = new Pensionar(nume);
                managementPrimarie.map.put(pensionar.getNume(), pensionar);
                break;
            case "entitate juridica":
                EntitateJuridica entitateJuridica = new EntitateJuridica(nume, arguments[3]);
                managementPrimarie.map.put(entitateJuridica.getNume(), entitateJuridica);
                break;
            default:
                System.out.println("Tipul de utilizator nu exista");
        }
    }
}