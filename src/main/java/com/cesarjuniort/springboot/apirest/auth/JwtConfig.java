package com.cesarjuniort.springboot.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:application.properties")
public class JwtConfig {
 

	public static final String SING_SECRET_KEY = "A-very-secret-key-to-sing-and-verify-the-token";

	public static final String PRIVATE_RSA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEogIBAAKCAQEAoHqqefQ4CPCrXf2Go9qmEE8vVrVabqeDbMuvP+74qrq21QFp\r\n"
			+ "3G0yonrvohupuQ/Pvk28lIYcf3ghn2VLvqLLlPR0bddjwY5AbqefhoGfGTsEpnQz\r\n"
			+ "EikfZF36bFkLUU+PljiEtPzj1lRhf+APo7NsC0/JCxXOgmVBPYwJ6SJl/92+HP1x\r\n"
			+ "VwW5zmRozsAIwsbulOb+o2EclyvzM5uK4VkuIVkLlsdeR1j6TvbqIWc0IwkFoChd\r\n"
			+ "/DVKLHr7X8XoeGUTahwBBpIZF+FZkNJ8/bKMQ1yi0kDPuSHlhnvJ0L1+qLFG9K8n\r\n"
			+ "NXWuslJ6Nae7nN9RFUVlyi2P4zhG1LQQkWdHTQIDAQABAoIBAGYxdzouITjgOeCi\r\n"
			+ "wAi8BiBArL6kwi/REMpJcxJdlZDzUdaXDdjG2QXLD6rFvvEQdpSTYFGcja1GzPJ6\r\n"
			+ "SR/e/5Gdkj8YeHgbpEBTEi1PggdJJA7z1EnuUgVSK1Sv55sh9SgOWcP/fv9PwL7z\r\n"
			+ "6kGu0lDpv/cFLfqvjDorYWxK8XVbt7CiTMDLe0lWfPVuw3g216t2jUZ3E3NYXhPP\r\n"
			+ "ATnWHEh3i/4ztRhRbBEyiOpNn6BO+k7cJoNqNtRkGBMnzCkdmtq+hA2LUsI6q0eS\r\n"
			+ "/+l7gpenHmasVV1AvCFdD9ROqOJNrpUk0SK05YWxzHUFlLk3SQ/U4xITrDc5edgk\r\n"
			+ "VQ6EX6kCgYEAzq6oImsw3El+CDJNhvmul1EFIwbUrWtFJh/peg1W+PJhhWgc8teQ\r\n"
			+ "DAqZo/ZaD0oaK3AhYlAzljHKZAP7j46okYYPHYD1oSpqg+hNS+EhuRFTNey4IV6K\r\n"
			+ "KmP5zdtWBDRnZE/Jv1ZzQm+wAmIK6teY7f9FvTEVfM5o81/JUrkg+n8CgYEAxsWo\r\n"
			+ "fYJPvis+12RfBQMfI15xBd6ACBqd6Dmu98JVkbsk45f9ZMZe2HArmX0Ma6jjG51i\r\n"
			+ "CpSVDfmRV2J3EBiHUzVRj6Tf1MagbT4LB0x21yzsOg7+7wvnbciJtLJX5xhlggCt\r\n"
			+ "klJqfg7LItop3UE1Pyhp28/ON+R/63lYPW26oDMCgYB4pzj7rrt9PWHXgxYC/2rO\r\n"
			+ "Zruq8OfivMZHNUXkvDjTZxtK97Y+hVxPyzhZoQx99HJGfVTfwZX7zeEqdbC64zrA\r\n"
			+ "iRP10zyQ8uPvUQMekrRPBzAeQKxUvo3FpUtEww/5sGXTB0js65ipZe3H1lgtM+LQ\r\n"
			+ "vL6HbUnv68cUnAw4wF3iHQKBgARXFom/Cay2VwXu/CAo5Eoqar9dD2Cd7CrA77Ab\r\n"
			+ "PzESbAjILdFAf5usP1d3oojLREI7GoPuoJ6Qf3rv/HeOgY3wkRpY2EZWG/SmsSj0\r\n"
			+ "o3epVHMkFq7zjJcaFN+sMT0rjDPdfey+/fJIsmjNaLKbFOWPJViWXgNcauqiWKnm\r\n"
			+ "tnd/AoGAIRGZCLt/gBSD0ZTV7elN6JoxP0ykXljF7IXvBlZKhcXeUKtATlQ3ZflQ\r\n"
			+ "supjTINqwNUv1QiKEfTU/QGTmg69IEHtAO6pq/NrCeg1SmAKUD6XsFnNKtK9/4Xf\r\n"
			+ "OHpezkE0AP7WUN57eF40iEPe2jI5xfL04E39v8ZBfrXkPXkQwQQ=\r\n" + "-----END RSA PRIVATE KEY-----";

	public static final String PUBLIC_RSA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoHqqefQ4CPCrXf2Go9qm\r\n"
			+ "EE8vVrVabqeDbMuvP+74qrq21QFp3G0yonrvohupuQ/Pvk28lIYcf3ghn2VLvqLL\r\n"
			+ "lPR0bddjwY5AbqefhoGfGTsEpnQzEikfZF36bFkLUU+PljiEtPzj1lRhf+APo7Ns\r\n"
			+ "C0/JCxXOgmVBPYwJ6SJl/92+HP1xVwW5zmRozsAIwsbulOb+o2EclyvzM5uK4Vku\r\n"
			+ "IVkLlsdeR1j6TvbqIWc0IwkFoChd/DVKLHr7X8XoeGUTahwBBpIZF+FZkNJ8/bKM\r\n"
			+ "Q1yi0kDPuSHlhnvJ0L1+qLFG9K8nNXWuslJ6Nae7nN9RFUVlyi2P4zhG1LQQkWdH\r\n" + "TQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

	// @Value("${cors.allowedorigins}")
	public static String CORS_ALLOWED_ORIGINS = "http://localhost:4200,http://chjavadev.chsdomain.com";

	 
}
