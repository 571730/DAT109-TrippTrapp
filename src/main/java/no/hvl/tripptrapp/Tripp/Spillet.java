package no.hvl.tripptrapp.Tripp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Spillet {
    private List<Rute> brett;
    private char tur;
    private boolean ferdig;
    private char vinner;

    public Spillet(){
        resetBrett();
        tur = Constants.SIRKEL;
    }

    public boolean spillTur(int pos){
        if (!ferdig && settSymbol(pos)){
            if (sjekkOmVinner())
                ferdig = true;
            tur = nyTur();
            return true;
        } else {
            return false;
        }
    }

    private boolean sjekkOmVinner() {
        boolean harEnVinner = false;
        harEnVinner = sjekkRader();
        if (!harEnVinner)
            harEnVinner = sjekkKol();
        if (!harEnVinner)
            harEnVinner = sjekkDiagonal();

        return harEnVinner;
    }

    private boolean sjekkRader(){
        int[] teller;
        for (int i = 0; i < 3; i ++){
            teller = new int[]{0, 0};
            for (int j = (i * 3); j < 3 + (i * 3); j++){
                if (brett.get(j).getTegn() == Constants.SIRKEL)
                    teller[0]++;
                else if (brett.get(j).getTegn() == Constants.KRYSS)
                    teller[1]++;
            }
            if (harVinner(teller))
                return true;
        }
        return false;
    }

    private boolean sjekkKol(){
        int[] teller;
        for (int i = 0 ; i < 3 ; i++){
            teller = new int[]{0, 0};
            for (int j = i; j < 9; j = j + 3){
                if (brett.get(j).getTegn() == Constants.SIRKEL)
                    teller[0]++;
                else if (brett.get(j).getTegn() == Constants.KRYSS)
                    teller[1]++;
            }
            if (harVinner(teller))
                return true;
        }
        return false;
    }
    private boolean sjekkDiagonal(){
        int[] teller = new int[]{0, 0};
        for (int i = 0; i < 9; i = i + 4){
            if (brett.get(i).getTegn() == Constants.SIRKEL)
                teller[0]++;
            else if (brett.get(i).getTegn() == Constants.KRYSS)
                teller[1]++;
        }
        if (harVinner(teller))
            return true;
        else {
            teller = new int[]{0, 0};
            for (int i = 2; i < 7; i = i + 2){
                if (brett.get(i).getTegn() == Constants.SIRKEL)
                    teller[0]++;
                else if (brett.get(i).getTegn() == Constants.KRYSS)
                    teller[1]++;
            }
            return harVinner(teller);
        }
    }

    private boolean harVinner(int[] teller){
        if (teller[0] == 3){
            vinner = Constants.SIRKEL;
            return true;
        } else if (teller[1] == 3){
            vinner = Constants.KRYSS;
            return true;
        } else
            return false;
    }

    public boolean settSymbol(int pos){
        Rute rute = brett.get(pos - 1);
        if (rute.getTegn() == ' '){
            rute.setTegn(tur);
            brett.set(pos - 1, rute);
            return true;
        } else {
            return false;
        }
    }

    public void resetBrett() {
        ferdig = false;
        vinner = ' ';
        brett = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            brett.add(new Rute(i + 1));
        }
    }

    public List<Rute> getBrett() {
        return brett;
    }

    private char nyTur(){
        return (this.tur == Constants.SIRKEL) ? Constants.KRYSS : Constants.SIRKEL;
    }

    public boolean isFerdig() {
        return ferdig;
    }

    public char getVinner() {
        return vinner;
    }

    public char getTur() {
        return tur;
    }
}
