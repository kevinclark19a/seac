/**
 *
 */
package core.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import utils.completion.SearchTree;
import core.view.adapters.DictionaryAdapter;

/**
 * @author Kevin Clark
 *
 */
class SearchField extends JComboBox<String> {

    public final Runnable saveDictionaryAction;

    private final String currentCompletion = "";

    private final SearchTree lookup;

    private final JTextField textBox;

    SearchField(final DictionaryAdapter dictionaryAdapter, final Runnable whenEnterPressed) {

        super();

        // The box is 35 columns wide.
        this.setPrototypeDisplayValue("___________________________________");
        this.setLightWeightPopupEnabled(true);

        this.lookup = dictionaryAdapter.getLookupTree();
        this.saveDictionaryAction = () -> dictionaryAdapter.saveDictionary(this.lookup);

        this.textBox = (JTextField)this.getEditor().getEditorComponent();
        this.textBox.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(final MouseEvent e) {

                SearchField.this.textBox.requestFocus();
            }

        });

        this.textBox.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent keyReleased) {

                if (SearchField.this.getFont().canDisplay(keyReleased.getKeyChar())
                        || keyReleased.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    SearchField.this.updateDropDown();
                }

                switch (keyReleased.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        whenEnterPressed.run();
                        // Intentional Fallthrough.
                    case KeyEvent.VK_SPACE:
                        // Prioritize the selected item.
                        SearchField.this.prioritizeCurrentItem();
                        break;
                }
            }
        });

        this.setEditable(true);

    }

    /**
     * @return
     */
    public String getText() {

        return this.textBox.getText();
    }

    public void start() {

        this.setFont(this.getFont().deriveFont(0, 64));
    }

    /**
     *
     */
    protected void prioritizeCurrentItem() {

        final String trimmedText = this.textBox.getText().trim();

        // Only one item, no need to parse.
        if ( !trimmedText.contains(" ")) this.lookup.prioritize(trimmedText);
        else this.lookup.prioritize(trimmedText.substring(trimmedText.lastIndexOf(' ')));
    }

    private void updateDropDown() {

        final String buffer = this.textBox.getText();

        while (this.getItemCount() > 0) {
            this.removeItemAt(0);
        }
        this.addItem(buffer);
        // this.textBox.setText(buffer);

        final String lastWord = buffer.substring(buffer.lastIndexOf(" ") + 1);
        final String everythingElse = buffer.substring(0, buffer.length() - lastWord.length());

        int numItems = 1;
        for (final String completion : this.lookup.get(lastWord)) {
            numItems++ ;
            this.addItem(everythingElse + completion);

            // Sanity cutoff.
            if (numItems > 9) break;
        }

        this.setMaximumRowCount(numItems);

        if (numItems > 1) this.showPopup();
    }

    private static final long serialVersionUID = 5041637841772479199L;
}