package com.moderndrummer.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moderndrummer.dao.MemberDao;
import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.exceptions.ModernDrummerException;
import com.moderndrummer.forms.models.RegisterFormModel;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.util.DateConverter;
import com.moderndrummer.validators.MemberValidator;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Controller("registerController")
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    MemberValidator memberValidator;

    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displaySortedMembers(Model model) {
        model.addAttribute("newMember", new RegisterFormModel());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("newMember") RegisterFormModel registerModel,
            BindingResult result, Model model, HttpServletRequest request) {
        if (!result.hasErrors()) {
            try {
                Member newMember = new Member();
                validateMember(newMember, registerModel);

                newMember.setId(0L);
                newMember.setCreatedDate(DateConverter.convertToTimeStamp(new Date(System.currentTimeMillis())));
                Member member = memberDao.register(newMember);
                HttpSession session = request.getSession();
                session.setAttribute("loggedUser", member);
                model.addAttribute("loggedInMember", member);
                return "redirect:personal?memberId=" + member.getId();

            } catch (ModernDrummerException e) {
                model.addAttribute("errorMessage", e.getMessage());
            }

            return "register";
        } else {

            return "register";
        }
    }

    private void validateMember(Member newMember, RegisterFormModel model) throws ModernDrummerException {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            throw new ModernDrummerException(ModernDrummerMessages.PASSWORD_NOT_EQUAL);
        }
        if (!model.getEmail().equals(model.getConfirmEmail())) {
            throw new ModernDrummerException(ModernDrummerMessages.EMAIL_NOT_EQUAL);
        }
        updateMember(newMember, model);
        if (!memberValidator.isInsertableMember(newMember)) {
            throw new ModernDrummerException(ModernDrummerMessages.FIELDS_INVALID);
        }
        Member foundMember = memberDao.findByEmailOrUsername(newMember.getName(), newMember.getEmail());
        if (foundMember.getId() > 0) {
            throw new ModernDrummerException(
                    ModernDrummerMessages.FIELDS_EXISTS + " " + newMember.getName() + " " + newMember.getEmail());
        }
    }

    private void updateMember(Member newMember, RegisterFormModel model) {
        newMember.setEmail(model.getEmail());
        newMember.setName(model.getName());
        newMember.setPassword(model.getPassword());
    }

}
