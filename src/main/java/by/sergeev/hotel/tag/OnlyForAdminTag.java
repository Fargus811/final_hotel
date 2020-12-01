package by.sergeev.hotel.tag;

import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.entity.SessionUser;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * The type Only for admin tag.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class OnlyForAdminTag extends BodyTagSupport {

    @Override
    public int doStartTag() throws JspException {
        SessionUser sessionUser = (SessionUser) pageContext.getSession().getAttribute(PageParameter.SESSION_USER);
        if (sessionUser != null && sessionUser.getRole().ordinal() == 1) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
}

