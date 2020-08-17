package sz.co.swazibank.personalloanservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sz.co.swazibank.personalloanservice.entity.PersonalLoan;

@Repository
public interface PersonalLoanRepository extends JpaRepository<PersonalLoan, Integer>{
	
	PersonalLoan findPersonalLoanById(int id);
	
	@Query(value="SELECT * FROM swazibank_intranet.personal_loan where loan_status != 'Loan disbursed' order by last_updated desc", nativeQuery=true)
	List<PersonalLoan> findInProgress();

}
