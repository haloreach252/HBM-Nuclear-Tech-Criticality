package com.miniverse.hbm.util;

import com.miniverse.hbm.util.Tuple.Triplet;

public class NoteBuilder {

    private String beat = "";

    /**
     * Initializes a new NoteBuilder.
     *
     * @return A new instance of NoteBuilder.
     */
    public static NoteBuilder start() {
        return new NoteBuilder();
    }

    /**
     * Adds a note component to the beat.
     * <p>
     * Each note is encoded as a string with the format "instrument:note:octave" where each value
     * is the ordinal of the corresponding enum. If the beat already contains notes, a hyphen is used
     * to separate the new entry.
     *
     * @param instrument The instrument to use.
     * @param note The note value.
     * @param octave The octave of the note.
     * @return The current NoteBuilder instance for chaining.
     */
    public NoteBuilder add(Instrument instrument, Note note, Octave octave) {
        if(!beat.isEmpty())
            beat += "-";

        String result = instrument.ordinal() + ":" + note.ordinal() + ":" + octave.ordinal();
        beat += result;

        return this;
    }

    /**
     * Finalizes the beat construction.
     *
     * @return The encoded beat as a String.
     */
    public String end() {
        return beat;
    }

    /**
     * Translates an encoded beat string into an array of Triplet objects containing the Instrument,
     * Note, and Octave.
     *
     * @param beat The encoded beat string.
     * @return An array of Triplet objects. Returns an empty array if an error occurs during parsing.
     */
    public static Triplet<Instrument, Note, Octave>[] translate(String beat) {
        String[] hits = beat.split("-");
        @SuppressWarnings("unchecked")
        Triplet<Instrument, Note, Octave>[] notes = new Triplet[hits.length];

        try {
            for(int i = 0; i < hits.length; i++) {
                String[] components = hits[i].split(":");
                Instrument instrument = Instrument.values()[Integer.parseInt(components[0])];
                Note note = Note.values()[Integer.parseInt(components[1])];
                Octave octave = Octave.values()[Integer.parseInt(components[2])];

                notes[i] = new Triplet<>(instrument, note, octave);
            }

            return notes;
        } catch(Exception ex) {
            return new Triplet[0];
        }
    }

    /**
     * Defines the available instruments.
     */
    public static enum Instrument {
        PIANO,
        BASSDRUM,
        SNARE,
        CLICKS,
        BASSGUITAR;
    }

    /**
     * Defines the available note values.
     */
    public static enum Note {
        F_SHARP,
        G,
        G_SHARP,
        A,
        A_SHARP,
        B,
        C,
        C_SHARP,
        D,
        D_SHARP,
        E,
        F;
    }

    /**
     * Defines the available octaves.
     */
    public static enum Octave {
        LOW, MID, HIGH;
    }
}
