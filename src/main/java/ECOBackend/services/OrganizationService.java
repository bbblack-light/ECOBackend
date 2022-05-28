package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.OrganizationDto;
import ECOBackend.dto.UserDto;
import ECOBackend.model.Organization;
import ECOBackend.model.user.User;
import ECOBackend.repo.OrganizationRepo;
import ECOBackend.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationService {
    private final UserRepo userRepo;
    private final OrganizationRepo organizationRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public OrganizationService(UserRepo userRepo, OrganizationRepo organizationRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.organizationRepo = organizationRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public OrganizationDto update(OrganizationDto dto) {
        Organization fromrepo;
        if (dto.getId() == null){
            Organization organization = modelMapper.map(dto, Organization.class);
            organization.setUsers(null);
            fromrepo = organizationRepo.save(organization);
            fromrepo.setUsers(new ArrayList<>());
        }
        else {
            if (!organizationRepo.existsById(dto.getId())) throw new NotFoundException("Класс не существует");
            fromrepo = organizationRepo.getById(dto.getId());
        }


        if (CollectionUtils.isEmpty(dto.getUsers()))
            dto.setUsers(new ArrayList<>());
        if (CollectionUtils.isEmpty(fromrepo.getUsers()))
            fromrepo.setUsers(new ArrayList<>());

        List<User> removeUserFromOrganization = new ArrayList<>();
        for (User user : fromrepo.getUsers()) {
            if (dto.getUsers().stream()
                    .noneMatch(u -> Objects.equals(u.getUserId(), user.getUserId()))) {
                removeUserFromOrganization.add(user);
                user.setOrganization(null);
                userRepo.save(user);
            }
        }
        fromrepo.getUsers().removeAll(removeUserFromOrganization);


        for (UserDto userDto : dto.getUsers()) {
            if (fromrepo.getUsers().stream()
                    .noneMatch(u -> Objects.equals(u.getUserId(), userDto.getUserId()))) {
                Optional<User> userOptional = userRepo.findOneByUserId(userDto.getUserId());
                if (!userOptional.isPresent()) throw new NotFoundException("Пользователь с userId = " + userDto.getUserId() + " не найден");

                User user = userOptional.get();
                user.setOrganization(fromrepo);
                user = userRepo.save(user);
                fromrepo.getUsers().add(user);
            }
        }

        Organization save = organizationRepo.save(fromrepo);
        return modelMapper.map(organizationRepo.getById(save.getId()), OrganizationDto.class);
    }

    @Transactional
    public OperationResponse delete(long id) {
        if (!organizationRepo.existsById(id)) {
            return new OperationResponse("Организация не существует");
        }
        Organization organization = organizationRepo.getById(id);
        organizationRepo.delete(organization);
        return new OperationResponse("ok");
    }

    @Transactional
    public OrganizationDto getById(long id) {
        if (!organizationRepo.existsById(id)) {
            throw new NotFoundException("Организация не существует");
        }
        return modelMapper.map(organizationRepo.getById(id), OrganizationDto.class);
    }

    @Transactional
    public List<OrganizationDto> getAll() {
        return organizationRepo.findAll().stream()
                .map(x -> modelMapper.map(x, OrganizationDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public OrganizationDto getByUser(String userId) {
        Optional<User> userOptional = userRepo.findOneByUserId(userId);
        if (!userOptional.isPresent()) throw new NotFoundException("Пользователь с userId = " + userId + " не найден");
        User user = userOptional.get();
        if (user.getOrganization()!=null) return modelMapper.map(user.getOrganization(), OrganizationDto.class);
        return null;
    }
}
