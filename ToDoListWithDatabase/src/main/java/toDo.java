public class toDo {
    private String toDo;

    toDo(String tD) {
        toDo = tD;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    @Override
    public String toString() {
        return toDo;
    }
}
