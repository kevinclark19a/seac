/**
 *
 */
package core.view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;

import core.view.adapters.DictionaryAdapter;
import core.view.adapters.ModelAdapter;

/**
 * @author Kevin Clark
 *
 */
public class CommandLinePanel extends JPanel {

    private final ModelAdapter adapter;
    private final SearchField commandField;

    CommandLinePanel(final ModelAdapter adapter, final DictionaryAdapter dictionaryAdapter) {

        this.adapter = adapter;

        this.commandField = new SearchField(dictionaryAdapter, CommandLinePanel.this::enterPressed);

        this.init();
    }

    /**
     *
     */
    public void saveDictionary() {

        this.commandField.saveDictionaryAction.run();
    }

    /**
     * Starts the command line panel.
     */
    public void start() {

        this.commandField.start();
    }

    /**
     *
     */
    private void enterPressed() {

        this.adapter.executeCommand(this.commandField.getText());
    }

    private void init() {

        this.add(this.commandField);

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(final FocusEvent arg0) {

                // Send the focus on to the text field.
                CommandLinePanel.this.commandField.grabFocus();
            }

            @Override
            public void focusLost(final FocusEvent arg0) {

                // Ask for focus again.
                CommandLinePanel.this.commandField.hidePopup();
                CommandLinePanel.this.requestFocusInWindow();
            }

        });
    }

    private static final long serialVersionUID = -3392209434987784740L;
}