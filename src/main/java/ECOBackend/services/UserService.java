package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.UserDto;
import ECOBackend.dto.UserWithOrganizationDto;
import ECOBackend.model.user.Role;
import ECOBackend.model.user.User;
import ECOBackend.repo.OrganizationRepo;
import ECOBackend.repo.UserRepo;
import com.google.common.base.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final OrganizationRepo organizationRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepo userRepo, OrganizationRepo organizationRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.organizationRepo = organizationRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UserDto getUserInformation(String userIdParam) {
        String loggedInUserId = getLoggedInUserId();
        User user;
        if (Strings.isNullOrEmpty(userIdParam)) {
            user = getLoggedInUser();
        } else if (loggedInUserId.equals(userIdParam)) {
            user = getLoggedInUser();
        } else {
            user = getUserInfoByUserId(userIdParam);
        }
        if (user == null) {
            user =  new User(
                    "",
                    "",
                    "",
                    "",
                    Role.USER);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Transactional
    public UserWithOrganizationDto getUserInformationWithOrganization(String userIdParam) {
        String loggedInUserId = getLoggedInUserId();
        User user;
        if (Strings.isNullOrEmpty(userIdParam)) {
            user = getLoggedInUser();
        } else if (loggedInUserId.equals(userIdParam)) {
            user = getLoggedInUser();
        } else {
            user = getUserInfoByUserId(userIdParam);
        }
        if (user == null) {
            user =  new User(
                    "",
                    "",
                    "",
                    "",
                    Role.USER);
        }
        return modelMapper.map(user, UserWithOrganizationDto.class);
    }

    public String getLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "nosession";
        }
        return auth.getName();
    }


    public User getLoggedInUser() {
        String loggedInUserId = this.getLoggedInUserId();
        return this.getUserInfoByUserId(loggedInUserId);
    }

    public User getUserInfoByUserId(String userId) {
        return this.userRepo.findOneByUserId(userId).orElse(null);
    }


    public User insertOrSaveUser(User user) {
        return this.userRepo.save(user);
    }

    public User addNewUser(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User newUser = this.getUserInfoByUserId(user.getUserId());
        if (newUser == null) {
            user.setRole(Role.USER);
            return this.insertOrSaveUser(user);
        } else {
            return null;
        }
    }

    public UserDto edit(UserDto user) {
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        User buffUser = this.getUserInfoByUserId(user.getUserId());
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            buffUser.setPassword(user.getPassword());
        }
        buffUser.setEmail(user.getEmail());
        buffUser.setFirstName(user.getFirstName());
        buffUser.setPhoneNumber(user.getPhoneNumber());
        buffUser.setRole(user.getRole());
        if (user.getOrganizationId()!=null)
            organizationRepo.findById(user.getOrganizationId())
                .ifPresent(buffUser::setOrganization);
        else buffUser.setOrganization(null);
        return modelMapper.map(this.insertOrSaveUser(buffUser), UserDto.class);
    }

    public List<UserDto> findAll() {
        return userRepo.findAll().stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
    }

    public OperationResponse delete(String userId) {
        Optional<User> user = userRepo.findOneByUserId(userId);
        if (user.isPresent()) {
           userRepo.delete(user.get());
            return new OperationResponse("ok");
        }
        throw new NotFoundException("Не найден пользователь");
    }
}

