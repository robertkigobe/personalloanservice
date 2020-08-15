package sz.co.swazibank.personalloanservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sz.co.swazibank.personalloanservice.dao.PersonalLoanRepository;
import sz.co.swazibank.personalloanservice.entity.PersonalLoan;

@Service
public class PersonalLoanServiceServiceImpl implements PesonalLoanServiceService{

	@Autowired
	PersonalLoanRepository personalLoanRepository; 
	@Override
	public List<PersonalLoan> findAll() {
		
		return personalLoanRepository.findAll();
	
	}
	
	@Override
	public void saveAndFlush(PersonalLoan thePersonalLoan) {
		personalLoanRepository.save(thePersonalLoan);
		
	}

	@Override
	public List<PersonalLoan> findInProgress() {
		// TODO Auto-generated method stub
		return personalLoanRepository.findInProgress();
	}

}
