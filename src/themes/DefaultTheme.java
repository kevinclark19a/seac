package themes;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalTheme;

import java.awt.*;

import java.io.IOException;

import utils.log.Log;

/**
 * @author Kevin Clark
 *
 */
public class DefaultTheme extends MetalTheme {
    private final Font font;

    public DefaultTheme() {
        super();

        Font font;

        try {
            var fontStream = ClassLoader.getSystemClassLoader().getResourceAsStream("themes/dejavu-sans.ttf");
            font = FontUIResource.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(64f);
        } catch (FontFormatException | IOException e) {
            Log.ex(e, "Something went wrong loading the special font. Falling back to default.");
            font = new Font("Candara", Font.PLAIN, 64);
        }

        this.font = font;
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getControlTextFont()
     */
    @Override
    public FontUIResource getControlTextFont() {
        return new FontUIResource(font);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getMenuTextFont()
     */
    @Override
    public FontUIResource getMenuTextFont() {
        return this.getControlTextFont();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getName()
     */
    @Override
    public String getName() {
        return "Seac-Theme-0.1.0";
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getSubTextFont()
     */
    @Override
    public FontUIResource getSubTextFont() {
        return this.getControlTextFont();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getSystemTextFont()
     */
    @Override
    public FontUIResource getSystemTextFont() {
        return this.getControlTextFont();
    }

    @Override
    public ColorUIResource getUserTextColor() {
        return new ColorUIResource(0, 0, 0);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getUserTextFont()
     */
    @Override
    public FontUIResource getUserTextFont() {
        return this.getControlTextFont();
    }

    @Override
    public ColorUIResource getWindowBackground() {
        return new ColorUIResource(224, 230, 255);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getWindowTitleFont()
     */
    @Override
    public FontUIResource getWindowTitleFont() {
        return this.getControlTextFont();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getPrimary1()
     */
    @Override
    protected ColorUIResource getPrimary1() {
        return new ColorUIResource(0, 0, 0);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getPrimary2()
     */
    @Override
    protected ColorUIResource getPrimary2() {
        return new ColorUIResource(137, 150, 180);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getPrimary3()
     */
    @Override
    protected ColorUIResource getPrimary3() {
        return new ColorUIResource(137, 150, 180);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getSecondary1()
     */
    @Override
    protected ColorUIResource getSecondary1() {
        return new ColorUIResource(0, 0, 0);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getSecondary2()
     */
    @Override
    protected ColorUIResource getSecondary2() {
        return new ColorUIResource(0, 0, 0);
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.plaf.metal.MetalTheme#getSecondary3()
     */
    @Override
    protected ColorUIResource getSecondary3() {
        return new ColorUIResource(157, 170, 200);
    }
}