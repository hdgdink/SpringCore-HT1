package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.dao.impl.AuditoriumDao;
import kz.epam.spring.hometask1.domain.Auditorium;
import kz.epam.spring.hometask1.service.AbstractDomainService;
import kz.epam.spring.hometask1.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service
@ComponentScan
public class AuditoriumServiceImpl implements AuditoriumService, AbstractDomainService<Auditorium> {
    @Autowired
    private AuditoriumDao auditoriumDao;

    public AuditoriumServiceImpl() {
    }

    public AuditoriumServiceImpl(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }

    @Override
    public Auditorium save(@Nonnull Auditorium object) {
        auditoriumDao.addObject(object);
        return object;
    }

    @Override
    public void remove(@Nonnull Auditorium object) {
        auditoriumDao.removeObject(object);
    }

    @Override
    public Auditorium getById(@Nonnull Long id) {
        return auditoriumDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Auditorium> getAll() {
        return auditoriumDao.getAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDao.getByName(name);
    }

    public void addAuditorium(Auditorium auditorium) {
        auditoriumDao.addObject(auditorium);
    }
}