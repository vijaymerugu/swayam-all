package sbi.kiosk.swayam.misreports.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.entity.MISAvailableColumns;
import sbi.kiosk.swayam.common.entity.MISGroupingCriteria;
import sbi.kiosk.swayam.common.entity.MISReportData;
import sbi.kiosk.swayam.misreports.dto.MISReportInputDto;
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
	public List<MISReportData> getMisReportData(MISReportInputDto misReportInputDto) {
		try {
			 List<MISReportData> misReportDataList = entityManager.createNamedStoredProcedureQuery("SP_MIS_REPORT")
			.setParameter("GROUPCRITERIA", misReportInputDto.getGroupingCriteriaId())
			.setParameter("FROM_DATE_PARAM", misReportInputDto.getFromDate())
			.setParameter("TO_DATE_PARAM", misReportInputDto.getToDate())
			.getResultList();
			return misReportDataList;
			} catch (Exception e) {
				logger.error("Exception in MISReportOutputDto." + e.getMessage());
				return new ArrayList<MISReportData>();
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
	public List<MISAvailableColumns> loadMISColumnsFromGroupId(String removeIds) {
		try {
			List<String> removeIdList = new ArrayList<>();
			removeIdList = Arrays.asList(removeIds.split(",")) ;
			List<MISAvailableColumns> misAvailableColumnsList = mISAvailableColumnsRepository.findByColumnIdNotIn(removeIdList);
			int i=1;
			for(MISAvailableColumns misAvailableColumns : misAvailableColumnsList) {
				misAvailableColumns.setColumnId(""+i);
				i++;
			}
			
			return misAvailableColumnsList;
		} catch (Exception e) {
			logger.error("Exception in getMISAvailableColumns:" + e.getMessage());
			return new ArrayList<MISAvailableColumns>();
		}
	}
}
