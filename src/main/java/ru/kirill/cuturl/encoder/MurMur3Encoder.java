package ru.kirill.cuturl.encoder;

import com.google.common.hash.Hashing;

public class MurMur3Encoder extends HashEncoder {

    public MurMur3Encoder(byte tokenLength) {
        super(tokenLength, tokenLength > 8 ? Hashing.murmur3_128() : Hashing.murmur3_32_fixed());
    }
}