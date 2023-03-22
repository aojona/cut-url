package ru.kirill.cuturl.encoder;

public class Base62Encoder extends UrlEncoder {

    private static final String BASE = "012345689abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Base62Encoder(byte LENGTH) {
        super(LENGTH);
    }

    public String encode(String url) {
        char[] code = new char[tokenLength];
        for (int i = 0; i < tokenLength; i++)
            code[i] = BASE.charAt((int) (Math.random() * BASE.length()));
        return String.valueOf(code);
    }
}
