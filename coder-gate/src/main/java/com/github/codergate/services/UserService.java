package com.github.codergate.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.dto.installation.AccountDTO;
import com.github.codergate.dto.push.SenderDTO;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /***
     * adds the value into the table using entity and returns the values in dto
     * class during installation event
     * 
     * @param userToAdd dto class
     * @return dto class with user values
     */
    public AccountDTO addUser(AccountDTO userToAdd) {
        AccountDTO account = null;
        UserEntity userEntity = convertAccountDtoToEntity(userToAdd);
        if (userEntity != null) {
            UserEntity savedEntity = userRepository.save(userEntity);
            LOGGER.info("addUser : The user information is added {}", savedEntity);
            account = convertEntityToAccountDto(savedEntity);
        }
        return account;
    }

    /***
     * adds user to the table during push event
     * 
     * @param userId,   login, userEmail
     * @param userEmail email of User in String format
     * @return SenderDTO Object
     */
    public UserEntity addUser(Integer userId, String login, String userEmail) {
        SenderDTO sender = null;
        UserEntity userEntity = convertSenderDtoToEntity(userId, login, userEmail);
        if (userEntity != null) {
            UserEntity savedEntity = userRepository.save(userEntity);
            LOGGER.info("addUser : The user information email is added {}", savedEntity);
        }
        return userEntity;
    }

    /***
     * Gets the user information from the table using id
     * 
     * @param userId user id
     * @return dto class with user information
     */

    public AccountDTO getUserById(Long userId) {
        AccountDTO account = null;
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            account = convertEntityToAccountDto(userEntity.get());
            LOGGER.info("getUserById : Successfully retrieved account with ID: {}", userId);
        } else {
            LOGGER.warn("getAccountById: Account with ID: {} not found", userId);
        }
        return account;
    }

    /***
     * updates the user information using userId
     * 
     * @param userId userId
     * @return dto class
     */
    public AccountDTO updateUserById(Long userId) {
        AccountDTO accountDto = null;
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if (userEntity != null) {
            UserEntity savedEntity = userRepository.save(userEntity);
            LOGGER.info("updateUserById : Updating the user information");
            accountDto = convertEntityToAccountDto(savedEntity);
        }
        return accountDto;
    }

    /***
     * deletes user row by id
     * 
     * @param userId user_id
     * @return true or false
     */
    public boolean deleteUserByID(Long userId) {
        boolean isDeleted = false;
        if (userId != null) {
            userRepository.deleteById(userId);
            isDeleted = true;
            LOGGER.info("deleteUserByID : Deleting the user information {}", userId);
        }
        return isDeleted;
    }

    /***
     * converts AccountDTO to User Entity
     * 
     * @param accountDTO dto, id and name check and set.
     * @return entity
     */
    public UserEntity convertAccountDtoToEntity(AccountDTO accountDTO) {
        UserEntity userEntity = null;
        if (accountDTO != null) {
            userEntity = new UserEntity();
            if (accountDTO.getId() != null) {
                userEntity.setUserId(accountDTO.getId());
            }
            if (accountDTO.getLogin() != null) {
                userEntity.setUserName(accountDTO.getLogin());
            }
            LOGGER.info("convertAccountDtoToEntity : Converted AccountDTO to Entity {}", userEntity);
        } else {
            LOGGER.warn("convertAccountDtoToEntity : AccountDTO value is null");
        }
        return userEntity;
    }

    /***
     * converts SenderDTO to User Entity during push event
     * 
     * @param userEmail email of User in String format
     * @return User Entity
     */
    public UserEntity convertSenderDtoToEntity(Integer id, String login, String userEmail) {
        UserEntity userEntity = null;
        userEntity = new UserEntity();
        if (id != null) {
            userEntity.setUserId(id);
        }
        if (login != null) {
            userEntity.setUserName(login);
        }
        if (userEmail != null) {
            userEntity.setEmail(userEmail);
        }
        LOGGER.info("convertSenderDtoToEntity : Converted SenderDTO to Entity {}", userEntity);

        return userEntity;
    }

    /***
     * converts User entity to AccountDTO
     * 
     * @param userEntity user entity
     * @return AccountDTO
     */
    public AccountDTO convertEntityToAccountDto(UserEntity userEntity) {
        AccountDTO accountDTO = null;
        if (userEntity != null) {
            accountDTO = new AccountDTO();
            if (userEntity.getUserId() != 0L) {
                accountDTO.setId((int) userEntity.getUserId());
            }
            if (userEntity.getUserName() != null) {
                accountDTO.setLogin(userEntity.getUserName());
            }
            LOGGER.info("convertEntityToAccountDto : Entity has been converted to AccountDTO {}", accountDTO);
        } else {
            LOGGER.warn("convertEntityToAccountDto : User entity value is null");
        }
        return accountDTO;
    }

    public String getUserDetails(String githubAccessToken) throws IOException {

        String urlString = "https://api.github.com/user";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + githubAccessToken);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        return "";
    }

    public String getPublicUserDetails(String userId) throws IOException {

        // Create a URL object with the GitHub API endpoint for retrieving user
        // information by ID
        URL url = new URL("https://api.github.com/user/" + userId);

        // Create an HttpURLConnection object to send the GET request
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Set the User-Agent header to identify your application to the GitHub API
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Check the response code to ensure the request was successful
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response from the API into a BufferedReader object
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } else {
            return null;
        }
    }
}
