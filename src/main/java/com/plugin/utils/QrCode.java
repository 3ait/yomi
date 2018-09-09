package com.plugin.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCode {
	
	
	public static void createZxing(String content,OutputStream outputStream) throws WriterException, IOException {
        int width=300;
        int hight=300;
        String format="png";
        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级L,M,Q,H
        hints.put(EncodeHintType.MARGIN, 2); //边距
        BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, hight, hints);
//        Path file=new File("D:/download/imag.png").toPath();
//        MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
        
    }
	
	
	
}
