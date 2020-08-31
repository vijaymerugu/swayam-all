package sbi.kiosk.swayam.misreports.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.MISGroupingCriteria;
import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;
import sbi.kiosk.swayam.misreports.dto.MISReportOutputDto;
import sbi.kiosk.swayam.misreports.repository.MISAvailableColumnsRepository;
import sbi.kiosk.swayam.misreports.repository.MISGroupingCriteriaRepository;

/**
 * @author vmph2371481
 *
 */
@Service
public class MISReporterServiceImpl implements MISReporterService {

	Logger logger = LoggerFactory.getLogger(MISReporterServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	MISGroupingCriteriaRepository mISGroupingCriteriaRepository;

	@Autowired
	MISAvailableColumnsRepository mISAvailableColumnsRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<MISReportOutputDto> getMisReportData(MISReportInputDto misReportInputDto) {
		try {
			// TODO:Change proc name
			List<MISReportOutputDto> misReportOutputDtoList =
			 entityManager.createNamedStoredProcedureQuery("SP_GET_MIS_REPORT_DATA")
			 .setParameter("fromDate", misReportInputDto.getFromDate())
			 .setParameter("toDate", misReportInputDto.getFromDate())
			 .setParameter("groupingCriteria", misReportInputDto.getGroupingCriteria())
			 .setParameter("selectedColumnIndexes", misReportInputDto.getSelectedColumnIndexes())
			 .getResultList();
			
			return misReportOutputDtoList;
		} catch (Exception e) {
			logger.error("Exception in getMisReportData:" + e.getMessage());
			return new ArrayList<MISReportOutputDto>();
		}
	}

	@Override
	public List<MISGroupingCriteria> getMISGroupingCriteria() {
		try {
			return (List<MISGroupingCriteria>) mISGroupingCriteriaRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception in getMISGroupingCriteria:" + e.getMessage());
			return new ArrayList<MISGroupingCriteria>();
		}
	}

	@Override
	public List<MISAvailableColumns> getMISAvailableColumns() {
		try {
			return (List<MISAvailableColumns>) mISAvailableColumnsRepository.findAll();
		} catch (Exception e) {
			logger.error("Exception in getMISAvailableColumns:" + e.getMessage());
			return new ArrayList<MISAvailableColumns>();
		}
	}
}
