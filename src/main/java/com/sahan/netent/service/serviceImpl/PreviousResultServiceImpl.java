package com.sahan.netent.service.serviceImpl;

import com.sahan.netent.model.PreviousResult;
import com.sahan.netent.repository.PreviousResultRepository;
import com.sahan.netent.service.PreviousResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreviousResultServiceImpl implements PreviousResultService {


    private final PreviousResultRepository previousResultRepository;

    @Autowired
    public PreviousResultServiceImpl(PreviousResultRepository previousResultRepository) {
        this.previousResultRepository = previousResultRepository;
    }

    @Override
    public PreviousResult findPreviousResultByUniqueId(int id) {
        return previousResultRepository.findByUniqueId(id);
    }
}
