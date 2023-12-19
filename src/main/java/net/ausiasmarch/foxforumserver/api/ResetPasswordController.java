package net.ausiasmarch.foxforumserver.api;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.foxforumserver.entity.UserEntity;
import net.ausiasmarch.foxforumserver.helper.JWTHelper;
import net.ausiasmarch.foxforumserver.repository.UserRepository;
import net.ausiasmarch.foxforumserver.service.ResetPasswordService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/reset")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService oResetPasswordService;


    @PostMapping("/password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestBody) {
        String to = requestBody.get("to");
        return ResponseEntity.ok(oResetPasswordService.sendEmail(to));
    }

    @PutMapping("/new")
    public ResponseEntity<String> newPassword(@RequestParam("token") String token,
            @RequestBody Map<String, String> requestBody) {
        String password = requestBody.get("password");
        String confirmPassword = requestBody.get("confirmPassword");

        return ResponseEntity.ok(oResetPasswordService.updatePassword(token, password, confirmPassword));

    }

}
