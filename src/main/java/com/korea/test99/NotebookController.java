package com.korea.test99;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notebook")
public class NotebookController {
    private final NotebookService notebookService;
    private final NotePageService notePageService;

    @RequestMapping("/{notebookId}")
    public String select(Model model, @PathVariable("notebookId") Long notebookId) {

        List<Notebook> notebookList = notebookService.getNotebookList();
        Notebook targetNotebook = notebookService.getNotebookById(notebookId);


        model.addAttribute("notebookList", notebookList);
        model.addAttribute("notePageList", targetNotebook.getNotePageList());
        model.addAttribute("targetNotebook", targetNotebook);
        model.addAttribute("targetNotePage", targetNotebook.getNotePageList().get(0));

        return "main";
    }

    @RequestMapping("/")
    public String main(Model model) {
        List<Notebook> notebookList = notebookService.getNotebookList();
        List<NotePage> notePageList = notePageService.getNotePageList();
        if(notebookList.isEmpty()){
            notebookService.saveDefaultNotebook();
        }
        Notebook targetNotebook = notebookList.get(0);

        if (notePageList.isEmpty()) {
            notePageService.saveDefaultNotePage(targetNotebook);
            return "redirect:/";
        }
        model.addAttribute("notebookList", notebookList);
        model.addAttribute("notePageList", targetNotebook.getNotePageList());
        model.addAttribute("targetNotebook",targetNotebook);
        model.addAttribute("targetNotePage", notePageList.get(0));

        return "main";
    }

    @PostMapping("/write")
    public String write(){
        Notebook notebook = notebookService.saveDefaultNotebook();
        notePageService.saveDefaultNotePage(notebook);
        return "redirect:/notebook/" + notebook.getId();
    }
}
