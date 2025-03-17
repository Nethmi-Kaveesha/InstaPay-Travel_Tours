//package com.example.InstaPay_Travel_Tours.service;
//
//import java.nio.ByteBuffer;
//import java.util.UUID;
//
//public class UUIDConverter {
//
//    public static String convertBinaryToUUID(byte[] binary) {
//        if (binary == null || binary.length != 16) {
//            throw new IllegalArgumentException("Invalid binary data for UUID conversion");
//        }
//
//        ByteBuffer bb = ByteBuffer.wrap(binary);
//        long high = bb.getLong();
//        long low = bb.getLong();
//        return new UUID(high, low).toString();
//    }
//}
