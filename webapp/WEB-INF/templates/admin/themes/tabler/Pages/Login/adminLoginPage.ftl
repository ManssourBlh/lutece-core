<#-- Macro: adminLoginPage
Description: Generates the login page for the admin dashboard, with a custom background image and site name. The macro includes a script that randomly selects a background image from a list of images.
Parameters:
- title (string, optional): the title to display on the login page.
- site_name (string, optional): the name of the site to display on the login page.
- layout (string, optional): the name of the site to display on the login page.
-->
<#macro adminLoginPage title='' site_name='LUTECE' layout=''>
<#local loginLayout=layout /> 
<#local loginLayoutImg='' /> 
<#if loginLayout?trim =''>
<#local loginLayout=dskey('portal.site.site_property.layout.login')?trim /> 
<#local loginLayoutImg=dskey('portal.site.site_property.layout.login.image')?trim /> 
</#if>
</head>
<body class="antialiased d-flex flex-column">
<#switch loginLayout>
	<#case 'cover'>
		<div id="login-page" class="row g-0 flex-fill">
			<div class="col-12 col-lg-6 col-xl-4 d-flex flex-column justify-content-center">
		<#break>
	<#case 'illustration'>
		<div id="login-page" class="page page-center">
			<div class="container container-normal py-4">
				<div class="row align-items-center g-4">
					<div class="col-lg">
						<div class="container-tight">
		<#break>
	<#default>	
		<div id="login-page" class="page page-center">
</#switch>
<#--  Content  -->
<@div class="container-tight py-4">
	<@div class="text-center mb-4">
		<@link href='.' target='_blank' >
			<figure>
				<@img url='#dskey{portal.site.site_property.logo_url}' alt='${site_name!}' title='${site_name!}' params=' height="36"' />
				<figcaption class="visually-hidden">#i18n{portal.admin.admin_login.gotoFO} ${site_name!'Lutece'} [ #i18n{portal.site.portal_footer.newWindow} ]</figcaption>
			</figure>
		</@link>
	</@div>
	<@div class="card card-md">
		<@div class="card-body bg-white">
			<h2 class="card-title text-center mb-4 pt-2">
				#i18n{portal.admin.admin_login.welcome}
				<span class="d-block">${site_name!}</span>
			</h2>
			<#nested>
		</@div>
	</@div>
</@div>
<#--  End content -->
<#switch loginLayout>
	<#case 'cover'>
		</div>
			<div class="col-12 col-lg-6 col-xl-8 d-none d-lg-block">
				<div class="bg-cover h-100 min-vh-100" style="background-image: url( ${loginLayoutImg} ) "></div>
			</div>  
		</div>
		<#break>
	<#case 'illustration'>
						</div>
					</div>
					<div class="col-lg d-none d-lg-block">
						<#--  <img src="${loginLayoutImg}" height="600" class="d-block mx-auto" alt="">  -->
						<div id="illustration" class="h-100 min-vh-100 p-5 mt-5" style="background-repeat: no-repeat"></div>
					</div>
				</div>
			</div>
		</div>
		<#break>
	<#default>	
		</div>
</#switch>
<script type="module">
import {
	LutecePassword
} from './js/modules/lutecePassword.js';
import {
	LuteceLogin
} from './js/modules/luteceLogin.js';

const login = new LuteceLogin();
const password = new LutecePassword();

document.addEventListener( "DOMContentLoaded", function(){
	/* backGround image random */
	const randomPageBackgoundImages = [ #dskey{portal.site.site_property.back_images} ];
	const loginPage = document.querySelector( '#login-page' );
	login.setRandomBackground( randomPageBackgoundImages, loginPage )
	<#if loginLayout != 'default' >
		const randomPanelBackgoundImages = [ #dskey{portal.site.site_property.layout.login.image} ];
		const illust = <#if loginLayout == 'cover' >document.querySelector( '.bg-cover' )<#else>document.querySelector( '#illustration' )</#if>;
		login.setRandomBackground( randomPanelBackgoundImages, illust )
	</#if>	
	/* Password */
	const inputPassword = document.querySelector('#password');
	const toggler = document.querySelector('#lutece-password-toggler');
	toggler.addEventListener( 'click', (evt) => {
		evt.preventDefault();
		password.showHidePassword( inputPassword, toggler ); 
	});
});
</script>
</#macro>