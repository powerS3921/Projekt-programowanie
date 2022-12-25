import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ksiazka {
    int id, idksiazki;
    String tytul, autor, dostepnosc;
    LocalDate czas;
    public ksiazka() {
        this.id = 0;
        this.tytul = null;
        this.autor = null;
        this.dostepnosc = "nie";
    }


    public ksiazka(int id, String tytul, String autor, String dostepnosc) {
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;
        this.dostepnosc = dostepnosc;
    }

    public int getId() {
        return id;
    }

    public String getTytul() {
        return tytul;
    }

    public String getAutor() {
        return autor;
    }

    public String isDostepnosc() {
        return dostepnosc;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String setTytul(String tytul) {
        this.tytul = tytul;
        return tytul;
    }

    public String setAutor(String autor) {
        this.autor = autor;
        return autor;
    }

    public String setDostepnosc(String dostepnosc) {
        this.dostepnosc = dostepnosc;
        return dostepnosc;
    }
    public int czywyporzyczone(){
        ResultSet result = QueryExecutor.executeQuery("SELECT * FROM wypozyczalnia");
        while(true){
            try {
                if (!result.next())
                    idksiazki = result.getInt("idksiazki");
                    break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return idksiazki;
    }

}
