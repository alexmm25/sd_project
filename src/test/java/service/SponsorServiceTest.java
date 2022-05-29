package service;

import backend.model.Sponsor;
import backend.model.Stadium;
import backend.repository.SponsorRepository;
import backend.repository.UserRepository;
import backend.service.SponsorService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SponsorServiceTest {

    @Mock
    private SponsorRepository sponsorRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SponsorService sponsorService;

    @Test
    public void validGetSponsorByIdTest() {
        Sponsor expectedSponsor = new Sponsor("Abibas", "abibas1234","abibas1234", new Stadium(),
                "abibas1234@gmail.com");
        Mockito.doReturn(Optional.of(expectedSponsor)).when(sponsorRepository).findById(1L);
        Sponsor actualSponsor = sponsorService.getSponsorById(1L);
        Assertions.assertEquals(expectedSponsor, actualSponsor);
    }

    @Test (expected = RuntimeException.class)
    public void invalidGetSponsorByIdTest() {
        Mockito.doReturn(Optional.empty()).when(sponsorRepository).findById(0L);
        Sponsor actualRestaurant = sponsorService.getSponsorById(0L);
        Assertions.assertNull(actualRestaurant);
    }

    @Test
    public void validCreateSponsorTest() {
        Sponsor expectedSponsor = new Sponsor("Nike", "nikefc0123","nikefc0123", new Stadium(),
                "nikefc0123@gmail.com");
        Mockito.doReturn(expectedSponsor).when(sponsorRepository).save(expectedSponsor);
        Sponsor actualSponsor = sponsorService.createSponsor(expectedSponsor);
        Assertions.assertEquals(expectedSponsor, actualSponsor);
    }

    @Test
    public void invalidCreateSponsorTest() {
        Sponsor expectedSponsor = new Sponsor("Nike", "nikefc0123","nikefc0123", new Stadium(),
                "nikefc0123");
        Mockito.doReturn(null).when(sponsorRepository).save(expectedSponsor);
        Sponsor actualSponsor = sponsorService.createSponsor(expectedSponsor);
        Assertions.assertNull(actualSponsor);
    }

        @Test
    public void getInvalidAdminIDByUsernameTest() {
        Long expectedID = null;
        String username = "sponsor";
        Mockito.doReturn(null).when(sponsorRepository).findIDbyUsername(username);
        Long actualID = sponsorService.getSponsorByUsername(username);
        Assertions.assertEquals(expectedID, actualID);
    }

    @Test
    public void getValidAdminIDByUsernameTest() {
        Long expectedID = 3L;
        String username = "abibas1234";
        Mockito.doReturn(expectedID).when(sponsorRepository).findIDbyUsername(username);
        Long actualID = sponsorService.getSponsorByUsername(username);
        Assertions.assertEquals(expectedID, actualID);
    }

    @Test
    public void invalidLogInTest() {
        Sponsor sponsor = new Sponsor();
        String username = "abibas";
        String password = "abibas1234";
        sponsor.setUsername(username);
        sponsor.setPassword(password);
        Mockito.doReturn(null).when(userRepository).findPasswordByUsername(username);
        Sponsor actualResponse = sponsorService.logIn(sponsor);
        Assertions.assertNull(actualResponse);
    }

}
