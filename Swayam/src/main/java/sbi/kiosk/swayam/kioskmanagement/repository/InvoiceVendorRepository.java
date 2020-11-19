package sbi.kiosk.swayam.kioskmanagement.repository;

//By Pankul 28-04-2020-----------STARTS---------

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sbi.kiosk.swayam.common.entity.InvoiceVendor;

	@Repository
	public interface InvoiceVendorRepository extends CrudRepository<InvoiceVendor, String>{

	}
