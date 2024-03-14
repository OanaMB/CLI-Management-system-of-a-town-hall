package org.example.birou;

import org.example.utilizatori.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Birou<T extends Utilizator> {
    private Queue<CerereUtilizator> cereriInAsteptareUtilizator;
    public Birou() {
        this.cereriInAsteptareUtilizator = new PriorityQueue<>(
                new Comparator<CerereUtilizator>(){
            @Override
            public int compare(CerereUtilizator cerere1, CerereUtilizator cerere2) {
                if(cerere1.getCerere().getPrioritate() > cerere2.getCerere().getPrioritate()) {
                    return 1;
                } else if (cerere1.getCerere().getPrioritate() < cerere2.getCerere().getPrioritate()) {
                    return -1;

                } else {
                    return getDate(cerere1.getCerere().getData()).compareTo(getDate(cerere2.getCerere().getData()));

                }
            }
        }
        );
    }

    public void afiseazaCereri(String tipUtilizator, PrintWriter printWriter) throws IOException {
        printWriter.write(tipUtilizator + " - cereri in birou:\n");
        if(this.cereriInAsteptareUtilizator.isEmpty()) {
            System.out.println("daaaa");
        }
        Queue<CerereUtilizator> cereriBirouCopie = new PriorityQueue<>(cereriInAsteptareUtilizator);
        while(!cereriBirouCopie.isEmpty()) {
            CerereUtilizator cerere = cereriBirouCopie.poll();
            printWriter.write(cerere.getCerere().getPrioritate() + " - " + cerere.getCerere().getData() + " - " + cerere.getCerere().getContinutText() + "\n");
        }
    }

    public Queue<CerereUtilizator> getCereriInAsteptareUtilizator() {
        return cereriInAsteptareUtilizator;
    }

    public void adaugaCerereInAsteptare(CerereUtilizator cerereUtilizator) {
        CerereUtilizator cerereUtilizator1 = new CerereUtilizator(cerereUtilizator.getUtilizator(), cerereUtilizator.getCerere());
        this.cereriInAsteptareUtilizator.add(cerereUtilizator1);
        System.out.println(" - cereri in asteptare:\n");
        Queue<CerereUtilizator> cereriElevCopie = new PriorityQueue<>(this.cereriInAsteptareUtilizator);
        while(!cereriElevCopie.isEmpty()) {
            CerereUtilizator cerere = cereriElevCopie.poll();
            System.out.println(cerere.getCerere().getData() + " - " + cerere.getCerere().getContinutText() );
        }
    }

    public void stergeCereriInAsteptare(String numeUtilizator, Date data) {
        Iterator<CerereUtilizator> iterator = this.cereriInAsteptareUtilizator.iterator();
        while(iterator.hasNext()) {
            CerereUtilizator cerereUtilizator = iterator.next();
            if(getDate(cerereUtilizator.getCerere().getData()).equals(data) && cerereUtilizator.getUtilizator().equals(numeUtilizator)) {
                iterator.remove();
                return;
            }
        }
    }

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
