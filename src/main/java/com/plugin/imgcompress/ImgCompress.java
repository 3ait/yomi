package com.plugin.imgcompress;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片压缩处理
 * @author Leo
 */
public class ImgCompress {
	
	
	
	/**
	 * 等比例缩放
	 * @param File inputPic
	 * @param File outputPic
	 * @param outputPicWidth
	 * 
	 */
	public static void resizePicture(MultipartFile fuMultipartFile,File outputPic,int outputPicWidth){
		BufferedImage image;
		try {
			image = ImageIO.read(fuMultipartFile.getInputStream());
			if (!isJpegImage(outputPic.getName()) && image.getTransparency() == Transparency.TRANSLUCENT) {
		        image = get24BitImage(image);                 // 第一种方式
		        // image = get24BitImage(image, Color.BLACK); // 第二种方式，可以指定背景色
		    }
			
			if(image.getWidth() > outputPicWidth){
				outputPicWidth =  image.getWidth();
				int	outputPicHeight = outputPicWidth*image.getHeight()/image.getWidth();
				
				BufferedImage bufferedImage = new BufferedImage(outputPicWidth, outputPicHeight,BufferedImage.TYPE_INT_RGB );
				Graphics2D graphics2d = bufferedImage.createGraphics();
				
				if(image.getWidth()>outputPicWidth){
					graphics2d.drawImage(image, 0,0,outputPicWidth,outputPicHeight, null);
				}else{
					graphics2d.drawImage(image, 0,0,image.getWidth(),outputPicHeight, null);
				}
				
				bufferedImage.flush();
				ImageIO.write(bufferedImage, "jpg", outputPic);
			}else{
				fuMultipartFile.transferTo(outputPic);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重新自定义尺寸 固定高宽
	 * @param fileInputStream
	 * @param outputPic
	 * @param outputPicWidth
	 * @param outputPicHeight
	 */
	public static void resizePicture(MultipartFile fuMultipartFile,File outputPic,int outputPicWidth,int outputPicHeight){
		BufferedImage image;
		try {
			image = ImageIO.read(fuMultipartFile.getInputStream());
			if(image.getWidth()>outputPicWidth){
				outputPicWidth =  image.getWidth();
				outputPicHeight = image.getHeight();
				if (!isJpegImage(outputPic.getName()) && image.getTransparency() == Transparency.TRANSLUCENT) {
					image = get24BitImage(image);                 // 第一种方式
					// image = get24BitImage(image, Color.BLACK); // 第二种方式，可以指定背景色
				}
				
				BufferedImage bufferedImage = new BufferedImage(outputPicWidth, outputPicHeight,BufferedImage.TYPE_INT_RGB );
				Graphics2D graphics2d = bufferedImage.createGraphics();
				graphics2d.drawImage(image, 0,0,outputPicWidth,outputPicHeight, null);
				bufferedImage.flush();
				ImageIO.write(bufferedImage, "jpg", outputPic);
			}else{
				fuMultipartFile.transferTo(outputPic);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重新自定义尺寸 固定高宽 自定义输出尺寸
	 * 添加水印
	 * @param InputStream
	 *            fileInputStream
	 * @param File
	 *            outputPic
	 * @param int
	 *            outputPicWidth
	 * @param int
	 *            outputPicHeight
	 * @param String
	 *            markImgUrl
	 */
	public static void resizeMarkPic(InputStream fileInputStream, File outputPic, int outputPicWidth, int flag,String markImgUrl) {
		try {
			
			BufferedImage image = ImageIO.read(fileInputStream);
			if (image.getWidth() < outputPicWidth) {
				outputPicWidth = image.getWidth();
			}

			int outputPicHeight = outputPicWidth;
			if (flag == 1) {
				outputPicHeight = outputPicWidth * image.getHeight() / image.getWidth();
			}
			BufferedImage bufferedImage = new BufferedImage(outputPicWidth, outputPicHeight,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2d = bufferedImage.createGraphics();
			graphics2d.drawImage(image, 0, 0, outputPicWidth, outputPicHeight, null);

			ImageIcon imgIcon = new ImageIcon(markImgUrl);
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = 0.3f; // 透明度
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 表示水印图片的位置
			// 6、水印图片的位置
			int interval = 50;
			
			for (int height = interval + imgIcon.getIconHeight(); height < outputPicHeight; height = height + interval + imgIcon.getIconHeight()) {
				for (int weight = interval + imgIcon.getIconWidth(); weight < outputPicWidth; weight = weight + interval + imgIcon.getIconWidth()) {
					graphics2d.drawImage(img, weight - imgIcon.getIconWidth(), height - imgIcon.getIconHeight(), null);
				}
			}
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			graphics2d.dispose();

			bufferedImage.flush();
			ImageIO.write(bufferedImage, "jpg", outputPic);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据文件后缀名判断是否是 JPEG 图片。
	 */
	private static boolean isJpegImage(String fileType) {
		return fileType.toLowerCase().endsWith("jpg") || fileType.toLowerCase().endsWith("jpeg");
	}
	/**
	 * 使用删除 Alpha 值的方式去掉图像的 Alpha 通道。
	 */
	private static BufferedImage get24BitImage(BufferedImage image) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    int[] argb = getRGBs(image.getRGB(0, 0, w, h, null, 0, w));
	    BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    newImage.setRGB(0, 0, w, h, argb, 0, w);
	    return newImage;
	}
	
	/**
	 * 将 32 位色彩转换成 24 位色彩（丢弃 Alpha 通道）。
	 */
	private static int[] getRGBs(int[] argbs) {
	    int[] rgbs = new int[argbs.length];
	    for (int i = 0; i < argbs.length; i++) {
	        rgbs[i] = argbs[i] & 0xffffff;
	    }
	    return rgbs;
	}
}