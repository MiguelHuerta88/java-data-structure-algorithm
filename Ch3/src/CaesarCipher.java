public class CaesarCipher {
    protected char[] encoder = new char[26];
    protected char[] decoder = new char[26];

    public CaesarCipher(int rotations) {
        for (int k = 0; k < 26; k++) {
            encoder[k] = (char)('A' + (k + rotations) % 26);
            decoder[k] = (char)('A' + (k - rotations + 26) % 26);
        }
    }

    public String encrypt(String message) {
        return transform(message, encoder);
    }

    public String decrypt(String secret) {
        return transform(secret, decoder);
    }

    private String transform(String original, char[] code) {
        char[] message = original.toCharArray();
        for (int k =0; k < message.length; k++) {
            if (Character.isUpperCase(message[k])) {
                int j = message[k] - 'A';
                message[k] = code[j];
            }
         }
        return new String(message);
    }

    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher(3);
        System.out.println("Encryption code = " + new String(cipher.encoder));
        System.out.println("Decryption code = " + new String(cipher.decoder));
        String message = "THE EAGLE IS IN PLAY; MEET AT JOE'S.";
        String coded = cipher.encrypt(message);
        System.out.println("Secret: " + coded);
        String answer = cipher.decrypt(coded);
        System.out.println("Message: " + answer);
    }
}
