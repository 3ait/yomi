package com.datas.easyorder.db.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.datas.easyorder.db.entity.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
	  

	public static final byte paid_unpaid = 0;

	public static final byte paid_paidInPart = 1;
	
	public static final byte paid_paid = 2;
	

	/**
	 */
	public static final byte status_cancelled = 0;
	/**
	 * {value: 1, text: 'New Invoice'},
	 */
	public static final byte status_new = 1;
	/**
	 * 	{value: 2, text: 'Emailed'},
	 */
	public static final byte status_emailed = 2;
	/**
	 * {value: 3, text: 'status_paid'},
	 */
	public static final byte status_finished = 3;
	
	
	public Page<Invoice> findAll(Specification<Invoice> specification,Pageable pageable);

	public Invoice findOneByIdAndUserCompanyId(long invoiceId, Long id);

	public Invoice findOneByAliasId(String aliasId);

	public Page<Invoice> findByStatusInAndModifyTimeBefore(Byte[] statuses, Date startDate, Pageable pageable);

	public Page<Invoice> findByCustomerIdAndStatusInAndModifyTimeBefore(Long customerId, Byte[] openStatus,Date startDate, Pageable pageable);

	public List<Invoice> findAllByCustomerIdAndPaidIn(Long customerId, Byte[] status);

	public Page<Invoice> findByCustomerIdAndStatusInAndModifyTimeBetween(Long customerId, Byte[] status, Date startDate,
			Date time, Pageable pageable);
    
}
