package views.uiComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomButton extends JButton {
    public CustomButton() {
        applySettings();
    }

    public CustomButton(Icon icon) {
        super(icon);
        applySettings();
    }

    public CustomButton(String text) {
        super(text);
        applySettings();
    }

    public CustomButton(Action a) {
        super(a);
        applySettings();
    }

    public CustomButton(String text, Icon icon) {
        super(text, icon);
        applySettings();
    }

    private void applySettings() {
        this.setFocusPainted(false);
        this.setFocusable(false);
        this.setMargin(new Insets(4, 4,4, 4));
        setResizable();
    }

    private void setResizable() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                try {
                    adapt();
                }
                catch (Exception ignored) {

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
        size = (float) Math.floor(size * 12 / (400 * 400) / 3);

        if(size <= 12) {
            size += 8;
        }

//        size += size * 0.2;
        this.setFont(font.deriveFont(font.getStyle(), size));
    }
}
