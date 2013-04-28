/*
 * Copyright (c) 2002-2012, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.portal.web.security;

import fr.paris.lutece.portal.business.rbac.RBAC;
import fr.paris.lutece.portal.business.security.PublicUrlParameterHome;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.rbac.RBACService;
import fr.paris.lutece.portal.service.security.PublicUrlResourceIdService;
import fr.paris.lutece.portal.service.security.PublicUrlService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.web.admin.AdminFeaturesPageJspBean;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.html.HtmlTemplate;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * PublicUrlJspBean used for managing Public Url
 *
 */
public class PublicUrlJspBean extends AdminFeaturesPageJspBean
{
    /**
    *
    */
    public static final String RIGHT_MANAGE = "CORE_PUBLIC_URL_MANAGEMENT";
    private static final long serialVersionUID = -669562727518395523L;

    // Parameters
    private static final String PARAMETER_CANCEL = "cancel";
    private static final String PARAMETER_PUBLIC_LIST_URL = "public_list_url";

    // Jsp url
    private static final String JSP_MANAGE_SECURITY = "ManagePublicUrl.jsp";

    // Properties
    private static final String PROPERTY_MANAGE_PUBLIC_URL_PAGETITLE = "portal.security.manage_public_url.pageTitle";

    // Template
    private static final String TEMPLATE_MANAGE_PUBLIC_URL = "admin/security/manage_public_url.html";

    /**
     * Builds the advanced parameters management page
     * @param request the HTTP request
     * @return the built page
     */
    public String getManageAdvancedParameters( HttpServletRequest request )
    {
        if ( !RBACService.isAuthorized( PublicUrlService.RESOURCE_TYPE, RBAC.WILDCARD_RESOURCES_ID,
                    PublicUrlResourceIdService.PERMISSION_MANAGE, getUser(  ) ) )
        {
            return AdminMessageService.getMessageUrl( request, Messages.USER_ACCESS_DENIED, AdminMessage.TYPE_STOP );
        }

        setPageTitleProperty( PROPERTY_MANAGE_PUBLIC_URL_PAGETITLE );

        Map<String, Object> model = PublicUrlService.getInstance(  ).getManageAdvancedParameters( getUser(  ), request );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MANAGE_PUBLIC_URL, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }

    /**
     * Processes the data capture form of advanced parameters
     * @param request the HTTP request
     * @return the jsp URL of the process result
     * @throws AccessDeniedException if permission to manage advanced parameters
     * on security service has not been granted to the user
     */
    public String doModifyAdvancedParameters( HttpServletRequest request )
        throws AccessDeniedException
    {
        if ( !RBACService.isAuthorized( PublicUrlService.RESOURCE_TYPE, RBAC.WILDCARD_RESOURCES_ID,
                    PublicUrlResourceIdService.PERMISSION_MANAGE, getUser(  ) ) )
        {
            throw new AccessDeniedException( "User " + getUser(  ) + " is not authorized to permission " +
                PublicUrlResourceIdService.PERMISSION_MANAGE );
        }

        if ( ( request.getParameter( PARAMETER_CANCEL ) == null ) && ( validateFormSubmit( request ) == null ) )
        {
            PublicUrlParameterHome.remove( PublicUrlService.PUBLIC_URL_PARAMETER );

            String[] tabPublicListUrl = request.getParameterValues( PARAMETER_PUBLIC_LIST_URL );
            ReferenceItem paramPublicUrl = new ReferenceItem(  );
            paramPublicUrl.setCode( PublicUrlService.PUBLIC_URL_PARAMETER );

            for ( int i = 0; i < tabPublicListUrl.length; i++ )
            {
                if ( !StringUtils.isBlank( tabPublicListUrl[i] ) )
                {
                    paramPublicUrl.setName( tabPublicListUrl[i] );
                    PublicUrlParameterHome.create( paramPublicUrl );
                }
            }
        }

        return JSP_MANAGE_SECURITY;
    }

    /**
     * Validate Form Submit
     * @param request the HTTP request
     * @return true if the form submit is validate
     */
    private String validateFormSubmit( HttpServletRequest request )
    {
        return null;
    }
}
