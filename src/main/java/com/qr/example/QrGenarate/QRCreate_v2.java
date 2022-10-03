package com.qr.example.QrGenarate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCreate_v2 {

    public static void main(String[] args) {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;

        try {

            bitMatrix = barcodeWriter.encode("https://github.com/MrBigBearrTR/QRImageGenerations",
                    BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream baos;
            FileOutputStream fos = null;

            baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            fos = new FileOutputStream(new File("C:\\Users\\Public\\QrCode2.jpg"));
            baos.writeTo(fos);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
