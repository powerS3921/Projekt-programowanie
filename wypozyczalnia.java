import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class wypozyczalnia {
    int idborrow, idreturn;
    LocalDate today;

    public wypozyczalnia(int idborrow, LocalDate today, int idreturn) {
        this.idborrow = idborrow;
        this.today = today;
        this.idreturn = idreturn;
    }

    public wypozyczalnia() {
    }

    public int getIdborrow() {
        return idborrow;
    }

    public int setIdborrow(int idborrow) {
        this.idborrow = idborrow;
        return idborrow;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public int getIdreturn() {
        return idreturn;
    }

    public int setIdreturn(int idreturn) {
        this.idreturn = idreturn;
        return idreturn;
    }

    public void wypozyczenie(int idksiazki, String login3){
        try{
            ResultSet result = QueryExecutor.executeSelect("SELECT distinct * FROM wypozyczalnia WHERE idksiazki = "+idksiazki);
            LocalDate today = LocalDate.now();
                if(result.next()){
                    System.out.println("Ksiązka jest już wypożyczona! Musisz wybrać inną lub poczekać aż będzie dostępna. ");
                }
                else{
                    QueryExecutor.executeQuery("UPDATE ksiazki SET dostepnosc = 'nie' WHERE idksiazki = "+idborrow);
                    QueryExecutor.executeQuery("INSERT INTO wypozyczalnia (idksiazki,czas) VALUES("+idborrow+",'"+today.toString()+"')");
                    QueryExecutor.executeQuery("UPDATE wypozyczalnia SET uzytkownik = '"+login3+"' WHERE idksiazki = "+idksiazki);
                    System.out.println("Brawo wypożyczyłeś ksiązkę o id "+idksiazki);
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void zwrot(int idreturn, String login3){
        try{
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM wypozyczalnia WHERE idksiazki = "+idreturn+" AND uzytkownik = '"+login3+"'");
            if(result.next()){
                QueryExecutor.executeQuery("DELETE FROM wypozyczalnia where idksiazki = "+idreturn);
                QueryExecutor.executeQuery("UPDATE ksiazki SET dostepnosc = 'tak' WHERE idksiazki = "+idreturn);
                System.out.println("Brawo zwróciłeś ksiązkę o id "+idreturn);
            }
            else{
                System.out.println("Nie możesz oddać ksiązki, gdyż to nie ty ją wypożyczałes! ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void liczNaleznosc(String login3){
        try{
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM wypozyczalnia WHERE uzytkownik = '"+login3+"'");
            while(result.next()){
                LocalDate wypozyczenie = result.getDate("czas").toLocalDate();
                int id = result.getInt("idksiazki");
                LocalDate today = LocalDate.now();
                int ile_dni = (int) ChronoUnit.DAYS.between(wypozyczenie, today);
                int dni = ile_dni-10;
                if(dni<0){
                    dni = dni*(-1);
                }
                System.out.println("Książkę o id: "+id+" masz przy sobie "+ile_dni+" dni i musisz ją oddac w "+ dni+" dni");
                int oplata = 0;
                if(ile_dni>10){
                    oplata = (ile_dni - 10);
                }
                System.out.println("Twoja opłata za tą ksiązkę wynosi w tym momencie: "+oplata+" zł!");
                System.out.println("--------------------------------------");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
