package sbi.kiosk.swayam.healthmonitoring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import sbi.kiosk.swayam.common.api.ManualCallLogApiRequest;
import sbi.kiosk.swayam.common.api.SMSSender;
import sbi.kiosk.swayam.common.api.SMTIntegrationCallOpenApi;
import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.AlertDto;
import sbi.kiosk.swayam.common.dto.CallTypeDto;
import sbi.kiosk.swayam.common.dto.ManualTicketCallLogDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.BranchMaster;
import sbi.kiosk.swayam.common.entity.KioskBranchMaster;
import sbi.kiosk.swayam.common.entity.ManualTicketCallLog;
import sbi.kiosk.swayam.common.entity.User;
import sbi.kiosk.swayam.common.exception.ManualTicketNotFoudException;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.BranchMasterRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.CallTypeRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.KioskMasterRepo;
import sbi.kiosk.swayam.healthmonitoring.repository.ManualTicketCallLogRepository;
import sbi.kiosk.swayam.healthmonitoring.repository.TicketCentorRepository;

@Service
public class ManualTicketServiceImpl implements ManualTicketService {
	
	Logger logger = LoggerFactory.getLogger(ManualTicketServiceImpl.class);
	
	@Autowired
	private TicketCentorRepository ticketCentorRepo;
	@Autowired
	ManualTicketCallLogRepository manualTicketCallLogRepo;
	@Autowired
	private KioskMasterRepo kioskMasterRepo;
	@Autowired
	private BranchMasterRepository branchMasterRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CallTypeRepository callTypeRpository;

	/*
	 * @Override
	 * 
	 * @Transactional(rollbackFor = Exception.class) public String
	 * createManualTicket(ManualTicketCallLogDto manualTicketCallLogDto) throws
	 * ManualTicketNotFoudException {
	 * 
	 * String manual_call_log_id = null; SimpleDateFormat date = null; String
	 * complaintId = null; try { manualTicketCallLogDto.getKioskError();
	 * manual_call_log_id = manualTicketCallLogRepo.findSeq(); // String seq =
	 * "MANUAL_CALL_LOG_ID" + manual_call_log_id; ManualTicketCallLog manualEnity =
	 * new ManualTicketCallLog();
	 * manualEnity.setBranchCode(manualTicketCallLogDto.getBranchCode());
	 * manualEnity.setCircle(manualTicketCallLogDto.getCircle());
	 * manualEnity.setManual_call_log_id(manual_call_log_id);
	 * manualEnity.setKioskId(manualTicketCallLogDto.getKioskId());
	 * manualEnity.setComments(manualTicketCallLogDto.getComment());
	 * manualEnity.setContactNo(manualTicketCallLogDto.getContactNo());
	 * manualEnity.setContactPerson(manualTicketCallLogDto.getContactPerson());
	 * manualEnity.setStatus(manualTicketCallLogDto.getStatus());
	 * manualEnity.setVendor(manualTicketCallLogDto.getVendor());
	 * manualEnity.setKioskError(manualTicketCallLogDto.getKioskError());
	 * manualTicketCallLogRepo.save(manualEnity);
	 * 
	 * String kioskId =
	 * ticketCentorRepo.findByKisokId(manualTicketCallLogDto.getKioskId()); String
	 * id = ticketCentorRepo.findByTicketId(kioskId); if (kioskId != null) { if
	 * (kioskId.equals(manualTicketCallLogDto.getKioskId())) { date = new
	 * SimpleDateFormat("dd/MM/YY"); String date1 = date.format(new Date()); String
	 * createdDate = date1.replace("/", ""); complaintId = "INC" + createdDate + id;
	 * manualTicketCallLogRepo.updateComplaintId(complaintId,
	 * manualTicketCallLogDto.getKioskId()); return id; } } else { date = new
	 * SimpleDateFormat("dd/MM/YY"); String date1 = date.format(new Date()); String
	 * createdDate = date1.replace("/", ""); complaintId = "INC" + createdDate +
	 * manual_call_log_id; String kisokId = manualTicketCallLogDto.getKioskId();
	 * manualTicketCallLogRepo.updateComplaintId(complaintId, kisokId); return
	 * manual_call_log_id;
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * logger.info("Exception "+ExceptionConstants.EXCEPTION); } return complaintId;
	 * }
	 */
	
	
	
	
	//changes By Satendra
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String createManualTicket(ManualTicketCallLogDto manualTicketCallLogDto)
			throws ManualTicketNotFoudException {

		String manual_call_log_id = null;
		SimpleDateFormat date = null;
		String complaintId = null;
		try {
			logger.info("createManualTicket................. error message" + manualTicketCallLogDto.getKioskError());
			manual_call_log_id = manualTicketCallLogRepo.findSeq();
			// String seq = "MANUAL_CALL_LOG_ID" + manual_call_log_id;
			ManualTicketCallLog manualEnity = new ManualTicketCallLog();
			manualEnity.setBranchCode(manualTicketCallLogDto.getBranchCode());
			manualEnity.setCircle(manualTicketCallLogDto.getCircle());
			manualEnity.setManual_call_log_id(manual_call_log_id);
			manualEnity.setKioskId(manualTicketCallLogDto.getKioskId());
			manualEnity.setComments(manualTicketCallLogDto.getComment());
			//manualEnity.setContactNo(manualTicketCallLogDto.getContactNo());
			
			String[] contacNoString=manualTicketCallLogDto.getContactNo().split(",");
			for(int i=0;i<contacNoString.length;i++){
				String contNo=contacNoString[0];
				manualEnity.setContactNo(contNo);
			}
			//manualEnity.setContactPerson(manualTicketCallLogDto.getContactPerson());
			String[] contacPersoString=manualTicketCallLogDto.getContactPerson().split(",");
			for(int i=0;i<contacPersoString.length;i++){
				String contacPerson=contacPersoString[0];
				manualEnity.setContactPerson(contacPerson);
			}
			
			
			if (manualTicketCallLogDto.getMailId().endsWith(",")) {
				 String  emailIdString = manualTicketCallLogDto.getMailId().substring(0, manualTicketCallLogDto.getMailId().length() - 1);
				 manualEnity.setMailId(emailIdString);
			}
		
			
			String koiskSr=kioskMasterRepo.getKioskSrNo(manualTicketCallLogDto.getKioskId());
			manualEnity.setKioskSrNo(koiskSr);
			manualEnity.setStatus("Active");
			manualEnity.setVendor(manualTicketCallLogDto.getVendor());
			manualEnity.setKioskError(manualTicketCallLogDto.getKioskError());
			manualEnity.setSubCategory(manualTicketCallLogDto.getKioskError());

			String checkStatusTicket = ticketCentorRepo.findByKisokIdAndCallSubCategoryAndStatus(manualTicketCallLogDto.getKioskId(),manualTicketCallLogDto.getKioskError(),"Active");
			
			if(checkStatusTicket!=null && !checkStatusTicket.isEmpty()){
				String existComplaintId= manualTicketCallLogRepo.findByKisokIdAndCallSubCategoryAndStatus(manualTicketCallLogDto.getKioskId(),manualTicketCallLogDto.getKioskError(),"Active");
				
				return "Your complaint Id: "+existComplaintId +" is already exist.";
			}else{
			
			//manualTicketCallLogRepo.save(manualEnity);
			
			String kioskId = ticketCentorRepo.findByKisokIdAndCallSubCategory(manualTicketCallLogDto.getKioskId(),manualTicketCallLogDto.getKioskError());
			
			String id = ticketCentorRepo.findByTicketId(kioskId);
			
			if (kioskId != null && !kioskId.isEmpty()) {
				if (kioskId.equals(manualTicketCallLogDto.getKioskId())) {
					/*date = new SimpleDateFormat("dd/MM/YY");
					String date1 = date.format(new Date());
					String createdDate = date1.replace("/", "");
					complaintId = "INC" + createdDate + id;
					manualEnity.setComplaintId(complaintId);
					manualTicketCallLogRepo.save(manualEnity);
					//manualTicketCallLogRepo.updateComplaintId(complaintId, manualTicketCallLogDto.getKioskId());
					return id;*/
					

					date = new SimpleDateFormat("dd/MM/YY");
					String date1 = date.format(new Date());
					String createdDate = date1.replace("/", "");
					complaintId = "INC" + createdDate + id;
					manualEnity.setComplaintId(complaintId);
					// comment by me 221220
					//manualEnity=manualTicketCallLogRepo.save(manualEnity);
					//manualTicketCallLogRepo.updateComplaintId(complaintId, manualTicketCallLogDto.getKioskId());
					//SMSSender sms=new SMSSender();
					if(manualEnity.getStatus().equalsIgnoreCase("Active")){
						//send alert to userid
						SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY HH::mm");
						String dateTime = sdf.format(new Date());
						
						// String dateTime = sdf.format(new Date());
						 String[] dateParts = dateTime.split(" ");
						 String datep = dateParts[0]; 
						 String time = dateParts[1]; 
						 String finalDateTime=datep+" at "+time;
						 logger.info("finalDateTime::: "+finalDateTime);
						
						AlertDto alertDto=new AlertDto();
						alertDto.setKioskSrNo(manualEnity.getKioskSrNo());
						alertDto.setCallLogId(complaintId);
						logger.info("ContactNo SMS:: "+manualEnity.getContactNo());
						logger.info("MailId:: "+manualEnity.getMailId());
						
						alertDto.setMobileNo(manualEnity.getContactNo());
						alertDto.setBranchCode(manualTicketCallLogDto.getBranchCode());
						alertDto.setSenderId(manualEnity.getCreatedBy());
						alertDto.setDateTime(finalDateTime);
						
						String requestId="smt"+manual_call_log_id;
						DateFormat sdf1 = new SimpleDateFormat("d-MMM-yyyy HH:mm:ss");
						String dateTime1 = sdf1.format(new Date());
						ManualCallLogApiRequest data1=new ManualCallLogApiRequest();
					    data1.setRequestId(requestId);
						data1.setReqType("open");
						data1.setReqTicketNumber(complaintId);
						data1.setReqDatetime(dateTime1);
						data1.setBrCode(manualTicketCallLogDto.getBranchCode());
						data1.setSrc("smt");
						if(manualEnity.getVendor().equalsIgnoreCase("LIPI")){
						data1.setKioskProvider("lipi");
						}else if(manualEnity.getVendor().equalsIgnoreCase("CMS")){
							data1.setKioskProvider("cms");
					     }else{
					    	 data1.setKioskProvider("forbes");
					      }
						
						data1.setKioskId(manualEnity.getKioskId());
						data1.setKioskSrno(manualEnity.getKioskSrNo());
						data1.setIssueCategory("Passbook Printer Related");
						data1.setIssueSubcategory(manualEnity.getKioskError());
						data1.setIssueDescription(manualEnity.getComments());
						data1.setContactName(manualEnity.getContactPerson());
						data1.setContactNumber(manualEnity.getContactNo());
						data1.setContactEmailId(manualEnity.getMailId());
						data1.setStatus("p");
						
						String apiRespo=SMTIntegrationCallOpenApi.makeWebServiceCall(data1);
						logger.info("Manual Callog ApiResponse::------- "+apiRespo);
						Gson gson=new Gson();
						Map map = gson.fromJson(apiRespo, Map.class);
						logger.info("map==91="+map);
						map.get("requestId");
						map.get("reqType");
						boolean successResp=  (boolean) map.get("success");
						logger.info("SuccessResp="+successResp);
						String resTicketNumber=(String) map.get("resTicketNumber");
						logger.info("Result resTicketNumber::" + resTicketNumber);
						ArrayList<String> errro=(ArrayList<String>)map.get("error");
						logger.info("Result errro::" + errro);
						  String errorCode =null;
						  String errorMessage =null;
						 try
					        {
					            JSONArray jsonArray = new JSONArray(errro);

					            for(int i=0;i<jsonArray.length();i++)
					            {
					                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
					                 errorCode = jsonObject1.optString("errorCode");
					                logger.info("Result value1::" + errorCode);
					                 errorMessage = jsonObject1.optString("errorMessage");
					                logger.info("Result value2::" + errorMessage);
					               
					            }
					        }
					        catch (JSONException e)
					        {
					            e.printStackTrace();
					        }
						if(successResp==true && resTicketNumber!=null && !resTicketNumber.isEmpty()){
							  manualEnity.setRespTicketNo(resTicketNumber);
						      manualEnity=manualTicketCallLogRepo.save(manualEnity);
						      complaintId = "Your complaint '"+resTicketNumber+"` has been successfully registered";
						      String result=SMSSender.sendSmsCall(alertDto, "", "","");
							  logger.info("------- "+result);
								
							  if(result!=null && !result.isEmpty() && result.equalsIgnoreCase("0")){
									logger.info("ELSE Result of SMS inside IF :: "+result);
								}
								
						}else{
							//complaintId = "Your complaint is not registered Kindly Contact with Admin.";
							complaintId ="Your complaint is not registered:"+errorMessage+" Please Contact Administrator.";
						}
						
						
					}else{
						//send fail to userid
					}
					return id;
				}
			} else {
				date = new SimpleDateFormat("dd/MM/YY");
				String date1 = date.format(new Date());
				String createdDate = date1.replace("/", "");
				complaintId = "INC" + createdDate + manual_call_log_id;
				String kisokId = manualTicketCallLogDto.getKioskId();
				manualEnity.setComplaintId(complaintId);
				//manualTicketCallLogRepo.save(manualEnity);
				// Comment by me 221220
				//manualEnity=manualTicketCallLogRepo.save(manualEnity);
				//manualTicketCallLogRepo.updateComplaintId(complaintId, kisokId);
				//return manual_call_log_id;
				if(manualEnity.getStatus().equalsIgnoreCase("Active")){
					//send alert to userid
					SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY HH::mm");
					String dateTime = sdf.format(new Date());
					// String dateTime = sdf.format(new Date());
					 String[] dateParts = dateTime.split(" ");
					 String datep = dateParts[0]; 
					 String time = dateParts[1]; 
					 String finalDateTime=datep+" at "+time;
					 logger.info("finalDateTime::: "+finalDateTime);
					
					AlertDto alertDto=new AlertDto();
					alertDto.setKioskSrNo(manualEnity.getKioskSrNo());
					alertDto.setCallLogId(complaintId);
					//logger.info("ContactNo SMS2:: "+manualEnity.getContactNo());
					//logger.info("mailId SMS2:: "+manualEnity.getMailId());
					alertDto.setMobileNo(manualEnity.getContactNo());
					alertDto.setBranchCode(manualTicketCallLogDto.getBranchCode());
					alertDto.setSenderId(manualEnity.getCreatedBy());
					alertDto.setDateTime(finalDateTime);
					
					//String result=sendSms(alertDto, "", "", "");
					
					// CMS Api Call Log
					
					String requestId="smt"+manual_call_log_id;
					DateFormat sdf1 = new SimpleDateFormat("d-MMM-yyyy HH:mm:ss");
					String dateTime1 = sdf1.format(new Date());
					ManualCallLogApiRequest data1=new ManualCallLogApiRequest();
				    data1.setRequestId(requestId);
					data1.setReqType("open");
					data1.setReqTicketNumber(complaintId);
					data1.setReqDatetime(dateTime1);
					data1.setBrCode(manualTicketCallLogDto.getBranchCode());
					data1.setSrc("smt");
					if(manualEnity.getVendor().equalsIgnoreCase("LIPI")){
					data1.setKioskProvider("lipi");
					}else if(manualEnity.getVendor().equalsIgnoreCase("CMS")){
						data1.setKioskProvider("cms");
				     }else{
				    	 data1.setKioskProvider("forbes");
				      }
					
					data1.setKioskId(manualEnity.getKioskId());
					data1.setKioskSrno(manualEnity.getKioskSrNo());
					data1.setIssueCategory("Passbook Printer Related");
					data1.setIssueSubcategory(manualEnity.getKioskError());
					data1.setIssueDescription(manualEnity.getComments());
					data1.setContactName(manualEnity.getContactPerson());
					data1.setContactNumber(manualEnity.getContactNo());
					data1.setContactEmailId("pranay.nakhale@lipi.in");
					data1.setStatus("p");
					String apiRespo=SMTIntegrationCallOpenApi.makeWebServiceCall(data1);
					logger.info("Manual Callog ApiResponse::------- "+apiRespo);
					
					Gson gson = new Gson();
					Map map = gson.fromJson(apiRespo, Map.class);
					logger.info("map==91=" + map);
					map.get("requestId");
					map.get("reqType");
					boolean successResp = (boolean) map.get("success");
					logger.info("SuccessResp=" + successResp);
					String resTicketNumber = (String) map.get("resTicketNumber");
					logger.info("Result resTicketNumber::" + resTicketNumber);
					ArrayList<String> errro=(ArrayList<String>)map.get("error");
					logger.info("Result errro::" + errro);
					  String errorCode =null;
					  String errorMessage =null;
					 try
				        {
				            JSONArray jsonArray = new JSONArray(errro);

				            for(int i=0;i<jsonArray.length();i++)
				            {
				                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
				                 errorCode = jsonObject1.optString("errorCode");
				                logger.info("Result value1::" + errorCode);
				                 errorMessage = jsonObject1.optString("errorMessage");
				                logger.info("Result value2::" + errorMessage);
				               
				            }
				        }
				        catch (JSONException e)
				        {
				            e.printStackTrace();
				        }

					if(successResp==true && resTicketNumber!=null && !resTicketNumber.isEmpty()){
						  manualEnity.setRespTicketNo(resTicketNumber);
					      manualEnity=manualTicketCallLogRepo.save(manualEnity);
					      complaintId = "Your complaint '"+resTicketNumber+"` has been successfully registered";
					      String result=SMSSender.sendSmsCall(alertDto, "", "","");
						  logger.info("ELSE Result of SMS::------- "+result);
							
						  if(result!=null && !result.isEmpty() && result.equalsIgnoreCase("0")){
								logger.info("ELSE Result of SMS inside IF :: "+result);
							}
							
					}else{
						
						//complaintId = "Your complaint is not registered Kindly Contact with Admin.";
						complaintId ="Your complaint is not registered:"+errorMessage+" Please Contact Administrator.";
					}
					
				}else{
					//send fail to userid
				}
				//complaintId = "Your complaint '"+complaintId+"` has been successfully registered";
				//complaintId = "Your complaint '"+complaintId+"` has been successfully registered";
			}
			
			}
			

		} catch (Exception e) {
			 logger.info("Exception "+ExceptionConstants.EXCEPTION); 
		}
		return complaintId;
	}

	
	
	

	public List<ManualTicketCallLogDto> getByBranchCode(String brachCode) {
		String branchName=null;
		String circle=null;
		List<String> kioskMasterList = kioskMasterRepo.getByBranchCode(brachCode);
		//List<String> kioskMastBranchNameList = branchMasterRepo.findByBranchCode(brachCode);
		List<BranchMaster> kioskMastBranchNameList = branchMasterRepo.findByBranchCode(brachCode);
		for(BranchMaster branchMaster:kioskMastBranchNameList){
			branchName=branchMaster.getBranchName();
			circle=branchMaster.getCircleName();
		}
		List<ManualTicketCallLogDto> listDto = new ArrayList<ManualTicketCallLogDto>();
		ManualTicketCallLogDto dto = null;
		for (String kioskMasterList1 : kioskMasterList) {
			dto = new ManualTicketCallLogDto();
			dto.setVendor(kioskMasterList1);
			dto.setBranchName(branchName);
			dto.setCircle(circle);
			listDto.add(dto);
		}
		
		return listDto;
	}

	// update method 27-03-2020
	public List<ManualTicketCallLogDto> getByKioskId(String kioskId,HttpSession session) {
		ManualTicketCallLogDto dto = null;
		List<ManualTicketCallLogDto> dtoList = new ArrayList<ManualTicketCallLogDto>();
		User user = userRepo.findIdByKioskId(kioskId);
		
		UserDto userObj =(UserDto) session.getAttribute("userObj");
		logger.info("createManualTicket................. getByKioskId ConNo::" +userObj.getMailId());
		/*if (user != null) {
			dto = new ManualTicketCallLogDto();
			dto.setCircle(user.getCircle());
			dto.setContactPerson(user.getUsername());
			dto.setContactNo(user.getPhoneNo());
			dto.setKioskError("Printer Error");
			dtoList.add(dto);
		}*/
		
		if (userObj != null) {
			dto = new ManualTicketCallLogDto();
			//dto.setCircle(user.getCircle());
			dto.setContactPerson(userObj.getUsername());
			dto.setContactNo(userObj.getPhoneNo());
			//dto.setKioskError("Printer Error");
			dto.setMailId(userObj.getMailId());
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public List<CallTypeDto> getCallTypeCategoryError() {
		CallTypeDto dto = null;
		List<CallTypeDto> dtoList = new ArrayList<CallTypeDto>();
		// Iterable<CallTypeEntity> errorList=callTypeRpository.findAll();
		List<String> list = callTypeRpository.findAllSubCategory();

		for (String list1 : list) {
			dto = new CallTypeDto();
			dto.setSubCategory(list1);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<ManualTicketCallLogDto> getByVendor(String vendor, String branchcode) {
		List<KioskBranchMaster> kioskMasterList = kioskMasterRepo.findByVendor(vendor, branchcode);
		List<ManualTicketCallLogDto> manualTicketCallLogDtoList = new ArrayList<ManualTicketCallLogDto>();
		ManualTicketCallLogDto dto = null;
		for (KioskBranchMaster kioskMaster : kioskMasterList) {
			dto = new ManualTicketCallLogDto();
			dto.setKioskId(kioskMaster.getKioskId());
			dto.setVendor(kioskMaster.getVendor());
			manualTicketCallLogDtoList.add(dto);
		}
		return manualTicketCallLogDtoList;

	}

}