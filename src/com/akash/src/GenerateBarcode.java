/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akash.src;

import com.aspose.barcode.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodePDF417;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author akash
 */
public class GenerateBarcode {

    String data, fileName;
    String filePath = "Barcodes";

    public GenerateBarcode(String data, String fileName) {
        this.data = data;
        this.fileName = fileName;
        createLoacalDirectory();

    }

    public boolean encode() throws Exception {

        
        Barcode128 barcode = new Barcode128();
        barcode.setGenerateChecksum(true);
        barcode.setCode(data);
        
//        BarcodePDF417 barcode = new BarcodePDF417();
//        barcode.setText(data);
        java.awt.Image img = barcode.createAwtImage(Color.BLACK, Color.WHITE);
        BufferedImage outImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        outImage.getGraphics().drawImage(img, 0, 0, null);
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ImageIO.write(outImage, "png", bytesOut);
        bytesOut.flush();
        byte[] pngImageData = bytesOut.toByteArray();
        FileOutputStream fos = new FileOutputStream(filePath + File.separator + fileName + ".png");
        fos.write(pngImageData);
        fos.flush();
        fos.close();
        
        
        
//        
//        BarCode barcode = new BarCode();
//        barcode.setCodeToEncode(data);
//        barcode.setSymbology(IBarCode.CODE128);
//        barcode.setX(2);
//        barcode.setY(50);
//        barcode.setRightMargin(0);
//        barcode.setLeftMargin(0);
//        barcode.setTopMargin(0);
//        barcode.setBottomMargin(0);
//        barcode.setChecksumEnabled(false);
//        barcode.setFnc1(IBarCode.FNC1_NONE);
//        try {
//            //barcode.draw(filePath + File.separator + fileName + ".gif");
//            ImageIO.write(barcode, "gif", filePath + File.separator + fileName + ".gif");
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
        return true;
    }

    private void createLoacalDirectory() {
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdir();
        }
    }

//    public static String convertToBase64(String barcodePath) {
//        String type = "jpg";
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File(barcodePath));
//        } catch (IOException e) {
//        }
//
//        String imageString = null;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        try {
//            ImageIO.write(img, type, bos);
//            byte[] imageBytes = bos.toByteArray();
//
//            BASE64Encoder encoder = new BASE64Encoder();
//            imageString = encoder.encode(imageBytes);
//
//            bos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return imageString;
//    }
//
//    public static BufferedImage decodeToImage(String imageString) {
//
//        BufferedImage image = null;
//        byte[] imageByte;
//        try {
//            BASE64Decoder decoder = new BASE64Decoder();
//            imageByte = decoder.decodeBuffer(imageString);
//            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//            image = ImageIO.read(bis);
//            bis.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return image;
//    }
}
