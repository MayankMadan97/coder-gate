package com.github.codergate.services;
import com.github.codergate.dto.installation.AccountDTO;
import com.github.codergate.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserEntity userEntityMock;
    @InjectMocks
    UserService userServiceMock;
    UserService userService =new UserService();


    @Test
    void testConvertAccountDTOToEntityWhenDataIsNull()
    {
        AccountDTO accountDTO = new AccountDTO();
        UserEntity expected =new UserEntity();
        UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
        assertNotNull(actual);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void testConvertAccountDTOToEntityWhenDTOValueIsNull()
    {
        AccountDTO accountDTO = null;
        UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
        assertNull(actual);
    }

    @Test
    void testConvertAccountDTOToEntityWhenDTOIsEmpty()
    {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(null);
        accountDTO.setLogin(null);
        UserEntity expected =new UserEntity();
        UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
        assertNotNull(actual);
        assertEquals(expected.toString(),actual.toString());
    }


    @Test
    void testConvertAccountDTOToEntityWhenSomeDataIsMissing()
    {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(32);
        UserEntity expected =new UserEntity();
        expected.setUserId(32);
        UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void testConvertAccountDTOToEntityWhenDataIsPresent()
    {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(32);
        accountDTO.setLogin("TestBot");
        UserEntity expected =new UserEntity();
        expected.setUserId(32);
        expected.setUserName("TestBot");
        UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void testConvertSenderDTOToEntityWhenDataIsPresent()
    {
        int id =32;
        String login = "TestBot";
        String email ="testbot@gmail.com";
        UserEntity expected =new UserEntity();
        expected.setUserId(id);
        expected.setUserName(login);
        expected.setEmail(email);
        UserEntity actual = userService.convertSenderDtoToEntity(id,login,email);
        assertEquals(expected.toString(),actual.toString());
    }
    @Test
    void testConvertSenderDTOToEntityWhenSomeDataIsMissing()
    {
        int id = 32;
        String login = null;
        String email ="testbot@gmail.com";
        UserEntity expected =new UserEntity();
        expected.setUserId(id);
        expected.setUserName(login);
        expected.setEmail(email);
        UserEntity actual = userService.convertSenderDtoToEntity(id,login,email);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void testConvertSenderDTOToEntityWhenAllDataAreNull()
    {
        Integer id = null;
        String login = null;
        String email =null;
        UserEntity expected =new UserEntity();
        expected.setUserName(login);
        expected.setEmail(email);
        UserEntity actual = userService.convertSenderDtoToEntity(id,login,email);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    void testConvertEntityToAccountDTOWithNonNullValues()
    {
        // set up the mock
        when(userEntityMock.getUserId()).thenReturn(32L);
        when(userEntityMock.getUserName()).thenReturn("TestBot");

        AccountDTO expectedAccountDTO = new AccountDTO();
        expectedAccountDTO.setId((int) userEntityMock.getUserId());
        expectedAccountDTO.setLogin(userEntityMock.getUserName());

        // call method to check
        AccountDTO accountDTO = userServiceMock.convertEntityToAccountDto(userEntityMock);

        // check result
        assertNotNull(accountDTO);
        assertEquals(expectedAccountDTO.getId(),accountDTO.getId());
        assertEquals(expectedAccountDTO.getLogin(),accountDTO.getLogin());

    }

    @Test
    void testConvertEntityToAccountDTOWithEmptyInput()
    {
        // set up the mock
        when(userEntityMock.getUserId()).thenReturn(0L);
        when(userEntityMock.getUserName()).thenReturn(null);

        // call method to check
        AccountDTO accountDTO = userServiceMock.convertEntityToAccountDto(userEntityMock);

        // check result
        assertNull(accountDTO.getId());
        assertNull(accountDTO.getLogin());

    }

    @Test
    void testConvertEntityToAccountDTOWithNullInput()
    {
        UserEntity userEntity=null;

        // call method to check
        AccountDTO accountDTO = userServiceMock.convertEntityToAccountDto(userEntity);

        // check result
        assertNull(accountDTO);
    }
}
