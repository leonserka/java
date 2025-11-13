import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ColorTest {

    @Test
    public void testDecodeAndGetters() {
        Color c = Color.decode("0x1FF0FF");
        assertEquals(31, c.getRed());
        assertEquals(240, c.getGreen());
        assertEquals(255, c.getBlue());
    }

    @Test
    public void testGetRGB() {
        Color c = new Color(31, 240, 255);
        assertEquals(0x1FF0FF, c.getRGB() & 0xFFFFFF);
    }

    @Test
    public void testRGBtoHSB() {
        float[] hsb = new float[3];
        Color.RGBtoHSB(31, 240, 255, hsb);

        // Hue ~184.7°
        assertEquals(184.7f / 360f, hsb[0], 0.01f);
        // Saturation ~87.8%
        assertEquals(0.878f, hsb[1], 0.01f);
        // Brightness = 1
        assertEquals(1.0f, hsb[2], 0.001f);
    }

    @Test
    public void testToHSL() {
        Color c = new Color(31, 240, 255);
        float[] hsl = c.toHSL();

        // Hue ~184.7°
        assertEquals(184.7f / 360f, hsl[0], 0.01f);
        // Saturation ~100%
        assertEquals(1.0f, hsl[1], 0.01f);
        // Lightness ~56.1%
        assertEquals(0.561f, hsl[2], 0.01f);
    }

    @Test
    public void testToCMYK() {
        Color c = new Color(31, 240, 255);
        float[] cmyk = c.toCMYK();

        // Cyan ~87.8%
        assertEquals(0.878f, cmyk[0], 0.01f);
        // Magenta ~5.9%
        assertEquals(0.059f, cmyk[1], 0.01f);
        // Yellow = 0%
        assertEquals(0f, cmyk[2], 0.001f);
        // Black = 0%
        assertEquals(0f, cmyk[3], 0.001f);
    }
}
