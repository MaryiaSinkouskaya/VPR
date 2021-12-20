package com.vpr.app.service.impl;

import com.vpr.app.entity.Address;
import com.vpr.app.entity.Proband;
import com.vpr.app.repository.ProbandRepository;
import com.vpr.app.service.ProbandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProbandServiceImpl implements ProbandService {
    private final ProbandRepository probandRepository;

    public int countByGender(String gender) {
            return probandRepository.countByGender(gender);
    }

    @Override
    public Proband findById(long id) {
        return probandRepository.findById(id).orElse(new Proband());
    }

    @Override
    public List<Proband> findAll() {
        return probandRepository.findAll();
    }

    @Override
    public Proband create(Proband proband) {
        return probandRepository.save(proband);
    }

    @Override
    public Proband update(Proband proband) {
        return probandRepository.save(proband);
    }

    @Override
    public void delete(long id) {
        probandRepository.deleteById(id);
    }
}
