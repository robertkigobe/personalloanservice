package sz.co.swazibank.personalloanservice.service;

import java.util.List;

import sz.co.swazibank.personalloanservice.entity.PersonalLoan;

public interface PesonalLoanServiceService {
	
	public List<PersonalLoan> findAll();

	public void saveAndFlush(PersonalLoan thePersonalLoan);

	public List<PersonalLoan> findInProgress();

}
