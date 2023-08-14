package com.livelyit.allcam.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.validation.Errors;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "QCDL57V58P"; //"QCDL57V58P";  //TODO Key는 하드코딩 하지말고 외부에서 가져오는것을 권장

    public static String createToken(String subject) {

        try {
            // read key
            //String privateKeyB64 = Files.lines(Paths.get("/Users/livelyit/Cert_Key_files/ios_meincam_api_key/SubscriptionKey_QCDL57V58P.p8")).collect(Collectors.joining());
            //System.out.println("ghostkj : "+privateKeyB64);
            byte[] privateKeyDecoded = Base64.getDecoder().decode("MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgMAYPrvwVTu7RdhQQkrhsx7uKgtHGHLlLr0wKkMBW88ugCgYIKoZIzj0DAQehRANCAAQ+nJo/ZS0JdOtzocEFYoolfUGMFLuwgdn0eNgJlvkGDnDnTgJFqfECYlNCjFxx2i16sWJx9uENb+byUSboyzqM");

            //create key spec
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyDecoded);

            // create key form spec
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PrivateKey privateKey = keyFactory.generatePrivate(spec);

            long iat = Instant.now().getEpochSecond();
            int hoursToAdd = 1;
            long hourValue = TimeUnit.HOURS.toSeconds(hoursToAdd);
            long exp = iat + hourValue;

            SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.ES256;

            //byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            //Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
            JwtBuilder builder = Jwts.builder()
                    .setSubject(subject)
                    .setHeaderParam("alg", "ES256")
                    .setHeaderParam("kid", SECRET_KEY)
                    .setHeaderParam("typ", "JWT")
                    .claim("iss", "9d7b4129-490b-463d-a6c7-32f706087ee0")
                    .claim("iat", iat)
                    .claim("exp", exp)
                    .claim("aud", "appstoreconnect-v1")
                    .claim("nonce",iat+exp)
                    .claim("bid","com.meangsun.allcam")
                    .signWith(signatureAlgorithm, privateKey);

            return builder.compact();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;

    }



    public static String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public static Claims getTokenData(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims;
    }

    public static Map<String, Object> getSubjectMap(String token) {

        String subject = getSubject(token);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", subject);
        return map;
    }
}
