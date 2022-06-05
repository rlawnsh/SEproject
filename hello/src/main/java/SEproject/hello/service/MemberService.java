package SEproject.hello.service;

import SEproject.hello.config.jwt.TokenDto;
import SEproject.hello.config.jwt.TokenProvider;
import SEproject.hello.config.security.SecurityUtil;
import SEproject.hello.controller.dto.request.LoginReq;
import SEproject.hello.controller.dto.TokenForm;
import SEproject.hello.db.entity.MbtiLevel;
import SEproject.hello.db.entity.Member;
import SEproject.hello.db.entity.RefreshToken;
import SEproject.hello.db.repository.MbtiLevelRepository;
import SEproject.hello.db.repository.MemberRepository;
import SEproject.hello.db.repository.RefreshTokenRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MbtiLevelRepository mbtiLevelRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public Member findByUserId(String memberId) {
        return memberRepository.findByMemberId(memberId).orElse(null);
    }

    @Transactional
    public void changePassword(String password, Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        member.updatePassword(passwordEncoder.encode(password));
    }

    public boolean isExistEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElse(null);
        return member != null;
    }

    public boolean isExistUserId(String userId) {
        Member member = memberRepository.findByMemberId(userId).orElse(null);
        return member != null;
    }

    public Member certificationUserId(String memberId) {
        return memberRepository.findByMemberId(memberId).orElse(null);
    }

    public Member certificationEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    public Member idFindByToken(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public TokenDto createToken(LoginReq loginReq) {
        // 1. Longin Id/pw 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginReq.getMemberId(), loginReq.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailService 에서 만들었던 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발금
        return tokenDto;
    }

    public TokenDto reissue(TokenForm tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new JwtException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 User Id 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 User Id 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshKey(authentication.getName())
                .orElseThrow(() -> new JwtException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getRefreshValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new JwtException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발금
        return tokenDto;
    }

    public void save(Member toUserEntity) {
        memberRepository.save(toUserEntity);
    }

    @Transactional
    public void saveLevel(String level) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentUserId()).orElse(null);
        MbtiLevel byLevel = mbtiLevelRepository.findByLevel(level);
        member.setMbtiLevel(byLevel);
    }

    public Member findById(Long currentUserId) {
        return memberRepository.findById(SecurityUtil.getCurrentUserId()).orElse(null);
    }
}
