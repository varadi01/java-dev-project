package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Viewer;
import com.epam.training.ticketservice.Repository.ViewerRepository;
import org.springframework.stereotype.Service;

@Service
public class ViewerService {
    private final ViewerRepository viewerRepository;

    public ViewerService(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    public void signupViewer(Viewer viewer) {
        viewerRepository.save(viewer);
    }

    public void signinViewer(Viewer viewer) {
        //TODO
    }

    public void describe(Viewer viewer) {
        //TODO
    }
}
