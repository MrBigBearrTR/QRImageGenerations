package com.qr.example.QrGenarate;

import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCreate_v1 {

    public static final int SIZE_LENGHT = 300;

    public static void main(String[] args) {
        try {
            System.out.println("----START-----");
            createQrImage("https://github.com/MrBigBearrTR/QRImageGenerations".getBytes(), "jpg");
            System.out.println("-----END------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] createQrImage(byte[] qrCodeBytes, String fileType) throws WriterException, IOException {

        String qrCodeString;
        int matrixWidth;
        byte[] retVal;

        Map<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
        QRCodeWriter qrCodeWriter;

        BitMatrix byteMatrix;
        BufferedImage image;
        Graphics2D graphics;
        ByteArrayOutputStream baos;
        FileOutputStream fos = null;

        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        qrCodeWriter = new QRCodeWriter();
        qrCodeString = new String(qrCodeBytes);

        byteMatrix = qrCodeWriter.encode(qrCodeString, BarcodeFormat.QR_CODE, SIZE_LENGHT, SIZE_LENGHT, hintMap);
        matrixWidth = byteMatrix.getWidth();
        image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_BYTE_BINARY);
        image.createGraphics();
        graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        baos = new ByteArrayOutputStream();
        ImageIO.write(image, fileType, baos);
        baos.flush();
        retVal = baos.toByteArray();
        fos = new FileOutputStream(new File("C:\\Users\\Public\\QrCode." + fileType));
        baos.writeTo(fos);

        return retVal;
    }
}
