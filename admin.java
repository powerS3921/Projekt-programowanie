public class admin {
    String login1, pass1;

    public admin(String login1, String pass1) {
        this.login1 = login1;
        this.pass1 = pass1;
    }

    public admin() {
    }

    public String getLogin() {
        return login1;
    }

    public String setLogin(String login1) {
        this.login1 = login1;
        return login1;
    }

    public String getPass() {
        return pass1;
    }

    public String setPass(String pass1) {
        this.pass1 = pass1;
        return pass1;
    }
}
