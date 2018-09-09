package com.datas.easyorder.controller.administrator.attachment.logic;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.attachment.AttachmentForm;
import com.datas.easyorder.db.dao.AttachmentRepository;
import com.datas.easyorder.db.dao.AttachmentSpecifications;
import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Menu;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

@Component
public class AttachmentLogic extends BaseLogic<Attachment> {

	@Autowired
	AttachmentRepository attachmentRepository;

	/**
	 * 删除附件
	 * @param attachmentId
	 * @return boolean
	 */
	@Transactional(rollbackOn = Exception.class)
	public boolean delAttachmentById(Long attachmentId) {
		boolean ret = false;
		Attachment attachment = attachmentRepository.findOne(attachmentId);
		if (attachment != null) {
			File f = new File(absoultPath.replace(contentPath, attachment.getFilePath()));
			if (f.exists()) {
				f.delete();
			}
			attachmentRepository.delete(attachment);
			ret = true;
		}
		return ret;
	}

	@Override
	public CrudRepository<Attachment, Long> getRepository() {
		return attachmentRepository;
	}

	/**
	 * 
	 * @param searchForm
	 * @param pageable
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Attachment> attachementList(SearchForm searchForm, Pageable pageable) {

		return attachmentRepository.findAll(AttachmentSpecifications.getSearchSpecification(searchForm), pageable);

	}

	/**
	 * save attachment
	 * 
	 * @param attachmentForm
	 */
	@Transactional(rollbackOn = Exception.class)
	public void saveAttachments(AttachmentForm attachmentForm) {

		// save attachment
		String defaultImgSrc = "/article/" + DateHelper.getYYYYMMDD();
		super.saveFiles(attachmentForm.getAttachments(), defaultImgSrc);

		for (MultipartFile mf : attachmentForm.getAttachments()) {
			// SAVE Attachment
			Attachment attachment = new Attachment();
			BeanUtils.copyProperties(attachmentForm.getAttachment(), attachment);
			Menu menu = null;
			Long categoryId = -1L;
			if (attachmentForm.getMenu3Id() != -1) {
				categoryId = attachmentForm.getMenu3Id();
			} else if (attachmentForm.getMenu2Id() != -1) {
				categoryId = attachmentForm.getMenu2Id();
			} else if (attachmentForm.getMenu1Id() != -1) {
				categoryId = attachmentForm.getMenu1Id();
			}
			if (categoryId != -1) {
				menu = new Menu();
				menu.setId(categoryId);
				attachment.setMenu(menu);
			}

			// save attachment
			attachment.setFileName(mf.getOriginalFilename());
			attachment.setFilePath(contentPath + defaultImgSrc + "/" + mf.getOriginalFilename());
			attachment.setCreateTime(Calendar.getInstance().getTime());

			attachmentRepository.save(attachment);
		}

	}

	public List<Attachment> getByProductId(Long productId) {
		return attachmentRepository.findAllByProductId(productId);
	}


}
