public class toDo {
    private String toDo;

    // constructor
    toDo(String tD) {
        toDo = tD;
    }

    // getters and setters
    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    // return to-do text as String
    @Override
    public String toString() {
        return toDo;
    }
}
