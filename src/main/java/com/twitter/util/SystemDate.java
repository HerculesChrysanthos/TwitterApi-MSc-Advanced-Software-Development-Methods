package com.twitter.util;

import java.time.LocalDate;

public class SystemDate {

    /**
     * Απαγορεύουμε τη δημιουργία αντικείμενων.
     */
    protected SystemDate() { }

    private static LocalDate stub;


    /**
     * Θέτει μία συγκεκριμένη ημερομηνία ως την ημερομηνία του συστήματος.
     * Η ημερομηνία αυτή επιστρέφεται από την {@link SystemDate#now()}.
     * Εάν αντί για προκαθορισμένης ημερομηνίας τεθεί
     * {@code null} τότε επιστρέφεται
     * η πραγματική ημερομηνία του συστήματος
     * @param stubDate Η ημερομηνία η οποία θα επιστρέφεται
     * ως ημερομηνία του συστήματος ή {@code null} για
     * να επιστρέφει την πραγματική ημερομηνία
     */
    protected static void setStub(LocalDate stubDate) {
        stub = stubDate;
    }

    /**
     * Απομακρύνει το στέλεχος.
     */
    protected static void removeStub() {
        stub = null;
    }

    /**
     * Επιστρέφει την ημερομηνία του συστήματος ή μία
     * προκαθορισμένη ημερομηνία που έχει
     * τεθεί από την {@link SystemDate#setStub}.
     * @return Η ημερομηνία του συστήματος ή μία προκαθορισμένη ημερομηνία
     */
    public static LocalDate now() {
        return stub == null ? LocalDate.now() : stub;
    }
}
