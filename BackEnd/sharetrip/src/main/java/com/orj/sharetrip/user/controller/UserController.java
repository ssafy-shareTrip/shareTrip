package com.orj.sharetrip.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orj.sharetrip.user.model.UserDto;
import com.orj.sharetrip.user.model.service.UserService;
import com.orj.sharetrip.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	private UserService UserService;
	private JWTUtil jwtUtil;

	public UserController(UserService UserService, JWTUtil jwtUtil) {
		super();
		this.UserService = UserService;
		this.jwtUtil = jwtUtil;
	}

	@ApiOperation(value = "회원가입", notes = "사용자가 입력한 정보를 이용하여 로그인 처리.회원가입 시 필요한 회원정보(아이디, 비밀번호, 이름, 이메일id, 이메일domain).")
	@PostMapping("/join")
	public ResponseEntity<Map<String, Object>> join(
			@RequestBody @ApiParam(value = "회원가입 시 필요한 회원정보(아이디, 비밀번호, 이름, 이메일id, 이메일domain).", required = true) UserDto UserDto) {
		log.debug("User info : {}", UserDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserService.joinUser(UserDto);
			resultMap.put("message", "회원가입 성공");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.debug("회원가입 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto UserDto) {
		log.debug("login user : {}", UserDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDto loginUser = UserService.login(UserDto);
			if (loginUser != null) {
//				String accessToken = jwtUtil.createAccessToken(loginUser.getId());
//				String refreshToken = jwtUtil.createRefreshToken(loginUser.getId());
//				log.debug("access token : {}", accessToken);
//				log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token을 DB에 저장.
//				UserService.saveRefreshToken(loginUser.getId(), refreshToken);

//				JSON으로 token 전달.
//				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
				resultMap.put("userId", loginUser.getId());
				resultMap.put("userName", loginUser.getName());
				resultMap.put("message", "로그인 성공.");
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다.", response = Map.class)
	@PutMapping("/modify/{userId}")
	public ResponseEntity<Map<String, Object>> modifyUser(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			@RequestBody @ApiParam(value = "회원 정보 수정 (비밀번호, 이름, 이메일id, 이메일domain, profile_image, age, birth, gender).", required = true) UserDto UserDto,
			HttpServletRequest request) {
		log.debug("UserDto : {} ", UserDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED; // 토큰 사용 부분
//		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
//			log.info("사용 가능한 토큰!!!");
		try {
//				로그인 사용자 정보.
			UserService.modifyUser(UserDto);
			resultMap.put("message", "회원 정보 수정 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("정보 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
//		} else {
//			log.error("사용 불가능 토큰!!!");
//			status = HttpStatus.UNAUTHORIZED;
//		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "회원을 틸퇴한다.", response = Map.class)
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Map<String, Object>> deleteUser(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			@RequestBody @ApiParam(value = "회원 정보 수정 (비밀번호, 이름, 이메일id, 이메일domain, profile_image, age, birth, gender).", required = true) UserDto UserDto,
			HttpServletRequest request) {
		log.debug("UserDto : {} ", UserDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED; // 토큰 사용 부분
//		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
//			log.info("사용 가능한 토큰!!!");
		try {
//				로그인 사용자 정보.
			UserService.deleteUser(UserDto);
			resultMap.put("message", "회원 탈퇴 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("탈퇴 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
//		} else {
//			log.error("사용 불가능 토큰!!!");
//			status = HttpStatus.UNAUTHORIZED;
//		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	@ApiOperation(value = "회원 조회", notes = "회원 정보를 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
		log.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED; // 토큰 사용 부분
//		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
//			log.info("사용 가능한 토큰!!!");
		try {
//				로그인 사용자 정보.
			UserDto UserDto = UserService.userInfo(userId);
			resultMap.put("data", UserDto);
			resultMap.put("message", "회원 정보 조회 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
//		} else {
//			log.error("사용 불가능 토큰!!!");
//			status = HttpStatus.UNAUTHORIZED;
//		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(
			@PathVariable("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
//			UserService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto UserDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, UserDto : {}", token, UserDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(UserService.getRefreshToken(UserDto.getId()))) {
				String accessToken = jwtUtil.createAccessToken(UserDto.getId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "유저 팔로우", notes = "팔로우한 유저를 저장한다", response = Map.class)
	@PostMapping("/follow/{idTo}")
	public ResponseEntity<Map<String, Object>> followUser(
			@PathVariable("idTo") @ApiParam(value = "팔로우할 회원 아이디.", required = true) String idTo,
			@RequestParam("userId") @ApiParam(value = "현재 로그인한 아이디", required = true) String userId) throws Exception {
		log.info("유저 팔로우");
		log.debug(" info : {}, {}", idTo, userId);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDto userDto = UserService.followUser(idTo,userId);
			resultMap.put("data", userDto);
			resultMap.put("message", "팔로우 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "유저 언팔로우", notes = "팔로우한 유저를 삭제한다", response = Map.class)
	@DeleteMapping("/follow/{idTo}")
	public ResponseEntity<Map<String, Object>> unFollowUser(
			@PathVariable("idTo") @ApiParam(value = "언팔로우할 회원 아이디.", required = true) String idTo,
			@RequestParam("userId") @ApiParam(value = "현재 로그인한 아이디", required = true) String userId)
			throws Exception {
		log.info("유저 팔로우");
		log.debug(" info : {}, {}", idTo, userId);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserService.unFollowUser(idTo, userId);
			resultMap.put("message", "언팔로우 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "맞팔 조회", notes = "서로 팔로우한 유저를 조회한다", response = Map.class)
	@GetMapping("/both/{userId}")
	public ResponseEntity<Map<String, Object>> bothFollow(
			@PathVariable("userId") @ApiParam(value = "현재 로그인한 아이디", required = true) String userId)
			throws Exception {
		log.info("유저 팔로우");
		log.debug(" info : {}", userId);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<UserDto> userDto = UserService.bothFollow(userId);
			resultMap.put("data", userDto);
			resultMap.put("message", "언팔로우 성공");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.debug("## 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		resultMap.put("status", status);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
