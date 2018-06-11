package com.akash.src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

public class GenerateInvoice {

    public boolean save(String clientname, String clientPhone, String clientAddress, float netAmount, String logopath, String signaturePath, ArrayList<InvoiceModel> invoiceList, String invoiceNo,float pendingAmt,String gst) {
        String hGST = String.valueOf(Float.parseFloat(gst)/2);
        float hGSTValue = (Float.parseFloat(hGST)/100*netAmount);
        
                System.err.println(hGST);
                        System.err.println(hGSTValue);


        try {
            String storeName = "Omega Mobile Store";
            String storeAddress = "Main Road Gudalur, The Nilgiris, Tamilnadu";
            String ph = "9486451238";
            String email = "samadwelgate@gmail.com";
            String code = "33ASWPA0523E1Z7:33ASWPA0523E1Z7";

            XWPFDocument document = new XWPFDocument();
            CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
            CTPageMar pageMar = sectPr.addNewPgMar();
            pageMar.setLeft(BigInteger.valueOf(720L));
            pageMar.setTop(BigInteger.valueOf(230L));
            pageMar.setRight(BigInteger.valueOf(720L));
            pageMar.setBottom(BigInteger.valueOf(460L));

            XWPFTable productTable = document.createTable();

            productTable.setInsideHBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
            productTable.setInsideVBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
            productTable.setCellMargins(50, 50, 50, 50);
            productTable.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(11000));

            XWPFTableRow row1 = productTable.getRow(0);

            XWPFTableCell cellLogo = row1.getCell(0);

            cellLogo.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2750));
            cellLogo.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
            cellLogo.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            XWPFParagraph logoPara = cellLogo.addParagraph();
            logoPara.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun logoRun = logoPara.createRun();
            BufferedImage bimg1 = ImageIO.read(new File(logopath));
            int imgFormat = getImageFormat(logopath);
            logoRun.addPicture(new FileInputStream(new File(logopath)), imgFormat, logopath, Units.toEMU(100), Units.toEMU(90));

            XWPFTableCell cellSellerDetails = row1.createCell();
            cellSellerDetails.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8250));
            cellSellerDetails.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
            cellSellerDetails.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            logoPara = cellSellerDetails.addParagraph();
            logoPara.setAlignment(ParagraphAlignment.LEFT);
            logoRun = logoPara.createRun();
            logoRun.setText(storeName);
            logoRun.setBold(true);
            logoRun.setFontSize(16);
            logoRun.addBreak();

            logoRun = logoPara.createRun();
            logoRun.setText(ph);
            logoRun.addBreak();

            logoRun = logoPara.createRun();
            logoRun.setText(email);
            logoRun.addBreak();

            logoRun = logoPara.createRun();
            logoRun.setText(storeAddress);
            logoRun.addBreak();

            logoRun = logoPara.createRun();
            logoRun.setText(code);
            logoRun.addBreak();

            XWPFParagraph para = document.createParagraph();
            XWPFRun paraRun = para.createRun();
            para.setAlignment(ParagraphAlignment.CENTER);
            paraRun.setFontSize(16);
            paraRun.setBold(true);
            paraRun.setText("Invoice");
            paraRun.addBreak();
            paraRun.setFontSize(10);
            paraRun.setText(invoiceNo);
            paraRun.addBreak();
            paraRun.setText(new MainUI().setdate("dd-MM-yyyy"));

            para = document.createParagraph();
            paraRun = para.createRun();
            para.setAlignment(ParagraphAlignment.LEFT);
            paraRun = para.createRun();
            paraRun.setText("Bill To");
            paraRun.setFontSize(12);
            paraRun.addBreak();
            paraRun = para.createRun();
            paraRun.setText(clientname);
            paraRun.setFontSize(12);
            paraRun.setBold(true);
            paraRun.addBreak();
            paraRun = para.createRun();
            paraRun.setText(clientAddress);
            paraRun.setFontSize(12);
            paraRun.addBreak();
            paraRun = para.createRun();
            paraRun.setText(clientPhone);
            paraRun.setFontSize(12);

            productTable = document.createTable();

            productTable.setInsideHBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
            productTable.setInsideVBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
            productTable.setCellMargins(50, 50, 50, 50);
            productTable.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(11000));

            XWPFTableRow rowHeader = productTable.getRow(0);
            XWPFTableCell cellHeader = rowHeader.getCell(0);
            rowHeader.setHeight(100);
            cellHeader.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);
            cellHeader.setColor("66b2ff");
            para = cellHeader.addParagraph();
            para.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run = para.createRun();
            run.setText("No.");
            run.setBold(true);

            cellHeader = rowHeader.createCell();
            cellHeader.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5000));
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);
            cellHeader.setColor("66b2ff");
            para = cellHeader.addParagraph();
            para.setAlignment(ParagraphAlignment.LEFT);
            run = para.createRun();
            run.setText("Product");
            run.setBold(true);

            cellHeader = rowHeader.createCell();
            cellHeader.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);
            cellHeader.setColor("66b2ff");
            para = cellHeader.addParagraph();
            para.setAlignment(ParagraphAlignment.LEFT);
            run = para.createRun();
            run.setText("Qty.");
            run.setBold(true);

            cellHeader = rowHeader.createCell();
            cellHeader.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);
            cellHeader.setColor("66b2ff");
            para = cellHeader.addParagraph();
            para.setAlignment(ParagraphAlignment.LEFT);
            run = para.createRun();
            run.setText("Rate");
            run.setBold(true);

            cellHeader = rowHeader.createCell();
            cellHeader.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);
            cellHeader.setColor("66b2ff");
            para = cellHeader.addParagraph();
            para.setAlignment(ParagraphAlignment.LEFT);
            run = para.createRun();
            run.setText("Discount");
            run.setBold(true);

            cellHeader = rowHeader.createCell();
            cellHeader.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
            cellHeader.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);
            cellHeader.setColor("66b2ff");
            para = cellHeader.addParagraph();
            para.setAlignment(ParagraphAlignment.LEFT);
            run = para.createRun();
            run.setText("Amount");
            run.setBold(true);

            XWPFTableRow row;
            XWPFTableCell cell;

            if (invoiceList.size() > 0) {
                int i = 0;
                for (InvoiceModel item : invoiceList) {
                    //            if(i==0)
                    row = productTable.createRow();
                    //            else
                    //                row = productTable.getRow(i);

                    i++;

                    cell = row.getCell(0);
                    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                    cell.setText(String.valueOf(i));

                    cell = row.getCell(1);
                    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5000));
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);

                    cell.setText(item.getpBrand() + " " + item.getpModel());

                    cell = row.getCell(2);
                    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                    cell.setText(String.valueOf(item.getQty()));

                    cell = row.getCell(3);
                    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                    cell.setText(String.valueOf(item.getpSellingPrice()));

                    cell = row.getCell(4);
                    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                    cell.setText(String.valueOf(item.getDiscount()));

                    cell = row.getCell(5);
                    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                    cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                    cell.setText(String.valueOf(item.getTotal()));
                }

                row = productTable.createRow();

                cell = row.getCell(0);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(10));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);

                cell = row.getCell(3);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(10));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                //        para = cell.addParagraph();
                //        para.setAlignment(ParagraphAlignment.LEFT);
                //        run = para.createRun();
                cell.setText("Item code");

                cell = row.getCell(5);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(20));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                //        para = cell.addParagraph();
                //        para.setAlignment(ParagraphAlignment.LEFT);
                //        run = para.createRun();
                //        run.setBold(true);
                cell.setText("Rs. " + (netAmount-(2*hGSTValue)));

                row = productTable.createRow();

                cell = row.getCell(0);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(10));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);

                cell = row.getCell(1);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(10000));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                //        para = cell.addParagraph();
                //        para.setAlignment(ParagraphAlignment.LEFT);
                //        run = para.createRun();
                //        run.setFontSize(10);
                cell.setText("Good Once sold will not be taken up or exchange");

                cell = row.getCell(3);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                //        para = cell.addParagraph();
                //        para.setAlignment(ParagraphAlignment.LEFT);
                //        run = para.createRun();
                cell.setText("Total");

                cell = row.getCell(5);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                //        para = cell.addParagraph();
                //        para.setAlignment(ParagraphAlignment.LEFT);
                //        run = para.createRun();
                //        run.setBold(true);
                cell.setText("Rs. " + (netAmount-(2*hGSTValue)));

                row = productTable.createRow();

                cell = row.getCell(0);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(10));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);

                cell = row.getCell(1);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(10000));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                //        para = cell.addParagraph();
                //        para.setAlignment(ParagraphAlignment.LEFT);
                //        run = para.createRun();
                //        run.setFontSize(9);

                cell.setText("warranty/services available only on brand service centres, please contact your nearest brand service center for any manufacture defects.");

                cell = row.getCell(3);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);
                para = cell.addParagraph();
                para.setAlignment(ParagraphAlignment.LEFT);
                run = para.createRun();
//                cell.setText("(-)Paid");
                
                run.setText("(+) CGST");
                run.addBreak();
                 run.setText("(+) SGST");
                run.addBreak();
                run.setText("(-) Unpaid");
                run.addBreak();
                run.setText("    Paid");
                
                cell = row.getCell(5);
                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);
                cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);

                para = cell.addParagraph();
                para.setAlignment(ParagraphAlignment.LEFT);
                run = para.createRun();
//                cell.setText("(-)Paid");
                
                run.setText("("+hGST+"%) Rs."+hGSTValue);
                run.addBreak();
                run.setText("("+hGST+"%) Rs."+hGSTValue);
                run.addBreak();
                run.setText("RS. "+String.valueOf(pendingAmt));
                run.addBreak();
                run.setText("Rs. "+(netAmount-pendingAmt));

            } 

            XWPFParagraph signPara = document.createParagraph();
            signPara.setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun signRun = signPara.createRun();
            signRun.addBreak();
            signRun.addBreak();
            BufferedImage bimg = ImageIO.read(new File(signaturePath));
            imgFormat = getImageFormat(signaturePath);
            signRun.addPicture(new FileInputStream(new File(signaturePath)), imgFormat, signaturePath, Units.toEMU(80), Units.toEMU(50));
            signRun.addBreak();
            signRun.setText("Omega Mobile Shop");
            signRun.addBreak();
            signRun.setText("Signature");

            String PATH = "Invoice" + File.separator;
            File directory = new File(PATH);

            if (!directory.exists()) {
                directory.mkdir();
            }
            FileOutputStream out = new FileOutputStream(PATH + File.separator + invoiceNo + ".docx");
            document.write(out);
            out.close();
            document.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int getImageFormat(String imgFileName) {
        int format;
        if (imgFileName.endsWith(".emf")) {
            format = XWPFDocument.PICTURE_TYPE_EMF;
        } else if (imgFileName.endsWith(".wmf")) {
            format = XWPFDocument.PICTURE_TYPE_WMF;
        } else if (imgFileName.endsWith(".pict")) {
            format = XWPFDocument.PICTURE_TYPE_PICT;
        } else if (imgFileName.endsWith(".jpeg") || imgFileName.endsWith(".jpg")) {
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        } else if (imgFileName.endsWith(".png")) {
            format = XWPFDocument.PICTURE_TYPE_PNG;
        } else if (imgFileName.endsWith(".dib")) {
            format = XWPFDocument.PICTURE_TYPE_DIB;
        } else if (imgFileName.endsWith(".gif")) {
            format = XWPFDocument.PICTURE_TYPE_GIF;
        } else if (imgFileName.endsWith(".tiff")) {
            format = XWPFDocument.PICTURE_TYPE_TIFF;
        } else if (imgFileName.endsWith(".eps")) {
            format = XWPFDocument.PICTURE_TYPE_EPS;
        } else if (imgFileName.endsWith(".bmp")) {
            format = XWPFDocument.PICTURE_TYPE_BMP;
        } else if (imgFileName.endsWith(".wpg")) {
            format = XWPFDocument.PICTURE_TYPE_WPG;
        } else {
            return 0;
        }
        return format;
    }

}
