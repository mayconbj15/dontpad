package br.com.dontpad.models;

public class User {
    private String name;
    private NotePad pad;

    public User(){
        this.name = "";
        this.pad = null;
    }

    public User(String name){
        this.name = name;
        this.pad = new NotePad();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NotePad getPad() {
        return pad;
    }

    public void setPad(NotePad pad) {
        this.pad = pad;
    }
}
