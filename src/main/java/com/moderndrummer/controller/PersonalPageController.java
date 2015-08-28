package com.moderndrummer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.moderndrummer.data.MemberDao;
import com.moderndrummer.entity.exceptions.ModernDrummerException;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.model.Member;
import com.moderndrummer.util.ObjectUtil;
import com.moderndrummer.validators.MemberValidator;
/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Controller
public class PersonalPageController {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	MemberValidator memberValidator;

	private MessageSource messageSource;

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public String displayPersonalPage(ModelMap model,HttpServletRequest request) {
			Member loggedMember = (Member) request.getSession().getAttribute("loggedUser");
			if(ObjectUtil.verifyMemberExists(loggedMember)){
				Member member = memberDao.findById(loggedMember.getId());
				model.addAttribute("personalModel", member);
				model.addAttribute("member", member);
				model.addAttribute("userName", member.getName());
				return "personal";
			}
			else{
				return "redirect:login";
			}
	
		
	}

	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public String updateMember(@Valid @ModelAttribute("personalModel") Member updateMember, BindingResult result,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (!result.hasErrors()) {
			try {

				if (!ObjectUtil.isValidId(updateMember.getId())) {
					updateMember.setId(((Member) session.getAttribute("loggedUser")).getId());
				}
				if (ObjectUtil.nullElements(updateMember.getCreatedDate())) {
					updateMember.setCreatedDate(((Member) session.getAttribute("loggedUser")).getCreatedDate());
				}

				if (memberValidator.isUpdatebleMember(updateMember)) {

					Member updatedMember = memberDao.updateMember(updateMember);// userService.updateMember(updateMember);
					session.setAttribute("loggedUser", updatedMember);
					model.addAttribute("personalModel", updatedMember);

				} else {
					throw new ModernDrummerException(ModernDrummerMessages.FIELDS_MISSING);
				}

			} catch (ModernDrummerException e) {

				model.addAttribute("personalModel", session.getAttribute("loggedUser"));
				model.addAttribute("errorMessage", e.getMessage());
			}

			return "personal";

		} else {
			model.addAttribute("members", session.getAttribute("loggedUser"));
			return "personal";
		}
	}
}
