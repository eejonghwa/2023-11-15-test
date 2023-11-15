package com.korea.test99;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;

    public List<Notebook> getNotebookList() {
        return notebookRepository.findAll();
    }

    public Notebook getNotebookById(Long notebookId) {
        Optional<Notebook> notebookOptional = notebookRepository.findById(notebookId);
        if(notebookOptional.isPresent()){
            return notebookOptional.get();
        }
        throw new IllegalArgumentException("해당 노트북은 존재하지 않습니다.");
    }

    public Notebook saveDefaultNotebook() {
        Notebook notebook = new Notebook();
        notebook.setName("새노트");
        notebook.setCreateDate(LocalDateTime.now());

        notebookRepository.save(notebook);
        return notebook;
    }
}
