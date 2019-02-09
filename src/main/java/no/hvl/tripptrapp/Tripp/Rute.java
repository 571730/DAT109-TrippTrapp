package no.hvl.tripptrapp.Tripp;

public class Rute {
    private int numRute;
    private char tegn;

    public Rute(int num){
        numRute = num;
        tegn = ' ';
    }

    public int getNumRute() {
        return numRute;
    }

    public void setNumRute(int numRute) {
        this.numRute = numRute;
    }

    public char getTegn() {
        return tegn;
    }

    public void setTegn(char tegn) {
        this.tegn = tegn;
    }
}
