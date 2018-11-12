package com.datas.easyorder.controller.web.attachment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.attachment.logic.AttachmentLogic;
import com.datas.easyorder.db.entity.Attachment;

/**
 * 
 * @author leo
 * Attachment management
 *
 */
@RestController
@RequestMapping("/web/attachment")
public class WebAttachmentController extends BaseController<Attachment> {

	@Autowired
	AttachmentLogic attachmentLogic;

	@RequestMapping(value = { "/title/{title}" })
	public ResponseEntity<Attachment> getAttachment(@PathVariable("title") String title) {

		Pageable pageable = new PageRequest(0,Integer.MAX_VALUE, Direction.DESC, "id");

		List<Attachment> list = attachmentLogic.getAttachmentsByTitle(title,pageable);

		Attachment attachment = null;
		if(list.size()>0){
			attachment = list.get(0);
		}
		return new ResponseEntity<Attachment>(attachment,HttpStatus.OK);
	}

}
