public class ScaleParser {

    private int[] majorSemitones = {
            0, 2, 4, 5, 7, 9, 11
    };

    private int[] dominantSemitones = {
            0, 2, 4, 5, 7, 9, 10
    };

    private int[] minorSemitones = {
            0, 2, 3, 5, 7, 8, 10
    };

    private String[] notes = {
            "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"
    };

    private String[] chordSequence = {
            "\u0394\u2077", "m\u2077", "m\u2077", "\u0394\u2077", "7", "m\u2077", "m\u2077b\u2075"
    };

    public ScaleParser() {  }

    public String[] getScale(String key, String mode) {
        String[] scale = new String[this.majorSemitones.length];
        int keyIndex = getKeyNoteIndex(key);
        switch (mode) {
            case "major":
                for (int i = 0; i < this.majorSemitones.length; i++) {
                    if (keyIndex + this.majorSemitones[i] < this.notes.length) {
                        scale[i] = this.notes[keyIndex + this.majorSemitones[i]];
                    } else {
                        scale[i] = this.notes[keyIndex + this.majorSemitones[i] - this.notes.length];
                    }
                }
                break;

            case "minor":
                for (int i = 0; i < this.minorSemitones.length; i++) {
                    if (keyIndex + this.minorSemitones[i] < this.notes.length) {
                        scale[i] = this.notes[keyIndex + this.minorSemitones[i]];
                    } else {
                        scale[i] = this.notes[keyIndex + this.minorSemitones[i] - this.notes.length];
                    }
                }
                break;

            default:
                break;
        }
        return scale;
    }

    public int getKeyNoteIndex(String key) {
        int index = 0;
        for (int i = 0; i < this.notes.length; i++) {
            if (this.notes[i].toLowerCase().equals(key.toLowerCase())) {
                index = i;
            }
        }
        return index;
    }

    public boolean verifyKey(String key) {
        boolean validKey = false;
        for (int i = 0; i < this.notes.length; i++) {
            if (this.notes[i].toLowerCase().equals(key.toLowerCase())) {
                validKey = true;
            }
        }
        if (!validKey) {
            System.out.println("Key not valid. Valid keys are:");
            for (String part : this.notes) {
                System.out.print(part + ", ");
            }
            System.out.println();
        }
        return validKey;
    }

    public boolean verifyMode(String mode) {
        boolean validMode = false;
        if (mode.equals("major") || mode.equals("minor")) {
            validMode = true;
        } else {
            System.out.println("Please type either major or minor");
        }
        return validMode;
    }

    public String[] getNotes() {
        return this.notes;
    }

    public String[] getChordSequence() {
        return this.chordSequence;
    }
}
