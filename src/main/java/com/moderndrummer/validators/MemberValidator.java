package com.moderndrummer.validators;

import org.springframework.stereotype.Component;

import com.moderndrummer.entity.Member;
import com.moderndrummer.util.ObjectUtil;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

@Component
public class MemberValidator {

    public boolean isUpdatebleMember(Member member) {
        if (!validateBasicAttributes(member)) {
            return false;
        }
        if (!StringUtilValidator.validatePhoneNr(member.getPhoneNumber())) {
            return false;
        }
        if (!ObjectUtil.isValidId(member)) {
            return false;
        }

        return true;
    }

    public boolean isInsertableMember(Member member) {
        if (!validateBasicAttributes(member)) {
            return false;
        }
        return true;
    }

    private boolean validateBasicAttributes(Member member) {
        if (!StringUtilValidator.validateEmail(member.getEmail())) {
            return false;
        }
        if (StringUtilValidator.isEmptyOrNull(member.getPassword())) {
            return false;
        }
        if (StringUtilValidator.isEmptyOrNull(member.getName())) {
            return false;
        }
        return true;
    }

}
