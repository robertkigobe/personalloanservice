package sz.co.swazibank.personalloanservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "personal_loan")
public class PersonalLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "business_consultant_email")
	private String businessConsultantEmail;

	@Column(name = "business_consultant_name")
	private String businessConsultantName;

	@Column(name = "business_consultant_comment")
	private String businessConsultantComment;

	@Column(name = "business_consultant_comment_date")
	private Date businessConsultantCommentDate;

	@Column(name = "branch")
	private String branch;

	@Column(name = "branch_address")
	private String branchAddress;

	@Column(name = "branch_manager_name")
	private String branchManagerName;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "branch_manager_email")
	private String branchManagerEmail;

	@Column(name = "branch_manager_comments")
	private String branchManagerComments;

	@Column(name = "branch_manager_comments_date")
	private Date branchManagerCommentDate;

	@Column(name = "cell_number")
	private String cellNumber;

	@Column(name = "cession_over")
	private Boolean cessionOver;

	@Column(name = "credit_analyst")
	private String creditAnalyst;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "credit_analyst_email")
	private String creditAnalystEmail;

	@Column(name = "credit_history")
	private String creaditHistory;

	@Column(name = "customer_cif")
	private String customerCif;

	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "date_created")
	private Date dateCreated;

	@Past(message = "Birth date can not be today")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "decision")
	private String decision;

	@Column(name = "disbursement_to_account")
	private String disbrusementToAccount;

	@Column(name = "employer")
	private String employer;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "floating_rate")
	private float floatingRate;

	@Column(name = "funeral_cover")
	private float funeralCover;

	@Column(name = "initiation_fee")
	private float initiationFee;

	@Column(name = "interest_rate")
	private float interestRate;

	@UpdateTimestamp
	@Column(name = "last_updated")
	private Date lastDateUpdated;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "level_term")
	private float levelTerm;
	
	@Column(name = "level_term_insurance_policy")
	private boolean levelTermInsurancePolicy;

	@Column(name = "loan_amount_final")
	private float loanAmountFinal;

	@Column(name = "loan_amount_final_words")
	private String loanAmountFinalWords;

	@Column(name = "loan_amount_requested")
	private float loanAmountRequested;

	@Column(name = "loan_and_charges")
	private float loanAndCharges;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "loan_end_date")
	private Date loanEndDate;

	@Column(name = "loan_period")
	private long loanPeriod;

	@Column(name = "loan_purpose")
	private String loanPurpose;

	@Column(name = "Loan_reference")
	private String loanReference;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "loan_start_date")
	private Date loanStartDate;

	@Column(name = "loan_status")
	private String loanStatus;

	@Column(name = "loan_disbursement_supervisor")
	private String loanDisbursementSupervisor;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "loan_disbursement_supervisor_email")
	private String loanDisbursementSupervisorEmail;

	@Column(name = "manager_credit_comment")
	private String managerCreditComment;

	@Column(name = "manager_credit_comment_date")
	private Date managerCreditCommentDate;

	@Column(name = "manager_credit_name")
	private String managerCreditName;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "manager_credit_email")
	private String managerCreditEmail;

	@Column(name = "management_fee")
	private float managementFee;

	@Column(name = "pledged_account")
	private String pledgedAccount;

	@Column(name = "pledged_account_balance")
	private float pledgedAccountBalance;

	@Column(name = "postal_address")
	private String postalAddress;

	@Column(name = "prime_rate")
	private float primaRate;

	@Column(name = "principal_repayment")
	private float principayRepayment;

	@Column(name = "residential_address")
	private String residentialAddress;

	@Column(name = "receive_marketting_informaiton")
	private boolean receiveMarkettingInformation;

	@Column(name = "receive_special_products")
	private boolean receiveSpecialProducts;

	@Column(name = "sharing_personal_info")
	private boolean sharingPersonalinfo;

	@Column(name = "securities_supervisor")
	private String securitiesSupervisor;

	@Email(message = "Enter a valid email address.") 
	@Column(name = "securities_supervisor_email")
	private String securitiesSupervisorEmail;

	@Column(name = "surname")
	private String surname;

	@Column(name = "stop_order_over_salary_account")
	private String stopOrderOverSalaryAccount;


	
	@Column(name = "time_elapsed") 
	private String timeElapsed;

	@Column(name = "total_premiums")
	private float totalPremiums;

	@Column(name = "total_repayment")
	private float totalRpayment;

	@Column(name = "previous_loan_amount")
	private float previousLoanAmount;

	@Column(name = "securities_location")
	private String securitiesLocation;

	@Column(name = "type_of_communication_mail")
	public Boolean typeOfCommunicationMail;

	@Column(name = "type_of_communication_email")
	public Boolean typeOfCommunicationEmail;

	@Column(name = "type_of_communication_sms")
	public Boolean typeOfCommunicationSms;

	@Column(name = "type_of_communication_telephone")
	private Boolean typeOfCommunicationTelephone;
}
