// package com.github.codergate.services;
// import com.github.codergate.dto.installation.AccountDTO;
// import com.github.codergate.entities.UserEntity;
// import com.github.codergate.repositories.UserRepository;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.when;


// @ExtendWith(MockitoExtension.class)
// class UserServiceTest {

//     @Mock
//     UserRepository userRepository;
//     @Mock
//     UserEntity userEntityMock;
//     @InjectMocks
//     UserService userServiceMock;
//     UserService userService =new UserService();


//     @Test
//     void testConvertAccountDTOToEntityWhenDataIsNull()
//     {
//         AccountDTO accountDTO = new AccountDTO();
//         UserEntity expected =new UserEntity();
//         UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
//         assertNotNull(actual);
//         assertEquals(expected.toString(),actual.toString());
//     }

//     @Test
//     void testConvertAccountDTOToEntityWhenDTOValueIsNull()
//     {
//         AccountDTO accountDTO = null;
//         UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
//         assertNull(actual);
//     }

//     @Test
//     void testConvertAccountDTOToEntityWhenDTOIsEmpty()
//     {
//         AccountDTO accountDTO = new AccountDTO();
//         accountDTO.setId(null);
//         accountDTO.setLogin(null);
//         UserEntity expected =new UserEntity();
//         UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
//         assertNotNull(actual);
//         assertEquals(expected.toString(),actual.toString());
//     }


//     @Test
//     void testConvertAccountDTOToEntityWhenSomeDataIsMissing()
//     {
//         AccountDTO accountDTO = new AccountDTO();
//         accountDTO.setId(32);
//         UserEntity expected =new UserEntity();
//         expected.setUserId(32);
//         UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
//         assertEquals(expected.toString(),actual.toString());
//     }

//     @Test
//     void testConvertAccountDTOToEntityWhenDataIsPresent()
//     {
//         AccountDTO accountDTO = new AccountDTO();
//         accountDTO.setId(32);
//         accountDTO.setLogin("TestBot");
//         UserEntity expected =new UserEntity();
//         expected.setUserId(32);
//         expected.setUserName("TestBot");
//         UserEntity actual = userService.convertAccountDtoToEntity(accountDTO);
//         assertEquals(expected.toString(),actual.toString());
//     }

//     @Test
//     void testConvertSenderDTOToEntityWhenDataIsPresent()
//     {
//         int id =32;
//         String login = "TestBot";
//         String email ="testbot@gmail.com";
//         UserEntity expected =new UserEntity();
//         expected.setUserId(id);
//         expected.setUserName(login);
//         expected.setEmail(email);
//         UserEntity actual = userService.convertSenderDtoToEntity(id,login,email);
//         assertEquals(expected.toString(),actual.toString());
//     }
//     @Test
//     void testConvertSenderDTOToEntityWhenSomeDataIsMissing()
//     {
//         int id = 32;
//         String login = null;
//         String email ="testbot@gmail.com";
//         UserEntity expected =new UserEntity();
//         expected.setUserId(id);
//         expected.setUserName(login);
//         expected.setEmail(email);
//         UserEntity actual = userService.convertSenderDtoToEntity(id,login,email);
//         assertEquals(expected.toString(),actual.toString());
//     }

//     @Test
//     void testConvertSenderDTOToEntityWhenAllDataAreNull()
//     {
//         Integer id = null;
//         String login = null;
//         String email =null;
//         UserEntity expected =new UserEntity();
//         expected.setUserName(login);
//         expected.setEmail(email);
//         UserEntity actual = userService.convertSenderDtoToEntity(id,login,email);
//         assertEquals(expected.toString(),actual.toString());
//     }

//     @Test
//     void testConvertEntityToAccountDTOWithNonNullValues()
//     {
//         // set up the mock
//         when(userEntityMock.getUserId()).thenReturn(32L);
//         when(userEntityMock.getUserName()).thenReturn("TestBot");

//         AccountDTO expectedAccountDTO = new AccountDTO();
//         expectedAccountDTO.setId((int) userEntityMock.getUserId());
//         expectedAccountDTO.setLogin(userEntityMock.getUserName());

//         // call method to check
//         AccountDTO accountDTO = userServiceMock.convertEntityToAccountDto(userEntityMock);

//         // check result
//         assertNotNull(accountDTO);
//         assertEquals(expectedAccountDTO.getId(),accountDTO.getId());
//         assertEquals(expectedAccountDTO.getLogin(),accountDTO.getLogin());

//     }

//     @Test
//     void testConvertEntityToAccountDTOWithEmptyInput()
//     {
//         // set up the mock
//         when(userEntityMock.getUserId()).thenReturn(0L);
//         when(userEntityMock.getUserName()).thenReturn(null);

//         // call method to check
//         AccountDTO accountDTO = userServiceMock.convertEntityToAccountDto(userEntityMock);

//         // check result
//         assertNull(accountDTO.getId());
//         assertNull(accountDTO.getLogin());

//     }

//     @Test
//     void testConvertEntityToAccountDTOWithNullInput()
//     {
//         UserEntity userEntity=null;

//         // call method to check
//         AccountDTO accountDTO = userServiceMock.convertEntityToAccountDto(userEntity);

//         // check result
//         assertNull(accountDTO);
//     }

//     @Test
//     void testDeleteUserByID()
//     {
//         Long userId = 32L;
//         // set up the  mock
//         Mockito.doNothing().when(userRepository).deleteById(userId);
//         // call method to check
//         boolean isDeleted = userServiceMock.deleteUserByID(userId);
//         assertTrue(isDeleted);
//         // verify method is called
//         Mockito.verify(userRepository,Mockito.times(1)).deleteById(userId);
//     }

//     @Test
//     void testDeleteUserByIDWhenNull()
//     {
//         Long userId = null;
//         boolean isDeleted = userServiceMock.deleteUserByID(userId);
//         assertFalse(isDeleted);
//         Mockito.verify(userRepository,Mockito.times(0)).deleteById(userId);
//     }

//     @Test
//     void testGetUserByID()
//     {
//         Long userId = 32L;
//         UserEntity userEntity = new UserEntity();
//         userEntity.setUserName("TestBot");
//         userEntity.setUserId(userId);

//         when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
//         AccountDTO expected = new AccountDTO();

//         expected.setId(32);
//         expected.setLogin("TestBot");

//         AccountDTO actual = userServiceMock.getUserById(userId);
//         assertEquals(expected, actual);
//     }

//     @Test
//     void testGetUserByIDWhenIdIsNull()
//     {
//         Long userId = null;

//         AccountDTO actual = userServiceMock.getUserById(userId);
//         assertNull(actual);
//     }

//     @Test
//     void testAddUser()
//     {
//         AccountDTO accountDTO = new AccountDTO();
//         accountDTO.setId(32);
//         accountDTO.setLogin("TestBot");

//         UserEntity expected = new UserEntity();
//         expected.setUserId(32);
//         expected.setUserName("TestBot");

//         Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(expected);

//         AccountDTO actual=userServiceMock.addUser(accountDTO);

//         assertEquals(expected.getUserId(), actual.getId().intValue());
//         assertEquals(expected.getUserName(), actual.getLogin());

//         Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
//     }

//     @Test
//     void testAddUserWithNullValues()
//     {
//         AccountDTO accountDTO = null;

//         AccountDTO actual=userServiceMock.addUser(accountDTO);

//         assertNull(actual);

//     }

//     @Test
//     void testAddUserHandlesException()
//     {
//         AccountDTO accountDTO = new AccountDTO();
//         accountDTO.setId(32);
//         accountDTO.setLogin("TestBot");
//         Mockito.doThrow(new RuntimeException()).when(userRepository).save(Mockito.any(UserEntity.class));
//         assertThrows(RuntimeException.class, ()-> userServiceMock.addUser(accountDTO));
//         Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
//     }

// }
