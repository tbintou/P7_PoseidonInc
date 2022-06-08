package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;


@Controller
public class BidListController {
    // TODO: Inject Bid service

    private final BidListService bidListService;

    @Autowired
    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RolesAllowed({"ADMIN", "USER"})
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        List<BidList> bidLists = bidListService.findAll();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
    }

    @RolesAllowed("USER")
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @RolesAllowed("USER")
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list

        if(!result.hasErrors()) {
            bidListService.addBidList(bid);
            model.addAttribute("bidLists", bidListService.findAll());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form

        BidList bidList = bidListService.findById(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid

        if (result.hasErrors()) {
            return "bidList/update";
        }
        Boolean updated = bidListService.updateBidList(id, bidList);
        if(updated) {
            model.addAttribute("bidLists", bidListService.findAll());
        }
        return "redirect:/bidList/list";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list

        BidList bidList = bidListService.findById(id);
        if (bidList != null) {
            bidListService.deleteById(id);
            model.addAttribute("bidLists", bidListService.findAll());
            return "redirect:/bidList/list";
        } else {
            throw new IllegalArgumentException("Invalid bibList id : " + id);
        }
    }
}
