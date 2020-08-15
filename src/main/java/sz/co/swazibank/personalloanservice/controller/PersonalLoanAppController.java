package sz.co.swazibank.personalloanservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String responseAction = null;

	@Autowired
	PesonalLoanServiceService personalLoanServiceService;

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
		List<PersonalLoan> thePersonalLoans = personalLoanServiceService.findInProgress();

		for (int x = 0; x < thePersonalLoans.size(); x++) {

			long seconds = -(thePersonalLoans.get(x).getBusinessConsultantCommentDate().getTime() - (new Date().getTime())) / 1000;

			long sec = seconds % 60;
			long minutes = seconds % 3600 / 60;
			long hours = seconds % 86400 / 3600;
			long days = seconds / 86400;

			thePersonalLoans.get(x).setTimeElapsed(days + "dd: " + hours + "hh: " + minutes + "mm: " + sec + "ss");

		}

		theModel.addAttribute("theDate", new java.util.Date());
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
		

		Random random = new Random();
		int randomWithNextInt = random.nextInt(200);

		thePersonalLoan.setLoanReference(thePersonalLoan.getBranch() + " " + randomWithNextInt);

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

		/*
		 * branch = thePersonalLoan.getBranch(); branchAddress =
		 * thePersonalLoan.getBranchAddress(); branchManagerName =
		 * thePersonalLoan.getBranchManagerName(); BusinessConsultantComment =
		 * thePersonalLoan.getBusinessConsultantComment(); BusinessConsultantName =
		 * thePersonalLoan.getBusinessConsultantName(); thePersonalLoan.getCellNumber();
		 * thePersonalLoan.getCustomerCif(); thePersonalLoan.getDateOfBirth();
		 * thePersonalLoan.getDisbrusementToAccount(); thePersonalLoan.getLoanEndDate();
		 * thePersonalLoan.getEmployer(); thePersonalLoan.getFirstName();
		 * thePersonalLoan.getFloatingRate(); thePersonalLoan.getLevelTerm();
		 */

		if (error.hasErrors()) {

			theModel.addAttribute("thePersonalLoan", thePersonalLoan);

			responseAction = "home/showFormForAddConsultant";

		} else if (action.equals("Generate Customer copy")) {
			

			atts.addFlashAttribute("thePersonalLoan", thePersonalLoan);

			responseAction = "redirect:/personalLoan/securitiesShortPdf";
			
		

		} else if (action.equals("Save")) {

			try {

				fileService.uploadFile(file, uploadDir);
				personalLoanServiceService.saveAndFlush(thePersonalLoan);

			} catch (Exception ex) {
				System.out.println(ex.toString());
			}

			toAddress = thePersonalLoan.getBranchManagerEmail();
			businessConsultantEmail = thePersonalLoan.getBusinessConsultantEmail();
			// String fromAddress = thePersonalLoan.getBusinessConsultantEmail();
			subject = "Loan Ref " + thePersonalLoan.getLoanReference() + " requires your attention";

			mailBody = "Dear " + thePersonalLoanSetup.getCreditAnalyst() + "\n \n"
					+ "A new personal Loan has been Submitted by " + thePersonalLoan.getBusinessConsultantName()
					+ " with reference: " + thePersonalLoan.getLoanReference()
					+ "  Please Click on the link http://mlu-itc-dw-03.mshome.net:8097/staffLoan/staffLoanGetSupervisorComment/  to comment."
					+ "\nThis mail is Auto generated. Please Contact the Applicant on   for additional Information.";

			try {

				mailService.send(businessConsultantEmail, toAddress, businessConsultantEmail, subject, mailBody);
			}

			catch (Exception e) {

				System.out.println(e.getMessage());
			}

			responseAction = "redirect:/personalLoan/status";

		}

		return responseAction;

	}

	@GetMapping("/securitiesShortPdf")
	public ModelAndView securitiesShortPdf(@ModelAttribute("thePersonalLoan") PersonalLoan thePersonalLoan) {

		return new ModelAndView(new PersonalLoan_Generate_Short_Securities(), "thePersonalLoan", thePersonalLoan);
	}

}
