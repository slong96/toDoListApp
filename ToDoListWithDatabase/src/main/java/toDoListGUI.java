import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class toDoListGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField newToDoTextField;
    private JButton addButton;
    private JList<toDo> toDoJList;
    private JButton deleteSelectedButton;
    private toDoDatabase db;
    private DefaultListModel<toDo> listModel; // contains JList data


    toDoListGUI(toDoDatabase db) {
        this.db = db;
        setTitle("To Do List App");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 500));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        toDoJList.setModel(listModel);
        getRootPane().setDefaultButton(addButton); // press enter

        addListeners();
        List<toDo> allToDo = db.getAllToDo();
        setListData(allToDo);
    }

    void setListData(List<toDo> getList) {
        listModel.clear();
        if (getList != null) {
            listModel.addAll(getList);
        }
    }
    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String toDoText = newToDoTextField.getText();
                toDoText = toDoText.trim();

                if (toDoText.isBlank()) {
                    showErrorDialog("Enter a To-Do text");
                    return;
                } else {
                    toDo tDL = new toDo(toDoText);
                    db.addToDo(tDL);
                    listModel.addElement(tDL);
                    newToDoTextField.setText("");
                }
            }
        });

        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<toDo> deleteToDo = toDoJList.getSelectedValuesList();
                if (deleteToDo == null || deleteToDo.isEmpty()) {
                    showErrorDialog("Please select a To-Do text to delete");
                } else {
                    for (toDo tD: deleteToDo) {
                        db.deleteToDo(tD);
                    }
                    List<toDo> getList = db.getAllToDo();
                    setListData(getList);
                }
            }
        });
    }

    private void showErrorDialog(String msg) {
        JOptionPane.showMessageDialog(toDoListGUI.this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
