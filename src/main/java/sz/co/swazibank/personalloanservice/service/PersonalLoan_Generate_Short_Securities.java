package sz.co.swazibank.personalloanservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import sz.co.swazibank.personalloanservice.entity.PersonalLoan;

public class PersonalLoan_Generate_Short_Securities extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		PersonalLoan thePersonalLoan = (PersonalLoan) model.get("thePersonalLoan");
		String branch = thePersonalLoan.getBranch().toUpperCase();
		String cif = thePersonalLoan.getCustomerCif();
		String customerName = thePersonalLoan.getFirstName().toUpperCase() + " "
				+ thePersonalLoan.getSurname().toUpperCase();
		String residentialAddress = thePersonalLoan.getResidentialAddress().toUpperCase();

		// employee age
		Date dateOfBirth = thePersonalLoan.getDateOfBirth();
		Date loanStartDate = thePersonalLoan.getLoanStartDate();
		Date loanEndDate = thePersonalLoan.getLoanEndDate();

		// create local dates
		LocalDate now = LocalDate.now();
		LocalDate localDateDob = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDateLoanStartDate = loanStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localLoanEndDate = loanEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		long loanPeriod = ChronoUnit.MONTHS.between(localDateLoanStartDate, localLoanEndDate);
		long customerAge = ChronoUnit.YEARS.between(localDateDob, now);

		/////////// date month and year calculation//////
		int dayOneP = 0;
		String dayOne = null;
		int monthOne = 0;
		int yearOne = 0;
		String monthOneV = null;

		try {

			dayOneP = localDateLoanStartDate.getDayOfMonth();
			monthOne = localDateLoanStartDate.getMonthValue();
			yearOne = localDateLoanStartDate.getYear();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (dayOneP == 1) {
			dayOne = "1st";
		} else if (dayOneP == 2) {
			dayOne = "2nd";
		} else if (dayOneP == 3) {
			dayOne = "3rd";
		} else if (dayOneP == 4) {
			dayOne = "4th";
		} else if (dayOneP == 5) {
			dayOne = "5th";
		} else if (dayOneP == 6) {
			dayOne = "6th";
		} else if (dayOneP == 7) {
			dayOne = "7th";
		} else if (dayOneP == 8) {
			dayOne = "8th";
		} else if (dayOneP == 9) {
			dayOne = "9th";
		} else if (dayOneP == 10) {
			dayOne = "10th";
		} else if (dayOneP == 11) {
			dayOne = "11tt";
		} else if (dayOneP == 12) {
			dayOne = "12th";
		} else if (dayOneP == 13) {
			dayOne = "13th";
		} else if (dayOneP == 14) {
			dayOne = "14th";
		} else if (dayOneP == 15) {
			dayOne = "15th";
		} else if (dayOneP == 16) {
			dayOne = "16th";
		} else if (dayOneP == 17) {
			dayOne = "17th";
		} else if (dayOneP == 18) {
			dayOne = "18th";
		} else if (dayOneP == 19) {
			dayOne = "19th";
		} else if (dayOneP == 20) {
			dayOne = "20th";
		} else if (dayOneP == 21) {
			dayOne = "21st";
		} else if (dayOneP == 22) {
			dayOne = "22nd";
		} else if (dayOneP == 23) {
			dayOne = "23rd";
		} else if (dayOneP == 24) {
			dayOne = "24th";
		} else if (dayOneP == 25) {
			dayOne = "25th";
		} else if (dayOneP == 26) {
			dayOne = "26th";
		} else if (dayOneP == 27) {
			dayOne = "27th";
		} else if (dayOneP == 28) {
			dayOne = "28th";
		} else if (dayOneP == 29) {
			dayOne = "29th";
		} else if (dayOneP == 30) {
			dayOne = "30th";
		} else if (dayOneP == 31) {
			dayOne = "31st";
		}

		if (monthOne == 1) {
			monthOneV = "January";
		} else if (monthOne == 2) {
			monthOneV = "February";
		} else if (monthOne == 3) {
			monthOneV = "March";
		} else if (monthOne == 4) {
			monthOneV = "April";
		} else if (monthOne == 5) {
			monthOneV = "May";
		} else if (monthOne == 6) {
			monthOneV = "June";
		} else if (monthOne == 7) {
			monthOneV = "July";
		} else if (monthOne == 8) {
			monthOneV = "August";
		} else if (monthOne == 9) {
			monthOneV = "September";
		} else if (monthOne == 10) {
			monthOneV = "October";
		} else if (monthOne == 11) {
			monthOneV = "November";
		} else if (monthOne == 12) {
			monthOneV = "December";
		}

///////////date month and year for application date//////
		int dayOneP1 = 0;
		String dayOne1 = null;
		int monthOne1 = 0;
		int yearOne1 = 0;
		String monthOneV1 = null;

		try {

			dayOneP1 = localLoanEndDate.getDayOfMonth();
			monthOne1 = localLoanEndDate.getMonthValue();
			yearOne1 = localLoanEndDate.getYear();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (dayOneP1 == 1) {
			dayOne1 = "1st";
		} else if (dayOneP == 2) {
			dayOne1 = "2nd";
		} else if (dayOneP == 3) {
			dayOne1 = "3rd";
		} else if (dayOneP == 4) {
			dayOne1 = "4th";
		} else if (dayOneP == 5) {
			dayOne1 = "5th";
		} else if (dayOneP == 6) {
			dayOne1 = "6th";
		} else if (dayOneP == 7) {
			dayOne1 = "7th";
		} else if (dayOneP == 8) {
			dayOne1 = "8th";
		} else if (dayOneP == 9) {
			dayOne1 = "9th";
		} else if (dayOneP == 10) {
			dayOne1 = "10th";
		} else if (dayOneP1 == 11) {
			dayOne1 = "11th";
		} else if (dayOneP == 12) {
			dayOne1 = "12th";
		} else if (dayOneP == 13) {
			dayOne1 = "13th";
		} else if (dayOneP == 14) {
			dayOne1 = "14th";
		} else if (dayOneP == 15) {
			dayOne1 = "15th";
		} else if (dayOneP == 16) {
			dayOne1 = "16th";
		} else if (dayOneP == 17) {
			dayOne1 = "17th";
		} else if (dayOneP == 18) {
			dayOne1 = "18th";
		} else if (dayOneP == 19) {
			dayOne1 = "19th";
		} else if (dayOneP == 20) {
			dayOne1 = "20th";
		} else if (dayOneP1 == 21) {
			dayOne1 = "21st";
		} else if (dayOneP == 22) {
			dayOne1 = "22nd";
		} else if (dayOneP == 23) {
			dayOne1 = "23rd";
		} else if (dayOneP == 24) {
			dayOne1 = "24th";
		} else if (dayOneP == 25) {
			dayOne1 = "25th";
		} else if (dayOneP == 26) {
			dayOne1 = "26th";
		} else if (dayOneP == 27) {
			dayOne1 = "27th";
		} else if (dayOneP == 28) {
			dayOne1 = "28th";
		} else if (dayOneP == 29) {
			dayOne1 = "29th";
		} else if (dayOneP == 30) {
			dayOne1 = "30th";
		} else if (dayOneP1 == 31) {
			dayOne1 = "31st";
		}

		if (monthOne1 == 1) {
			monthOneV1 = "January";
		} else if (monthOne1 == 2) {
			monthOneV1 = "February";
		} else if (monthOne1 == 3) {
			monthOneV1 = "March";
		} else if (monthOne1 == 4) {
			monthOneV1 = "April";
		} else if (monthOne1 == 5) {
			monthOneV1 = "May";
		} else if (monthOne1 == 6) {
			monthOneV1 = "June";
		} else if (monthOne1 == 7) {
			monthOneV1 = "July";
		} else if (monthOne1 == 8) {
			monthOneV1 = "August";
		} else if (monthOne1 == 9) {
			monthOneV1 = "September";
		} else if (monthOne1 == 10) {
			monthOneV1 = "October";
		} else if (monthOne1 == 11) {
			monthOneV1 = "November";
		} else if (monthOne1 == 12) {
			monthOneV1 = "December";
		}

		double x = 0;
		double singlePremium = 0;
		double levelTermInsurance = 0;
		double initiationfee = 0;
		double loanAndCharges = 0;

		if (customerAge <= 29) {
			if (loanPeriod < 13) {
				x = 6.35;
			} else if (loanPeriod < 25) {
				x = 12.0;
			} else if (loanPeriod < 37) {
				x = 17.4;
			} else if (loanPeriod < 49) {
				x = 22.75;
			} else if (loanPeriod < 61) {
				x = 27.8;
			}

		} else if (customerAge <= 39) {
			if (loanPeriod < 13) {
				x = 10.5;
			} else if (loanPeriod < 25) {
				x = 19.50;
			} else if (loanPeriod < 37) {
				x = 27.65;
			} else if (loanPeriod < 49) {
				x = 35.1;
			} else if (loanPeriod < 61) {
				x = 42.1;
			}

		}

		else if (customerAge <= 49) {
			if (loanPeriod < 13) {
				x = 8.55;
			} else if (loanPeriod < 25) {
				x = 16.85;
			} else if (loanPeriod < 37) {
				x = 25.4;
			} else if (loanPeriod < 49) {
				x = 32.0;
			} else if (loanPeriod < 61) {
				x = 37.75;
			}

		}

		else if (customerAge <= 59) {
			if (loanPeriod < 13) {
				x = 13.45;
			} else if (loanPeriod < 25) {
				x = 25.5;
			} else if (loanPeriod < 37) {
				x = 37.2;
			} else if (loanPeriod < 49) {
				x = 48.3;
			} else if (loanPeriod < 61) {
				x = 62.0;
			}

		}

		else if (customerAge >= 60) {
			if (loanPeriod < 13) {
				x = 21.9;
			} else if (loanPeriod < 25) {
				x = 43.45;
			} else if (loanPeriod < 37) {
				x = 66.3;
			} else if (loanPeriod < 49) {
				x = 90.75;
			} else if (loanPeriod < 61) {
				x = 115.45;
			}

		}
		
		String loanAmountFinalWords = thePersonalLoan.getLoanAmountFinalWords();

		double r = ((thePersonalLoan.getPrimaRate() + thePersonalLoan.getFloatingRate()) / 100) / 12;
		double presentValue = thePersonalLoan.getLoanAmountRequested();
		float instalment = 0;
		double beginingBalance = 0;
		double totalEarlyPayments = 0;
		double extraPayment = 0;
		double totalPayment = 0;
		double principal = 0;
		double finalValue = 0;
		double interest = 0;
		double endingBalance = 0;
		double totalInterest = 0;
		totalInterest = interest * loanPeriod;
		double itcCharge = 0;

		singlePremium = 6.91 * loanPeriod;
		levelTermInsurance = (x * presentValue / 1000) + singlePremium;
		//initiationfee = (thePersonalLoan.getLoanAmountRequested() + itcCharge + levelTermInsurance);
		loanAndCharges = levelTermInsurance + initiationfee + presentValue;


		// Add fonts for the document
		Font title = FontFactory.getFont(FontFactory.TIMES_BOLD, 11, Font.NORMAL);
		Font subtitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 9, Font.NORMAL);
		Font body = FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL);
		Font parameter = FontFactory.getFont(FontFactory.TIMES, 9, Font.BOLD);

		//Image img = Image.getInstance("http://localhost:8097/img/logo.jpg");
		Image img = Image.getInstance("http://live-intranet.swazibank.co.sz:8151/actuator/info/img/logo.jpg");
		document.add((Element) img);

		Phrase application = new Phrase();
		application.add(new Chunk("\nSECURED PERSONAL LOAN APPLICATION FORM\n", title));
		application.add(new Chunk("\n*RECEIVED AT ", body));
		application.add(new Chunk("BRANCH: " + branch + " CIF NO: " + cif + "\n", parameter));
		application.add(
				new Chunk("I.........." + customerName + "..........OF.........." + residentialAddress, parameter));
		application.add(new Chunk(
				"\nApply for the undermentioned loan on the terms and conditions hereinafter set out:-\n", body));
		application.add(new Chunk("\n1.	TOTAL LOAN E ", body));
		application.add(new Chunk("Total Loan Amount ......" + loanAmountFinalWords , parameter));
		application.add(new Chunk(
				"\n                                						                         (AMOUNT IN WORDS)\n",
				body));
		application.add(new Chunk(" (a) Credit Life Cover ", body));
		application.add(new Chunk("E" + String.valueOf(levelTermInsurance), parameter));
		application.add(new Chunk("  (b) Initiation Fee	    E ", body));
		application.add(new Chunk("E"+ String.valueOf(initiationfee), parameter));
		application.add(new Chunk("  (c) Credit account No.", body));
		application.add(new Chunk("XXX", parameter));
		application.add(new Chunk("  (d) Service Fee        E", body));
		application.add(new Chunk("XXX", parameter));
		application.add(new Chunk(
				"\n\n2. 	Interest on the loan will be payable by me  at the rate equals to prime + XXX% ( XXX    %) per annum "
						+ "calculated from the date the loan is granted until the end of the month during which it is granted and "
						+ "compounded monthly in arrears thereafter.  I agree that the Bank has the right to increase this rate at "
						+ "any time to a rate which will not be in excess of the rate provided by the Consumer Credit Act 2016 .  "
						+ "The right to increase the rate of interest will apply by changing those things which need to be changed "
						+ "on any other loan/s granted to me against the security of deposits and which have not at the date "
						+ "hereof been paid.",
				body));

		application.add(new Chunk(
				"\n\n3. 	The loan together with compound interest thereon shall be repayable in ", body));application.add(new Chunk(String.valueOf(loanPeriod) + "Months", parameter));application.add(new Chunk(" instalments of  "
						+ "EXXX per month or in full on XXX. or on demand made by the Bank by letter to me at the  "
						+ "address registered in the Bank’s records or on the day on which the pledge deposits mature, whichever is the earlier.",
				body));

		application.add(new Chunk(
				"\n\n4.	As security for repayment of the loan together with interest thereon.  I hereby pledge to the Bank the "
						+ "deposits specified below and being (type of cash security) ……………………………………... together "
						+ "with all benefits accruing thereon.  I hereby agree that unless the loan together with interest thereon is "
						+ "repaid before the deposits mature they shall be reinvested on maturity at the discretion of the Bank and "
						+ "the relevant new deposit receipts shall be deemed to have been pledged to the Bank.  Should I fail to "
						+ "repay the loan together with interest thereon on the day on which the loan is repaid, the Bank is hereby "
						+ "authorised without notice to me, to transfer, redeem or treat as surrendered to their face value the "
						+ "deposits pledged hereunder and to apply the moneys derived therefrom in reduction of my indebtedness and credit the account for any balance.",
				body));

		application.add(new Chunk(
				"\n\n5.	The deposit accounts against which this application is made is enclosed herewith.  "
						+ "I understand and consent to the Bank retaining the said certificate/s until the loan is repaid in full.",
				body));

		application.add(new Chunk(
				"\n\n6.	I admit full knowledge that this loan is secured by ______________________________________ "
						+ "and funds held by the Bank under Account No._________________________.",
				body));

		application.add(new Chunk("\n\n7.	I acknowledge having received copy of this application.", body));

		application.add(new Chunk(
				"\n\n8.	This application if approved by the Bank will constitute an agreement for which the loan is granted to me and on any default, "
						+ "omission or breach by me the Bank is hereby authorised to enforce and realize any security held by it in respect of the loan.",
				body));

		application.add(new Chunk(
				"\n\n9.	I agree, that the address shown on this application is to be registered in the Bank’s records for all accounts in my name.",
				body));

		application.add(new Chunk("\n\n10.        I consent to the following:", body));

		application.add(new Chunk(
				"\n10.1 Marketing of bank products, services and special offers to me:	Yes		No ", body));

		application.add(new Chunk(
				"\n10.2 Communicating other company products, services and special offers to me. If I respond positively    to such communication I may be contacted by the bank:		Yes		No",
				body));

		application.add(new Chunk(
				"\n10.3 Sharing of my personal information within the bank for marketing purposes and the bank marketing its products, services and other special offers to me:		Yes		No",
				body));

		application.add(
				new Chunk("\n10.4 I may be happy to receive such communication via (Please tick where applicable):\r\n"
						+ "Mail		Telephone		SMS		Email", body));

		application.add(new Chunk(
				"\n\n11. 	No I do not want to receive any communication from the bank on products, services or special offers and understand that this means "
						+ "that I will not be kept informed of products and services that may be of value to me. I will therefore not hold the bank liable for any "
						+ "opportunities I may lose as a result of the decision.",
				body));

		application.add(new Chunk(
				"\n\n12. 	I agree that the bank may enquire about my credit record with any credit reference agencies to consider this application or to update any information. "
						+ "I agree that the bank may share any information with the credit agencies about how to manage the loan agreement during the time of the loan."
						+ " I agree that the bank may carry out identity, fraud and anti-money laundering checks and share information to this application with the relevant "
						+ "authorities as per the requirements of the Money Laundering and Financing of Terrorism (Prevention) Act 2011 (as amended).",
				body));

		application.add(new Chunk("\n\nDate:……………………………………………………………….		Signature:………………………………………………………", body));
		application.add(new Chunk("\n\nPLEDGED DEPOSITS", subtitle));
		document.add(application);

		PdfPTable pledgeddepositsTable = new PdfPTable(2);
		pledgeddepositsTable.setWidthPercentage(100);
		pledgeddepositsTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		pledgeddepositsTable.setWidths(new int[] { 3, 2 });
		pledgeddepositsTable.getDefaultCell().setBorder(Rectangle.BOX);

		pledgeddepositsTable.addCell(new Paragraph("DEPOSIT A/C NO.", body));
		pledgeddepositsTable.addCell(new Paragraph("VALUE (E)", body));

		pledgeddepositsTable.addCell(new Paragraph("xxx", body));
		pledgeddepositsTable.addCell(new Paragraph("xxx", body));

		document.add(pledgeddepositsTable);

		PdfPTable officialuseTable = new PdfPTable(3);
		officialuseTable.setWidthPercentage(100);
		officialuseTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		officialuseTable.setWidths(new int[] { 5, 5, 5 });
		officialuseTable.getDefaultCell().setBorder(Rectangle.BOX);

		officialuseTable.addCell(new Paragraph("DEPOSITs PLEDGED", body));
		officialuseTable.addCell(new Paragraph("DOCUMENTS ATTACHED", body));
		officialuseTable.addCell(new Paragraph("BRANCH MANAGER SIGNATURE", body));

		officialuseTable.addCell(new Paragraph("xxx\nXXX\nxxx\nxxxx\nxxx", body));
		officialuseTable.addCell(new Paragraph("xxx\nXXX\nxxx\nxxxx\nxxx", body));
		officialuseTable.addCell(new Paragraph("", body));

		document.add(officialuseTable);

		Phrase applicationDescision = new Phrase();

		applicationDescision.add(new Chunk("\n\nAPPLICATION  APPROVED / DEFERRED / DECLINED", body));
		applicationDescision.add(new Chunk("xxx", body));
		applicationDescision.add(new Chunk("DATE:", body));
		applicationDescision.add(new Chunk("xxx", body));
		applicationDescision.add(new Chunk("AUTHORISED SIGNATURE: …………………………………………", body));
		applicationDescision.add(new Chunk("\n\nCustomer to be given a copy of this application", body));
		document.add(applicationDescision);


		document.newPage();
		document.add((Element) img);
		
		Phrase sanction = new Phrase();
		sanction.add(new Chunk("MEMORANDUM", title));
		sanction.add(new Chunk("\n\nTO:		MANAGER     BRANCH", title));
		sanction.add(new Chunk("\nFROM:		MANAGER CREDIT", title));
		sanction.add(new Chunk("\nDATE:", title));
		sanction.add(new Chunk("xxx", body));
		sanction.add(new Chunk("\nBORROWER:	……………..CIF:", body));
		sanction.add(new Chunk("\nFACILITY/TYPE:	 PERSONAL LOAN", body));
		sanction.add(new Chunk("\nAMOUNT", body));
		sanction.add(new Chunk("\nRATE", body));
		sanction.add(new Chunk("\nREPAYMENT", body));
		sanction.add(new Chunk("\nEXPIRY DATE", body));
		sanction.add(new Chunk("\nPERSONAL	E		9%(Prime + 1.5%)	E.00p.m.", body));
		sanction.add(new Chunk("\nDECISION:	APPROVED", body));
		sanction.add(new Chunk("\nPERIOD:	 ", body));sanction.add(new Chunk(String.valueOf(loanPeriod) + "Months", parameter));
		sanction.add(new Chunk("\nNEXT REVIEWABLE DATE:	2020", body));
		sanction.add(new Chunk("\nPURPOSE OF LOAN:	 To meet personal needs.", body));
		sanction.add(new Chunk("\nSECURITY: "
				+ "\n1)	Stop order from salary account"
				+ "\n2)	General pledge & letter of set off\r\n" 
				+"\n3)	Level Term Insurance Policy"
				+ "\n4)	Finance Facility Agreement\r\n" 
				+"\n5)	Cession over", body));
		sanction.add(new Chunk("\n\nAny security held now, or at any future time, shall be security for all liabilities of the borrower to the Bank. "
						+ "Security currently held and or that is required for the above loan is as listed above.",
				body));
		sanction
				.add(new Chunk("\n\nCONDITIONS:" 
		+ "\n1)	Disbursement must be effected upon perfection of securities."
		+ "\n2)	Collect applicable charges." 
		+ "\n3)	Account should be closely monitored.",
						body));
		sanction.add(new Chunk("\nE.T. DLAMINI" + "MANAGER  CREDIT", body));
		document.add(sanction);
		
		document.newPage();
		document.add((Element) img);
		
		Phrase resume = new Phrase();
		resume.add(new Chunk("RESUME\n\n", title));
		document.add(resume);
		 

		PdfPTable resumeTable = new PdfPTable(2);
		resumeTable.setWidthPercentage(100);
		resumeTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		resumeTable.setWidths(new int[] { 3, 2 });
		resumeTable.getDefaultCell().setBorder(Rectangle.BOX);
		
		resumeTable.addCell(new Paragraph("BRANCH", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("DATE", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("NAME", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("POSTAL ADDRESS", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("RESIDENTIAL ADDRESS", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CELL NUMBER", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("PLACE OF EMPLOYMENT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("REQUESTED LOAN AMOUNT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("INITIATION FEE", body));
		resumeTable.addCell(new Paragraph("E" + initiationfee));
		resumeTable.addCell(new Paragraph("TOTAL LOAN AMOUNT (Requested Loan + Charges) ", body));
		resumeTable.addCell(new Paragraph("E" +String.valueOf(loanAndCharges),parameter));
		resumeTable.addCell(new Paragraph("LEVEL TERM INSURANCE", body));
		resumeTable.addCell(new Paragraph(String.valueOf(levelTermInsurance),parameter));
		resumeTable.addCell(new Paragraph("TOTAL PREMIUMS TO BE REMITTED TO INSURANCE", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("TOTAL LOAN AMOUNT ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("DEBT CONSOLIDATION", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("EXISTING SWAZIBANK KHULULEKA ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CONSOLIDATION BALANCE ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CONSOLIDATION BALANCE ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CONSOLIDATION BALANCE ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("BANK CHEQUE CHARGES", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("DISBURSEMENT TO ACCOUNT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("INTEREST Prime + 12", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("PURPOSE", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("PERIOD", body));
		resumeTable.addCell(new Paragraph(String.valueOf(loanPeriod) + "Months", parameter));
		resumeTable.addCell(new Paragraph("PRINCIPAL REPAYMENT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("MANAGEMENT FEE", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("TOTAL REPAYMENT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("STOP ORDER OVER SALARY ACCOUNT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("GENERAL PLEDGE & LETTER OF SET OFF", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("LEVEL TERM INSURANCE POLICY", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CESSION OVER TARGET ACCOUNT TO BE ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("FINANCIAL FACILITY AGREEMENT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("BANKER'S REPORT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CUSTOMER CIF", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("ELIGIBILITY", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CLIENT GROSS PAY", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("PROPOSED MONTHLY REPAYMENT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("OTHER LOANS DEDUCTION ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("TOTAL DEDUCTIONS", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CREDIT HISTORY ", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("PREVIOUS LOAN AMOUNT", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("CONCLUSION", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("BRANCH MANAGER'S COMMENTS", body));
		resumeTable.addCell(new Paragraph(""));
		resumeTable.addCell(new Paragraph("DECISION", body));
		resumeTable.addCell(new Paragraph(""));
		
		document.add(resumeTable);

	}

}
