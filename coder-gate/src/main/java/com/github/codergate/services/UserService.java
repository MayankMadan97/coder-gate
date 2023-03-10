package com.github.codergate.services;

import com.github.codergate.dto.installation.Account;
import com.github.codergate.dto.installation.Sender;
import com.github.codergate.dto.push.SenderDTO;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);


    /***
     * adds the value into the table using entity and returns the values in dto class
     * @param user dto class
     * @return dto class with user values
     */
    public Account addUser(Account user)
    {
        Account account;
        UserEntity userEntity = accountDtoToEntity(user);
        UserEntity saveEntity= userRepository.save(userEntity);
        LOGGER.info("UserService : The user information is added");
        account = entityToAccountDto(saveEntity);
        return account;
    }
    public SenderDTO addUser(SenderDTO user)
    {
        SenderDTO sender;
        UserEntity userEntity = senderDtoToEntity(user);
        UserEntity saveEntity = userRepository.save(userEntity);
        LOGGER.info("UserService : The user information is added");
        sender = entityToSenderDto(saveEntity);
        return sender;
    }

    /***
     * Gets the user information from the table using id
     * @param userId user id
     * @return dto class with user information
     */

    public Account getUser(Long userId)
    {
        Account account = null;
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()){
            account = entityToAccountDto(userEntity.get());
            LOGGER.info("UserService : Getting the user information");
        }
        return account;
    }

    /***
     * updates the user information using userId
     * @param userId userId
     * @return dto class
     */
    public Account updateUser(Long userId)
    {
        Account account=null;
        UserEntity userEntity=userRepository.findById(userId).orElse(null);
        if(userEntity!=null)
        {
//            userEntity.setUserName("alec");
            UserEntity saveEntity = userRepository.save(userEntity);
            LOGGER.info("UserService : Updating the user information");
            account= entityToAccountDto(saveEntity);
        }
        return account;
    }


    /***
     *  deletes user row by id
     * @param userId user_id
     * @return true or false
     */
    public boolean deleteUserByID(Long userId) {
        boolean isDeleted =false;
        if(userId!=null)
        {
            userRepository.deleteById(userId);
            isDeleted=true;
            LOGGER.info("UserService : Deleting the user information");
        }
        return isDeleted;
    }


    /***
     * converts dto to entity
     * @param user dto
     * @return entity
     */
    private UserEntity accountDtoToEntity(Account user)
    {
        UserEntity userEntity=null;
        if(user!=null)
        {
            userEntity = new UserEntity();
            if(user.getId()!=null)
            {
                userEntity.setUserId(user.getId());
            }
            if(user.getLogin()!=null)
            {
                userEntity.setUserName(user.getLogin());
            }
            LOGGER.info("UserService : Account DTO has been converted to Entity");
        }else {
            LOGGER.warn("UserService : User value is null ");
        }
        return userEntity;
    }

    private UserEntity senderDtoToEntity(SenderDTO senderDTO)
    {
        UserEntity userEntity = null;
        if(senderDTO != null)
        {
            userEntity = new UserEntity();
            if(senderDTO.getId() != null)
            {
                userEntity.setUserId(senderDTO.getId());
            }
            if(senderDTO.getLogin() != null)
            {
                userEntity.setUserName(senderDTO.getLogin());
            }

            LOGGER.info("UserService : Sender DTO has been converted to Entity");
        } else {
            LOGGER.warn("UserService : User value is null ");
        }
        return userEntity;
    }

    /***
     * converts entity to dto
     * @param user user entity
     * @return dto
     */
    private Account entityToAccountDto(UserEntity user)
    {
        Account account = null;
        if(user!=null)
        {
            account=new Account();
            if(user.getUserId()!=0L)
            {
                account.setId((int) user.getUserId());
            }
            if(user.getUserName()!=null)
            {
                account.setLogin(user.getUserName());
            }
            LOGGER.info("UserService : Entity has been converted to Account DTO");
        }else {
            LOGGER.warn("UserService : User value is null ");
        }
        return account;
    }

    private SenderDTO entityToSenderDto(UserEntity user)
    {
        SenderDTO sender = null;
        if(user != null)
        {
            sender = new SenderDTO();
            if(user.getUserId() != 0L)
            {
                sender.setId((int) user.getUserId());
            }
            if (user.getUserName() != null)
            {
                sender.setLogin(user.getUserName());
            }
            LOGGER.info("UserService : Entity has been converted to Sender DTO");
        } else {
            LOGGER.warn("UserService : User value is null ");
        }
        return sender;
    }


}

