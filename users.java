public class users {
    String login, pass;

    public users(String login, String pass) {

        this.login = login;
        this.pass = pass;
    }

    public users() {
    }


    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String setLogin(String login) {
        this.login = login;
        return login;
    }

    public String setPass(String pass) {
        this.pass = pass;
        return pass;
    }
}
