package com.datas.easyorder.db.dao;

import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.InvoiceItem;

public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {

    
}
