package core.application;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.plaf.metal.MetalLookAndFeel;

import utils.completion.dictionary.DictionaryUtils;
import utils.log.Log;
import utils.completion.dictionary.DictionaryFactory;
import utils.completion.SearchTree;

import core.view.GUI;
import core.view.adapters.DictionaryAdapter;
import core.view.adapters.ModelAdapter;

import core.model.CommandProcessor;
import core.model.parsing.CommandLineParser;

/**
 * @author Kevin Clark
 *
 */
public class Controller {

    private final CommandProcessor model;
    private final GUI view;

    private Controller() {

        this.model = new CommandProcessor();

        this.view = new GUI(new ModelAdapter() {

            @Override
            public void executeCommand(String text) {
                final var parser = new CommandLineParser(text);

                while (parser.hasMore()) {
                    final var nextToken = parser.getNextCommand();

                    if (nextToken.isNull()) return;

                    model.addToken(nextToken);
                }

                model.execute();

                view.dispatchEvent(new WindowEvent(Controller.this.view, WindowEvent.WINDOW_CLOSING));
            }

        }, new DictionaryAdapter() {

            @Override
            public SearchTree getLookupTree() {

                Log.i("Lookup Trie requested.");

                return DictionaryFactory.make();
            }

            @Override
            public void saveDictionary(final SearchTree lookup) {
                Log.i("saving Lookup Trie.");

                DictionaryUtils.saveDictionary(lookup);
            }

        });

    }

    /**
     *
     */
    private void start() {

        Log.i("Starting application...");
        this.view.start();

    }

    /**
     * Runs the application.
     *
     * @param args
     *            unused.
     */
    public static void main(final String[] args) {

        MetalLookAndFeel.setCurrentTheme(new themes.DefaultTheme());

        EventQueue.invokeLater(() -> {
            Log.setThreshold(Log.Level.e);
            new Controller().start();
        });
    }
}