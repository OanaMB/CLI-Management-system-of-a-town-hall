package org.example;

/* clasa exceptie tip de cerere nepermis pentru acest utilizator */
public class UtilizatorCerereNepermisa extends Exception {
    public UtilizatorCerereNepermisa(String message) {
        super(message);
    }
}

