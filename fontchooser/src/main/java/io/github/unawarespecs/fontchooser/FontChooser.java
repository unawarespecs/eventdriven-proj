package io.github.unawarespecs.fontchooser;

import io.github.unawarespecs.fontchooser.listeners.FamilyListSelectionListener;
import io.github.unawarespecs.fontchooser.listeners.SizeListSelectionListener;
import io.github.unawarespecs.fontchooser.listeners.StyleListSelectionListener;
import io.github.unawarespecs.fontchooser.model.DefaultFontSelectionModel;
import io.github.unawarespecs.fontchooser.model.FontSelectionModel;
import io.github.unawarespecs.fontchooser.panes.FamilyPane;
import io.github.unawarespecs.fontchooser.panes.PreviewPane;
import io.github.unawarespecs.fontchooser.panes.SizePane;
import io.github.unawarespecs.fontchooser.panes.StylePane;
import io.github.unawarespecs.fontchooser.util.ResourceBundleUtil;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ResourceBundle;


public class FontChooser extends JPanel implements FontContainer {

    private static final int DEFAULT_FONT_SIZE = 12;
    private static final int DEFAULT_SPACE = 11;

    private static final String SELECTION_MODEL_PROPERTY = "selectionModel";
    private final ResourceBundle resourceBundle;
    private final ResourceBundleUtil resourceBundleUtil;
    private final JLabel familyLabel = new JLabel();
    private final JLabel styleLabel = new JLabel();
    private final JLabel sizeLabel = new JLabel();
    private final JLabel previewLabel = new JLabel();
    private final JPanel fontPanel = new JPanel();
    private final JPanel previewPanel = new JPanel();
    private final FamilyPane familyPane = new FamilyPane();
    private final PreviewPane previewPane = new PreviewPane();
    private final StylePane stylePane = new StylePane();
    private final SizePane sizePane = new SizePane();
    private final FamilyListSelectionListener familyPaneListener = new FamilyListSelectionListener(this);
    private final StyleListSelectionListener stylePaneListener = new StyleListSelectionListener(this);
    private final SizeListSelectionListener sizePaneListener = new SizeListSelectionListener(this);
    private FontSelectionModel selectionModel;

    /**
     * Creates a FontChooser pane with an initial default Font
     * (Sans Serif, Plain, 12).
     */
    public FontChooser() {
        this(new Font(Font.SANS_SERIF, Font.PLAIN, DEFAULT_FONT_SIZE));
    }

    /**
     * Creates a FontChooser pane with the specified initial Font.
     *
     * @param initialFont the initial Font set in the chooser
     */
    public FontChooser(Font initialFont) {
        this(new DefaultFontSelectionModel(initialFont));
    }

    /**
     * Creates a FontChooser pane with the specified
     * {@code FontSelectionModel}.
     *
     * @param model the {@code FontSelectionModel} to be used
     */
    public FontChooser(FontSelectionModel model) {
        resourceBundle = ResourceBundle.getBundle("FontChooser");
        resourceBundleUtil = new ResourceBundleUtil(resourceBundle);
        setSelectionModel(model);
        setLayout(new BorderLayout());
        addComponents();
        initPanes();

        previewPane.setPreviewFont(selectionModel.getSelectedFont());
    }


    /**
     * Gets the current Font value from the FontChooser.
     * By default, this delegates to the model.
     *
     * @return the current Font value of the FontChooser
     */
    @Override
    public Font getSelectedFont() {
        return selectionModel.getSelectedFont();
    }

    /**
     * Sets the current font of the FontChooser to the specified font.
     * The {@code FontSelectionModel} will fire a {@code ChangeEvent}
     *
     * @param font the font to be set in the font chooser
     * @see JComponent#addPropertyChangeListener
     */
    @Override
    public void setSelectedFont(Font font) {
        familyPane.removeListSelectionListener(familyPaneListener);
        stylePane.removeListSelectionListener(stylePaneListener);
        sizePane.removeListSelectionListener(sizePaneListener);

        selectionModel.setSelectedFont(font);

        initPanes();
    }

    /**
     * Returns the data model that handles Font selections.
     *
     * @return a {@code FontSelectionModel} object
     */
    public FontSelectionModel getSelectionModel() {
        return selectionModel;
    }

    /**
     * Sets the model containing the selected Font.
     *
     * @param newModel the new {@code FontSelectionModel} object
     */
    public void setSelectionModel(FontSelectionModel newModel) {
        if (newModel == null) {
            throw new IllegalArgumentException("New model must not be null");
        }
        FontSelectionModel oldModel = selectionModel;
        selectionModel = newModel;
        selectionModel.addChangeListener(stylePane);
        firePropertyChange(SELECTION_MODEL_PROPERTY, oldModel, newModel);
    }

    /**
     * Adds a {@code ChangeListener} to the model.
     *
     * @param listener the {@code ChangeListener} to be added
     */
    public void addChangeListener(ChangeListener listener) {
        selectionModel.addChangeListener(listener);
    }

    /**
     * Removes a {@code ChangeListener} from the model.
     *
     * @param listener the {@code ChangeListener} to be removed
     */
    public void removeChangeListener(ChangeListener listener) {
        selectionModel.removeChangeListener(listener);
    }

    private void initPanes() {
        familyPane.setSelectedFamily(selectionModel.getSelectedFontFamily());
        familyPane.addListSelectionListener(familyPaneListener);

        stylePane.loadFamily(selectionModel.getSelectedFontFamily());
        stylePane.setSelectedStyle(selectionModel.getSelectedFontName());
        stylePane.addListSelectionListener(stylePaneListener);

        sizePane.addListSelectionListener(sizePaneListener);
        sizePane.setSelectedSize(selectionModel.getSelectedFontSize());
    }

    private void addComponents() {
        addFontPanel();
        addFamilyLabel();
        addStyleLabel();
        addSizeLabel();
        addFamilyPane();
        addStylePane();
        addSizePane();
        addPreviewLabel();
        addPreview();
    }

    private void addPreview() {
        previewPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        previewPanel.add(previewLabel, gridBagConstraints);
        add(previewPanel, BorderLayout.PAGE_END);

        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.weightx = 1.0;
        previewPanel.add(previewPane, gridBagConstraints2);
    }

    private void addPreviewLabel() {
        previewLabel.setDisplayedMnemonic(resourceBundleUtil.getFirstChar("font.preview.mnemonic"));
        previewLabel.setText(resourceBundle.getString("font.preview"));
    }

    private void addSizePane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, DEFAULT_SPACE, 0);
        fontPanel.add(sizePane, gridBagConstraints);
    }

    private void addStylePane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, DEFAULT_SPACE, DEFAULT_SPACE);
        fontPanel.add(stylePane, gridBagConstraints);
    }

    private void addFamilyPane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, DEFAULT_SPACE, DEFAULT_SPACE);
        fontPanel.add(familyPane, gridBagConstraints);
    }

    private void addSizeLabel() {
        sizeLabel.setLabelFor(sizePane);
        sizeLabel.setDisplayedMnemonic(resourceBundleUtil.getFirstChar("font.size.mnemonic"));
        sizeLabel.setText(resourceBundle.getString("font.size"));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        fontPanel.add(sizeLabel, gridBagConstraints);
    }

    private void addStyleLabel() {
        styleLabel.setLabelFor(stylePane);
        styleLabel.setDisplayedMnemonic(resourceBundleUtil.getFirstChar("font.style.mnemonic"));
        styleLabel.setText(resourceBundle.getString("font.style"));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, DEFAULT_SPACE);
        fontPanel.add(styleLabel, gridBagConstraints);
    }

    private void addFamilyLabel() {
        familyLabel.setLabelFor(familyPane);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 0, 5, DEFAULT_SPACE);
        fontPanel.add(familyLabel, gridBagConstraints);
        familyLabel.setDisplayedMnemonic(resourceBundleUtil.getFirstChar("font.family.mnemonic"));
        familyLabel.setText(resourceBundle.getString("font.family"));
    }

    private void addFontPanel() {
        fontPanel.setLayout(new GridBagLayout());
        add(fontPanel);
    }

    @Override
    public String getSelectedStyle() {
        return stylePane.getSelectedStyle();
    }

    @Override
    public float getSelectedSize() {
        return sizePane.getSelectedSize();
    }

    @Override
    public String getSelectedFamily() {
        return familyPane.getSelectedFamily();
    }

    @Override
    public void setPreviewFont(Font font) {
        previewPane.setPreviewFont(font);
    }

    public String getPreviewText() {
        return previewPane.getPreviewText();
    }

    public void setPreviewText(String text) {
        previewPane.setPreviewText(text);
    }
}
