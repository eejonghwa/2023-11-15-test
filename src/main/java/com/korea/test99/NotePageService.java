package com.korea.test99;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotePageService {
    private final NotePageRepository notePageRepository;

    public List<NotePage> getNotePageList() {
        return notePageRepository.findAll();
    }


    public void save(NotePage notePage) {
        notePageRepository.save(notePage);
    }

    public NotePage getNotePageById(Long id) {
        Optional<NotePage> notePageOptional = notePageRepository.findById(id);
        if (notePageOptional.isPresent()) {
            return notePageOptional.get();
        }
        throw new IllegalArgumentException("해당 페이지가 존재하지 않습니다.");
    }

    public void saveDefaultNotePage(Notebook notebook) {
        NotePage notePage = new NotePage();
        notePage.setTitle("new title..");
        notePage.setContent("");
        notePage.setNotebook(notebook);
        notePage.setCreateDate(LocalDateTime.now());

        notePageRepository.save(notePage);
    }

    public void deleteById(Long id) {
        notePageRepository.deleteById(id);
    }
}
