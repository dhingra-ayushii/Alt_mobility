package server.user.service;

import com.google.gson.Gson;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import server.user.model.FleetUser;
import server.user.model.Role;
import server.user.repository.UserRepository;
import server.user.response.SignInResponse;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.Principal;
import java.util.*;

@Service
public class UserService {
    @Value("${jws.secret}")
    public String secret;

    @Autowired
    private UserRepository userRepository;

    private Long ttlMillis = 3600000l;

    public static String createJWT(FleetUser user, String secret, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Map<String, Object> claims = new HashMap<>();
        claims.put("authority", user.getRole());
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setSubject(user.getEmail())
                .setId(user.getId())
                .setIssuer("carbonShunya")
                .signWith(signatureAlgorithm, secret.getBytes());

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public FleetUser processOAuthPostLogin(String email) {
        Optional<FleetUser> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException(" admin email not found");

    }

    public FleetUser createUser(String email, String name, Role role) {
        FleetUser user = new FleetUser(UUID.randomUUID().toString(), name, email, role);
        userRepository.save(user);
        return user;
    }

    @SneakyThrows
    public SignInResponse processGoogleLogin(String authToken) {
        Map<String, Object> decodedJWT = verifyToken(authToken);
        String email = decodedJWT.get("email").toString();
        FleetUser user = processOAuthPostLogin(email);
        return new SignInResponse(generateAuthToken(user));
    }

    private Map<String, Object> verifyToken(String authToken) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] parts = authToken.split("\\."); // split out the "parts" (header, payload and signature)

        String headerJson = new String(decoder.decode(parts[0]));
        String payloadJson = new String(decoder.decode(parts[1]));
        //String signatureJson = new String(decoder.decode(parts[2]));
        return new Gson().fromJson(payloadJson, Map.class);
    }

    private String generateAuthToken(FleetUser user) {
        return createJWT(user, secret, ttlMillis);
    }

    public FleetUser getUserFromPrincipal(Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        FleetUser user = (FleetUser) usernamePasswordAuthenticationToken.getDetails();
        return user;
    }


    public FleetUser getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<FleetUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
