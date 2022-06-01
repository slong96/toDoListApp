public class Main {
    public static void main(String[] args) {
        toDoDatabase db = new toDoDatabase();
        toDoListGUI gui = new toDoListGUI(db);
    }
}