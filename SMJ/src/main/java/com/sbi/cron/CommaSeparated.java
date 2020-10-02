package com.sbi.cron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbi.cron.dto.BranchTransactionDayDto;
import com.sbi.cron.dto.SwayamTransactionhourlyDto;
import com.sbi.cron.entity.BranchTransactionDayEntity;
import com.sbi.cron.entity.SwayamTranactionhourEntity;
import com.sbi.cron.repository.BranchTransactionRepository;
import com.sbi.cron.repository.SwayamTranactionhourRepository;
import com.sbi.cron.repository.SwayanTxnReportRepository;

@Component
@Transactional
public class CommaSeparated {
	
	Logger logger = LoggerFactory.getLogger(CommaSeparated.class);

	@Autowired
	BranchTransactionRepository branchTransactionRepository;

	@Autowired
	SwayamTranactionhourRepository swayamTranactionhourRepository;
	
	@Autowired
	SwayanTxnReportRepository swayanTxnReportRepository;

	public void fileRead(String path) {

		BufferedReader br = null;
		List<BranchTransactionDayDto> listDto = new ArrayList<BranchTransactionDayDto>();

		int count = 0;
		try {
			String strLine;
			File filePath = getLastModified(path);
			//logger.info("filepath" + filePath);

			br = new BufferedReader(new FileReader(filePath));
			//System.out.println("===========================branch txn read file start==============================" + new Date());
			while ((strLine = br.readLine()) != null) {
				//logger.info("strLine" + strLine);
				if (count != 0) {

					String rep = strLine.replaceAll("\"", "");
					String[] data = rep.split("\\,");

					/*
					 * logger.info("CIRCLENAME - " + data[0] + " MODULENAME- " + data[1] +
					 * " NETWORKNAME- " + data[2] + " REGIONNAME- " + data[3] + " BRANCH_NO- " +
					 * data[4] + " BRANCHNAME- " + data[5] + " LAST_PBK_DT- " + data[6] +
					 * " NO_OF_ACCOUNTS- " + data[7]);
					 */

					BranchTransactionDayDto dto = new BranchTransactionDayDto();
					dto.setCircleName(data[0].trim());
					dto.setModuleName(data[1].trim());
					dto.setNetworkName(data[2].trim());
					dto.setRegionName(data[3].trim());
					dto.setBranchNo(data[4].trim());
					dto.setBranchName(data[5].trim());
					dto.setLastPbkdate(data[6].trim());
					dto.setNoOfAccounts(data[7].trim());
					listDto.add(dto);

				}
				count++;
			}
			
			addBranchTransactionDay(listDto);

		} catch (IOException exp) {
			logger.error("Error while reading file " + exp.getMessage());
		} finally {
			try {

				if (br != null) {
					br.close();
				}
			} catch (IOException e) {

				logger.error("Error while reading BufferedReader " + e.getMessage());
			}
		}

	}

	public void addBranchTransactionDay(List<BranchTransactionDayDto> listp) {

		BranchTransactionDayEntity entity = null;
		List<BranchTransactionDayEntity> listentity = new ArrayList<BranchTransactionDayEntity>();
		//logger.info("listp" + listp);

		try {
			//System.out.println("===========================branch txn list start==============================" + new Date());
			for (BranchTransactionDayDto lidtDto1 : listp) {

				entity = new BranchTransactionDayEntity();
				entity.setCircleName(lidtDto1.getCircleName());// 1
				entity.setModuleName(lidtDto1.getModuleName());// 2
				entity.setNetworkName(lidtDto1.getNetworkName());// 3
				entity.setRegionName(lidtDto1.getRegionName());// 4
				entity.setBranchNo(lidtDto1.getBranchNo());// 5
				entity.setBranchName(lidtDto1.getBranchName());// 6
				entity.setLastPbkdate(lidtDto1.getLastPbkdate());// 7
				entity.setNoOfAccounts(lidtDto1.getNoOfAccounts());// 8
				listentity.add(entity);

			}
			logger.info("=================Branch TXN Daily Upload Stat=================="+ new Date());
			//branchTransactionRepository.deleteAll();
			branchTransactionRepository.saveAll(listentity);
			//System.out.println("===========================branch txn save end==============================" + new Date());
			logger.info("=================Branch TXN Daily Upload Finish=================="+ new Date());
		} catch (Exception e) {
			logger.error("Exception "+ e.getMessage());
		}
	}

//=== hourly 
	public void filehourlyRead(String path) {

		BufferedReader br = null;
		List<SwayamTransactionhourlyDto> listDto = new ArrayList<SwayamTransactionhourlyDto>();

		int count = 0;
		try {
			String strLine;
			File filePath = getLastModified(path);
			//logger.info("filepath" + filePath);

			br = new BufferedReader(new FileReader(filePath));
			//System.out.println("===========================swayam txn read file start==============================" + new Date());
			while ((strLine = br.readLine()) != null) {
				//logger.info("strLine" + strLine);
				//if (count != 0) {

					String rep = strLine.replaceAll("\"", "");
					String[] data = rep.split("\\^");

					/*
					 * logger.info("UNIQUE_REFERENCE_NO - " + data[0] + " REQUEST_DATE_TIME- " +
					 * data[1] + " REQUESTING_BRANCH- " + data[2] + " KIOSK_ID- " + data[3] +
					 * " RESPONSE_DATE_TIME- " + data[4] + " ACKNOWLEDGE_DATE_TIME- " + data[5] +
					 * "RESPONSE_CODE- " + data[6] + " ERROR_CODE- " + data[7] + " ERROR_DESC- " +
					 * data[8] + " STATUS- " + data[9] + " BARCODE- " + data[10]);
					 */

					SwayamTransactionhourlyDto dto = new SwayamTransactionhourlyDto();
					// dto.setId(count);// 0
					// System.out.println("getid"+dto.getId());
					dto.setUniqueReferenceNo(data[0].trim());// 1
					dto.setRequestDateTime(data[1].trim());// 2
					dto.setRequestingBranch(data[2].trim());// 3
					dto.setKioskId(data[3].trim());// 4
					dto.setResponseDateTime(data[4].trim());// 5
					dto.setAcknowledgeDateTime(data[5].trim());// 6
					dto.setResponseCode(data[6].trim());// 7
					dto.setErrorCode(data[7].trim());// 8
					dto.setErrorDesc(data[8].trim());// 9
					dto.setStatus(data[9].trim());// 10
					dto.setBarcode(data[10].trim());// 11
					listDto.add(dto);

				//}
				count++;
			}
			//System.out.println("===========================swayam txn read file end==============================" + new Date());
			//System.out.println(listDto + "size of array" + listDto.size());
			parseDatahour(listDto);

		} catch (IOException exp) {
			logger.error("Error while reading file " + exp.getMessage());
		} finally {
			try {

				if (br != null) {
					br.close();
				}
			} catch (IOException e) {

				logger.error("Error while reading IOException " + e.getMessage());
			}
		}

	}
  
	
	private void parseDatahour(List<SwayamTransactionhourlyDto> listDto) {

		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
		 Date curDate=new Date();
		 String passedDate=sdf.format(curDate);

			SwayamTranactionhourEntity entity = null;
			List<SwayamTranactionhourEntity> listentity = new ArrayList<SwayamTranactionhourEntity>();
			//logger.info("listp" + listDto);
			//System.out.println("===========================swayam txn list start==============================" + new Date());
			try {
				for (SwayamTransactionhourlyDto lidtDto1 : listDto) {
					passedDate = lidtDto1.getRequestDateTime();
					entity = new SwayamTranactionhourEntity();
					entity.setUniqueReferenceNo(lidtDto1.getUniqueReferenceNo());
					entity.setRequestDateTime(lidtDto1.getRequestDateTime());
					entity.setRequestingBranch(lidtDto1.getRequestingBranch());
					entity.setKioskId(lidtDto1.getKioskId());
					entity.setResponseDateTime(lidtDto1.getResponseDateTime());
					entity.setAcknowledgeDateTime(lidtDto1.getAcknowledgeDateTime());
					entity.setResponseCode(lidtDto1.getResponseCode());
					entity.setErrorCode(lidtDto1.getErrorCode());
					entity.setErrorDesc(lidtDto1.getErrorDesc());
					entity.setStatus(lidtDto1.getStatus());
					entity.setBarcode(lidtDto1.getBarcode());
					listentity.add(entity);
				}
				logger.info("=================Swayam TXN Daily Upload Start=================="+ new Date());
				swayamTranactionhourRepository.deleteByRequestDateTime(passedDate);
				swayamTranactionhourRepository.saveAll(listentity);
				//System.out.println("===========================swayam txn end==============================" + new Date());
				logger.info("=================Swayam TXN Daily Upload Finish=================="+ new Date());
				executprodure(passedDate);
			} catch (Exception e) {
				logger.info("Exception is "+e.getMessage());
			}
		
	}
	public static File getLastModified(String directoryFilePath) {
		File directory = new File(directoryFilePath);
		File[] files = directory.listFiles(File::isFile);
		long lastModifiedTime = Long.MIN_VALUE;
		File chosenFile = null;

		if (files != null) {
			for (File file : files) {
				if (file.lastModified() > lastModifiedTime) {
					chosenFile = file;
					lastModifiedTime = file.lastModified();
				}
			}
		}

		return chosenFile;
	}
	
	// 
	@PersistenceContext
    private EntityManager em;
	//  private static final SimpleDateFormat dateFormat1   = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public void executprodure(String passedDate)
	{
		try {
		logger.info("=================Swayam TXN Report Upload Start=================="+ new Date());
		swayanTxnReportRepository.deleteByTxnDate(passedDate);
		StoredProcedureQuery nearByEntities= em.createNamedStoredProcedureQuery("SP_SWAYAM_TXN_REPORT");
		nearByEntities.setParameter("txnDate", passedDate); 
		nearByEntities.execute();
		//logger.info("nearByEntities"+nearByEntities);
		// nearByEntities.setParameter("fromdate_param", dateFormat1);
		logger.info("=================Swayam TXN Report Upload Finish=================="+ new Date());
		}
		catch(Exception e) {			
			logger.error("Exception in executprodure is "+e.getMessage());
		}
	}
}
