package core.view;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import core.view.adapters.DictionaryAdapter;
import core.view.adapters.ModelAdapter;

import utils.log.Log;

/**
 * @author Kevin Clark
 *
 */
public class GUI extends JFrame {

    private final JPanel commandLine;

    /**
     * @param modelAdapter
     *
     */
    public GUI(final ModelAdapter modelAdapter, final DictionaryAdapter dictionaryAdapter) {

        super();

        this.commandLine = new CommandLinePanel(modelAdapter, dictionaryAdapter);

        // define close behavior
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {

                ((CommandLinePanel)commandLine).saveDictionary();

                System.exit(0);
            }
        });

        this.initGUI();

    }

    /**
     * Starts the GUI.
     */
    public void start() {

        ((CommandLinePanel)commandLine).start();
        this.pack();

        final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setLocation((width - this.getWidth()) / 2, (height - this.getHeight()) / 3);
        this.setVisible(true);
    }

    /**
     *
     */
    private void initGUI() {

        Log.i(UIManager.getLookAndFeel().getName());

        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setAutoRequestFocus(true);

        this.getContentPane().add(commandLine, null);
    }

    private static final long serialVersionUID = 1560508750802569605L;
}