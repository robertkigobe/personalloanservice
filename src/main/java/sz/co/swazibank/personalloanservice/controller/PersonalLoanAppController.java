package sz.co.swazibank.personalloanservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import sz.co.swazibank.personalloanservice.MediaTypeUtils;
import sz.co.swazibank.personalloanservice.entity.PersonalLoan;
import sz.co.swazibank.personalloanservice.entity.PersonalLoanSetup;
import sz.co.swazibank.personalloanservice.service.FileService;
import sz.co.swazibank.personalloanservice.service.MailService;
import sz.co.swazibank.personalloanservice.service.PersonalLoan_Generate_Short_Securities;
import sz.co.swazibank.personalloanservice.service.PesonalLoanServiceService;

@Controller
@RequestMapping("/personalLoan")
public class PersonalLoanAppController {

	private static String JSON_PERSONAL_LOAN_SETUP = "http://mlu-itc-dw-03.mshome.net:8091/setuprest/personalLoanSetup/1";
	private static String JSON_PERSONALLOAN = null;
	public String responseAction = null;

	@Autowired
	PesonalLoanServiceService personalLoanServiceService;

	@Autowired
	private EurekaClient discoveryClient;
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	FileService fileService;

	@Autowired
	private MailService mailService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${app.documentpath}")
	private String uploadDir;
	private float floatingRate;
	private float funeralCover;
	private float managementFee;
	private float primaRate;
	private String creditAnalyst;
	private String creditAnalystEmail;
	private String securitiesSupervisor;
	private String securitiesSupervisorEmail;
	private String loanDisbursementSupervisor;
	private String loanDisbursementSupervisorEmail;
	private String managerCreditName;
	private String managerCreditEmail;
	private Date businessConsultantCommentDate;
	private String toAddress;
	private String businessConsultantEmail;
	private String subject;
	private String mailBody;

	@GetMapping("/status")
	public String getPersonalLoanStatus(Model theModel) {
		/*
		 * List<PersonalLoan> thePersonalLoans =
		 * personalLoanServiceService.findInProgress();
		 * 
		 * for (int x = 0; x < thePersonalLoans.size(); x++) {
		 * 
		 * long seconds =
		 * -(thePersonalLoans.get(x).getBusinessConsultantCommentDate().getTime() - (new
		 * Date().getTime())) / 1000;
		 * 
		 * long sec = seconds % 60; long minutes = seconds % 3600 / 60; long hours =
		 * seconds % 86400 / 3600; long days = seconds / 86400;
		 * 
		 * thePersonalLoans.get(x).setTimeElapsed(days + "dd: " + hours + "hh: " +
		 * minutes + "mm: " + sec + "ss");
		 * 
		 * }
		 * 
		 * theModel.addAttribute("theDate", new java.util.Date());
		 */

		return "home/status";

	}

	@GetMapping("/showFormForAdd")
	public String getPersonalLoanConsultantForm(Model theModel) {

		PersonalLoan personalLoan = new PersonalLoan();
		theModel.addAttribute("personalLoan", personalLoan);
		return "home/showFormForAddConsultant";

	}

	@PostMapping("/save")
	public String savePersonalLoanConsultantForm(Model theModel,
			@ModelAttribute("personalLoan") @Valid PersonalLoan thePersonalLoan,
			@RequestParam("file") MultipartFile file, Errors error, @RequestParam("action") String action,
			RedirectAttributes atts) {

		

		Map<String, String> params = new HashMap<String, String>();
		params.put("theId", "1");

		PersonalLoanSetup thePersonalLoanSetup = restTemplate.getForObject(JSON_PERSONAL_LOAN_SETUP,
				PersonalLoanSetup.class, params);

		floatingRate = thePersonalLoanSetup.getFloatingrate();
		funeralCover = thePersonalLoanSetup.getFuneralCover();
		managementFee = thePersonalLoanSetup.getManagementFee();
		primaRate = thePersonalLoanSetup.getPrimeRate();
		creditAnalyst = thePersonalLoanSetup.getCreditAnalyst();
		creditAnalystEmail = thePersonalLoanSetup.getCreditAnalystEmail();
		securitiesSupervisor = thePersonalLoanSetup.getSecuritiesSupervisor();
		securitiesSupervisorEmail = thePersonalLoanSetup.getSecuritiesSupervisorEmail();
		loanDisbursementSupervisor = thePersonalLoanSetup.getLoanDisbursementSupervisor();
		loanDisbursementSupervisorEmail = thePersonalLoanSetup.getLoanDisbursementSupervisorEmail();
		managerCreditName = thePersonalLoanSetup.getManagerCredit();
		managerCreditEmail = thePersonalLoanSetup.getManagerCreditEmail();
		businessConsultantCommentDate = new Date();

		thePersonalLoan.setLoanStatus("Pending Credit Analysis Action");
		thePersonalLoan.setFuneralCover(funeralCover);
		thePersonalLoan.setFloatingRate(floatingRate);
		thePersonalLoan.setManagementFee(managementFee);
		thePersonalLoan.setPrimaRate(primaRate);
		thePersonalLoan.setCreditAnalyst(creditAnalyst);
		thePersonalLoan.setCreditAnalystEmail(creditAnalystEmail);
		thePersonalLoan.setSecuritiesSupervisor(securitiesSupervisor);
		thePersonalLoan.setSecuritiesSupervisorEmail(securitiesSupervisorEmail);
		thePersonalLoan.setLoanDisbursementSupervisor(loanDisbursementSupervisor);
		thePersonalLoan.setLoanDisbursementSupervisorEmail(loanDisbursementSupervisorEmail);
		thePersonalLoan.setBusinessConsultantCommentDate(new Date());
		
		

		if (error.hasErrors()) {

			theModel.addAttribute("thePersonalLoan", thePersonalLoan);

			responseAction = "home/showFormForAddConsultant";

		} else if (action.equals("Generate Customer copy")) {

			atts.addFlashAttribute("thePersonalLoan", thePersonalLoan);

			responseAction = "redirect:/personalLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {
			
			Random random = new Random();
			int randomWithNextInt = random.nextInt(200);
			thePersonalLoan.setLoanReference(thePersonalLoan.getBranch() + " " + randomWithNextInt);
			
			try {

				fileService.uploadFile(file, uploadDir);

			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			
			thePersonalLoan.setSecuritiesLocation(uploadDir + file.getOriginalFilename());
			try {

				personalLoanServiceService.saveAndFlush(thePersonalLoan);
				

			} catch (Exception ex) {
				System.out.println(ex.toString());
			}

			toAddress = thePersonalLoan.getBranchManagerEmail();
			businessConsultantEmail = thePersonalLoan.getBusinessConsultantEmail();
			// String fromAddress = thePersonalLoan.getBusinessConsultantEmail();
			subject = "Loan Ref " + thePersonalLoan.getLoanReference() + " requires your attention";

			try {
				mailBody = "Dear " + thePersonalLoanSetup.getCreditAnalyst() + "\n \n"
						+ "A new personal Loan has been Submitted by " + thePersonalLoan.getBusinessConsultantName()
						+ " with reference: " + thePersonalLoan.getLoanReference() + "  Please Click on the link "
						+ personalLoanLink() + "getPersonalLoanBranchManagerComment/" + thePersonalLoan.getId()
						+ "  to comment."
						+ "\nThis mail is Auto generated. Please Contact the Applicant on   for additional Information.";
				try {
					mailService.send(businessConsultantEmail, toAddress, businessConsultantEmail, subject, mailBody);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			catch (Exception e) {

				System.out.println(e.getMessage());
			}

			responseAction = "redirect:/personalLoan/status";

		}

		return responseAction;

	}

	@GetMapping("/getPersonalLoanBranchManagerComment/{theId}")
	public String getPersonalLoanBranchManagerComment(@PathVariable int theId, Model theModel) {

		PersonalLoan thePersonalLoan = personalLoanServiceService.findPersonalLoanById(theId);

		if (thePersonalLoan == null) {
			throw new RuntimeException("personal Loan by Ref" + theId + " is not found");
		}

		theModel.addAttribute("personalLoan", thePersonalLoan);

		return "home/showFormForUpdateBranchManager";
	}
	
	@PostMapping("/saveBranchmanagerUpdate")
	public String savePersonalLoanBranchManagerForm(Model theModel,
			@ModelAttribute("personalLoan") @Valid PersonalLoan thePersonalLoan,
			Errors error, @RequestParam("action") String action,
			RedirectAttributes atts) {



		thePersonalLoan.setLoanStatus("Pending Credit Analysis Action");
		thePersonalLoan.setFuneralCover(funeralCover);
		thePersonalLoan.setFloatingRate(floatingRate);
		thePersonalLoan.setManagementFee(managementFee);
		thePersonalLoan.setPrimaRate(primaRate);
		thePersonalLoan.setCreditAnalyst(creditAnalyst);
		thePersonalLoan.setCreditAnalystEmail(creditAnalystEmail);
		thePersonalLoan.setSecuritiesSupervisor(securitiesSupervisor);
		thePersonalLoan.setSecuritiesSupervisorEmail(securitiesSupervisorEmail);
		thePersonalLoan.setLoanDisbursementSupervisor(loanDisbursementSupervisor);
		thePersonalLoan.setLoanDisbursementSupervisorEmail(loanDisbursementSupervisorEmail);
		
		

		if (error.hasErrors()) {

			theModel.addAttribute("thePersonalLoan", thePersonalLoan);

			responseAction = "home/showFormForAddConsultant";

		} else if (action.equals("Generate Customer copy")) {

			atts.addFlashAttribute("thePersonalLoan", thePersonalLoan);

			responseAction = "redirect:/personalLoan/securitiesShortPdf";

		} else if (action.equals("Save")) {
			
			Random random = new Random();
			int randomWithNextInt = random.nextInt(200);
			thePersonalLoan.setLoanReference(thePersonalLoan.getBranch() + " " + randomWithNextInt);

			try {

				personalLoanServiceService.saveAndFlush(thePersonalLoan);
				

			} catch (Exception ex) {
				System.out.println(ex.toString());
			}

			toAddress = thePersonalLoan.getBranchManagerEmail();
			businessConsultantEmail = thePersonalLoan.getBusinessConsultantEmail();
			// String fromAddress = thePersonalLoan.getBusinessConsultantEmail();
			subject = "Loan Ref " + thePersonalLoan.getLoanReference() + " requires your attention";

			try {
				mailBody = "Dear " + thePersonalLoan.getCreditAnalyst() + "\n \n"
						+ "A new personal Loan has been Submitted by " + thePersonalLoan.getBusinessConsultantName()
						+ " with reference: " + thePersonalLoan.getLoanReference() + "  Please Click on the link "
						+ personalLoanLink() + "getPersonalLoanBranchManagerComment/" + thePersonalLoan.getId()
						+ "  to comment."
						+ "\nThis mail is Auto generated. Please Contact the Applicant on   for additional Information.";
				try {
					mailService.send(businessConsultantEmail, toAddress, businessConsultantEmail, subject, mailBody);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			catch (Exception e) {

				System.out.println(e.getMessage());
			}

			responseAction = "redirect:/personalLoan/status";

		}

		return responseAction;

	}

	public String personalLoanLink() throws IOException {

		String url = null;
		String name = null;

		List<Application> applications = discoveryClient.getApplications().getRegisteredApplications();

		for (Application application : applications) {
			List<InstanceInfo> applicationsInstances = application.getInstances();
			for (InstanceInfo applicationsInstance : applicationsInstances) {

				name = applicationsInstance.getAppName();

				if (name.equals("PERSONAL-LOAN-SERVICE"))

				{

					url = applicationsInstance.getHomePageUrl();

					JSON_PERSONALLOAN = url + "personalLoan/";
				}

			}

		}

		return JSON_PERSONALLOAN;

	}

	@GetMapping("/securitiesShortPdf")
	public ModelAndView securitiesShortPdf(@ModelAttribute("thePersonalLoan") PersonalLoan thePersonalLoan) {

		return new ModelAndView(new PersonalLoan_Generate_Short_Securities(), "thePersonalLoan", thePersonalLoan);
	}
	
	@RequestMapping("/download_file")
	public ResponseEntity<InputStreamResource> downloadFile1(@RequestParam("fileName") String securitiesLocation)
			throws IOException {

		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, securitiesLocation);

		File file = new File(securitiesLocation);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()

				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()).contentType(mediaType)
				.contentLength(file.length()).body(resource);
	}

}
