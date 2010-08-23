/*
 * Copyright (c) 2002-2010, Mairie de Paris
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
package fr.paris.lutece.portal.business.user.attribute;

import java.util.List;

import fr.paris.lutece.portal.service.spring.SpringContextService;

/**
 * 
 * AdminUserFieldHome
 *
 */
public class AdminUserFieldHome 
{
	private static IAdminUserFieldDAO _dao = (IAdminUserFieldDAO) SpringContextService.getBean( "adminUserFieldDAO" );

	/**
	 * Load the user field
	 * @param nIdUserField ID
	 * @return AdminUserField
	 */
	public static AdminUserField findByPrimaryKey( int nIdUserField )
	{
		return _dao.load( nIdUserField );
	}
		
	/**
	 * Insert a new user field
	 * @param userField the user field
	 */
	public static void create( AdminUserField userField )
	{
		_dao.insert( userField );
	}
	
	/**
	 * Update an user field 
	 * @param attribute the attribute
	 */
	public static void update( AdminUserField userField )
	{
		_dao.store( userField );
	}
	
	/**
	 * Delete an attribute
	 * @param nIdUserField the ID of the user field
	 */
	public static void remove( int nIdUserField )
	{
		_dao.delete( nIdUserField );
	}
	
	/**
	 * Delete all user fields from given id field
	 * @param nIdField id field
	 */
	public static void removeUserFieldsFromIdField( int nIdField )
	{
		_dao.deleteUserFieldsFromIdField( nIdField );
	}
	
	/**
	 * Delete all user fields from given id user
	 * @param nIdUser id user
	 */
	public static void removeUserFieldsFromIdUser( int nIdUser )
	{
		_dao.deleteUserFieldsFromIdUser( nIdUser );
	}
	
	/**
	 * Delete all user fields from given id attribute
	 * @param nIdAttribute id attribute
	 */
	public static void removeUserFieldsFromIdAttribute( int nIdAttribute )
	{
		_dao.deleteUserFieldsFromIdAttribute( nIdAttribute );
	}

	/**
	 * Load all the user field by a given ID user and a given ID attribute
	 * @param nIdUser the ID user
	 * @param nIdAttribute
	 * @return a list of adminuserfield
	 */
	public static List<AdminUserField> selectUserFieldsByIdUserIdAttribute( int nIdUser, int nIdAttribute )
	{
		return _dao.selectUserFieldsByIdUserIdAttribute( nIdUser, nIdAttribute );
	}
}
