package ECOBackend.services;

import ECOBackend.controllers.utils.exception.NotFoundException;
import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.PlaceDto;
import ECOBackend.dto.PublicationDto;
import ECOBackend.model.Favorite;
import ECOBackend.model.Publication;
import ECOBackend.model.user.User;
import ECOBackend.repo.FavoriteRepo;
import ECOBackend.repo.PublicationRepo;
import ECOBackend.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    private final FavoriteRepo favoriteRepo;
    private final PublicationRepo publicationRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public FavoriteService(FavoriteRepo favoriteRepo, PublicationRepo publicationRepo, UserRepo userRepo, ModelMapper modelMapper) {
        this.favoriteRepo = favoriteRepo;
        this.publicationRepo = publicationRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    public OperationResponse update(String userId, Long publicationId) {
        Optional<User> user = userRepo.findOneByUserId(userId);
        if (!user.isPresent()) throw new NotFoundException("Пользователь не найден");

        Optional<Publication> publication = publicationRepo.findById(publicationId);
        if (!publication.isPresent()) throw new NotFoundException("Публикация не найдена");

        Optional<Favorite> favorite = favoriteRepo.findByPublicationAndUser(publication.get(), user.get());
        if(favorite.isPresent()) return new OperationResponse("Уже в избранном");

        Favorite f = new Favorite();
        f.setPublication(publication.get());
        f.setUser(user.get());
        favoriteRepo.save(f);
        return new OperationResponse("Добавлено в избранное");
    }

    @Transactional
    public OperationResponse delete(String userId, Long publicationId) {
        Optional<User> user = userRepo.findOneByUserId(userId);
        if (!user.isPresent()) throw new NotFoundException("Пользователь не найден");

        Optional<Publication> publication = publicationRepo.findById(publicationId);
        if (!publication.isPresent()) throw new NotFoundException("Публикация не найдена");

        Optional<Favorite> favorite = favoriteRepo.findByPublicationAndUser(publication.get(), user.get());
        favorite.ifPresent(favoriteRepo::delete);
        return new OperationResponse("ok");
    }

    @Transactional
    public OperationResponse delete(long id) {
        if (favoriteRepo.existsById(id)) {
            Favorite place = favoriteRepo.getById(id);
            favoriteRepo.delete(place);
        }
        return new OperationResponse("ok");
    }

    @Transactional
    public List<PublicationDto> getAllByUser(String userId) {

        Optional<User> user = userRepo.findOneByUserId(userId);
        if (!user.isPresent()) throw new NotFoundException("Пользователь не найден");

        return favoriteRepo.findAllByUser(user.get()).stream()
                .map(x -> modelMapper.map(x.getPublication(), PublicationDto.class))
                .collect(Collectors.toList());
    }

}
