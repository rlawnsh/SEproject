package SEproject.hello.controller.api;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.config.jwt.TokenDto;
import SEproject.hello.controller.dto.request.*;
import SEproject.hello.controller.dto.TokenForm;
import SEproject.hello.controller.dto.response.FindIdRes;
import SEproject.hello.controller.dto.response.LoginRes;
import SEproject.hello.controller.dto.response.TempToken;
import SEproject.hello.db.entity.Member;
import SEproject.hello.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponse> signUpUser(@Valid @RequestBody SignUpUserReq singUpUserReq) {
        if (memberService.isExistEmail(singUpUserReq.getEmail()))
            // 200, 201, 400, 401, 403, 409, 500
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 이메일입니다.", 409));
        if (memberService.isExistUserId(singUpUserReq.getMemberId()))
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 아이디입니다.", 409));

        String encode = passwordEncoder.encode(singUpUserReq.getPassword());
        memberService.save(singUpUserReq.toUserEntity(encode));

        return ResponseEntity.status(201).body(new BaseResponse("회원가입에 성공하였습니다.", 201));
    }

    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponse> login(@Valid @RequestBody LoginReq loginReq) {
        Member byId = memberService.findByUserId(loginReq.getMemberId());

        if (byId == null) {
            return ResponseEntity.status(400).body(new BaseResponse("존재하지 않는 아이디입니다.", 400));
        }

        if (!passwordEncoder.matches(loginReq.getPassword(), byId.getPassword())) {
            return ResponseEntity.status(400).body(new BaseResponse("비밀번호가 일치하지 않습니다.", 400));
        }

        TokenDto tokenDto = memberService.createToken(loginReq);
        return ResponseEntity.status(201).body(new LoginRes("로그인을 성공적으로 했습니다.", 201, tokenDto.getAccessToken(), tokenDto.getRefreshToken()));
    }

    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestBody TokenForm tokenForm) {

        TokenDto newToken = memberService.reissue(tokenForm);
        return ResponseEntity.status(201).body(new TokenForm(newToken.getAccessToken(), newToken.getRefreshToken()));
    }

    @PostMapping("/certificate/find-userId")
    public ResponseEntity<? extends BaseResponse> certificationFindUserId(@Valid @RequestBody CertificationEmail certificationEmail) {
        Member member = memberService.certificationEmail(certificationEmail.getEmail());
        LoginReq loginReq = new LoginReq("test", "1234");
        TokenDto token = memberService.createToken(loginReq);
        return ResponseEntity.status(200).body(new TempToken("메일 인증에 성공했습니다.", 200, member.getId(), token.getAccessToken()));
    }

    @GetMapping("/findMemberId/{id}")
    public ResponseEntity<? extends BaseResponse> findUserId(@PathVariable Long id) {
        Member member = memberService.idFindByToken(id);
        String memberId = member.getMemberId();
        String subStringId = memberId.substring(0, memberId.length() - (memberId.length()/2)) + "*".repeat(memberId.length()/2);
        return ResponseEntity.status(200).body(new FindIdRes("아이디를 찾았습니다", 200, subStringId));
    }

    @PostMapping("/certificate/change-pwd")
    public ResponseEntity<? extends BaseResponse> certificationChangePwd(@Valid @RequestBody CertificationId certificationId) {
        Member member = memberService.certificationUserId(certificationId.getMemberId());
        LoginReq loginReq = new LoginReq("test", "1234");
        TokenDto token = memberService.createToken(loginReq);
        return ResponseEntity.status(200).body(new TempToken("아이디 인증에 성공했습니다.", 200, member.getId(), token.getAccessToken()));
    }

    @PostMapping("/change/password/{memberId}")
    public ResponseEntity<? extends BaseResponse> changePwd(@RequestBody ChangePwdReq changePwdReq, @PathVariable Long memberId) {
        memberService.changePassword(changePwdReq.getPassword(), memberId);
        return ResponseEntity.status(201).body(new BaseResponse("비밀번호 변경을 완료했습니다.", 201));
    }
}
