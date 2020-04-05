/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.moderndrummer.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moderndrummer.dao.MemberDao;
import com.moderndrummer.model.Member;
import com.moderndrummer.util.ObjectUtil;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

@Controller
@RequestMapping(value = "/videos")
public class VideosController {
	@Autowired
	private MemberDao memberDao;
	
	/***
	 * #TODO
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String displayVideos(Model model, HttpServletRequest request) {
		Member loggedMember = (Member) request.getSession().getAttribute("loggedUser");
		if (ObjectUtil.verifyMemberExists(loggedMember)) {
			return "videos";
		} else {
			return "redirect:login";
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public String postVideo(BindingResult result, Model model) {

		return "videos";
	}
}
