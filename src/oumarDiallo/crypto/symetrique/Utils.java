package oumarDiallo.crypto.symetrique;

/**
 * General utilities for the second chapter examples.
 */
public class Utils
{
    private static String digits = "0123456789abcdef";

    /**
     * Return length many bytes of the passed in byte array as a hex string.
     *
     * @param data the bytes to be converted.
     * @param length the number of bytes in the data block to be converted.
     * @return a hex representation of length bytes of data.
     */
    public static String toHex(byte[] data, int length)
    {
        StringBuffer    buf = new StringBuffer();

        for (int i = 0; i != length; i++)
        {
            int v = data[i] & 0xff;

            buf.append(digits.charAt(v >>4));
            buf.append(digits.charAt(v & 0xf));
        }

        return buf.toString();
    }
    
    public static byte[] fromHex(String hexString) throws IllegalArgumentException {
        hexString = hexString.toLowerCase();
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("La représentation hexadécimale doit avoir une longueur paire.");
        }

        int length = hexString.length() / 2;
        byte[] result = new byte[length];

        for (int i = 0; i < length; i++) {
            int highDigit = digits.indexOf(hexString.charAt(i * 2));
            int lowDigit = digits.indexOf(hexString.charAt(i * 2 + 1));

            if (highDigit == -1 || lowDigit == -1) {
                throw new IllegalArgumentException("Caractère hexadécimal invalide trouvé.");
            }

            result[i] = (byte) ((highDigit << 4) + lowDigit);
        }

        return result;
    }
    
  
    public static String hexToString(String hexString) throws IllegalArgumentException {
        hexString = hexString.toLowerCase();
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("La représentation hexadécimale doit avoir une longueur paire.");
        }

        int length = hexString.length() / 2;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int highDigit = digits.indexOf(hexString.charAt(i * 2));
            int lowDigit = digits.indexOf(hexString.charAt(i * 2 + 1));

            if (highDigit == -1 || lowDigit == -1) {
                throw new IllegalArgumentException("Caractère hexadécimal invalide trouvé.");
            }

            result.append((char) ((highDigit << 4) + lowDigit));
        }

        return result.toString();
    }

    
    
    /**
     * Return the passed in byte array as a hex string.
     *
     * @param data the bytes to be converted.
     * @return a hex representation of data.
     */
    public static String toHex(byte[] data)
    {
        return toHex(data, data.length);
    }
}

