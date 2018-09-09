package com.datas.easyorder.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

import com.datas.utils.GuessingWord;
import com.plugin.imgcompress.ImgCompress;
import com.plugin.utils.DateHelper;
import com.plugin.utils.Md5;

/**
 * logic
 * @author yaoliang
 *
 */
public abstract class BaseLogic<T> implements IBaseLogic{

	private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseLogic.class);
	/**
	 * 上传最大图片宽度
	 */
	private int ImgWidth = 1400;
	
	//项目绝对路径
	@Value("${attachment.absolute.path}")
	public String absoultPath;
	
	@Value("${attachemnt.content.path}")
	public String contentPath;
	
	
	/**
	 * 保存单张图片
	 * @param multipartFile
	 * @param relativePath
	 */
	public void saveImg(MultipartFile multipartFile,String relativePath){
		if(multipartFile.isEmpty()){
			return;
		}
		
		String path = absoultPath + relativePath;
		if (!new File(path).isDirectory()) {
			new File(path).mkdirs();
		}
		
		try {
			if((multipartFile.getOriginalFilename().toLowerCase().endsWith("jpg") ||
					multipartFile.getOriginalFilename().toLowerCase().endsWith("gif")||
					multipartFile.getOriginalFilename().toLowerCase().endsWith("png")||
					multipartFile.getOriginalFilename().toLowerCase().endsWith("bmp"))){
				
				String fileName = multipartFile.getOriginalFilename();
				String listFileName = path + "/" + fileName;
				ImgCompress.resizePicture(multipartFile, new File(listFileName), ImgWidth);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
	}
	
	/**
	 * 保存多张图片
	 * @param multipartFiles
	 * @param relativePath
	 */
	public void saveImgs(MultipartFile[] multipartFiles,String relativePath){
		for(MultipartFile mf:multipartFiles){
			saveImg(mf, relativePath);
		}
	}

	/**
	 * 保存多个附件
	 * @param multipartFiles
	 * @param order
	 * @throws IOException 
	 */
	public void saveFiles(MultipartFile[] multipartFiles,String relativePath) {
		for(MultipartFile mf:multipartFiles){
			saveFile(mf, relativePath);
		}
	}
	
	/**
	 * 保存单个附件
	 * @param multipartFiles
	 * @param order
	 * @throws IOException 
	 */
	public void saveFile(MultipartFile multipartFile,String relativePath) {
		
		String path = absoultPath + relativePath;
		if (!new File(path).isDirectory()) {
			new File(path).mkdirs();
		}
		try {
			multipartFile.transferTo(new File(path + "/"  + multipartFile.getOriginalFilename()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	
	/**
	 * Update coloumns
	 * @param pk id
	 * @param atrributeName colomnName
	 * @param attributeValue colomnValue
	 * 
	 */
	@Transactional(rollbackOn=Exception.class)
	public T update(Long id, String atrributeName, String attributeValue) {
		T t = getRepository().findOne(id);
		try {
			Field field = t.getClass().getDeclaredField(atrributeName);
			field.setAccessible(true);
			if(atrributeName.equals("password")){
				attributeValue = Md5.getMd5String(attributeValue);
			}
			if(field.getType()  ==  String.class){
				field.set(t, attributeValue);
			}else if(field.getType().equals(Double.class) || field.getType().equals(double.class)){
				field.set(t, Double.valueOf(attributeValue));
			}else if(field.getType().equals(Integer.class) || field.getType().equals(int.class)){
				field.set(t, Integer.valueOf(attributeValue));
			}else if(field.getType().equals(Byte.class) || field.getType().equals(byte.class)){
				field.set(t, Byte.valueOf(attributeValue));
			}else if(field.getType().equals(java.util.Date.class)){
				field.set(t, DateHelper.parseYYYYMMDD(attributeValue));
			}
			for (Field f : t.getClass().getDeclaredFields()) {
				if(f.getName().equals("modifyTime")){
					f.setAccessible(true);
					f.set(t, Calendar.getInstance().getTime());
					break;
				}
			}
			getRepository().save(t);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * abstract CrudRepository
	 * @return
	 */
	public abstract CrudRepository<T, Long> getRepository();
	
	
	
	/**
	 * 获取关联查询
	 * @param q
	 * @param size
	 * @return
	 * @throws JSONException 
	 */
	public JSONObject getGuessingWords(String q, int size) throws JSONException {
		PageRequest pageRequest = new PageRequest(0, size);
		
		List<GuessingWord> list = guessingWordList(q, pageRequest);
		
		
		String[] strList = new String[2];
		StringBuffer s0 = new StringBuffer();
		StringBuffer s1 = new StringBuffer();
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			jsonObject.put(list.get(i).getWord(), list.get(i).getId());
			jsonArray.put(i, list.get(i).getWord());
			
		}
		jsonObject.put("source", jsonArray);
		strList[0] = s0.toString(); 
		strList[1] = s1.toString(); 
		return jsonObject;
	}
	
	@Override
	public List<GuessingWord> guessingWordList(String q, PageRequest pageRequest) {
		
		return new ArrayList<>();
	}
	
}
