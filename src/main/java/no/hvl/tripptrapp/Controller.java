package no.hvl.tripptrapp;

import no.hvl.tripptrapp.Tripp.Spillet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    Spillet spillet;

    @GetMapping("/")
    public String forside(Model model){
        model.addAttribute("brett", spillet.getBrett());
        model.addAttribute("tur", spillet.getTur());
        model.addAttribute("ferdig", spillet.isFerdig());
        model.addAttribute("vinner", spillet.getVinner());
        return "index";
    }

    @GetMapping("/click/{id}")
    public String click(@PathVariable("id") int id){
        spillet.spillTur(id);
        return "redirect:/";
    }

    @GetMapping("/reset")
    public String reset(){
        spillet.resetBrett();
        return "redirect:/";
    }
}
