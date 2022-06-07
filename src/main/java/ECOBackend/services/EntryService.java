package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.EntryDto;
import ECOBackend.model.Entry;
import ECOBackend.model.Publication;
import ECOBackend.model.user.User;
import ECOBackend.repo.EntryRepo;
import ECOBackend.repo.PublicationRepo;
import ECOBackend.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryService {
    private final EntryRepo entryRepo;
    private final UserRepo userRepo;
    private final PublicationRepo publicationRepo;
    private final ModelMapper modelMapper;

    public EntryService(EntryRepo entryRepo, UserRepo userRepo, PublicationRepo publicationRepo, ModelMapper modelMapper) {
        this.entryRepo = entryRepo;
        this.userRepo = userRepo;
        this.publicationRepo = publicationRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public EntryDto update(EntryDto dto) {
        Entry entry = new Entry();
        if (dto.getUser()!=null && dto.getUser().getUserId()!=null &&
                userRepo.existsByUserId(dto.getUser().getUserId())) {
            entry.setUser(userRepo.findOneByUserId(dto.getUser().getUserId()).get());
        }
        if (dto.getPublication()!=null && dto.getPublication().getId()!=null &&
                publicationRepo.existsById(dto.getPublication().getId())) {
            entry.setPublication(publicationRepo.findById(dto.getPublication().getId()).get());
        }
        entry.setStatus(dto.getStatus());
        return modelMapper.map(entryRepo.save(entry), EntryDto.class);
    }

    @Transactional
    public OperationResponse delete(String userId, Long publicationId) {
        User u;
        Publication p;
        if (userRepo.existsByUserId(userId)) {
            u = userRepo.findOneByUserId(userId).get();
        }
        else {
            throw new NotFoundException("Пользователь не найден");
        }
        if (publicationRepo.existsById(publicationId)) {
            p = publicationRepo.findById(publicationId).get();
        }
        else {
            throw new NotFoundException("Пользователь не найден");
        }
        entryRepo.deleteByUserAndPublication(u, p);
        return new OperationResponse("ok");
    }

    public List<EntryDto> getAllByUser(String userId) {
        User u;
        if (userRepo.existsByUserId(userId)) {
            u = userRepo.findOneByUserId(userId).get();
            return u.getEntries().stream()
                    .map(e -> modelMapper.map(e, EntryDto.class))
                    .collect(Collectors.toList());
        }
        throw new NotFoundException("Пользователь не найден");
    }

    public List<EntryDto> getAllByPublication(Long publicationId) {
        Publication p;
        if (publicationRepo.existsById(publicationId)) {
            p = publicationRepo.findById(publicationId).get();
            return p.getEntries().stream()
                    .map(e -> modelMapper.map(e, EntryDto.class))
                    .collect(Collectors.toList());
        }
        throw new NotFoundException("Публикация не найдена");
    }
}
