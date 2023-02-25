package com.twitter.util;

import java.time.LocalDateTime;

public class SystemDateTime {

    /**
     * Απαγορεύουμε τη δημιουργία αντικείμενων.
     */
    protected SystemDateTime() { }

    private static LocalDateTime stub;


    /**
     * Θέτει μία συγκεκριμένη ημερομηνία ως την ημερομηνία του συστήματος.
     * Η ημερομηνία αυτή επιστρέφεται από την {@link SystemDateTime#now()}.
     * Εάν αντί για προκαθορισμένης ημερομηνίας τεθεί
     * {@code null} τότε επιστρέφεται
     * η πραγματική ημερομηνία του συστήματος
     * @param stubDateTime Η ημερομηνία η οποία θα επιστρέφεται
     * ως ημερομηνία του συστήματος ή {@code null} για
     * να επιστρέφει την πραγματική ημερομηνία
     */
    protected static void setStub(LocalDateTime stubDateTime) {
        stub = stubDateTime;
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
     * τεθεί από την {@link SystemDateTime#setStub}.
     * @return Η ημερομηνία του συστήματος ή μία προκαθορισμένη ημερομηνία
     */
    public static LocalDateTime now() {
        return stub == null ? LocalDateTime.now() : stub;
    }
}
