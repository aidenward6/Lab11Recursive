import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;

public class RecursiveLister extends JFrame {
    JPanel mainPanel;
    JScrollPane scroller;
    JTextArea textArea;
    JButton fileLoader, quitButton;
    JLabel label;
    JFileChooser fileChooser;

    public RecursiveLister() {
        JPanel mainPanel = new JPanel();
        textArea = new JTextArea(20, 20);
        scroller = new JScrollPane(textArea);


        fileLoader = new JButton("Load File");
        fileLoader.addActionListener(e -> {
            listFiles(loadFile());

        });


        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Do you want to quit?") == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        JLabel label = new JLabel("Recursive Lister");

        mainPanel.add(label);
        mainPanel.add(scroller);
        mainPanel.add(fileLoader);
        mainPanel.add(quitButton);


        add(mainPanel);


        setTitle("Recursive Lister");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    private File loadFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File selectedFile = new File("");

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
            }
            return selectedFile;
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
        return new File("");
    }

    private void listFiles(File dir){
        for(File currentFile : dir.listFiles()) {
            if (currentFile.isDirectory()) {
                textArea.append(currentFile.getName() + "\n");
                listFiles(currentFile);
            }
            else {
                textArea.append(currentFile.getName());
            }
        }

    }

}





