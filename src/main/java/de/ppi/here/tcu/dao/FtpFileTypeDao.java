package de.ppi.here.tcu.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import de.ppi.here.tcu.entity.FtpFileType;


@Repository
public class FtpFileTypeDao implements Dao<FtpFileType> {

    @Override
    public Optional<FtpFileType> findById(final Integer id) {
        return Optional.of(new FtpFileType());
    }

    @Override
    public FtpFileType makePersistent(final FtpFileType entity) {
        return entity;
    }
}
