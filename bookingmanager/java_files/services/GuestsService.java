package progmatic.bookingmanager.services;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.CompanyGuest;
import progmatic.bookingmanager.databaseEntity.Guest;
import progmatic.bookingmanager.databaseEntity.PersonGuest;
import progmatic.bookingmanager.dtos.GuestDto;
import progmatic.bookingmanager.enums.GuestTitleEnum;
import progmatic.bookingmanager.repositories.CompanyGuestRepository;
import progmatic.bookingmanager.repositories.GuestRepository;
import progmatic.bookingmanager.repositories.PersonGuestRepository;

@Service
public class GuestsService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    PersonGuestRepository personGuestRepository;

    @Autowired
    CompanyGuestRepository companyGuestRepository;

    @Transactional
    public List<GuestDto> allGuestsAndCompanies() {
        List<PersonGuest> allGuests = personGuestRepository.findAllByOrderByLastNameAsc();
        List<CompanyGuest> allCompanies = companyGuestRepository.findAllByOrderByCompanyNameAsc();
        List<GuestDto> guestsAndCompanies = new LinkedList<>();
        for (PersonGuest guest : allGuests) {
            GuestDto addGuest = new GuestDto(
                    guest.getId(), guest.getEmailAddress(), guest.getPhoneNumber(),
                    guest.getCity(), guest.getAddress1(), guest.getZip(), guest.getCountry(),
                    guest.getTitle(), guest.getTaxNo(), guest.getVatID(),
                    guest.getFirstName(), guest.getLastName());
            guestsAndCompanies.add(addGuest);
        }
        for (CompanyGuest company : allCompanies) {
            GuestDto addGuest = new GuestDto(
                    company.getId(), company.getEmailAddress(), company.getPhoneNumber(),
                    company.getCity(), company.getAddress1(), company.getZip(), company.getCountry(),
                    company.getTitle(), company.getTaxNo(), company.getVatID(),
                    company.getCompanyName());
            addGuest.setLastName(addGuest.getCompanyName());
            guestsAndCompanies.add(addGuest);
        }
        Collections.sort(guestsAndCompanies, (gac1, gac2) -> gac1.getLastName().compareTo(gac2.getLastName()));
        return guestsAndCompanies;
    }

    @Transactional
    public List<GuestDto> findGuestsByFragmentsOfName(String fragment) {
        List<PersonGuest> allGuests = em.createQuery("SELECT pig FROM PersonGuest pig WHERE pig.lastName LIKE :fragment OR pig.firstName LIKE :fragment")
                .setParameter("fragment", "%" + fragment + "%")
                .getResultList();
        Collections.sort(allGuests, (pig1, pig2) -> pig1.getLastName().compareTo(pig2.getLastName()));
        List<CompanyGuest> allCompanies = em.createQuery("SELECT cig FROM CompanyGuest cig WHERE cig.companyName LIKE :fragment")
                .setParameter("fragment", "%" + fragment + "%")
                .getResultList();
        Collections.sort(allCompanies, (cig1, cig2) -> cig1.getCompanyName().compareTo(cig2.getCompanyName()));
        List<GuestDto> guestsWithNameWithFragment = new LinkedList<>();
        for (PersonGuest guest : allGuests) {
            GuestDto addGuest = new GuestDto(
                    guest.getId(), guest.getEmailAddress(), guest.getPhoneNumber(),
                    guest.getCity(), guest.getAddress1(), guest.getZip(), guest.getCountry(),
                    guest.getTitle(), guest.getTaxNo(), guest.getVatID(),
                    guest.getFirstName(), guest.getLastName());
            guestsWithNameWithFragment.add(addGuest);
        }
        for (CompanyGuest company : allCompanies) {
            GuestDto addGuest = new GuestDto(
                    company.getId(), company.getEmailAddress(), company.getPhoneNumber(),
                    company.getCity(), company.getAddress1(), company.getZip(), company.getCountry(),
                    company.getTitle(), company.getTaxNo(), company.getVatID(),
                    company.getCompanyName());
            addGuest.setLastName(addGuest.getCompanyName());
            guestsWithNameWithFragment.add(addGuest);
        }
        Collections.sort(guestsWithNameWithFragment, (gac1, gac2) -> gac1.getLastName().compareTo(gac2.getLastName()));
        return guestsWithNameWithFragment;
    }

    @Transactional
    public GuestDto findGuestDtoById(Long id) {
        Guest guest = guestRepository.findById(id);
        GuestDto guestById;
        if (guest.getTitle().equals("COMPANY")) {
            CompanyGuest actGuest = (CompanyGuest) guest;
            guestById = new GuestDto(
                    guest.getId(), guest.getEmailAddress(), guest.getPhoneNumber(),
                    guest.getCity(), guest.getAddress1(), guest.getZip(), guest.getCountry(),
                    guest.getTitle(), guest.getTaxNo(), guest.getVatID(),
                    actGuest.getCompanyName());
        } else {
            PersonGuest actGuest = (PersonGuest) guest;
            guestById = new GuestDto(
                    guest.getId(), guest.getEmailAddress(), guest.getPhoneNumber(),
                    guest.getCity(), guest.getAddress1(), guest.getZip(), guest.getCountry(),
                    guest.getTitle(), guest.getTaxNo(), guest.getVatID(),
                    actGuest.getFirstName(), actGuest.getLastName());
        }
        return guestById;
    }
    public Guest findGuestById (long id){
        return guestRepository.findById(id);
    }

    @Transactional
    public GuestDto addNewGuest(GuestDto guestDto) {
        if (guestDto.getTitle().equals("COMPANY")) {
            CompanyGuest newGuest = new CompanyGuest();
            newGuest.setCompanyName(guestDto.getCompanyName());
            newGuest.setAddress1(guestDto.getAddress1());
            newGuest.setCity(guestDto.getCity());
            newGuest.setCountry(guestDto.getCountry());
            newGuest.setEmailAddress(guestDto.getEmailAddress());
            newGuest.setPhoneNumber(guestDto.getPhoneNumber());
            newGuest.setTaxNo(guestDto.getTaxNo());
            newGuest.setVatID(guestDto.getVatID());
            newGuest.setZip(guestDto.getZip());
            newGuest.setTitle(guestDto.getTitle());
            em.persist(newGuest);
            em.flush();
        } else {
            PersonGuest newGuest = new PersonGuest();
            newGuest.setFirstName(guestDto.getFirstName());
            newGuest.setLastName(guestDto.getLastName());
            newGuest.setAddress1(guestDto.getAddress1());
            newGuest.setCity(guestDto.getCity());
            newGuest.setCountry(guestDto.getCountry());
            newGuest.setEmailAddress(guestDto.getEmailAddress());
            newGuest.setPhoneNumber(guestDto.getPhoneNumber());
            newGuest.setTaxNo(guestDto.getTaxNo());
            newGuest.setVatID(guestDto.getVatID());
            newGuest.setZip(guestDto.getZip());
            newGuest.setTitle(guestDto.getTitle());
            em.persist(newGuest);
            em.flush();
        }
        return guestDto;
    }

    @Transactional
    public Guest modifyGuestData(GuestDto guestDto) {
        if (guestDto.getTitle().equals("COMPANY")) {
            CompanyGuest guest = new CompanyGuest();
            guest.setId(guestDto.getId());
            guest.setCompanyName(guestDto.getCompanyName());
            guest.setAddress1(guestDto.getAddress1());
            guest.setCity(guestDto.getCity());
            guest.setCountry(guestDto.getCountry());
            guest.setEmailAddress(guestDto.getEmailAddress());
            guest.setPhoneNumber(guestDto.getPhoneNumber());
            guest.setTaxNo(guestDto.getTaxNo());
            guest.setVatID(guestDto.getVatID());
            guest.setZip(guestDto.getZip());
            guest.setTitle(guestDto.getTitle());
            guestRepository.save(guest);
            em.flush();
            return guest;
        } else {
            PersonGuest guest = new PersonGuest();
            guest.setId(guestDto.getId());
            guest.setFirstName(guestDto.getFirstName());
            guest.setLastName(guestDto.getLastName());
            guest.setAddress1(guestDto.getAddress1());
            guest.setCity(guestDto.getCity());
            guest.setCountry(guestDto.getCountry());
            guest.setEmailAddress(guestDto.getEmailAddress());
            guest.setPhoneNumber(guestDto.getPhoneNumber());
            guest.setTaxNo(guestDto.getTaxNo());
            guest.setVatID(guestDto.getVatID());
            guest.setZip(guestDto.getZip());
            guest.setTitle(guestDto.getTitle());
            guestRepository.save(guest);
            em.flush();
            return guest;
        }
    }

    public Boolean checkEmailAddresses(String email) {
        List<Guest> resultList = em.createQuery("select g from Guest g where g.emailAddress = :email")
                .setParameter("email", email)
                .getResultList();
        if (resultList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean personGuestFirstNameError(GuestDto newGuest) {
        if (newGuest.getFirstName() != "") {
            return false;
        } else {
            return true;
        }
    }

    public boolean personGuestLastNameError(GuestDto newGuest) {
        if (newGuest.getLastName() != "") {
            return false;
        } else {
            return true;
        }
    }

    public boolean companyGuestCompanyNameError(GuestDto newGuest) {
        if (newGuest.getCompanyName() != "") {
            return false;
        } else {
            return true;
        }

    }
}
