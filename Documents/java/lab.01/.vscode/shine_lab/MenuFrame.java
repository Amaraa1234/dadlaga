import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame implements ActionListener {
    private JLabel response;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu;
    private JMenuItem newItem, openItem, saveItem, quitItem;
    private JMenuItem cutItem, copyItem, pasteItem;

    public MenuFrame() {
        // 1. Frame-ийн үндсэн тохиргоо
        setTitle("Photo Editor GUI");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // 2. Цэснүүдийг үүсгэх функцуудыг дуудах
        createFileMenu();
        createEditMenu();

        // 3. Menu Bar үүсгэж цэснүүдийг нэмэх
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        // 4. Мэдээлэл харуулах текст (Label)
        response = new JLabel("Цэснээс сонголт хийнэ үү.");
        add(response);

        setVisible(true);
    }

    private void createFileMenu() {
        fileMenu = new JMenu("File");

        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        quitItem = new JMenuItem("Quit");

        // Action Listener холбох
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        quitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // Хэвтээ зураас
        fileMenu.add(quitItem);
    }

    private void createEditMenu() {
        editMenu = new JMenu("Edit");

        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
    }

    // Цэс дээр дарахад ажиллах функц
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Консол руу хэвлэх
        System.out.println("Сонгогдсон цэс: " + command);

        // Дэлгэц дээрх текстэд өөрчлөлт оруулах
        response.setText("Та '" + command + "' tsesiig songoloo.");

        if (command.equals("Quit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MenuFrame();
    }
}