public class Main {
    public static void main(String[] args) {
        String hexColor = "0x1FF0FF";

        Color c = Color.decode(hexColor);

        float[] hsbCode = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);

        System.out.println("Boja u HEX formatu: 0x" +
                Integer.toHexString(c.getRGB() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " +
                c.getRed() + ", " + c.getGreen() + ", " + c.getBlue());
        System.out.println("Boja u HSB formatu: " +
                (hsbCode[0] * 360) + "°, " +
                (hsbCode[1] * 100) + "%, " +
                (hsbCode[2] * 100) + "%");
// 2 dio 2 zad
        float[] hsl = c.toHSL();
        System.out.println("Boja u HSL formatu: " +
                (hsl[0] * 360) + "°, " +
                (hsl[1] * 100) + "%, " +
                (hsl[2] * 100) + "%");

        float[] cmyk = c.toCMYK();
        System.out.println("Boja u CMYK formatu: " +
                (cmyk[0] * 100) + "%, " +
                (cmyk[1] * 100) + "%, " +
                (cmyk[2] * 100) + "%, " +
                (cmyk[3] * 100) + "%");
    }
}

class Color {
    private int red, green, blue;

    public Color(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    public static Color decode(String hex) {
        if (hex.startsWith("0x") || hex.startsWith("0X"))
            hex = hex.substring(2);
        int rgb = Integer.parseInt(hex, 16);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return new Color(r, g, b);
    }

    public int getRed()   { return red; }
    public int getGreen() { return green; }
    public int getBlue()  { return blue; }

    public int getRGB() {
        return (red << 16) | (green << 8) | blue;
    }

    // -----------------------------
    // RGB → HSB (postojeće)
    // -----------------------------
    public static float[] RGBtoHSB(int r, int g, int b, float[] hsb) {
        if (hsb == null) hsb = new float[3];
        float rf = r / 255f, gf = g / 255f, bf = b / 255f;
        float max = Math.max(rf, Math.max(gf, bf));
        float min = Math.min(rf, Math.min(gf, bf));
        float delta = max - min;

        float h;
        if (delta == 0) h = 0;
        else if (max == rf) h = ((gf - bf) / delta) % 6;
        else if (max == gf) h = ((bf - rf) / delta) + 2;
        else h = ((rf - gf) / delta) + 4;

        h = (h / 6f);
        if (h < 0) h += 1f;

        float s = (max == 0) ? 0 : delta / max;
        float v = max;

        hsb[0] = h; hsb[1] = s; hsb[2] = v;
        return hsb;
    }

    //2 dio
    public float[] toHSL() {
        float r = red / 255f;
        float g = green / 255f;
        float b = blue / 255f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float delta = max - min;

        float h, s, l;
        l = (max + min) / 2f;

        if (delta == 0) {
            h = 0;
            s = 0;
        } else {
            s = delta / (1 - Math.abs(2 * l - 1));
            if (max == r)
                h = ((g - b) / delta) % 6;
            else if (max == g)
                h = ((b - r) / delta) + 2;
            else
                h = ((r - g) / delta) + 4;

            h /= 6f;
            if (h < 0) h += 1f;
        }

        return new float[]{h, s, l};
    }

    public float[] toCMYK() {
        float r = red / 255f;
        float g = green / 255f;
        float b = blue / 255f;

        float k = 1 - Math.max(r, Math.max(g, b));
        if (k == 1) { // potpuno crna
            return new float[]{0, 0, 0, 1};
        }
        float c = (1 - r - k) / (1 - k);
        float m = (1 - g - k) / (1 - k);
        float y = (1 - b - k) / (1 - k);

        return new float[]{c, m, y, k};
    }
}

