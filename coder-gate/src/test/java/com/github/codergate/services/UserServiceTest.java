package com.github.codergate.services;

import com.github.codergate.dto.installation.AccountDTO;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    UserService userServiceMock;

    private final UserEntity userEntity = new UserEntity();
    private final AccountDTO accountDTO = new AccountDTO();
    private final long userId =32;
    int userIds = 32;
    int expectedId =32;

    @Test
    void testDeleteUserByID() {
        int wantedNumberOfInvocation =1;
        Mockito.doNothing().when(userRepositoryMock).deleteById(userId);
        boolean isDeleted = userServiceMock.deleteUserByID(userId);
        assertTrue(isDeleted);
        verify(userRepositoryMock, Mockito.times(wantedNumberOfInvocation)).deleteById(userId);
    }

    @Test
    void testDeleteUserByIDWhenNull() {
        int wantedNumberOfInvocation =0;
        Long userId = null;
        boolean isDeleted = userServiceMock.deleteUserByID(userId);
        assertFalse(isDeleted);
        verify(userRepositoryMock, Mockito.times(wantedNumberOfInvocation)).deleteById(userId);
    }

    @Test
    void testGetUserByID() {
        userEntity.setUserName("TestBot");
        userEntity.setUserId(userId);
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(userEntity));
        AccountDTO expected = new AccountDTO();
        expected.setId(expectedId);
        expected.setLogin("TestBot");
        AccountDTO actual = userServiceMock.getUserById(userId);
        assertNotNull(actual);
    }

    @Test
    void testGetUserByIDWhenIdIsNull() {
        Long userId = null;
        AccountDTO actual = userServiceMock.getUserById(userId);
        assertNull(actual);
    }

    @Test
    void testAddUser() {
        int wantedNumberOfInvocation =1;
        accountDTO.setId(userIds);
        accountDTO.setLogin("TestBot");
        UserEntity expected = new UserEntity();
        expected.setUserId(userIds);
        expected.setUserName("TestBot");
        Mockito.when(userRepositoryMock.save(Mockito.any(UserEntity.class))).thenReturn(expected);
        AccountDTO actual = userServiceMock.addUser(accountDTO);
        assertEquals(expected.getUserId(), actual.getId().intValue());
        verify(userRepositoryMock, Mockito.times(wantedNumberOfInvocation)).save(Mockito.any(UserEntity.class));
    }

    @Test
    void testAddUserWithNullValues() {
        AccountDTO accountDTO = null;
        AccountDTO actual = userServiceMock.addUser(accountDTO);
        assertNull(actual);

    }

    @Test
    void testAddUserWithAccountDTOIsNUllOrUserIdIsNull() {

        accountDTO.setId(null);
        accountDTO.setLogin(null);
        AccountDTO actual = userServiceMock.addUser(accountDTO);
        assertNull(actual);
        verify(userRepositoryMock, never()).saveAll(anyList());

    }

    @Test
    void testAddUserWithUserEntityIsNUllOrUserIdIsNull() {

        int userId =0;
        accountDTO.setId(userId);
        accountDTO.setLogin(null);
        UserEntity expected = new UserEntity();
        expected.setUserId(userId);
        expected.setUserName(null);
        Mockito.when(userRepositoryMock.save(Mockito.any(UserEntity.class))).thenReturn(expected);
        AccountDTO actual = userServiceMock.addUser(accountDTO);
        assertNotNull(actual);
        verify(userRepositoryMock, never()).saveAll(anyList());

    }

    @Test
    void testAddUserHandlesException() {
        int wantedNumberOfInvocation =1;
        accountDTO.setId(userIds);
        accountDTO.setLogin("TestBot");
        Mockito.doThrow(new RuntimeException()).when(userRepositoryMock).save(Mockito.any(UserEntity.class));
        assertThrows(RuntimeException.class, () -> userServiceMock.addUser(accountDTO));
        verify(userRepositoryMock, Mockito.times(wantedNumberOfInvocation)).save(Mockito.any(UserEntity.class));
    }


    @Test
    void testUpdateUser() {

        int wantedNumberOfInvocation =1;
        String login = "TestBot";
        accountDTO.setId(expectedId);
        accountDTO.setLogin(login);
        userEntity.setUserName(login);
        userEntity.setUserId(userId);
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);
        AccountDTO actual = userServiceMock.updateUserById(userId);
        verify(userRepositoryMock, times(wantedNumberOfInvocation)).findById(userId);
        verify(userRepositoryMock, times(wantedNumberOfInvocation)).save(userEntity);
        assertNotNull(actual);
    }

    @Test
    void testUpdateUserIfNull() {
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());
        AccountDTO accountDTO = userServiceMock.updateUserById(userId);
        assertNull(accountDTO);
    }

    @Test
    void testAddUserForPush() {

        String login = "TestBot";
        String email = "test@abc.com";
        userEntity.setUserName(login);
        userEntity.setEmail(email);
        userEntity.setUserId(userId);
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(userEntity);
        UserEntity actual = userServiceMock.addUser(userIds, login, email);
        assertEquals(actual.getEmail(), email);
        verify(userRepositoryMock, times(1)).save(any(UserEntity.class));
    }


    @Test
    void testAddUserWhenUserEntityForPushIsNull() {
        Integer userId = null;
        String login = null;
        String userEmail = null;
        UserEntity actual = userServiceMock.addUser(userId, login, userEmail);
        assertNull(actual.getEmail());
    }

    @Test
    void testAddUserWithNullUserEntity() {
        UserEntity userEntity = null;
        int userId=0;
        Mockito.when(userRepositoryMock.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        UserEntity actual = userServiceMock.addUser(null, null, null);
        assertEquals(actual.getUserId(), userId);
    }

    @Test
    void testAddUserWhenUserEntityNameIsNull() {
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(null);
        UserEntity actual = userServiceMock.addUser(null, null, null);
        assertNull(actual.getUserName());
    }
}
