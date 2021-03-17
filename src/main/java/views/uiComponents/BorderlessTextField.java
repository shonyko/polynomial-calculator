package views.uiComponents;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BorderlessTextField extends JTextField {

    public BorderlessTextField() {
        super();
        applySettings();
    }

    public BorderlessTextField(String text) {
        super(text);
        applySettings();
    }

    public BorderlessTextField(int columns) {
        super(columns);
        applySettings();
    }

    public BorderlessTextField(String text, int columns) {
        super(text, columns);
        applySettings();
    }

    public BorderlessTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
        applySettings();
    }

    private void applySettings() {
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorder(null);
        this.setEditable(false);
        setResizable();
    }

    private void setResizable() {
        initResizeOnResize();
        initResizeOnValueChanged();
    }

    private void initResizeOnResize() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                try {
                    adapt();
                } catch (Exception ex) {

                }
            }
        });
    }

    private void initResizeOnValueChanged() {
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    adapt();
                } catch (Exception ex) {

                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    adapt();
                } catch (Exception ex) {

                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    adapt();
                } catch (Exception ex) {

                }
            }
        });
    }

    private void adapt() {
        var font = this.getFont();
        var fontMetrics = this.getFontMetrics(font);
        var textWidth = fontMetrics.stringWidth(this.getText());
        var parent = getParent().getParent();
        float size = parent.getHeight() * (parent.getWidth() - textWidth);
        size = (float) Math.floor(size * 10 / (400 * 400) / 2);

        if(size <= 25) {
            size += 20;
        }

//        size += size * 0.2;
        this.setFont(font.deriveFont(font.getStyle(), size));
    }
}
