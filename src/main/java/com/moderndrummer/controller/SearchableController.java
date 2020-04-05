package com.moderndrummer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moderndrummer.dao.MemberDao;
import com.moderndrummer.model.Member;
import com.moderndrummer.searchdomain.SearchModel;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Controller
public class SearchableController {

    @Autowired
    private MemberDao memberDao;

    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/searchable", method = RequestMethod.GET)
    public String displaySearchPage(Model model) {
        model.addAttribute("searchModel", new SearchModel());
        model.addAttribute("members", new LinkedHashSet<Member>());
         return "searchable";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

    @RequestMapping(value = "/searchable",method = RequestMethod.POST)
    public String searchMembers(
            @Valid @ModelAttribute("searchModel") SearchModel searchModel,
            BindingResult result, Model model) {
        Date fromDate = searchModel.getFromDate();
        Date toDate = searchModel.getToDate();
        List<Member> members = memberDao.findAllCreatedMembersByFromAndToDate(
                fromDate, toDate);
        if (members.isEmpty()) {
            model.addAttribute("members", new LinkedHashSet<Member>());
        } else {
            model.addAttribute("members", members);
        }
        return "searchable";

    }

}
