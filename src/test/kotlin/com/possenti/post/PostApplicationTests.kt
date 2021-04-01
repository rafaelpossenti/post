package com.possenti.post

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest

class PostApplicationTests {

	@Test
	fun contextLoads() {
	}

//	@Test
//	fun getAccessTokenViaSpringSecurityOAuthClient() {
//		try {
//			val resourceDetails = ClientCredentialsResourceDetails()
//			resourceDetails.clientSecret = TestOAuthConstants.CLIENT_SECRET
//			resourceDetails.clientId = TestOAuthConstants.CLIENT_ID
//			resourceDetails.accessTokenUri = TestOAuthConstants.TOKEN_REQUEST_URL
//			resourceDetails.scope = TestOAuthConstants.SCOPES
//			val oAuthRestTemplate = OAuth2RestTemplate(resourceDetails)
//			val headers = HttpHeaders()
//			headers.contentType = MediaType.APPLICATION_JSON
//			val token = oAuthRestTemplate.accessToken
//			println(oAuthRestTemplate.resource)
//			println(oAuthRestTemplate.oAuth2ClientContext)
//			println(token)
//			assertTrue(token != null)
//		} catch (e: Exception) {
//			e.printStackTrace()
//		}
//	}

}
