package com.ssafy.uniqon.dto.member;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.member.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDto {

    @NotBlank(message = "지갑 주소를 입력해주세요.")
    private String walletAddress;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,16}$",
            message = "비밀번호는 6~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    @NotBlank
    private String passwordConfirm;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Size(min = 3, max = 30, message = "닉네임은 3~30자리수여야 합니다.")
    private String nickName;

    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "회원 유형은 필수 입력값입니다.")
    private MemberType memberType;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .walletAddress(walletAddress)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .memberType(memberType)
                .nickname(nickName)
                .build();
    }

}
