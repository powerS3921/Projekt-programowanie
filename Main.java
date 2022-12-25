import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        int c;
        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("Wybierz co chcesz zrobic: ");
            System.out.println("1. Zaloguj się jeśli masz konto");
            System.out.println("2. Utwórz konto dla czytelnika");
            System.out.println("3. Wyjdź");
            c = scan.nextInt();
            switch (c){
                case 1:
                    int b;
                    do{
                        System.out.println("Podaj jako kto chcesz sie zalogować: ");
                        System.out.println("1. Administrator");
                        System.out.println("2. Użytkownik");
                        System.out.println("3. Zakończ");
                        b = scan.nextInt();
                        switch(b){
                            case 1:
                                System.out.print("Podaj login: ");
                                scan.nextLine();
                                String login = scan.nextLine();
                                System.out.print("Podaj hasło: ");
                                String haslo = scan.nextLine();
                                    ResultSet rs = QueryExecutor.executeSelect("SELECT * FROM admin where login = '"+login+"'");
                                    while(rs.next()){
                                        String login1 = rs.getString("login");
                                        String pass = rs.getString("pass");
                                        if(login.equals(login1.trim()) && haslo.equals(pass.trim())){
                                            int a;
                                            do{
                                                System.out.println("Podaj co chcesz zrobic: ");
                                                System.out.println("1. Dodanie ksiązki do biblioteki");
                                                System.out.println("2. Usuwanie wszystkich ksiązek z biblioteki");
                                                System.out.println("3. Usuwanie danej ksiązki z biblioteki");
                                                System.out.println("4. Modyfikowanie ksiązek w bibliotece");
                                                System.out.println("5. Wyświetlanie wszystkich książek w bibliotece");
                                                System.out.println("6. Utwórz konto dla administratora");
                                                System.out.println("7. Wyloguj");
                                                a = scan.nextInt();

                                                switch (a){
                                                    case 1:
                                                        ksiazka k = new ksiazka();
                                                        scan.nextLine();
                                                        System.out.print("Podaj tytuł ksiązki: ");
                                                        String tytul = k.setTytul(scan.nextLine());
                                                        System.out.print("Podaj imię i nazwisko autora: ");
                                                        String autor = k.setAutor(scan.nextLine());
                                                        System.out.print("Podaj czy książka jest dostępna (tak lub nie): ");
                                                        String dostepnosc = k.setDostepnosc(scan.nextLine());
                                                        QueryExecutor.executeQuery("INSERT INTO ksiazki (tytul, autor, dostepnosc) VALUES ('"+tytul+"', '"+autor+"', '"+dostepnosc+"')");
                                                        System.out.println("Brawo dodałeś/aś książkę do biblioteki");
                                                        break;
                                                    case 2:
                                                        QueryExecutor.executeQuery("DELETE FROM ksiazki");
                                                        System.out.println("Usunąłeś wszystskie ksiązki z biblioteki");
                                                        break;
                                                    case 3:
                                                        System.out.print("Podaj id ksiązki, którą chcesz usunąć: ");
                                                        int delete = scan.nextInt();
                                                        QueryExecutor.executeQuery("DELETE FROM ksiazki WHERE idksiazki = "+delete);
                                                        System.out.println("Usunąłeś z biblioteki książkę o id: "+delete);
                                                        break;
                                                    case 4:
                                                        System.out.print("Podaj id ksiązki, którą chcesz zmodyfikować: ");
                                                        int idmod = scan.nextInt();
                                                        scan.nextLine();
                                                        System.out.print("Podaj kolumnę, którą chcesz zmodyfikować: ");
                                                        String kolumna = scan.nextLine();
                                                        System.out.print("Podaj wartość jaką chcesz zmodyfikować: ");
                                                        String wartosc = scan.nextLine();
                                                        ResultSet result  = QueryExecutor.executeSelect("Select * from ksiazki where idksiazki = "+idmod);
                                                        while(result.next()){
                                                            QueryExecutor.executeQuery("UPDATE ksiazki SET "+kolumna+" = '"+wartosc+"' WHERE idksiazki = "+idmod);
                                                            String wynik = result.getString(kolumna);
                                                            System.out.println("Brawo podmieniłeś wartość "+wynik.trim()+" na wartość "+wartosc+" w kolumnie "+kolumna+" o id równym "+idmod);
                                                        }

                                                        break;
                                                    case 5:
                                                        try {
                                                            ResultSet result1 = QueryExecutor.executeSelect("SELECT * FROM ksiazki");
                                                            int idk;
                                                            System.out.println("ID || Tytuł || Autor || Dostępnosc ");
                                                            while(result1.next()) {
                                                                idk = result1.getInt("idksiazki");
                                                                tytul = result1.getString("tytul");
                                                                autor = result1.getString("autor");
                                                                dostepnosc = result1.getString("dostepnosc");
                                                                System.out.println(idk+" || "+tytul.trim()+" || "+autor.trim()+" || "+dostepnosc.trim());
                                                            }
                                                        } catch (SQLException e) {
                                                            e.printStackTrace();
                                                            return;
                                                        }
                                                        break;
                                                    case 6:
                                                        admin ad = new admin();
                                                        scan.nextLine();
                                                        System.out.print("Podaj login: ");
                                                        String login2 = ad.setLogin(scan.nextLine());
                                                        System.out.print("Podaj hasło: ");
                                                        String haslo1 = ad.setPass(scan.nextLine());
                                                        QueryExecutor.executeQuery("INSERT INTO admin (login, pass) VALUES('"+login2+"', '"+haslo1+"')");
                                                        System.out.println("Dodałes administratora do bazy, teraz możesz się zalogować!");
                                                        break;
                                                }//koniec switcha a
                                            }while(a!=7);
                                        }
                                        else{
                                            System.out.println("Nieprawidłowe dane logowania!!!");
                                        }
                                    }
                                break;

                            case 2:
                                System.out.print("Podaj login: ");
                                scan.nextLine();
                                String login2 = scan.nextLine();
                                System.out.print("Podaj hasło: ");
                                String haslo2 = scan.nextLine();
                                try{
                                    ResultSet rs1 = QueryExecutor.executeSelect("Select * FROM users  where login = '"+login2+"'");
                                    while(rs1.next()){
                                        String login3 = rs1.getString("login");
                                        String pass2 = rs1.getString("pass");
                                        if(login2.equals(login3.trim()) && haslo2.equals(pass2.trim())){
                                            int a;
                                            do{
                                                System.out.println("Podaj co chcesz zrobic: ");
                                                System.out.println("1. Zobaczyć dostępne ksiązki");
                                                System.out.println("2. Wypożyczyć ksiązkę");
                                                System.out.println("3. Oddac książkę");
                                                System.out.println("4. Zobacz jakie ksiązki masz wypożyczone");
                                                System.out.println("5. Ile dni zostało na oddanie ksiązki?(Za każdy dzień 1 zł kary)");
                                                System.out.println("6. Wyloguj");
                                                a = scan.nextInt();
                                                switch (a){
                                                    case 1:
                                                        try {
                                                            ResultSet result1 = QueryExecutor.executeSelect("SELECT * FROM ksiazki WHERE dostepnosc = 'tak'");
                                                            System.out.println("ID || Tytuł || Autor || Dostępnosc ");
                                                            while(result1.next()) {
                                                                int idk = result1.getInt("idksiazki");
                                                                String tytul = result1.getString("tytul");
                                                                String autor = result1.getString("autor");
                                                                String dostepnosc = result1.getString("dostepnosc");
                                                                System.out.println(idk+" || "+tytul.trim()+" || "+autor.trim()+" || "+dostepnosc.trim());
                                                            }
                                                        } catch (SQLException e) {
                                                            e.printStackTrace();
                                                            return;
                                                        }
                                                        break;
                                                    case 2:
                                                        wypozyczalnia w = new wypozyczalnia();
                                                        System.out.print("Podaj id ksiązki, którą chcesz wypożyczyć: ");
                                                        int idksiazki = w.setIdborrow(scan.nextInt());
                                                        w.wypozyczenie(idksiazki, login3);
                                                        break;
                                                    case 3:
                                                        wypozyczalnia z = new wypozyczalnia();
                                                        System.out.print("Podaj id ksiązki, którą chcesz zwrócić: ");
                                                        int idzwrot = z.setIdreturn(scan.nextInt());
                                                        z.zwrot(idzwrot, login3);
                                                        break;
                                                    case 4:
                                                        ResultSet result = QueryExecutor.executeSelect("SELECT * FROM wypozyczalnia WHERE uzytkownik = '"+login3+"'");
                                                        System.out.println("Książki, które posiadasz w wypożyczeniu to: ");
                                                        while(result.next()){
                                                            int id = result.getInt("idksiazki");
                                                            ResultSet result1 = QueryExecutor.executeSelect("SELECT * FROM ksiazki WHERE idksiazki = "+id);
                                                            while(result1.next()){
                                                                int idwynik = result1.getInt("idksiazki");
                                                                String tytul = result1.getString("tytul");
                                                                String autor = result1.getString("autor");
                                                                System.out.println(idwynik+" "+tytul+" "+autor);
                                                            }
                                                        }
                                                        break;
                                                    case 5:
                                                        wypozyczalnia licz = new wypozyczalnia();
                                                        licz.liczNaleznosc(login3);
                                                        break;
                                                }

                                            }while(a!=6);
                                        }
                                    }
                                }catch (SQLException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            break;


                        }//koniec switcha b

                    }while(b!=3);
                    break;
                case 2:
                    users u = new users();
                    scan.nextLine();
                    System.out.print("Podaj login: ");
                    String login = u.setLogin(scan.nextLine());
                    System.out.print("Podaj hasło: ");
                    String haslo = u.setPass(scan.nextLine());
                    QueryExecutor.executeQuery("INSERT INTO users (login, pass) VALUES('"+login+"', '"+haslo+"')");
                    System.out.println("Dodałes użytkownika do bazy, teraz możesz się zalogować!");
                    break;
            } // koniec switcha c
        }while(c!=3);

}}
