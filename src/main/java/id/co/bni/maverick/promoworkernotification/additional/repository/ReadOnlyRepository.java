package id.co.bni.maverick.promoworkernotification.additional.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);
}
