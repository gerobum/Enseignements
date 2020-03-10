package fr.miage.web.controller;

import fr.miage.core.entity.Message;
import fr.miage.core.service.MessageService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/message")
public class MessageController {


    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)

    public String findAll(Model model) {

        model.addAttribute("messages", messageService.findAll());
        return "message/list";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Message message = new Message();
        model.addAttribute("message", message);
        return "message/edit";
    }

    @PostMapping("/create")
    public String created(@Valid Message message, BindingResult br) {

        if (br.hasErrors()) {

            return "message/edit";
        }
        messageService.save(message);

        return "redirect:/message";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("message", messageService.findById(id).get());
        return "message/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Message message, BindingResult br) {
        if (br.hasErrors()) {
            return "message/edit";
        }

        messageService.save(message);
        return "redirect:/message";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        messageService.delete(id);
        return "redirect:/message";
    }
}
